package dev.lightwood.logger.api;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import dev.lightwood.logger.WoodLogger;
import dev.lightwood.logger.listeners.own.LogSaveEvent;
 
public class LogAPI {
	
	private Plugin plugin;
	private String user;
	private String address;
	private String type;
	private String message;
	
	public LogAPI(Plugin plugin) {
		this.plugin = plugin;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public void setUserAddress(String address) {
		this.address = address;
	}
	
	public void setLogType(String type) {
		this.type = type;
	}

	public void setLogMessage(String message) {
		this.message = message;
	}
	
	public void saveLog() {
		LogSaveEvent event = new LogSaveEvent(user, address, type, message);
		Bukkit.getServer().getPluginManager().callEvent(event);
		if(event.isCancelled()) return;
		new BukkitRunnable() {
			public void run() {
				Connection conn = null;
				try {
					conn = WoodLogger.getConnectionFactory().getConnection();
					PreparedStatement insertPs = conn.prepareStatement("INSERT INTO logs(dateInMillis, user, cAddress, type, logMessage) VALUES(?,?,?,?,?);");
					insertPs.setString(1, String.valueOf(System.currentTimeMillis()));
					insertPs.setString(2, user);
					insertPs.setString(3, address);
					insertPs.setString(4, type);
					insertPs.setString(5, message);
					insertPs.executeUpdate();
				} catch (Exception ex) {
					ex.printStackTrace(System.out);
				} finally {
					if(conn != null) {
						try {
							conn.close();
						} catch (SQLException ex) {
							ex.printStackTrace();
						}
					}
				}
			}
		}.runTaskAsynchronously(plugin);
	}
	
}
