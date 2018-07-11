package dev.lightwood.logger.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import dev.lightwood.logger.logs.Log;

public class PlayerJoinListener implements Listener {
	
	@EventHandler (priority = EventPriority.MONITOR)
	public void onPlayerJoin(PlayerJoinEvent e) {
		if(e.getPlayer().hasPermission("woodlogger.bypass")) return;
		Log joinLog = new Log(e.getPlayer().getName(), e.getPlayer().getAddress().getAddress().getHostAddress(), "join", "joined the server");
		joinLog.save();
	}
	
}
