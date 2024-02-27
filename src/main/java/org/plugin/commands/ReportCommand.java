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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReportCommand implements CommandExecutor {
    public ReportCommand(PluginAjneb97 plugin) {
        this.plugin = plugin;
    }

    private final PluginAjneb97 plugin;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        FileConfiguration config = plugin.getConfig();
        Player player = (Player) sender;
        if (!(sender instanceof Player)){
            Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "["+ plugin.getName() + "]" + ChatColor.RED + " No puedes ejecutar comandos desde la consola");

        }else {
            if (args.length == 0) {

                player.sendMessage(ChatColor.translateAlternateColorCodes( '&', "&cUsa &f/report <player> <razón> &cpara reportar un jugador"));
            } else if (args.length == 1){
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "\n&cEscriba la razón del reporte"));
            }else if (args.length >= 2){
                //args[1] = /report <usuario>
                //args[2] = /report <usuario> <razón>

                String usuario = args[0];
                String razon = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
                if (Bukkit.getPlayer(usuario) != null){
                    if (config.contains("Config.usuarios-reportados")){
                        List<String> reportados = config.getStringList("Config.usuarios-reportados");
                        if (reportados.contains(usuario)){
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4&lError! &cEse jugador ya ha sido reportado anteriormente"));
                        }else {
                            reportados.add(usuario + ": " + razon);
                            config.set("Config.usuarios-reportados", reportados);
                            plugin.saveConfig();
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&lTu reporte ha sido enviado correctamente"));
                        }
                      }else {
                        List<String> reportados = new ArrayList<String>();
                        reportados.add(usuario + ": " + razon);
                        config.set("Config.usuarios-reportados", reportados);
                        plugin.saveConfig();
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&lTu reporte ha sido enviado correctamente"));
                    }
                }else{
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4&lError! &cEse jugador no está conectado"));
                }
            }
        }
        return true;
    }
}
