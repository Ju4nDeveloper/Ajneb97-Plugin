package me.hub.commands;

import me.hub.PluginAjneb97;
import me.hub.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Project: pluginAjneb97
 * Created at: 28/03/2024 22:33
 * Created by: Ju4nDeveloper
 * GitHub: https://github.com/Ju4nDeveloper
 */
public class MuteCommand implements CommandExecutor {
    private final PluginAjneb97 plugin;
    public MuteCommand(PluginAjneb97 plugin) {
        this.plugin = plugin;
    }
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Bukkit.getConsoleSender().sendMessage(MessageUtils.getColoredMessage("&e[" + plugin.getName() + "] &cNo puedes ejecutar comandos desde la consola"));
        } else {
            Player player = (Player) sender;
            if (args.length == 0) {
                player.sendMessage(MessageUtils.getColoredMessage("&cUsa &f/mute <player> "));
            } else {
                String usuario = args[0];
                if (Bukkit.getPlayer(usuario) != null) {

                    List<String> muteados = plugin.getMutedPlayers();
                    if (muteados.contains(usuario)) {
                        player.sendMessage(MessageUtils.getColoredMessage("&4&lHEY! &cEse jugador ya ha sido muteado"));

                    } else {
                        player.sendMessage(MessageUtils.getColoredMessage("&a&lEl jugador ha sido muteado correctamente"));
                        muteados.add(usuario);
                    }
                } else {
                    player.sendMessage(MessageUtils.getColoredMessage("&4&lHEY! &cEse jugador no está conectado"));
                }

            }
        }

        return true;
    }
}

