package dev.lightwood.logger.listeners.own;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class LogSaveEvent extends Event implements Cancellable {
	
	private static final HandlerList handlers = new HandlerList();
	private boolean isCancelled;
	private String user;
	private String address;
	private String type;
	private String message;
	
	public LogSaveEvent(String user, String address, String type, String message) {
		this.user = user;
		this.address = address;
		this.type = type;
		this.message = message;
	}
	
	public String getUser() {
		return this.user;
	}
	
	public String getConnectionAddress() {
		return this.address;
	}
	
	public String getLogType() {
		return this.type;
	}
	
	public String getLogMessage() {
		return this.message;
	}

	public boolean isCancelled() {
		return this.isCancelled;
	}

	public void setCancelled(boolean cancelled) {
		this.isCancelled = cancelled;
	}

	public HandlerList getHandlers() {
		return handlers;
	}

}
