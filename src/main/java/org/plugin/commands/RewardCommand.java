package org.plugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.plugin.PluginAjneb97;
import org.plugin.listener.CooldownListener;
import org.plugin.utils.MessageUtils;

public class RewardCommand implements CommandExecutor {

    private final PluginAjneb97 plugin;

    public RewardCommand(PluginAjneb97 plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        FileConfiguration config = plugin.getConfig();

        if (!(sender instanceof Player)) {
            Bukkit.getConsoleSender().sendMessage(MessageUtils.getColoredMessage("&6["+ plugin.getName() + "]" + "&c No puedes ejecutar comandos desde la consola"));
            return true;
        }

        Player player = (Player) sender;
        String pathtime = "Players." + player.getUniqueId() + ".cooldown-recompensa";
        CooldownListener c = new CooldownListener(plugin);
        String cooldown = c.getCooldown(player);

        if (cooldown.equals("-1")) {
            long millis = System.currentTimeMillis();
            config.set(pathtime, millis);
            plugin.saveConfig();

            player.sendMessage(MessageUtils.getColoredMessage("&a&lAcabas de recibir tu recompensa diaría!"));
            player.getInventory().addItem(new ItemStack(Material.DIAMOND, 10));
        }
        return true;
    }
}
