package dev.lightwood.logger;

import java.util.ArrayList;

import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import dev.lightwood.logger.api.LogAPI;
import dev.lightwood.logger.database.ConnectionPoolFactory;
import dev.lightwood.logger.listeners.BlockBreakListener;
import dev.lightwood.logger.listeners.BlockPlaceListener;
import dev.lightwood.logger.listeners.ChatListener;
import dev.lightwood.logger.listeners.CommandListener;
import dev.lightwood.logger.listeners.PlayerDeathListener;
import dev.lightwood.logger.listeners.PlayerDropListener;
import dev.lightwood.logger.listeners.PlayerJoinListener;
import dev.lightwood.logger.listeners.PlayerKillListener;
import dev.lightwood.logger.listeners.PlayerLeaveListener;

public class WoodLogger extends JavaPlugin {

	private static Plugin $plugin;
	private static ConnectionPoolFactory $connectionPool;
	private static ArrayList<String> $commandLogsExcpt;

	@Override
	public void onEnable() {
		$plugin = (Plugin) this;
		$connectionPool = new ConnectionPoolFactory($plugin.getConfig().getString("MySQL.hostname"), $plugin.getConfig().getString("MySQL.database"), $plugin.getConfig().getString("MySQL.user"), $plugin.getConfig().getString("MySQL.password"));
		startCommandExceptionList();
		registerAllListeners();
	}

	public static Plugin getPlugin() {
		return $plugin;
	}

	public static ConnectionPoolFactory getConnectionFactory() {
		try {
			return $connectionPool;
		} catch (NullPointerException e) {
			$connectionPool = new ConnectionPoolFactory($plugin.getConfig().getString("MySQL.hostname"), $plugin.getConfig().getString("MySQL.database"), $plugin.getConfig().getString("MySQL.user"), $plugin.getConfig().getString("MySQL.password"));
			return $connectionPool;
		}
	}
	
	private void startCommandExceptionList() {
		$commandLogsExcpt = new ArrayList<String>();
		if($plugin.getConfig().getStringList("CommandLogsException") == null || $plugin.getConfig().getStringList("CommandLogsException").isEmpty()) return;
		$commandLogsExcpt.addAll($plugin.getConfig().getStringList("CommandLogsException"));
	}
	
	public static ArrayList<String> getCommandExceptions(){
		return $commandLogsExcpt;
	}
	
	private void registerAllListeners() {
		if(getConfig().getBoolean("SaveLogs.commands")) registerListener(new CommandListener());
		if(getConfig().getBoolean("SaveLogs.breakBlock")) registerListener(new BlockBreakListener());
		if(getConfig().getBoolean("SaveLogs.placeBlock")) registerListener(new BlockPlaceListener());
		if(getConfig().getBoolean("SaveLogs.chat")) registerListener(new ChatListener());
		if(getConfig().getBoolean("SaveLogs.kill")) registerListener(new PlayerKillListener());
		if(getConfig().getBoolean("SaveLogs.death")) registerListener(new PlayerDeathListener());
		if(getConfig().getBoolean("SaveLogs.drop")) registerListener(new PlayerDropListener());
		if(getConfig().getBoolean("SaveLogs.leave")) registerListener(new PlayerLeaveListener());
		if(getConfig().getBoolean("SaveLogs.join")) registerListener(new PlayerJoinListener());
	}
	
	private void registerListener(Listener l) {
		getServer().getPluginManager().registerEvents(l, this);
	}
	
	public static LogAPI getAPI(Plugin plugin) {
		return new LogAPI(plugin);
	}
	
}
