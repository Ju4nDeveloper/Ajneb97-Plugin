package org.plugin.listener;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.plugin.PluginAjneb97;

public class CooldownListener implements Listener {
    private PluginAjneb97 plugin;

    public CooldownListener(PluginAjneb97 plugin) {
        this.plugin = plugin;
    }

    public String getCooldown(Player player) {
        FileConfiguration config = plugin.getConfig();
        String pathtime = "Players." + player.getUniqueId() + ".cooldown-recompensa";
        if (config.contains(pathtime)) {
            String timecooldownString = config.getString(pathtime);
            long timecooldown = Long.valueOf(timecooldownString);
            long millis = System.currentTimeMillis();
            long cooldown = 86400; //En Segundos
            long cooldownmil = cooldown * 1000;

            long espera = millis - timecooldown;
            long esperaDiv = espera / 1000;
            long esperatotalseg = cooldown - esperaDiv;
            long esperatotalmin = esperatotalseg / 60;
            long esperatotalhour = esperatotalmin / 60;
            if (((timecooldown + cooldownmil) > millis) && (timecooldown != 0)) {
                if (esperatotalseg > 59) {
                    esperatotalseg = esperatotalseg - 60 * esperatotalmin;
                }
                String time = "";
                if (esperatotalseg != 0) {
                    time = esperatotalseg + "s";
                }

                if (esperatotalmin > 59) {
                    esperatotalmin = esperatotalmin - 60 * esperatotalhour;
                }
                if (esperatotalmin > 0) {
                    time = esperatotalmin + "min" + " " + time;
                }

                if (esperatotalhour > 0) {
                    time = esperatotalhour + "h" + " " + time;
                }

                //Aun no se termina el cooldown
                player.sendMessage("Puedes reclamar otra recompensa diaria dentro de " + time);
            } else {
                //Ya se termino el cooldown
                player.sendMessage("Acabas de recibir la recompensa diaria nuevamente");
                config.set(pathtime, millis);
                plugin.saveConfig();
            }
        } else {
            //Usa el comando por primera vez, ya que no existe el path en la config
            return "-1";

        }
        return pathtime;
    }


}
