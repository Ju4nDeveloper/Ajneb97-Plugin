package org.plugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.*;
import org.bukkit.event.Listener;
import org.plugin.PluginAjneb97;
import org.plugin.listener.KillEntityListener;

public class EntityCommands implements CommandExecutor {

    private final PluginAjneb97 plugin;
    public EntityCommands(PluginAjneb97 plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[" + plugin.getName() + "] &cNo puedes ejecutar comandos desde la consola"));
        } else {
            Player player = (Player) sender;
            FileConfiguration config = plugin.getConfig();
            String path = "Players." + player.getUniqueId();

            if (args.length == 0) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&7Usa /entity player para ver los players matados"));
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&7Usa /entity zombie para ver los zombies matados"));
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&7Usa /entity esqueleto para ver los esqueletos matados"));
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&7Usa /entity araña para ver las arañas matadas"));
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&7Usa /entity vaca para ver las vacas matadas"));
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&7Usa /entity cerdo para ver los cerdos matados"));
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&7Usa /entity pollo para ver los pollos matados"));
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&n                                                "));
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&7Usa /entity spawn [nombre_entidad-español] "));
            } else {
                if (args[0].equalsIgnoreCase("player")) {
                    path = path + ".playerKills";
                    int kills;
                    if (config.contains(path)) {
                        kills = config.getInt(path);
                    } else {
                        kills = 0;
                    }
                    if (kills <= 1){
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&a&lHas matado ha: " + kills + " player"));
                    } else if (kills >= 2){
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&a&lHas matado ha: " + kills + " players"));
                    }

                }else if (args[0].equalsIgnoreCase("zombie")) {
                    path = path + ".zombieKills";
                    int kills;
                    if (config.contains(path)) {
                        kills = config.getInt(path);
                    } else {
                        kills = 0;
                    }
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',""));
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&7&lHas matado ha: " + kills + " zombie"));

                } else if (args[0].equalsIgnoreCase("esqueleto")) {
                    path = path + ".esqueletoKills";
                    int kills;
                    if (config.contains(path)) {
                        kills = config.getInt(path);
                    } else {
                        kills = 0;
                    }
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',""));
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&7&lHas matado ha: " + kills + " esqueleto"));
                } else if (args[0].equalsIgnoreCase("araña")) {
                    path = path + ".arañaKills";
                    int kills;
                    if (config.contains(path)) {
                        kills = config.getInt(path);
                    } else {
                        kills = 0;
                    }
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',""));
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&7&lHas matado ha: " + kills + " araña"));

                } else if (args[0].equalsIgnoreCase("vaca")) {
                    path = path + ".vacaKills";
                    int kills;
                    if (config.contains(path)) {
                        kills = config.getInt(path);
                    } else {
                        kills = 0;
                    }
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',""));
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&7&lHas matado ha: " + kills + " vaca"));

                } else if (args[0].equalsIgnoreCase("cerdo")) {
                    path = path + ".cerdoKills";
                    int kills;
                    if (config.contains(path)) {
                        kills = config.getInt(path);
                    } else {
                        kills = 0;
                    }
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',""));
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&7&lHas matado ha: " + kills + " cerdo"));

                } else if (args[0].equalsIgnoreCase("pollo")) {
                    path = path + ".polloKills";
                    int kills;
                    if (config.contains(path)) {
                        kills = config.getInt(path);
                    } else {
                        kills = 0;
                    }
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',""));
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&7&lHas matado ha: " + kills + " pollo"));

                } else if (args[0].equalsIgnoreCase("spawn pollo")) {
                    LivingEntity entidad = (LivingEntity) player.getWorld().spawnEntity(player.getLocation(), EntityType.CHICKEN);
                    Chicken pollo = (Chicken) entidad;

                } else if (args[0].equalsIgnoreCase("spawn vaca")) {
                    LivingEntity entidad = (LivingEntity) player.getWorld().spawnEntity(player.getLocation(), EntityType.COW);
                    Cow vaca = (Cow) entidad;

                } else if (args[0].equalsIgnoreCase("spawn cerdo")) {
                    LivingEntity entidad = (LivingEntity) player.getWorld().spawnEntity(player.getLocation(), EntityType.PIG);
                    Pig cerdo = (Pig) entidad;

                } else if (args[0].equalsIgnoreCase("spawn zombie")) {
                    LivingEntity entidad = (LivingEntity) player.getWorld().spawnEntity(player.getLocation(), EntityType.ZOMBIE);
                    Zombie zombie = (Zombie) entidad;
                    //entidad.getEquipment().setItemHand(new ItemmStack(Material.IRON_AXE,1));
                    //entidad.setPassenger(entidad);

                } else if (args[0].equalsIgnoreCase("spawn araña")) {
                    LivingEntity entidad = (LivingEntity) player.getWorld().spawnEntity(player.getLocation(), EntityType.SPIDER);
                    Spider araña = (Spider) entidad;

                } else if (args[0].equalsIgnoreCase("spawn golem")) {
                    LivingEntity entidad = (LivingEntity) player.getWorld().spawnEntity(player.getLocation(), EntityType.IRON_GOLEM);
                    IronGolem golem = (IronGolem) entidad;

                } else if (args[0].equalsIgnoreCase("spawn blaze")) {
                    LivingEntity entidad = (LivingEntity) player.getWorld().spawnEntity(player.getLocation(), EntityType.BLAZE);
                    Blaze blaze = (Blaze) entidad;

                } else if (args[0].equalsIgnoreCase("spawn bruja")) {
                    LivingEntity entidad = (LivingEntity) player.getWorld().spawnEntity(player.getLocation(), EntityType.WITCH);
                    Witch bruja = (Witch) entidad;

                } else if (args[0].equalsIgnoreCase("spawn creeper")) {
                    LivingEntity entidad = (LivingEntity) player.getWorld().spawnEntity(player.getLocation(), EntityType.CREEPER);
                    Creeper creeper = (Creeper) entidad;

                } else if (args[0].equalsIgnoreCase("spawn esqueleto")) {
                    LivingEntity entidad = (LivingEntity) player.getWorld().spawnEntity(player.getLocation(), EntityType.SKELETON);
                    Skeleton esqueleto = (Skeleton) entidad;

                } else if (args[0].equalsIgnoreCase("spawn guardian")) {
                    LivingEntity entidad = (LivingEntity) player.getWorld().spawnEntity(player.getLocation(), EntityType.GUARDIAN);
                    Guardian guardian = (Guardian) entidad;

                } else if (args[0].equalsIgnoreCase("spawn oveja")) {
                    LivingEntity entidad = (LivingEntity) player.getWorld().spawnEntity(player.getLocation(), EntityType.SHEEP);
                    Sheep oveja = (Sheep) entidad;
                    oveja.setColor(DyeColor.ORANGE);
                    oveja.setBaby();
                    oveja.setCustomName(ChatColor.translateAlternateColorCodes('&', "&6&lmadre de oval"));
                    oveja.setCustomNameVisible(true);
                }else {
                    player.sendMessage("Entidad no encontrada");
                }
            }
        }
        return false;
    }
}
