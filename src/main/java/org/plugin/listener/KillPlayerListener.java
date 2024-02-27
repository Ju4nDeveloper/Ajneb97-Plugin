package org.plugin.listener;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.plugin.PluginAjneb97;

import java.util.ArrayList;
import java.util.List;

public class KillPlayerListener implements Listener {
    private PluginAjneb97 plugin;
    public KillPlayerListener(PluginAjneb97 plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void kill(EntityDeathEvent event){
        Player killer = event.getEntity().getKiller();
        EntityType entity = event.getEntityType();
        if (killer != null && killer.getType().equals(EntityType.PLAYER) && entity.equals(EntityType.PLAYER)){
            ItemStack stack = new ItemStack(Material.EMERALD,1);
            ItemMeta meta = stack.getItemMeta();
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&2&lGema &4&l[Unica]"));
            List<String> lore = new ArrayList<String>();
            lore.add(ChatColor.translateAlternateColorCodes('&', "&4&m                                            "));
            lore.add(ChatColor.translateAlternateColorCodes('&', ""));
            lore.add(ChatColor.translateAlternateColorCodes('&', "&7Se te ha otorgado esta gema por tu gran fuerza"));
            lore.add(ChatColor.translateAlternateColorCodes('&', "&7Puedes utilizarla para comprar items"));
            lore.add(ChatColor.translateAlternateColorCodes('&', "&c&lPrecio: &2$5000"));
            lore.add(ChatColor.translateAlternateColorCodes('&', ""));
            lore.add(ChatColor.translateAlternateColorCodes('&', "&4&m                                            "));
            meta.setLore(lore);
            meta.addEnchant(Enchantment.DURABILITY,10,true);
            stack.setItemMeta(meta);

            if (killer.getInventory().firstEmpty() == -1){
                ChatColor.translateAlternateColorCodes('&', "Tu inventario está lleno, no has podido recibir la gema.");
            }else {
                killer.getInventory().addItem(stack);
            }

            FileConfiguration config = plugin.getConfig();
            String path = "Players." + killer.getUniqueId();
            config.set(path + ".name", killer.getName());

            if (entity.equals(EntityType.PLAYER)) {
                incrementKills(config, path + ".playerkills");
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
