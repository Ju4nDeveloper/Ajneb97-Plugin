package org.plugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.plugin.PluginAjneb97;
import org.plugin.countdown.Countdown;

public class SpawnCommand implements CommandExecutor {
    private final PluginAjneb97 plugin;
    public SpawnCommand(PluginAjneb97 plugin){
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        FileConfiguration config = plugin.getConfig();
        String spawn = "Config.Spawn.spawn";
        Player player = (Player) sender;
        if (!(sender instanceof Player)){
            Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "["+ plugin.getName() + "]" + ChatColor.RED + " No puedes ejecutar comandos desde la consola");

        }else {
            if (config.contains(spawn) && config.getBoolean(spawn)){
                if (!plugin.playerIsOnThisList(player)){
                    plugin.addPlayer(player);
                    Location location = new Location(player.getWorld(), 0.500, 63, 0.500, 90, -1);
                    Countdown countdown = new Countdown(plugin, 5, player, location);
                    countdown.ejecucion();
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&c&l[TELETRANSPORTANDOSE] &cAl spawm"));
                }else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cEste comando ya esta en proceso"));
                }
            } else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cEl comando no esta activado en este momento"));
            }
        }
        return true;
    }
}