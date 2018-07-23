package net.twilightdevelopment.plugin.autohub;

import net.twilightdevelopment.plugin.autohub.updater.AutoHubUpdater;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class AutoHub extends JavaPlugin {

  public static boolean isHubSet = false;
  public static boolean newVersionFound = false;
  public static AutoHub instance;

  private static final long UPDATE_INTERVAL = 20 * 60 * 60;

  public void onEnable() {
    instance = this;

    Bukkit.getServer().getPluginManager().registerEvents(new Join(this), this);

    Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

    getCommand("hub").setExecutor(new HubCommand(this));
    getCommand("sethub").setExecutor(new SetHubCommand(this));
    saveDefaultConfig();
    setHub();

    if (getConfig().getBoolean("update-checker"))
      Bukkit.getScheduler().runTaskTimerAsynchronously(this, new AutoHubUpdater(this), 0,
              UPDATE_INTERVAL);
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
    } else isHubSet = false;
  }

  public static AutoHub getInstance() {
    return instance;
  }
}
