package org.plugin.listener;

import org.bukkit.ChatColor;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.plugin.utils.MessageUtils;

public class EntityInteractListener implements Listener {

    void clickEntidad(PlayerInteractEntityEvent event){
        if (event.getHand().equals(EquipmentSlot.HAND)){
            Player player = event.getPlayer();
            Entity entidad = event.getRightClicked();
            if (entidad.getType().equals(EntityType.COW)){
                Cow vaca = (Cow) entidad;
                if (!vaca.isAdult() && entidad.getCustomName().equals("itemperus")){
                    player.sendMessage(MessageUtils.getColoredMessage("&aHola! Soy la vaca Itemperus"));
                }
            }
        }
    }
}
