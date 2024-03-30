package me.hub.countdown;

import me.hub.PluginAjneb97;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

public class Countdown {
    private final PluginAjneb97 plugin;
    int taskID;
    int tiempo;
    private Player player;
    private Location location;

    public Countdown(PluginAjneb97 plugin, int tiempo, Player player, Location location){
        this.plugin = plugin;
        this.tiempo = tiempo;
        this.player = player;
        this.location = location;
    }
    public void ejecucion(){
        BukkitScheduler sh = Bukkit.getServer().getScheduler();
        taskID = sh.scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                if (tiempo == 0) {
                    Bukkit.getScheduler().cancelTask(taskID);
                    player.teleport(location);
                    plugin.removePlayer(player);
                }else {
                    player.sendMessage(String.valueOf(tiempo));
                    tiempo--;
                }
            }
        },0L,20);
    }

}
