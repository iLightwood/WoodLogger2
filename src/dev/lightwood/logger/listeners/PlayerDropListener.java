package dev.lightwood.logger.listeners;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import dev.lightwood.logger.logs.Log;

public class PlayerDropListener implements Listener {
	
	@EventHandler (priority = EventPriority.MONITOR)
	public void onPlayerDrop(PlayerDropItemEvent e) {
		if(e.getPlayer().hasPermission("woodlogger.bypass")) return;
		Location ploc = e.getPlayer().getLocation();
		String loc = ploc.getWorld().getName()+"@X:"+String.valueOf(ploc.getX())+"@Y:"+String.valueOf(ploc.getY())+"@Z:"+String.valueOf(ploc.getZ());
		Log dropLog = new Log(e.getPlayer().getName(), e.getPlayer().getAddress().getAddress().getHostAddress(), "drop", "dropped x"+String.valueOf(e.getItemDrop().getItemStack().getAmount())+" "+e.getItemDrop().getItemStack().getType().toString()+" at "+loc);
		dropLog.save();
	}

}
