package me.hub.commands.home;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import me.hub.PluginAjneb97;
import me.hub.countdown.Countdown;
import me.hub.utils.MessageUtils;

public class HomeCommand implements CommandExecutor {
    private final PluginAjneb97 plugin;
    public HomeCommand(PluginAjneb97 plugin){
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            Bukkit.getConsoleSender().sendMessage(MessageUtils.getColoredMessage("&e[" + plugin.getName() + "] &cNo puedes ejecutar comandos desde la consola"));
        } else {
            Player player = (Player) sender;
            FileConfiguration config = plugin.getConfig();

            String path = "Config." + player.getUniqueId() + ".";
            if (config.contains(path + "x")) {
                if (!plugin.playerIsOnThisList(player)){
                    plugin.addPlayer(player);
                    double x = Double.valueOf(config.getString(path + "x"));
                    double y = Double.valueOf(config.getString(path + "y"));
                    double z = Double.valueOf(config.getString(path + "z"));
                    float yaw = Float.valueOf(config.getString(path + "yaw"));
                    float pitch = Float.valueOf(config.getString(path + "pitch"));
                    World world = plugin.getServer().getWorld((config.getString(path + "world")));
                    Location location = new Location(world, x, y, z, yaw, pitch);
                    Countdown countdown = new Countdown(plugin,5, player, location);
                    countdown.ejecucion();
                    player.sendMessage(MessageUtils.getColoredMessage("&c&l[TELETRANSPORTANDOSE] &cA la home en: "));
                }else {
                    player.sendMessage(MessageUtils.getColoredMessage("&cEste comando ya esta en proceso"));
                }

            } else {
                player.sendMessage(MessageUtils.getColoredMessage("&cTu home no existe usa /sethome para marcar tu home"));
            }

        }

        return false;
    }
}
