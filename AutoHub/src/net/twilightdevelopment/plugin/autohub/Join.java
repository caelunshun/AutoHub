package net.twilightdevelopment.plugin.autohub;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Join implements Listener {

	private final JavaPlugin plugin;
	
	public Join(JavaPlugin plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player joiner = event.getPlayer();
		
		if (plugin.getConfig().getBoolean("tponjoin") && !(joiner.hasPermission("autohub.bypass"))
				&& !(plugin.getConfig().getBoolean("bungeecord"))
				&& AutoHub.isHubSet) {
			double x = plugin.getConfig().getDouble("hub.x");
			double y = plugin.getConfig().getDouble("hub.y");
			double z = plugin.getConfig().getDouble("hub.z");
			String world = plugin.getConfig().getString("hub.world");
			
			joiner.teleport(new Location(Bukkit.getWorld(world), x, y, z));
			joiner.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("joinmessage")));
			
		}
	}
	
	
}
