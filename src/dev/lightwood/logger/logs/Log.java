package dev.lightwood.logger.logs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import dev.lightwood.logger.WoodLogger;
import dev.lightwood.logger.listeners.own.LogSaveEvent;

public class Log {

	private String user;
	private String address;
	private String message;
	private String type;

	public Log(String user, String userAddress, String logType, String logMessage) {
		this.user = user;
		this.type = logType;
		this.address = userAddress;
		this.message = logMessage;
	}

	public void save() {
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
		}.runTaskAsynchronously(WoodLogger.getPlugin());	
	}
	
}
