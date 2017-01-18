package net.twilightdevelopment.plugin.autohub;


import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import net.md_5.bungee.api.ChatColor;

public class HubCommand implements CommandExecutor {

	private final JavaPlugin plugin;
	public HubCommand(JavaPlugin plugin) {
		this.plugin = plugin;
	}
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("hub") && sender.hasPermission("autohub.tphub")
				&& plugin.getConfig().getBoolean("hubcommand") == true
				&& plugin.getConfig().getBoolean("bungeecord") == false
				) {
			
			if (sender instanceof Player) {
				if (Main.isHubSet == true) {
			double x = plugin.getConfig().getDouble("hub.x");
			double y = plugin.getConfig().getDouble("hub.y");
			double z = plugin.getConfig().getDouble("hub.z");
			String world = plugin.getConfig().getString("hub.world");
			
			Player player = (Player) sender;
			
			player.teleport(new Location(Bukkit.getWorld(world), x, y, z));
			player.sendMessage(plugin.getConfig().getString("hubmessage"));
			return true;
			}
				else if (Main.isHubSet == false) {
					Player player = (Player) sender;
					player.sendMessage(ChatColor.RED + "Hub is not set. Set it using /sethub!");
				}
			}
			else {
				sender.sendMessage("This command must be executed by a player.");
			}
		}
		
		
		else if (cmd.getName().equalsIgnoreCase("hub")
				&& plugin.getConfig().getBoolean("hubcommand") == true
				&& plugin.getConfig().getBoolean("bungeecord") == true) {
			if (sender instanceof Player && sender.hasPermission("autohub.tphub")) {
				
				ByteArrayDataOutput out = ByteStreams.newDataOutput();
				String servy = plugin.getConfig().getString("bungeeserver");
				
				out.writeUTF("Connect");
				out.writeUTF(servy);
				
				Player player = (Player) sender;
				player.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
				
				
				
				
			}
			else {
				sender.sendMessage("This command must be executed by a player.");
			}
		}
		
		
		
		
		
		
		else if (cmd.getName().equalsIgnoreCase("sethub") && sender instanceof Player && sender.hasPermission("autohub.set")
				&& args.length == 3) {
			Player player = (Player) sender;
			double x = 0;
			double y = 64;
			double z = 0;
			String w = "world";
		
			
			try {
			x = Double.parseDouble(args[0]);
			y = Double.parseDouble(args[1]);
			z = Double.parseDouble(args[2]);

			
			}
			catch(NumberFormatException e) {
			player.sendMessage(ChatColor.YELLOW + "ERROR: To execute this command, you must do /sethub <x> <y> <z> <world> (world is optional, if not specified it will default to the world named 'world').");
			
			}
			
			plugin.getConfig().set("hub.x", x);
			plugin.getConfig().set("hub.y", y);
			plugin.getConfig().set("hub.z", z);
			plugin.getConfig().set("hub.world", w);
			plugin.saveConfig();
			player.sendMessage(ChatColor.GREEN + "Hub set.");
			
		
			return true;
			
		}
		
		
		else if (cmd.getName().equalsIgnoreCase("sethub") && sender instanceof Player && sender.hasPermission("autohub.set")
				&& args.length == 4) {
			Player player = (Player) sender;
			double x = 0;
			double y = 64;
			double z = 0;
			String w = args[3];
		
			
			try {
			x = Double.parseDouble(args[0]);
			y = Double.parseDouble(args[1]);
			z = Double.parseDouble(args[2]);
			
			
			}
			catch(NumberFormatException e) {
			player.sendMessage(ChatColor.YELLOW + "ERROR: To execute this command, you must do /sethub <x> <y> <z> <world> (world is optional, if not specified it will default to the world named 'world').");
			
			}
			
			plugin.getConfig().set("hub.x", x);
			plugin.getConfig().set("hub.y", y);
			plugin.getConfig().set("hub.z", z);
			plugin.getConfig().set("hub.world", w);
			plugin.saveConfig();
			player.sendMessage(ChatColor.GREEN + "Hub set.");
			
		
			return true;
		}
		
		
		else if (cmd.getName().equalsIgnoreCase("sethub") && args.length == 4) {
			ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
			double x = 0;
			double y = 64;
			double z = 0;
			String w = args[3];
		
			
			try {
			x = Double.parseDouble(args[0]);
			y = Double.parseDouble(args[1]);
			z = Double.parseDouble(args[2]);
			
			
			}
			catch(NumberFormatException e) {
			console.sendMessage(ChatColor.YELLOW + "ERROR: To execute this command from the console, you must do /sethub <x> <y> <z> <world> (world is optional, if not specified it will default to the world named 'world').");
			
			}
			
			plugin.getConfig().set("hub.x", x);
			plugin.getConfig().set("hub.y", y);
			plugin.getConfig().set("hub.z", z);
			plugin.getConfig().set("hub.world", w);
			plugin.saveConfig();
			console.sendMessage(ChatColor.GREEN + "Hub set.");
			
		
			return true;
		}
		
		
	else if (cmd.getName().equalsIgnoreCase("sethub") && args.length == 3) {
	ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
			double x = 0;
			double y = 64;
			double z = 0;
			String w = "world";
		
	
			try {
			x = Double.parseDouble(args[0]);
			y = Double.parseDouble(args[1]);
			z = Double.parseDouble(args[2]);
			
			
			}
			catch(NumberFormatException e) {
			console.sendMessage(ChatColor.YELLOW + "ERROR: To execute this command from the console, you must do /sethub <x> <y> <z> <world> (world is optional, if not specified it will default to the world named 'world').");
		
			}
			
			plugin.getConfig().set("hub.x", x);
			plugin.getConfig().set("hub.y", y);
			plugin.getConfig().set("hub.z", z);
			plugin.getConfig().set("hub.world", w);
			plugin.saveConfig();
			console.sendMessage(ChatColor.GREEN + "Hub set.");
			
		
			return true;
		}
		
		
	else if (cmd.getName().equalsIgnoreCase("sethub") && sender instanceof Player && sender.hasPermission("autohub.set")
			&& args.length == 0) {
			Player player = (Player) sender;
			
			plugin.getConfig().set("hub.x", player.getLocation().getX());
			plugin.getConfig().set("hub.y", player.getLocation().getY());
			plugin.getConfig().set("hub.z", player.getLocation().getZ());
			plugin.saveConfig();
			player.sendMessage(ChatColor.GREEN + "Hub location set!");
			
			return true;
}
	
	return false;
}

}