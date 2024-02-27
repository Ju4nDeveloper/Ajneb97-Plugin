package org.plugin.listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitScheduler;
import org.plugin.PluginAjneb97;

import java.util.List;
import java.util.Random;

public class RepeatingListener implements Listener {

    int taskID;
    long ticks;
    private PluginAjneb97 plugin;
    public RepeatingListener(PluginAjneb97 plugin,long ticks){
        this.plugin = plugin;
        this.ticks = ticks;
    }
    public void ejecucion(){
        BukkitScheduler sh = Bukkit.getServer().getScheduler();
        taskID = sh.scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                realizarAccion();
            }
        },0L,ticks);
    }



    public void realizarAccion() {
        Random r = new Random();
        int numero = r.nextInt(4);
        FileConfiguration config = plugin.getConfig();
        FileConfiguration messages = plugin.getMessages();

        for (Player player : Bukkit.getOnlinePlayers()) {

            if (numero == 0) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "\"&eGRACIAS POR JUGAR EN NUESTRO SERVIDOR! + \n" +
                        "          &6&lRecuerda visitar nuestra web para una mejor experiencia\""));
            } else if (numero == 1) {
                if (config.getBoolean("Config.TIPS.MESSAGE-1")) {
                    List<String> mensaje = messages.getStringList("Messages.TIPS.ANNOUNCEMENTS.MESSAGE-1");

                    for (String texto : mensaje) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', texto.replaceAll("%player%", player.getName())));
                    }
                }
            } else if (numero == 2) {
                if (config.getBoolean("Config.TIPS.MESSAGE-2")) {
                    List<String> mensaje = messages.getStringList("Messages.TIPS.ANNOUNCEMENTS.MESSAGE-2");

                    for (int i = 0; i < mensaje.size(); i++) {
                        String texto = mensaje.get(i);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', texto.replaceAll("%player%", player.getName())));
                    }
                }
            } else if (numero == 3) {
                if (config.getBoolean("Config.TIPS.MESSAGE-3")) {
                    List<String> mensaje = messages.getStringList("Messages.TIPS.ANNOUNCEMENTS.MESSAGE-3");

                    for (int i = 0; i < mensaje.size(); i++) {
                        String texto = mensaje.get(i);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', texto.replaceAll("%player%", player.getName())));
                    }
                }
            }



        }



    }
}

