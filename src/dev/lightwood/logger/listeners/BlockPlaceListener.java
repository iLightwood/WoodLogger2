package dev.lightwood.logger.listeners;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import dev.lightwood.logger.logs.Log;

public class BlockPlaceListener implements Listener {
	
	@EventHandler (priority = EventPriority.MONITOR)
	public void onBlockPlace(BlockPlaceEvent e) {
		if(e.getPlayer().hasPermission("woodlogger.bypass")) return;
		Player p = e.getPlayer();
		Location ploc = e.getPlayer().getLocation();
		String loc = ploc.getWorld().getName()+"@X:"+String.valueOf(ploc.getX())+"@Y:"+String.valueOf(ploc.getY())+"@Z:"+String.valueOf(ploc.getZ());
		Log placeLog = new Log(p.getName(), p.getAddress().getAddress().getHostAddress(), "placeBlock", "placed "+e.getBlock().getType().toString()+" at "+loc);
		placeLog.save();
	}

}
