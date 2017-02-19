package net.twilightdevelopment.plugin.autohub;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;


public class API {

	private static JavaPlugin plugin;
	
	
	
	public API(JavaPlugin plugin) {
		API.plugin = plugin;
	}
	
	
	public static boolean tpToHub(Player player) {
		if(AutoHub.isHubSet == true) {
			
			double x = plugin.getConfig().getDouble("hub.x");
			double y = plugin.getConfig().getDouble("hub.y");
			double z = plugin.getConfig().getDouble("hub.z");
			String w = plugin.getConfig().getString("hub.world");
			
			player.teleport(new Location(Bukkit.getWorld(w), x, y, z));
			return true;
		}
		return false;
	}
	
	
	
	
}
