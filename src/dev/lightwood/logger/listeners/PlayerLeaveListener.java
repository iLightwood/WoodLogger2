package dev.lightwood.logger.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import dev.lightwood.logger.logs.Log;

public class PlayerLeaveListener implements Listener {
	
	@EventHandler (priority = EventPriority.MONITOR)
	public void onPlayerQuit(PlayerQuitEvent e) {
		if(e.getPlayer().hasPermission("woodlogger.bypass")) return;
		Log quitLog = new Log(e.getPlayer().getName(), e.getPlayer().getAddress().getAddress().getHostAddress(), "leave", "left the server");
		quitLog.save();
	}

}
