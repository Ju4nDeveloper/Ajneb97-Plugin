package org.plugin.listener;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.plugin.PluginAjneb97;

public class KillEntityListener implements Listener {
    private final PluginAjneb97 plugin;

    public KillEntityListener(PluginAjneb97 plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void killZombies(EntityDeathEvent event){
        Player killer = event.getEntity().getKiller();
        EntityType entity = event.getEntityType();
        FileConfiguration config = plugin.getConfig();

        if (killer != null && killer.getType().equals(EntityType.PLAYER)){

            String path = "Players." + killer.getUniqueId();
            config.set(path + ".name", killer.getName());

            if (entity.equals(EntityType.ZOMBIE)) {
                incrementKills(config, path + ".zombieKills");
            } else if (entity.equals(EntityType.SKELETON)) {
                incrementKills(config, path + ".esqueletoKills");
            }
            else if (entity.equals(EntityType.SPIDER)) {
                incrementKills(config, path + ".arañaKills");
            }
            else if (entity.equals(EntityType.COW)) {
                incrementKills(config, path + ".vacaKills");
            }
            else if (entity.equals(EntityType.PIG)) {
                incrementKills(config, path + ".pigKills");
            }
            else if (entity.equals(EntityType.CHICKEN)) {
                incrementKills(config, path + ".polloKills");
            }
            else if (entity.equals(EntityType.PLAYER)) {
                incrementKills(config, path + ".playerKills");
            }


        }
    }

    private void incrementKills(FileConfiguration config, String path) {
        if (config.contains(path)){
            int cantidad = Integer.valueOf(config.getString(path));
            config.set(path, cantidad + 1);
            plugin.saveConfig();

        }else {
            config.set(path, 1);
            plugin.saveConfig();
        }
    }
}
