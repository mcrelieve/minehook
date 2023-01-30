package me.kadotcom.Minehook.Events;

import me.kadotcom.Minehook.Minehook;
import me.kadotcom.Minehook.Util.DiscordWebhook;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerCommandEvent;

import java.awt.*;
import java.io.IOException;
import java.util.logging.Logger;

public class ServerEvent implements Listener {
    public Minehook p;
    private Logger log;

    public ServerEvent(Minehook pl){
        p = pl;
        log = p.getLogger();


    }
    @EventHandler
    public void onCommandRanServer(ServerCommandEvent ev){
        if(p.getConfig().getBoolean("log.server.command")){
            DiscordWebhook web = new DiscordWebhook(p.getConfig().getString("discordWebhook"));

            DiscordWebhook.EmbedObject em = new DiscordWebhook.EmbedObject();

            em.setColor(Color.WHITE);

            em.setDescription(ev.getSender().getName() + " ran the command " + ev.getCommand());
            em.setFooter(p.getConfig().getString("serverName"),"");

            web.addEmbed(em);

            try{
                web.execute();
            }catch(IOException e){
                log.info(e.getStackTrace().toString());
            }
        }

    }
}
