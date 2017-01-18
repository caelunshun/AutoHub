package net.twilightdevelopment.plugin.autohub;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	public void onEnable() {
		
		Bukkit.getServer().getPluginManager().registerEvents(new Join(this), this);
		
		Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		
		getCommand("hub").setExecutor(new HubCommand(this));
		getCommand("sethub").setExecutor(new HubCommand(this));
		saveConfig();
		saveDefaultConfig();
		
	}
	
	public void onDisable() {
		saveDefaultConfig();
	}
}
