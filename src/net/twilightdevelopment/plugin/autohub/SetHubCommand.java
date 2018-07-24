package net.twilightdevelopment.plugin.autohub;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SetHubCommand implements CommandExecutor {

  private JavaPlugin plugin;
  private static final String USAGE_MESSAGE =
      ChatColor.RED
          + "Usage: /sethub <x> <y> <z> [world]";

  public SetHubCommand(JavaPlugin plugin) {
    this.plugin = plugin;
  }

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (!sender.hasPermission("autohub.set") && !(sender instanceof ConsoleCommandSender)) {
      sender.sendMessage(ChatColor.RED + "You do not have permission to execute this command.");
      return true;
    }

    if (plugin.getConfig().getBoolean("bungeecord")) {
      sender.sendMessage(
          ChatColor.GOLD
              + "Warning: BungeeCord mode is enabled in the configuration. Players will"
              + " not be teleported to the location you are setting; they will be teleported to the server specified"
              + " in the configuration.");
    }
    double x = 0;
    double y = 0;
    double z = 0;
    World world = Bukkit.getWorld("world");
    switch (args.length) {
      case 0:
        if (!(sender instanceof Player)) {
          sender.sendMessage(ChatColor.RED + "Please specify the location.");
          return true;
        }
        Player player = (Player) sender;
        x = player.getLocation().getX();
        y = player.getLocation().getY();
        z = player.getLocation().getZ();
        world = player.getWorld();
        setHub(x, y, z, world);
        sender.sendMessage(ChatColor.GREEN + "Hub set!");
        break;
      case 3:
        try {
          x = Double.parseDouble(args[0]);
          y = Double.parseDouble(args[1]);
          z = Double.parseDouble(args[2]);
        } catch (NumberFormatException e) {
          sender.sendMessage(USAGE_MESSAGE);
          return true;
        }
        setHub(x, y, z, world);
        sender.sendMessage(ChatColor.GREEN + "Hub set!");
        break;
      case 4:
        world = Bukkit.getWorld(args[3]);
        if (world == null) {
          sender.sendMessage(ChatColor.RED + "World not found.");
          return true;
        }
        try {
          x = Double.parseDouble(args[0]);
          y = Double.parseDouble(args[1]);
          z = Double.parseDouble(args[2]);
        } catch (NumberFormatException e) {
          sender.sendMessage(USAGE_MESSAGE);
          return true;
        }
        setHub(x, y, z, world);
        sender.sendMessage(ChatColor.GREEN + "Hub set!");
        break;
      default:
        sender.sendMessage(USAGE_MESSAGE);
        break;
    }
    return true;
  }

  private void setHub(double x, double y, double z, World world) {
    plugin.getConfig().createSection("hub.x");
    plugin.getConfig().createSection("hub.y");
    plugin.getConfig().createSection("hub.z");
    plugin.getConfig().createSection("hub.world");
    plugin.getConfig().set("hub.x", x);
    plugin.getConfig().set("hub.y", y);
    plugin.getConfig().set("hub.z", z);
    plugin.getConfig().set("hub.world", world.getName());
    plugin.saveConfig();
    plugin.reloadConfig();
    ((AutoHub) plugin).setHub();
  }

  public List<String> tabComplete(CommandSender sender, Command cmd, String label, String[] args) {
    if (args.length == 4) {
      List<String> result = new ArrayList<>();
      StringUtil.copyPartialMatches(
          args[3], ArrayUtil.applyModification(Bukkit.getWorlds(), World::getName), result);
      return result;
    }

    return Collections.emptyList();
  }
}
