package de.Panomenal.Lobby.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import de.Panomenal.Lobby.Lobby;

public class PlayerLoginListener implements Listener{

	@EventHandler
	public void onPlayerLogin(PlayerLoginEvent e) {
		Player p = e.getPlayer();
		String uuid = p.getUniqueId().toString();
		
		if(Lobby.getInstance().mysql_z.isFirstJoin(p)) {
			//First Login
			
			
			
		}else {
			
		}
		
	}
	
}
