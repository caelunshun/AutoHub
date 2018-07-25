package net.twilightdevelopment.plugin.autohub;

import com.google.common.collect.ImmutableSet;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class AutoHubCommand implements CommandExecutor {
  private static final String INFO =
      ChatColor.GRAY
          + "--------"
          + ChatColor.AQUA
          + ChatColor.BOLD
          + "AutoHub"
          + ChatColor.GRAY
          + "--------\n"
          + ChatColor.AQUA
          + "Version: "
          + ChatColor.GRAY
          + "{version}";

  private static final Set<String> COMMANDS = ImmutableSet.of("reload", "info");

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if ((args.length == 0 || (args.length >= 1 && args[0].equalsIgnoreCase("info")))
        && (sender instanceof ConsoleCommandSender || sender.hasPermission("autohub.info"))) {
      sender.sendMessage(
          INFO.replaceAll("\\{version}", AutoHub.getInstance().getDescription().getVersion()));
      return true;
    }

    if (args.length >= 1
        && args[0].equalsIgnoreCase("reload")
        && (sender instanceof ConsoleCommandSender || sender.hasPermission("autohub.reload"))) {
      AutoHub.getInstance().reloadConfig();
      sender.sendMessage(ChatColor.GREEN + "Plugin reloaded successfully.");
    }
    return true;
  }

  public List<String> tabComplete(CommandSender sender, Command cmd, String label, String[] args) {
    if (args.length == 1) {
      List<String> result = new ArrayList<>();
      StringUtil.copyPartialMatches(args[0], COMMANDS, result);
      Collections.sort(result);
      return result;
    }
    return Collections.emptyList();
  }
}
