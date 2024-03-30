package me.hub.listener;

import me.hub.PluginAjneb97;
import me.hub.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitScheduler;

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
        int numero = r.nextInt(3);
        FileConfiguration config = plugin.getConfig();
        FileConfiguration messages = plugin.getMessages();

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (numero == 0) {
                if (config.getBoolean("Config.TIPS.MESSAGE-1")) {
                    List<String> mensaje = messages.getStringList("Messages.TIPS.ANNOUNCEMENTS.MESSAGE-1");

                    for (String texto : mensaje) {
                        player.sendMessage(MessageUtils.getColoredMessage( texto.replaceAll("%player%", player.getName())));
                    }
                }
            } else if (numero == 1) {
                if (config.getBoolean("Config.TIPS.MESSAGE-2")) {
                    List<String> mensaje = messages.getStringList("Messages.TIPS.ANNOUNCEMENTS.MESSAGE-2");

                    for (int i = 0; i < mensaje.size(); i++) {
                        String texto = mensaje.get(i);
                        player.sendMessage(MessageUtils.getColoredMessage( texto.replaceAll("%player%", player.getName())));
                    }
                }
            } else if (numero == 2) {
                if (config.getBoolean("Config.TIPS.MESSAGE-3")) {
                    List<String> mensaje = messages.getStringList("Messages.TIPS.ANNOUNCEMENTS.MESSAGE-3");

                    for (int i = 0; i < mensaje.size(); i++) {
                        String texto = mensaje.get(i);
                        player.sendMessage(MessageUtils.getColoredMessage( texto.replaceAll("%player%", player.getName())));
                    }
                }
            }



        }



    }
}


