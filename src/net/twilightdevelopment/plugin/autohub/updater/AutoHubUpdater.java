package net.twilightdevelopment.plugin.autohub.updater;

import net.md_5.bungee.api.ChatColor;
import net.twilightdevelopment.plugin.autohub.AutoHub;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class AutoHubUpdater implements Runnable {
  private JavaPlugin plugin;
  private String currentVersion;

  private static final String API_URL = "https://api.spigotmc.org/legacy/update.php?resource=";
  private static final String PREFIX = "[AutoHub] ";
  private static final String RESOURCE_ID = "34966";

  public AutoHubUpdater(JavaPlugin plugin) {
    this.plugin = plugin;
    this.currentVersion = this.plugin.getDescription().getVersion();
  }

  public void run() {
    if (AutoHub.newVersionFound) return;

    HttpsURLConnection con = null;
    try {
      URL url = new URL(API_URL + RESOURCE_ID);
      con = (HttpsURLConnection) url.openConnection();
      con.setRequestMethod("GET");
      con.setReadTimeout(20 * 1000);

      con.connect();
      try (BufferedReader reader =
          new BufferedReader(new InputStreamReader(con.getInputStream()))) {
        String latestVersion = reader.readLine();
        if (latestVersion != null && !currentVersion.equals(latestVersion)) {
          Bukkit.getConsoleSender()
              .sendMessage(
                  String.format(
                      PREFIX
                          + ChatColor.AQUA
                          + "A new version (v%s) is available! Please download it from the plugin resource page; "
                          + "you will receive no support regarding old versions of the plugin.",
                      latestVersion));
          AutoHub.newVersionFound = true;
        }
        con.disconnect();
      }
    } catch (Exception e) {
      if (con != null) con.disconnect();
    }
  }
}
