package org.plugin.listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.plugin.PluginAjneb97;
import java.util.ArrayList;
import java.util.List;


public class InventoryListener implements Listener {
    private final PluginAjneb97 plugin;
    private final String INVENTORY_TITLE =  ChatColor.translateAlternateColorCodes('&', "&2Inventario de PluginAjneb97");
    public InventoryListener(PluginAjneb97 plugin) {
        this.plugin = plugin;
    }

    @SuppressWarnings("deprecation")
    public void inventoryCreate(Player player){
        Inventory inv = Bukkit.createInventory(null,45, INVENTORY_TITLE);
        ItemStack item = new ItemStack(Material.REDSTONE_BLOCK,1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',"&4&lTeletrasportador"));
        List<String> lore = new ArrayList<String>();
        lore.add((ChatColor.translateAlternateColorCodes('&',"&7Te teletransporta a un lugar misterioso")));
        meta.setLore(lore);
        item.setItemMeta(meta);
        inv.setItem(20, item);

        item = new ItemStack(Material.DIAMOND,1);
        meta = item.getItemMeta();
        meta.setDisplayName((ChatColor.translateAlternateColorCodes('&',"&3&lRegalo diamantes")));
        lore = new ArrayList<String>();
        lore.add((ChatColor.translateAlternateColorCodes('&',"&7Te regala un diamante si tienes permiso")));
        meta.setLore(lore);
        item.setItemMeta(meta);

        inv.setItem(22, item);

        item = new ItemStack(Material.DIAMOND_SWORD,1);
        meta = item.getItemMeta();
        meta.setDisplayName((ChatColor.translateAlternateColorCodes('&',"&b&lNo Contiene Nada")));
        item.setItemMeta(meta);
        inv.setItem(24, item);

        ItemStack decoration = new ItemStack(Material.GLASS,1);
        for (int i=0; i<9; i++){
            inv.setItem(i, decoration);
        }
        for (int i=35; i<45; i++){
            inv.setItem(i, decoration);
        }

        item = new ItemStack(Material.GLASS,1);
        inv.setItem(9, item);
        inv.setItem(18, item);
        inv.setItem(27, item);
        inv.setItem(36, item);
        inv.setItem(17, item);
        inv.setItem(26, item);
        inv.setItem(35, item);
        player.openInventory(inv);
    }

    @EventHandler
    public void clickInventory(InventoryClickEvent event){
        String nombre = INVENTORY_TITLE;
        String nombreM = ChatColor.stripColor(nombre);
        if (ChatColor.stripColor(event.getView().getTitle()).equals(nombreM)){
            event.setCancelled(true);
            if (event.getCurrentItem() == null || event.getSlotType() == null || event.getCurrentItem().getType() == Material.AIR) {
                return;
            }


            if (event.getCurrentItem().hasItemMeta()) {
                Player player = (Player) event.getWhoClicked();

                if (event.getSlot() == 20){
                    Location l = new Location(player.getWorld(), 200,60,-200,0,0);
                    player.closeInventory();
                    player.teleport(l);

                } else if (event.getSlot() == 22) {
                    if (player.hasPermission("miplugin.inventario.diamantes")){
                        ItemStack item = new ItemStack(Material.DIAMOND);
                        player.getInventory().addItem(item);
                    }else {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cNo tienes permisos"));
                    }
                } else if (event.getSlot() == 24) {
                    Inventory inv = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', "&fAbsolutamente Nada"));
                    player.openInventory(inv);
                }
            }
        }
    }
}