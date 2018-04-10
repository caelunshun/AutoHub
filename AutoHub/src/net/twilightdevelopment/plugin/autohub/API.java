package net.twilightdevelopment.plugin.autohub;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class API {
	private static AutoHub plugin = AutoHub.getInstance();

	private API() {

	}

	/**
	 * Teleports the specified player to the hub.
	 * 
	 * @param player
	 *            The player to teleport
	 * @return Whether the teleport was successful or not
	 */
	public static boolean tpToHub(Player player) {
		if (AutoHub.isHubSet) {

			double x = plugin.getConfig().getDouble("hub.x");
			double y = plugin.getConfig().getDouble("hub.y");
			double z = plugin.getConfig().getDouble("hub.z");
			String w = plugin.getConfig().getString("hub.world");

			player.teleport(new Location(Bukkit.getWorld(w), x, y, z));
			return true;
		}
		return false;
	}

	/**
	 * Gets the set hub location.
	 * 
	 * @return The set hub location - null if it's not set.
	 */
	public static Location getHubLocation() {
		if (AutoHub.isHubSet)
			return new Location(Bukkit.getWorld(plugin.getConfig().getString("hub.world")),
					plugin.getConfig().getDouble("hub.x"), plugin.getConfig().getDouble("hub.y"),
					plugin.getConfig().getDouble("hub.z"));
		else
			return null;
	}

	/**
	 * Gets the set join message.
	 * 
	 * @return The set join message - null if it's not set.
	 */
	public static String getJoinMessage() {
		return plugin.getConfig().getString("joinmessage");
	}

}
