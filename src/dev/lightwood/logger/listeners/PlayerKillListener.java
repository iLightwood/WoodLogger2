package dev.lightwood.logger.listeners;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import dev.lightwood.logger.logs.Log;

public class PlayerKillListener implements Listener {

	@EventHandler (priority = EventPriority.MONITOR)
	public void onPlayerDeath(EntityDeathEvent e) {
		if(e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if(p.getKiller() instanceof Player) {
				Player killer = (Player) p.getKiller();
				if(killer.hasPermission("woodlogger.bypass")) return;
				Location kloc = killer.getLocation();
				String klloc = kloc.getWorld().getName()+"@X:"+String.valueOf(kloc.getX())+"@Y:"+String.valueOf(kloc.getY())+"@Z:"+String.valueOf(kloc.getZ());
				Log killLog = new Log(p.getName(), p.getAddress().getAddress().getHostAddress(), "kill", "killed "+p.getName()+" at "+klloc);
				killLog.save();
			}
		}
	}

}
