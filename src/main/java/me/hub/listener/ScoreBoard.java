package me.hub.listener;

import me.hub.PluginAjneb97;
import me.hub.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scoreboard.*;

import java.text.NumberFormat;
import java.util.List;

/*
 * Project: pluginAjneb97
 * Created at: 29/03/2024 20:30
 * Created by: Ju4nDeveloper
 * GitHub: https://github.com/Ju4nDeveloper
 */
public class ScoreBoard {
    private PluginAjneb97 plugin;
    int taskID;
    public ScoreBoard(PluginAjneb97 plugin) {
        this.plugin = plugin;
    }
    public void crearScoreBoard(int ticks){
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        taskID = scheduler.scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                FileConfiguration configuration = plugin.getConfig();
                for (Player player : Bukkit.getOnlinePlayers()){
                    actualizarScoreBoard(player, configuration);
                }
            }
        }, 0, ticks);
    }

    private void actualizarScoreBoard(Player player, FileConfiguration configuration){
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard scoreBoard = manager.getNewScoreboard();
        Objective objetivo = scoreBoard.registerNewObjective("plugin", "dummy");
        objetivo.setDisplaySlot(DisplaySlot.SIDEBAR);
        objetivo.setDisplayName(MessageUtils.getColoredMessage(configuration.getString("ScoreBoard.title")));
        List<String> lineas = configuration.getStringList("ScoreBoard.text");
        double x = player.getLocation().getX();
        double y = player.getLocation().getY();
        double z = player.getLocation().getZ();
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);

        for (int i = 0; i<lineas.size(); i++){
            Score score = objetivo.getScore(MessageUtils.getColoredMessage(lineas.get(i)
                    .replace("%coord_x%",numberFormat.format(x) + "")
                    .replace("%coord_y%",numberFormat.format(y) + "")
                    .replace("%coord_z%",numberFormat.format(z) + "")
                    .replace("%player%", player.getName())));
            score.setScore(lineas.size()-(i));
        }
        player.setScoreboard(scoreBoard);
    }
}
