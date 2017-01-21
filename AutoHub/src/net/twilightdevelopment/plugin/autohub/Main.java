package net.twilightdevelopment.plugin.autohub;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	public static boolean isHubSet = false;

	public void onEnable() {
		
		
		Bukkit.getServer().getPluginManager().registerEvents(new Join(this), this);
		
		Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		
		getCommand("hub").setExecutor(new HubCommand(this));
		getCommand("sethub").setExecutor(new HubCommand(this));
		saveDefaultConfig();
		setHub(this);
		
		@SuppressWarnings("unused")
		API instance = new API(this);
		
	}
	
	public void onDisable() {
		saveDefaultConfig();
	}
	public static void setHub(JavaPlugin plugin) {
		
		if (plugin.getConfig().contains("hub.x")
				&& plugin.getConfig().contains("hub.y")
				&& plugin.getConfig().contains("hub.z")
				&& plugin.getConfig().contains("hub.world")) {
			isHubSet = true;
			
		}
		else {
			isHubSet = false;
		}
	}
}
