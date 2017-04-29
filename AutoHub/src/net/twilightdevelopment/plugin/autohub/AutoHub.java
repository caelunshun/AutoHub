package net.twilightdevelopment.plugin.autohub;

import java.net.InetAddress;
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
		setHub(this);
		
		try {
			new UpdaterMain(InetAddress.getByName("bbabytest.dynu.com"), this).start();
		} catch(Exception e) {}
		
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
		else 
			isHubSet = false;
		
	}
	
	
	public static AutoHub getInstance() {
		return instance;
	}
	
	
		
		
	}
	
	
	
	
	
	
	

