package dev.lightwood.logger.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import dev.lightwood.logger.logs.Log;

public class ChatListener implements Listener {
	
	@EventHandler (priority = EventPriority.MONITOR)
	public void onChat(AsyncPlayerChatEvent e) {
		if(e.getPlayer().hasPermission("woodlogger.bypass")) return;
		Player p = e.getPlayer();
		Log chatLog = new Log(p.getName(), p.getAddress().getAddress().getHostAddress(), "chat", e.getMessage());
		chatLog.save();
	}

}
