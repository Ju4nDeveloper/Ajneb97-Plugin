package me.hub;

import me.hub.commands.*;
import me.hub.commands.home.DelHomeCommand;
import me.hub.commands.home.HomeCommand;
import me.hub.commands.home.SetHomeCommand;
import me.hub.listener.*;
import me.hub.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import me.hub.commands.subCommands.DiscordCommand;
import me.hub.commands.subCommands.WebCommand;
import me.hub.commands.subCommands.YoutubeCommand;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/*
 * Project: hub
 * Created at: 27/02/24 16:20
 * Created by: Ju4nDeveloper
 * GitHub: https://github.com/Ju4nDeveloper
 */
public final class PluginAjneb97 extends JavaPlugin {
    private FileConfiguration messages = null;
    private File messagesFile = null;
    public PluginDescriptionFile pdffile = getDescription();
    public String version = pdffile.getVersion();
    public String nombre = pdffile.getName();
    private ArrayList<String> players;
    private List<String> mutedPlayers;
    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "[" + nombre + "]" + ChatColor.WHITE + ChatColor.ITALIC + " El plugin ha sido activado " + ChatColor.DARK_GRAY + "(version: " + version + ")");
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "[" + nombre + "]" + ChatColor.WHITE + ChatColor.ITALIC + " Gracias por utilizar mi plugin: ~JuanDev");
        registerCommands();
        registerEvents();
        registerConfig();
        registerMessages();
        RepeatingListener tarea1 = new RepeatingListener(this,2400);
        tarea1.ejecucion();
        players =  new ArrayList<String>();

        FileConfiguration config = getConfig();
        if (config.contains("Config.usuarios-muteados")) {
            this.mutedPlayers = config.getStringList("Config.usuarios-muteados");
        } else {
            this.mutedPlayers = new ArrayList<>();
        }
        ScoreBoard scoreBoard = new ScoreBoard(this);
        scoreBoard.crearScoreBoard(Integer.valueOf(getConfig().getString("ScoreBoard.ticks")));
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "[" + nombre + "]" + ChatColor.WHITE + ChatColor.STRIKETHROUGH + " El plugin ha sido desactivado " + ChatColor.DARK_GRAY + "(version: " + version + ")");
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "[" + nombre + "]" + ChatColor.WHITE + ChatColor.STRIKETHROUGH + " Gracias por haber utilizado mi plugin: ~JuanDev");

        FileConfiguration config = getConfig();
        config.set("Config.usuarios-muteados", this.mutedPlayers);
        saveConfig();
    }

    public void registerCommands(){
        this.getCommand("youtube").setExecutor(new YoutubeCommand(this));
        this.getCommand("plugin").setExecutor(new PrincipalCommand(this));
        this.getCommand("spawn").setExecutor(new SpawnCommand(this));
        this.getCommand("sethome").setExecutor(new SetHomeCommand(this));
        this.getCommand("delhome").setExecutor(new DelHomeCommand(this));
        this.getCommand("home").setExecutor(new HomeCommand(this));
        this.getCommand("entity").setExecutor(new EntityCommands(this));
        this.getCommand("report").setExecutor(new ReportCommand(this));
        this.getCommand("discord").setExecutor(new DiscordCommand(this));
        this.getCommand("web").setExecutor(new WebCommand(this));
        this.getCommand("recompensa").setExecutor(new RewardCommand(this));
        this.getCommand("mute").setExecutor(new MuteCommand(this));
        this.getCommand("unmute").setExecutor(new UnMuteCommand(this));
    }
    @EventHandler
    public void registerEvents(){
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new EnterListener(this), this);
        pluginManager.registerEvents(new KillEntityListener(this), this);
        pluginManager.registerEvents(new InventoryListener(this), this);
        pluginManager.registerEvents(new ChatListener(this), this);
        pluginManager.registerEvents(new CooldownListener(this),this);
        pluginManager.registerEvents(new WorldListener(this),this);

    }
    
    public void registerConfig(){
        File config = new File(this.getDataFolder(),"config.yml");
        String rutaConfig = config.getPath();
        if(!config.exists()) {
            this.getConfig().options().copyDefaults(true);
            saveConfig();
        }
    }

    public FileConfiguration getMessages(){
        if (messages == null){
            reloadMessages();
        }
        return messages;
    }
    public void reloadMessages(){
        if (messages == null){
            messagesFile = new File(getDataFolder(), "messages.yml");
        }
        messages = YamlConfiguration.loadConfiguration(messagesFile);
        Reader defConfigStream;
        defConfigStream = new InputStreamReader(this.getResource("messages.yml"), StandardCharsets.UTF_8);
        if (defConfigStream != null) {
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
            messages.setDefaults(defConfig);
        }
    }

    public void saveMessages(){
        try {
            messages.save(messagesFile);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void registerMessages(){
        messagesFile = new File(this.getDataFolder(), "messages.yml");
        if (!messagesFile.exists()){
            this.getMessages().options().copyDefaults(true);
            saveMessages();
        }
    }

    public void addPlayer(Player player){
        players.add(player.getName());
    }

    public void removePlayer(Player player){
        players.remove(player.getName());
    }

    public boolean playerIsOnThisList(Player player){
        return players.contains(player.getName());
    }

    public List<String> getMutedPlayers() {
        return mutedPlayers;
    }
}
