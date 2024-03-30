package me.hub.commands.subCommands;

import me.hub.PluginAjneb97;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DiscordCommand implements CommandExecutor {

    private final PluginAjneb97 plugin;

    public DiscordCommand(PluginAjneb97 plugin){
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
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cVisita nuestro discord: &9https://discord.gg/rhNPjqDCqa"));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f&n                                          "));
            return true;
        }

    }
}
