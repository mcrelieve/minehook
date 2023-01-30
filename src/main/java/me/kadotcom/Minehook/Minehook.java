package me.kadotcom.Minehook;

import me.kadotcom.Minehook.Events.PlayerEvent;
import me.kadotcom.Minehook.Events.ServerEvent;
import me.kadotcom.Minehook.Util.DiscordWebhook;
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
