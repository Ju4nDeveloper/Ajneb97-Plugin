package org.plugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.plugin.commands.*;
import org.plugin.commands.home.DelHomeCommand;
import org.plugin.commands.home.HomeCommand;
import org.plugin.commands.home.SetHomeCommand;
import org.plugin.commands.subCommands.DiscordCommand;
import org.plugin.commands.subCommands.WebCommand;
import org.plugin.commands.subCommands.YoutubeCommand;
import org.plugin.listener.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public final class PluginAjneb97 extends JavaPlugin {
    private FileConfiguration messages = null;
    private File messagesFile = null;
    public PluginDescriptionFile pdffile = getDescription();
    public String version = pdffile.getVersion();
    public String nombre = pdffile.getName();
    private ArrayList<String> players;
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
        players =  new ArrayList<String>(players);
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "[" + nombre + "]" + ChatColor.WHITE + ChatColor.STRIKETHROUGH + " El plugin ha sido desactivado " + ChatColor.DARK_GRAY + "(version: " + version + ")");
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "[" + nombre + "]" + ChatColor.WHITE + ChatColor.STRIKETHROUGH + " Gracias por haber utilizado mi plugin: ~JuanDev");
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
    }
    @EventHandler
    public void registerEvents(){
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new EnterListener(this), this);
        pluginManager.registerEvents(new KillEntityListener(this), this);
        pluginManager.registerEvents(new InventoryListener(this), this);
        pluginManager.registerEvents(new ChatListener(), this);

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
        player.remove();
    }

    public boolean playerIsOnThisList(Player player){
        if (players.contains(player.getName())){
            return true;
        }else {
            return false;
        }
    }
}
