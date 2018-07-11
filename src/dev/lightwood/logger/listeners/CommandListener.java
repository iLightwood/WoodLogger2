package dev.lightwood.logger.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import dev.lightwood.logger.WoodLogger;
import dev.lightwood.logger.logs.Log;

public class CommandListener implements Listener {
	
	@EventHandler (priority = EventPriority.MONITOR)
	public void onCommand(PlayerCommandPreprocessEvent e) {
		if(e.getPlayer().hasPermission("woodlogger.bypass")) return;
		String command = e.getMessage().toLowerCase().replace("/", "");
		if(command.contains(" ")) {
			String[] split = command.split(" ");
			command = split[0];
		}
		if(WoodLogger.getCommandExceptions().contains(command)) return;
		Player p = e.getPlayer();
		Log commandLog = new Log(p.getName(), p.getAddress().getAddress().getHostAddress(), "command", "inserted the command "+e.getMessage());
		commandLog.save();
	}

}
