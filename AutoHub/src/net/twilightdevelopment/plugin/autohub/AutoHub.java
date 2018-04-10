package net.twilightdevelopment.plugin.autohub;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import net.twilightdevelopment.plugin.autohub.updater.UpdaterMain;

public class AutoHub extends JavaPlugin {

	public static boolean isHubSet = false;
	
	public static AutoHub instance;
	public void onEnable() {
		instance = this;
		
		Bukkit.getServer().getPluginManager().registerEvents(new Join(this), this);
		
		Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		
		getCommand("hub").setExecutor(new HubCommand(this));
		getCommand("sethub").setExecutor(new HubCommand(this));
		saveDefaultConfig();
		setHub();
		
		if (getConfig().getBoolean("update-checker"))
			Bukkit.getScheduler().runTaskAsynchronously(this, new UpdaterMain(this));
		
	}
	
	public void onDisable() {
		saveDefaultConfig();
	}
	public void setHub() {
		
		if (instance.getConfig().contains("hub.x")
				&& instance.getConfig().contains("hub.y")
				&& instance.getConfig().contains("hub.z")
				&& instance.getConfig().contains("hub.world")) {
			isHubSet = true;
			
		}
		else 
			isHubSet = false;
		
	}
	
	
	public static AutoHub getInstance() {
		return instance;
	}
		
		
	}
	
	
	
	
	
	
	

