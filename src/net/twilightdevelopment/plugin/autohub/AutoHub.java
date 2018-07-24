package net.twilightdevelopment.plugin.autohub;

import net.twilightdevelopment.plugin.autohub.updater.AutoHubUpdater;
import org.bstats.bukkit.MetricsLite;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class AutoHub extends JavaPlugin {

  public static boolean isHubSet = false;
  public static boolean newVersionFound = false;
  public static AutoHub instance;

  private static final long UPDATE_INTERVAL = 20 * 60 * 60;

  public void onEnable() {
    instance = this;

    MetricsLite metrics = new MetricsLite(this);

    Bukkit.getServer().getPluginManager().registerEvents(new Join(this), this);

    Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

    PluginCommand cmdHub = getCommand("hub");
    PluginCommand cmdSetHub = getCommand("sethub");

    HubCommand cmdHubImpl = new HubCommand(this);
    SetHubCommand cmdSetHubImpl = new SetHubCommand(this);
    cmdHub.setExecutor(cmdHubImpl);
    cmdSetHub.setExecutor(cmdSetHubImpl);

    cmdHub.setTabCompleter(cmdHubImpl::tabComplete);
    cmdSetHub.setTabCompleter(cmdSetHubImpl::tabComplete);

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
