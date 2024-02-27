package org.plugin.commands.subCommands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.plugin.PluginAjneb97;

public class WebCommand implements CommandExecutor {
    private final PluginAjneb97 plugin;

    public WebCommand(PluginAjneb97 plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)){
            Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "["+ plugin.getName() + "]" + ChatColor.RED + " No puedes ejecutar comandos desde la consola");
            return false;
        }else {
            Player player = (Player) sender;
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f&n                                          "));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', ""));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cVisita nuestra pagina oficial: &9https://www.aurora.net "));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f&n                                          "));
            return true;
        }
    }
}
