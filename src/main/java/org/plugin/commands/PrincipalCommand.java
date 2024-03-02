package org.plugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.plugin.PluginAjneb97;
import org.plugin.listener.InventoryListener;
import org.plugin.utils.MessageUtils;

public class PrincipalCommand implements CommandExecutor {
    private final PluginAjneb97 plugin;

    public PrincipalCommand(PluginAjneb97 plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        FileConfiguration config = plugin.getConfig();
        String comandos = "Config.Principal-command.commands";
        String version = "Config.Principal-command.version";
        String reload = "Config.Principal-command.reload";
        if (!(sender instanceof Player)) {
            Bukkit.getConsoleSender().sendMessage(MessageUtils.getColoredMessage( "&e[" + plugin.getName() + "] &cNo puedes ejecutar comandos desde la consola"));
        } else {
            Player player = (Player) sender;
            // /plugin (0 args)
            // /plugin hola (1 args)
            // /plugin hola de nuevo (
            // 3 args)

            if (args.length > 0) {
                if (args[0].equalsIgnoreCase("comandos")) {
                    if (config.contains(comandos) && config.getBoolean(comandos)){
                        player.sendMessage(MessageUtils.getColoredMessage("&aUsa /sethome para marcar una home"));
                        player.sendMessage(MessageUtils.getColoredMessage("&aUsa /delhome para eliminar la home"));
                        player.sendMessage(MessageUtils.getColoredMessage("&aUsa /home para teletransportarse a la home"));
                        player.sendMessage(MessageUtils.getColoredMessage("&aUsa /report para reportar un jugador "));
                        player.sendMessage(MessageUtils.getColoredMessage("&aUsa /spawn para teletransportarse al spawn"));
                        player.sendMessage(MessageUtils.getColoredMessage("&aUsa /youtube para ver el canal de youtube"));
                        player.sendMessage(MessageUtils.getColoredMessage("&aUsa /entity para ver la cantidad de entidades eliminadas\n"));
                    } else {
                        player.sendMessage(MessageUtils.getColoredMessage("&cEl comando no esta activado en este momento"));
                    }

                }else if (args[0].equalsIgnoreCase("version")) {
                    if (config.contains(version) && config.getBoolean(version)){
                        player.sendMessage("La version del plugin es: " + ChatColor.WHITE + plugin.version);
                    }else {
                        player.sendMessage(MessageUtils.getColoredMessage("&cEl comando no esta activado en este momento"));
                    }
                } else if (args[0].equalsIgnoreCase("reload")) {
                    if (config.contains(reload) && config.getBoolean(reload)){
                        plugin.registerConfig();
                        player.sendMessage(MessageUtils.getColoredMessage("&eEl plugin se ha recargado correctamente"));
                    } else{
                        player.sendMessage(MessageUtils.getColoredMessage("&cEl comando no esta activado en este momento"));
                    }
                }else if (args[0].equalsIgnoreCase("inventory")){
                    InventoryListener inv = new InventoryListener(plugin);
                    inv.inventoryCreate(player);
                    return true;
                }else {
                    player.sendMessage(MessageUtils.getColoredMessage("&cEse comando no existe"));
                }
            } else {
                player.sendMessage(MessageUtils.getColoredMessage("&7Usa /plugin version para ver la version del plugin"));
                player.sendMessage(MessageUtils.getColoredMessage("&7Usa /plugin comandos para ver los comandos del plugin"));
                player.sendMessage(MessageUtils.getColoredMessage("&7Usa /plugin reload para recargar la config del plugin"));
            }

        }
        return true;
    }
}
