package org.plugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.plugin.PluginAjneb97;
import org.plugin.listener.KillEntityListener;

public class EntityCommands implements CommandExecutor {

    private final PluginAjneb97 plugin;

    public EntityCommands(PluginAjneb97 plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[" + plugin.getName() + "] &cNo puedes ejecutar comandos desde la consola"));
        } else {
            Player player = (Player) sender;
            FileConfiguration config = plugin.getConfig();
            String path = "Players." + player.getUniqueId();

            if (args.length == 0) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&7Usa /entity player para ver los players matados"));
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&7Usa /entity zombie para ver los zombies matados"));
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&7Usa /entity esqueleto para ver los esqueletos matados"));
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&7Usa /entity araña para ver las arañas matadas"));
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&7Usa /entity vaca para ver las vacas matadas"));
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&7Usa /entity cerdo para ver los cerdos matados"));
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&7Usa /entity pollo para ver los pollos matados"));
            } else {
                if (args[0].equalsIgnoreCase("player")) {
                    path = path + ".playerKills";
                    int kills;
                    if (config.contains(path)) {
                        kills = config.getInt(path);
                    } else {
                        kills = 0;
                    }
                    if (kills <= 1){
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&a&lHas matado ha: " + kills + " player"));
                    } else if (kills >= 2){
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&a&lHas matado ha: " + kills + " players"));
                    }

                }else if (args[0].equalsIgnoreCase("zombie")) {
                    path = path + ".zombieKills";
                    int kills;
                    if (config.contains(path)) {
                        kills = config.getInt(path);
                    } else {
                        kills = 0;
                    }
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',""));
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&7&lHas matado ha: " + kills + " zombie"));

                } else if (args[0].equalsIgnoreCase("esqueleto")) {
                    path = path + ".esqueletoKills";
                    int kills;
                    if (config.contains(path)) {
                        kills = config.getInt(path);
                    } else {
                        kills = 0;
                    }
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',""));
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&7&lHas matado ha: " + kills + " esqueleto"));
                } else if (args[0].equalsIgnoreCase("araña")) {
                    path = path + ".arañaKills";
                    int kills;
                    if (config.contains(path)) {
                        kills = config.getInt(path);
                    } else {
                        kills = 0;
                    }
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',""));
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&7&lHas matado ha: " + kills + " araña"));

                } else if (args[0].equalsIgnoreCase("vaca")) {
                    path = path + ".vacaKills";
                    int kills;
                    if (config.contains(path)) {
                        kills = config.getInt(path);
                    } else {
                        kills = 0;
                    }
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',""));
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&7&lHas matado ha: " + kills + " vaca"));

                } else if (args[0].equalsIgnoreCase("cerdo")) {
                    path = path + ".cerdoKills";
                    int kills;
                    if (config.contains(path)) {
                        kills = config.getInt(path);
                    } else {
                        kills = 0;
                    }
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',""));
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&7&lHas matado ha: " + kills + " cerdo"));

                } else if (args[0].equalsIgnoreCase("pollo")) {
                    path = path + ".polloKills";
                    int kills;
                    if (config.contains(path)) {
                        kills = config.getInt(path);
                    } else {
                        kills = 0;
                    }
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',""));
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&7&lHas matado ha: " + kills + " pollo"));

                } else {
                    player.sendMessage("Entidad no encontrada");
                }
            }
        }
        return false;
    }
}
