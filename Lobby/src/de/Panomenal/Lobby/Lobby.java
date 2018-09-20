package de.Panomenal.Lobby;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import de.Panomenal.Lobby.CMDs.LobbyCommand;
import de.Panomenal.Lobby.Listeners.PlayerJoinListener;
import de.Panomenal.Lobby.Managers.MySQL_zusatz;
import de.blo0dr0gue.messageapi.MessageAPI;
import de.blo0dr0gue.mysqlapi.MySQLAPI;

public class Lobby extends JavaPlugin{
	
	//Variables
	private String prefix;
	private static Lobby m;
	public MySQLAPI mysql;
	public MySQL_zusatz mysql_z;
	public MessageAPI messageAPI;
	
	@Override
	public void onEnable() {
		mysql = new MySQLAPI();
		mysql_z = new MySQL_zusatz();
		messageAPI = new MessageAPI(true);
		m = this;
		prefix = mysql_z.getPrefix();
		Bukkit.getConsoleSender().sendMessage(getPrefix()+"Plugin wird gestartet.");
		mysql_z.con = mysql.connect("minecraft_server");
		implementsListener();
		getCommand("Lobby").setExecutor(new LobbyCommand());
	}
	
	/**
	 * Register all Listeners
	 */
	private void implementsListener() {
		getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
	}
	
	/**
	 * Returns the Prefix
	 * @return String prefix
	 */
	public String getPrefix() {
		return this.prefix;
	}
	
	@Override
	public void onDisable() {
		mysql.disconnect();
	}
	
	/**
	 * Returns the Instance of the Lobby Class
	 * @return Lobby m
	 */
	public static Lobby getInstance(){
		return m;
	}
	
}
