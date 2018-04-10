package net.twilightdevelopment.plugin.autohub.updater;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class UpdaterMain implements Runnable {
	private JavaPlugin plugin;
	private String currentVersion;

	private static final String API_URL = "https://api.spigotmc.org/legacy/general.php?resource=";
	private static final String PREFIX = "[AutoHub] ";
	private static final String RESOURCE_ID = "34966";

	public UpdaterMain(JavaPlugin plugin) {
		this.plugin = plugin;
		this.currentVersion = this.plugin.getDescription().getVersion();
	}

	public void run() {
		boolean newVersionFound = false;
		while (!newVersionFound) {
			HttpURLConnection con = null;
			BufferedReader reader = null;
			try {
				URL url = new URL(API_URL + RESOURCE_ID);
				con = (HttpURLConnection) url.openConnection();
				con.setRequestMethod("GET");
				con.setReadTimeout(20 * 1000);

				con.connect();
				reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String latestVersion = reader.readLine();

				if (latestVersion != null && !currentVersion.equals(latestVersion)) {
					Bukkit.getConsoleSender().sendMessage(
							PREFIX + "A new version is available! Please download it from the plugin resource page; "
									+ "you will receive no support regarding old versions of the plugin.");
					newVersionFound = true;
					
					// Check every hour
					Thread.sleep(1000 * 60 * 60); 
				}
			} catch (Exception e) {
				if (con != null)
					con.disconnect();
			}
		}
	}
}
