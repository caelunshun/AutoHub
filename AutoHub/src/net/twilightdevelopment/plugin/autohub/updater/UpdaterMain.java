package net.twilightdevelopment.plugin.autohub.updater;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import net.md_5.bungee.api.ChatColor;

public class UpdaterMain extends BukkitRunnable {
	
	InetAddress ip;
	int port = 63125;
	private final JavaPlugin plugin;
	
	public UpdaterMain (InetAddress ip, JavaPlugin plugin) {
		this.ip = ip;
		this.plugin = plugin;
	}
	
	
	public void run() {
		Socket s = getSocket(port);
		
		try {
			Scanner in = new Scanner(s.getInputStream());
			
			PrintWriter out = new PrintWriter(s.getOutputStream(), true);
			
			out.println("version autohub");
			String newVersion = null;
			
			
			newVersion = in.next();
			
			
			String currentVersion = plugin.getDescription().getVersion();
			ConsoleCommandSender console = Bukkit.getConsoleSender();
			if (!newVersion.equals(currentVersion)) console.sendMessage(ChatColor.AQUA
					+ "[AutoHub] "
					+ "A new version is available! " 
					+ "Download it at https://www.spigotmc.org/resources/extracommands.35102/");
			else {
				console.sendMessage("[AutoHub] Plugin is up to date.");
			}
			in.close();
		} catch (IOException e) {e.printStackTrace(); System.out.println(e.getMessage());}
		catch(Exception e) {e.printStackTrace(); System.out.println(e.getMessage());}
		  
			
		
	}
	
	private Socket getSocket(int port) {
		Socket s;
		try {
			s = new Socket(ip, port);
			return s;
		} catch (IOException e) {e.printStackTrace(); System.out.println(e.getMessage());}
		return null;
		
		
		
	}
	
}
