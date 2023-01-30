package me.kadotcom.Minehook.Events;

import me.kadotcom.Minehook.Minehook;
import me.kadotcom.Minehook.Util.DiscordWebhook;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;

import java.awt.*;
import java.io.IOException;
import java.util.logging.Logger;

public class PlayerEvent implements Listener {
    public Minehook p;
    private Logger log;

    public PlayerEvent(Minehook pl){
        p = pl;
        log = p.getLogger();



    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent ev){
        if(p.getConfig().getBoolean("log.player.playerJoin")){
            DiscordWebhook web = new DiscordWebhook(p.getConfig().getString("discordWebhook"));
            DiscordWebhook.EmbedObject em = new DiscordWebhook.EmbedObject();
            em.setColor(Color.GREEN);
            em.setAuthor(ev.getPlayer().getName(), "", "https://cravatar.eu/helmavatar/" + ev.getPlayer().getName() + "/128.png");
            em.setDescription("Joined.");
            em.setFooter(p.getConfig().getString("serverName"),"");

            web.addEmbed(em);

            try{
                web.execute();
            }catch(IOException e){
                log.info(e.getStackTrace().toString());
            }
        }
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent ev){
        if(p.getConfig().getBoolean("log.player.playerLeave")){
            DiscordWebhook web = new DiscordWebhook(p.getConfig().getString("discordWebhook"));
            DiscordWebhook.EmbedObject em = new DiscordWebhook.EmbedObject();

            em.setColor(Color.RED);
            em.setAuthor(ev.getPlayer().getName(), "", "https://cravatar.eu/helmavatar/" + ev.getPlayer().getName() + "/128.png");
            em.setDescription("Left.");
            em.setFooter(p.getConfig().getString("serverName"),"");

            web.addEmbed(em);

            try{
                web.execute();
            }catch(IOException e){
                log.info(e.getStackTrace().toString());
            }
        }

    }

    @EventHandler
    public void onPlayerKick(PlayerKickEvent ev){
        if(p.getConfig().getBoolean("log.player.playerKick")){
            DiscordWebhook web = new DiscordWebhook(p.getConfig().getString("discordWebhook"));
            DiscordWebhook.EmbedObject em = new DiscordWebhook.EmbedObject();

            em.setColor(new Color(139,0,0));
            em.setAuthor(ev.getPlayer().getName(), "", "https://cravatar.eu/helmavatar/" + ev.getPlayer().getName() + "/128.png");
            em.setDescription("Kicked, reason: " + ev.getReason() + ".");
            em.setFooter(p.getConfig().getString("serverName"),"");

            web.addEmbed(em);

            try{
                web.execute();
            }catch(IOException e){
                log.info(e.getStackTrace().toString());
            }
        }

    }

    @EventHandler
    public void onPlayerChat(PlayerChatEvent ev){
        if(p.getConfig().getBoolean("log.player.playerChat")){
            DiscordWebhook web = new DiscordWebhook(p.getConfig().getString("discordWebhook"));
            DiscordWebhook.EmbedObject em = new DiscordWebhook.EmbedObject();

            em.setColor(Color.ORANGE);
            em.setAuthor(ev.getPlayer().getName(), "", "https://cravatar.eu/helmavatar/" + ev.getPlayer().getName() + "/128.png");
            em.setDescription(ev.getMessage());
            em.setFooter(p.getConfig().getString("serverName"),"");

            web.addEmbed(em);

            try{
                web.execute();
            }catch(IOException e){
                log.info(e.getStackTrace().toString());
            }
        }

    }



    @EventHandler
    public void onCommandRanPlayer(PlayerCommandPreprocessEvent ev){
        if(p.getConfig().getBoolean("log.player.command")){
            DiscordWebhook web = new DiscordWebhook(p.getConfig().getString("discordWebhook"));

            DiscordWebhook.EmbedObject em = new DiscordWebhook.EmbedObject();
            em.setColor(Color.WHITE);
            em.setAuthor(ev.getPlayer().getName(), "", "https://cravatar.eu/helmavatar/" + ev.getPlayer().getName() + "/128.png");
            em.setDescription("ran the command " + ev.getMessage());
            em.setFooter(p.getConfig().getString("serverName"),"");

            web.addEmbed(em);

            try{
                web.execute();
            }catch(IOException e){
                log.info(e.getStackTrace().toString());
            }
        }

    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent ev){
        if(p.getConfig().getBoolean("log.player.playerDeath")){
            DiscordWebhook web = new DiscordWebhook(p.getConfig().getString("discordWebhook"));

            DiscordWebhook.EmbedObject em = new DiscordWebhook.EmbedObject();
            em.setColor(Color.LIGHT_GRAY);
            em.setAuthor(ev.getEntity().getName(), "", "https://cravatar.eu/helmavatar/" + ev.getEntity().getName() + "/128.png");
            em.setDescription(ev.getDeathMessage().replace(ev.getEntity().getName(),""));
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
