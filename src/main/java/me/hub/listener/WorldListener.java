package me.hub.listener;

import me.hub.PluginAjneb97;
import me.hub.utils.MessageUtils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.List;

public class WorldListener implements Listener {
    private PluginAjneb97 plugin;
    public WorldListener(PluginAjneb97 plugin){
        this.plugin = plugin;
    }
    @EventHandler
    public void alRomber(BlockBreakEvent event){
        Player player = event.getPlayer();
        FileConfiguration config = plugin.getConfig();
        List<String> mundos = config.getStringList("BlockEvents.destroy_blocks");
        String mundo = player.getWorld().getName();
        if (!player.isOp()){
            for (int i=0;i< mundos.size();i++){
                if (mundos.get(i).equals(mundo)){
                    event.setCancelled(true);
                    player.sendMessage(MessageUtils.getColoredMessage("&cNo puedes romper bloques en este mundo!"));
                    return;
                }
            }
        }

    }
    @EventHandler
    public void alConstruir(BlockPlaceEvent event){
        Player player = event.getPlayer();
        FileConfiguration config = plugin.getConfig();
        List<String> mundos = config.getStringList("BlockEvents.build_blocks");
        String mundo = player.getWorld().getName();
        if (!player.isOp()){
            for (int i=0;i< mundos.size();i++){
                if (mundos.get(i).equals(mundo)){
                    event.setCancelled(true);
                    player.sendMessage(MessageUtils.getColoredMessage("&cNo puedes colocar bloques en este mundo!"));
                    return;
                }
            }
        }

    }
    @EventHandler
    public void alPvP(EntityDamageByEntityEvent event){
        Entity entidad = event.getDamager();
        Entity entidadAtacada = event.getEntity();
        if (entidad.getType().equals(EntityType.PLAYER) && entidadAtacada.getType().equals(EntityType.PLAYER)){
            Player player = (Player) entidad;
            FileConfiguration config = plugin.getConfig();
            List<String> mundos = config.getStringList("BlockEvents.pvp");
            String mundo = player.getWorld().getName();
            if (!player.isOp()){
                for (int i=0;i< mundos.size();i++){
                    if (mundos.get(i).equals(mundo)){
                        event.setCancelled(true);
                        player.sendMessage(MessageUtils.getColoredMessage("&cNo puedes hacer pvp en este mundo!"));
                        return;
                    }
                }
            }

        }
    }
    @EventHandler
    public void alChatear(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();
        FileConfiguration config = plugin.getConfig();
        List<String> mundos = config.getStringList("BlockEvents.chat");
        String mundo = player.getWorld().getName();
            if (!player.isOp()){
                for (int i=0;i< mundos.size();i++){
                    if (mundos.get(i).equals(mundo)){
                        event.setCancelled(true);
                        player.sendMessage(MessageUtils.getColoredMessage("&cNo puedes chatear en este mundo!"));
                        return;
                }
            }

        }
    }
}