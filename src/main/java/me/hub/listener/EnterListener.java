package me.hub.listener;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import me.hub.PluginAjneb97;
import me.hub.utils.MessageUtils;

import java.util.List;

public class EnterListener implements Listener {
    private final PluginAjneb97 plugin;

    public EnterListener(PluginAjneb97 plugin){
        this.plugin = plugin;
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        FileConfiguration config = plugin.getConfig();
        String path = "Config." + player.getUniqueId() + ".";
        if (config.contains(path + "x")){
            double x = Double.valueOf(config.getString( path + "x"));
            double y = Double.valueOf(config.getString(path + "y"));
            double z = Double.valueOf(config.getString(path + "z"));
            float yaw = Float.valueOf(config.getString(path + "yaw"));
            float pitch = Float.valueOf(config.getString(path + "pitch"));
            World world = plugin.getServer().getWorld((config.getString(path + "world")));

            Location l = new Location(world, x, y, z, yaw, pitch);
            player.teleport(l);
        }


        if (config.getBoolean("Config.Mensaje.bienvenida")){
            FileConfiguration messages = plugin.getMessages();
            List<String> mensaje = messages.getStringList("Messages.mensaje-bienvenida");

            for (int i=0; i < mensaje.size(); i++ ){
                String texto = mensaje.get(i);
                player.sendMessage(MessageUtils.getColoredMessage(texto.replaceAll("%player%", player.getName())));
            }
        }
    }
}
