package de.Panomenal.Lobby;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import de.Panomenal.Lobby.CMDs.LobbyCommand;
import de.Panomenal.Lobby.Managers.MySQL_zusatz;
import de.blo0dr0gue.messageapi.MessageAPI;
import de.blo0dr0gue.mysqlapi.MySQLAPI;

public class Lobby extends JavaPlugin{
	
	//Variables
	private String prefix;
	private static Lobby m;
	private File file = new File("plugins/Lobby", "config.yml");
	private FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
	public MySQLAPI mysql;
	public MySQL_zusatz mysql_z;
	public MessageAPI messageAPI;
	
	@Override
	public void onEnable() {
		mysql = new MySQLAPI();
		mysql_z = new MySQL_zusatz();
		messageAPI = new MessageAPI();
		m = this;
		createConfig();
		setPrefix(cfg.getString("Lobby.Prefix"));
		Bukkit.getConsoleSender().sendMessage(getPrefix()+"Plugin wird gestartet.");
		mysql_z.con = mysql.connect("minecraft_server");
		implementsListener();
		getCommand("Lobby").setExecutor(new LobbyCommand());
	}
	
	private void implementsListener() {
		
	}
	
	@Override
	public void onDisable() {
		mysql.disconnect();
	}
	
	
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
	private void createConfig(){
		if(file.exists()){
			System.out.println("Die Config Datei existiert bereits und die Daten werden geladen!");
		}else{
			System.out.println("Die Config Datei wird erstellt. Bitte warten ....");
			
			cfg.set("Lobby.Prefix", "§4§l[§5LobbySystem§4§l] §7");
			
			try {
				cfg.save(file);
				System.out.println("Die Config Datei wurde erfolgreich erstellt.");
			} catch (IOException e) {
				System.out.println("Es ist ein Fehler beim erstellen der Config Datei aufgetreten!");
				e.printStackTrace();
			}
		}
	}
	public static Lobby getInstance(){
		return m;
	}
	
}
