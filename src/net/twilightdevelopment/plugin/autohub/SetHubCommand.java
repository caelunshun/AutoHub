package net.twilightdevelopment.plugin.autohub;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class SetHubCommand implements CommandExecutor {

	private JavaPlugin plugin;
	public SetHubCommand(JavaPlugin plugin) {
		this.plugin = plugin;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!sender.hasPermission("autohub.set") && !(sender instanceof ConsoleCommandSender)) {
			sender.sendMessage(ChatColor.RED + "You do not have permission to execute this command.");
			return true;
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
				sender.sendMessage(ChatColor.RED + "To execute this command, you must do /sethub <x> <y> <z> <world>"
						+ " (world is optional; if not specified it will default to the world named 'world'");
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
				sender.sendMessage(ChatColor.RED + "To execute this command, you must do /sethub <x> <y> <z> <world>"
						+ " (world is optional; if not specified it will default to the world named 'world'");
				return true;
			}
			setHub(x, y, z, world);
			sender.sendMessage(ChatColor.GREEN + "Hub set!");
			break;
		default:
			sender.sendMessage(ChatColor.RED + "To execute this command, you must do /sethub <x> <y> <z> <world>"
						+ " (world is optional; if not specified it will default to the world named 'world'");
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
}
