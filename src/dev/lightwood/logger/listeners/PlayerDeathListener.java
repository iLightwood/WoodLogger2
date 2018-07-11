package dev.lightwood.logger.listeners;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import dev.lightwood.logger.logs.Log;

public class PlayerDeathListener implements Listener {
	
	@EventHandler (priority = EventPriority.MONITOR)
	public void onPlayerDeath(EntityDeathEvent e) {
		if(e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if(p.hasPermission("woodlogger.bypass")) return;
			Location ploc = p.getLocation();
			String loc = ploc.getWorld().getName()+"@X:"+String.valueOf(ploc.getX())+"@Y:"+String.valueOf(ploc.getY())+"@Z:"+String.valueOf(ploc.getZ());
			Log deathLog = new Log(p.getName(), p.getAddress().getAddress().getHostAddress(), "death", "died at "+loc);
			deathLog.save();
		}
	}

}
