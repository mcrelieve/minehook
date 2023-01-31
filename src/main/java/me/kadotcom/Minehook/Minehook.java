package me.kadotcom.Minehook;

import me.kadotcom.Minehook.Events.PlayerEvent;
import me.kadotcom.Minehook.Events.ServerEvent;
import me.kadotcom.Minehook.Util.DiscordWebhook;
import me.kadotcom.Minehook.Util.HTTP;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.logging.Logger;

public final class Minehook extends JavaPlugin {

    private Logger log;

    @Override
    public void onEnable() {
        log = getLogger();
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new PlayerEvent(this), this);
        getServer().getPluginManager().registerEvents(new ServerEvent(this), this);

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        log.info("\nMinehook " + getDescription().getVersion() + "\n         # #                   # #        \n       # #     # # # # # # #     # #      \n     # # # # # # # # # # # # # # # # #    \n     # # # # # # # # # # # # # # # # #    \n     # # # # # # # # # # # # # # # # #    \n   # # # # # # # # # # # # # # # # # # #  \n   # # # # # # # # # # # # # # # # # # #  \n   # # # # #     # # # # #     # # # # #  \n   # # # #         # # #         # # # #  \n # # # # #         # # #         # # # # #\n # # # # # #     # # # # #     # # # # # #\n # # # # # # # # # # # # # # # # # # # # #\n # # # # # # # # # # # # # # # # # # # # #\n # # # # #     # # # # # # #     # # # # #\n     # # # #                   # # # #    \n       # # # #               # # # #\n");
        if(getConfig().getString("discordWebhook").isEmpty() || getConfig().getString("discordWebhook").length() == 0){
            log.info("You are missing the Webhook URL. To learn more, read this: https://github.com/kadotcom/minehook/wiki/How-to-use-Minehook/");
        }
        if(!HTTP.get("https://api.spigotmc.org/legacy/update.php?resource=107722").equalsIgnoreCase(this.getDescription().getVersion())){
            log.info("This version of Minehook is outdated. Please install the newest version: https://www.spigotmc.org/resources/minehook.107722/");
        }
        if(getConfig().getBoolean("log.server.serverStart")) {
            DiscordWebhook web = new DiscordWebhook(getConfig().getString("discordWebhook"));

            DiscordWebhook.EmbedObject em = new DiscordWebhook.EmbedObject();

            em.setFooter(getConfig().getString("serverName"),"");
            em.setDescription("Server connected.");
            web.addEmbed(em);


            try {
                web.execute();

            } catch (IOException e) {

                log.info(e.getStackTrace().toString());

            }
        }

    }

    @Override
    public void onDisable() {
        if(getConfig().getBoolean("log.server.serverShutdown")) {
            DiscordWebhook web = new DiscordWebhook(getConfig().getString("discordWebhook"));

            DiscordWebhook.EmbedObject em = new DiscordWebhook.EmbedObject();

            em.setFooter(getConfig().getString("serverName"),"");
            em.setDescription("Server shutting down.");
            web.addEmbed(em);

            try {
                web.execute();
            } catch (IOException e) {
                log.info(e.getStackTrace().toString());
            }
        }
    }
}
