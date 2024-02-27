package org.plugin.listener;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {
    @EventHandler
    public void modificarChat(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();
        String message = event.getMessage().toLowerCase();
        boolean edited = false;
        String[] block = {"hack", "choto", "escroto", "joder", "mierda", "gilipolla", "pene",  "polla", "put", "coño", "chocho", "porno", "hostia", "cag", "cojones", "clitoris", "paja", "masturbarse", "orto", "foll", "sexo", "chup", "cabron", "culo", "jaqueline", "fuck", "suck", "ez", "cagon"};
        for (int i=0;i<block.length;i++){
            if (message.contains(block[i])){
                String a = "";
                for (int c=0;c<block[i].length();c++){
                    a = a+"*";
                }
                message = message.replace(block[i],a);
                edited = true;
            }

        }

        if (!edited) {
            message = event.getMessage();
        } else {
            event.setMessage(message);
        }

        event.setFormat(ChatColor.translateAlternateColorCodes('&', "&8[&8&lUsuario&8] &7" + player.getName()+ "&7 >> " + message));
    }
}
