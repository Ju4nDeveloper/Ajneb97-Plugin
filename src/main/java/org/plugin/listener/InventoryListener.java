package org.plugin.listener;

import com.cryptomorin.xseries.XMaterial;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitScheduler;
import org.plugin.PluginAjneb97;
import org.plugin.utils.MessageUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class InventoryListener implements Listener {
    private final PluginAjneb97 plugin;
    int taskID;
    private final String INVENTORY_TITLE =  MessageUtils.getColoredMessage("&2Inventario de PluginAjneb97");
    public InventoryListener(PluginAjneb97 plugin) {
        this.plugin = plugin;
    }

    @SuppressWarnings("deprecation")
    public void inventoryCreate(Player player){
        FileConfiguration config = plugin.getConfig();
        String pathtime = "Players." + player.getUniqueId() + ".cooldown-recompensa";
        CooldownListener c = new CooldownListener(plugin);
        String cooldown = c.getCooldown(player);

        Inventory inv = Bukkit.createInventory(null,45, INVENTORY_TITLE);
        ItemStack item = new ItemStack(Material.REDSTONE_BLOCK,1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(MessageUtils.getColoredMessage("&4&lTeletrasportador"));
        List<String> lore = new ArrayList<String>();
        lore.add((MessageUtils.getColoredMessage("&7Te teletransporta a un lugar misterioso")));
        meta.setLore(lore);
        item.setItemMeta(meta);
        inv.setItem(20, item);

        if (c.getCooldown(player).equals("-1")) {
            item = new ItemStack(XMaterial.CHEST_MINECART.parseMaterial(),1);
            meta = item.getItemMeta();
            meta.setDisplayName((MessageUtils.getColoredMessage("&3&lRegalo diamantes")));
            lore = new ArrayList<String>();
            lore.add((MessageUtils.getColoredMessage("&e[Cick para obtener tu recompensa diaria]")));
            meta.setLore(lore);
            item.setItemMeta(meta);
            inv.setItem(22, item);
        }else {
            item = XMaterial.MINECART.parseItem();
            meta = item.getItemMeta();
            meta.setDisplayName((MessageUtils.getColoredMessage("&a&lRecompensa diaria")));
            lore = new ArrayList<String>();
            lore.add((MessageUtils.getColoredMessage("&cDebes esperar &7" + cooldown + "&c para recibir tu recompensa diaria")));
            meta.setLore(lore);
            item.setItemMeta(meta);
            inv.setItem(22, item);
        }


        item = new ItemStack(Material.DIAMOND_SWORD,1);
        meta = item.getItemMeta();
        meta.setDisplayName((MessageUtils.getColoredMessage("&b&lNo Contiene Nada")));
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
        actualizar(config, player);
    }
    private void actualizar(final FileConfiguration config,final Player player) {
        BukkitScheduler sh = Bukkit.getServer().getScheduler();
        taskID = sh.scheduleSyncRepeatingTask(plugin,new Runnable(){
            public void run(){
                if(!updateInventory(config,player)){
                    Bukkit.getScheduler().cancelTask(taskID);
                    return;
                }

            }

            private boolean updateInventory(FileConfiguration config, Player jugador) {
                if (player.getOpenInventory().getTopInventory() != null && player.getOpenInventory().getTopInventory().getType().equals("Inventario de PluginAjneb97")){
                    String pathtime = "Players." + player.getUniqueId() + ".cooldown-recompensa";
                    CooldownListener c = new CooldownListener(plugin);
                    String cooldown = c.getCooldown(player);
                    if (c.getCooldown(player).equals("-1")) {
                        ItemStack item = XMaterial.CHEST_MINECART.parseItem();
                        ItemMeta meta = item.getItemMeta();
                        meta.setDisplayName((MessageUtils.getColoredMessage("&3&lRegalo diamantes")));
                        List<String> lore = new ArrayList<String>();
                        lore.add((MessageUtils.getColoredMessage("&e[Cick para obtener tu recompensa diaria]")));
                        meta.setLore(lore);
                        item.setItemMeta(meta);
                        player.getInventory().setItem(22, item);
                    }else {
                        ItemStack item = new ItemStack(Material.MINECART);
                        ItemMeta meta = item.getItemMeta();
                        meta.setDisplayName((MessageUtils.getColoredMessage("&a&lRecompensa diaria")));
                        List<String> lore = new ArrayList<String>();
                        lore.add((MessageUtils.getColoredMessage("&cDebes esperar &7" + cooldown + "&c para recibir tu recompensa diaria")));
                        meta.setLore(lore);
                        item.setItemMeta(meta);
                        player.getInventory().setItem(22, item);
                    }
                    return true;
                }

                return false;
            }
        },0L,20L);

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
                   if (event.getCurrentItem().getType().equals(Material.MINECART)){
                       FileConfiguration config = plugin.getConfig();
                       CooldownListener c = new CooldownListener(plugin);
                       String cooldown = c.getCooldown(player);
                       if (cooldown.equals("-1")) {
                           String pathtime = "Players." + player.getUniqueId() + ".cooldown-recompensa";
                           long millis = System.currentTimeMillis();
                           config.set(pathtime, millis);
                           plugin.saveConfig();

                           player.sendMessage(MessageUtils.getColoredMessage("&a&lAcabas de recibir tu recompensa diaría!"));
                           player.getInventory().addItem(new ItemStack(Material.DIAMOND, 10));
                       }
                       ItemStack item = new ItemStack(Material.MINECART);
                       ItemMeta meta = item.getItemMeta();
                       meta.setDisplayName((MessageUtils.getColoredMessage("&a&lRecompensa diaria")));
                       List<String> lore = new ArrayList<String>();
                       lore.add((MessageUtils.getColoredMessage("&cDebes esperar &7" + cooldown + "&c para recibir tu recompensa diaria")));
                       meta.setLore(lore);
                       item.setItemMeta(meta);
                       event.getInventory().setItem(22, item);
                   }
                   /*
                    if (player.hasPermission("miplugin.inventario.diamantes")){
                        ItemStack item = new ItemStack(Material.DIAMOND);
                        player.getInventory().addItem(item);
                    }else {
                        player.sendMessage(MessageUtils.getColoredMessage( "&cNo tienes permisos"));
                    }
                    */
                } else if (event.getSlot() == 24) {
                    Inventory inv = Bukkit.createInventory(null, 9, MessageUtils.getColoredMessage("&fAbsolutamente Nada"));
                    player.openInventory(inv);
                }
            }
        }
    }
}