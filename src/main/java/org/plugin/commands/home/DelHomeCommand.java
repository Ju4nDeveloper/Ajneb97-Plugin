package org.plugin.commands.home;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.plugin.PluginAjneb97;
import org.plugin.utils.MessageUtils;
import sun.security.krb5.Config;


public class DelHomeCommand implements CommandExecutor {

    public DelHomeCommand(PluginAjneb97 plugin) {
        this.plugin = plugin;
    }

    private final PluginAjneb97 plugin;



    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Bukkit.getConsoleSender().sendMessage(MessageUtils.getColoredMessage("&e[" + plugin.getName() + "] &cNo puedes ejecutar comandos desde la consola"));
        } else {
            Player player = (Player) sender;
            FileConfiguration config = plugin.getConfig();

            String path = "Config." + player.getUniqueId();
            if (config.contains(path + ".x")) {
                config.set(path, null);
                plugin.saveConfig();
                player.sendMessage(MessageUtils.getColoredMessage("&a&lLa home ha sido eliminada correctamente" ));
            } else {
                player.sendMessage(MessageUtils.getColoredMessage("&cTu home no existe usa /sethome para marcar tu home"));
            }

        }

        return false;
    }
}
