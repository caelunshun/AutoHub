package net.twilightdevelopment.plugin.autohub;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import net.md_5.bungee.api.ChatColor;

public class HubCommand implements CommandExecutor {

	private final AutoHub plugin;

	public HubCommand(AutoHub plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("hub") && sender.hasPermission("autohub.tphub")
				&& plugin.getConfig().getBoolean("hubcommand") && !(plugin.getConfig().getBoolean("bungeecord"))) {

			if (sender instanceof Player) {
				if (AutoHub.isHubSet) {
					double x = plugin.getConfig().getDouble("hub.x");
					double y = plugin.getConfig().getDouble("hub.y");
					double z = plugin.getConfig().getDouble("hub.z");
					String world = plugin.getConfig().getString("hub.world");

					Player player = (Player) sender;

					player.teleport(new Location(Bukkit.getWorld(world), x, y, z));
					player.sendMessage(
							ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("hubmessage")));
					return true;
				} else {
					Player player = (Player) sender;
					player.sendMessage(ChatColor.RED + "Error: Hub is not set. Set it using /sethub!");
				}
			} else
				sender.sendMessage("This command must be executed by a player.");

		}

		else if (cmd.getName().equalsIgnoreCase("hub") && plugin.getConfig().getBoolean("hubcommand")
				&& plugin.getConfig().getBoolean("bungeecord")) {
			if (sender instanceof Player && sender.hasPermission("autohub.tphub")) {

				sender.sendMessage(
						ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("hubmessage")));
				ByteArrayDataOutput out = ByteStreams.newDataOutput();
				String servy = plugin.getConfig().getString("bungeeserver");

				out.writeUTF("Connect");
				out.writeUTF(servy);

				Player player = (Player) sender;
				player.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());

			} else {
				sender.sendMessage("This command must be executed by a player.");
			}
		}
		return true;
	}
}