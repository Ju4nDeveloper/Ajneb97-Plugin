package me.hub.commands.home;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import me.hub.PluginAjneb97;
import me.hub.utils.MessageUtils;

public class SetHomeCommand implements CommandExecutor {

    private final PluginAjneb97 plugin;
    public SetHomeCommand(PluginAjneb97 plugin){
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)){
            Bukkit.getConsoleSender().sendMessage(MessageUtils.getColoredMessage("&e[" + plugin.getName()+ "] &cNo puedes ejecutar comandos desde la consola"));
        }else {
            Player player = (Player) sender;
            Location l = player.getLocation();
            String path = "Config." + player.getUniqueId() + ".";
            double x = l.getX();
            double y = l.getY();
            double z = l.getZ();
            String world = l.getWorld().getName();
            float yaw = l.getYaw();
            float pitch = l.getPitch();
            FileConfiguration config = plugin.getConfig();
            config.set(path + "x", x);
            config.set(path + "y", y);
            config.set(path + "z", z);
            config.set(path + "yaw", yaw);
            config.set(path + "pitch", pitch);
            config.set(path + "world", world);
            plugin.saveConfig();
            player.sendMessage(MessageUtils.getColoredMessage("&a La home ha sido cambiada correctamente"));
        }
        return false;
    }
}


