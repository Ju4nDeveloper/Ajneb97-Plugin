package org.plugin.utils;

import org.bukkit.ChatColor;

public class MessageUtils {

    public static String getColoredMessage(String message){
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
