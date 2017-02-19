package net.twilightdevelopment.plugin.autohub.updater;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import net.md_5.bungee.api.ChatColor;

public class UpdaterMain extends Thread {
	
	InetAddress ip;
	int port = 63125;
	private final JavaPlugin plugin;
	
	public UpdaterMain (InetAddress ip, JavaPlugin plugin) {
		this.ip = ip;
		this.plugin = plugin;
	}
	
	
	public void run() {
		while(true) {
		
		try {
			Socket s = getSocket(port);
			if (s != null) {
			Scanner in = new Scanner(s.getInputStream());
			
			PrintWriter out = new PrintWriter(s.getOutputStream(), true);
			
			out.println("version autohub");
			String newVersion = null;
			
			
			newVersion = in.next();
			
			
			String currentVersion = plugin.getDescription().getVersion();
			ConsoleCommandSender console = Bukkit.getConsoleSender();
			if (!newVersion.equals(currentVersion)) { console.sendMessage(ChatColor.AQUA
					+ "[AutoHub] "
					+ "A new version is available! " 
					+ "Download it at https://www.spigotmc.org/resources/autohub.34966/");
			}
			else {
				console.sendMessage("[AutoHub] Plugin is up to date.");
			}
			
			in.close();
			}
		} catch (IOException e) {}
		catch(Exception e) {}
		  
			try {
				Thread.sleep(1000 * (60 * 60));
			} catch (InterruptedException e) {}
		}
	}
	
	private Socket getSocket(int port) {
		Socket s;
		try {
			s = new Socket(ip, port);
			return s;
		} catch (Exception e) {}
		return null;
		
		
		
	}
	
}
