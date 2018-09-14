package de.Panomenal.Lobby.CMDs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.Panomenal.Lobby.Lobby;

public class LobbyCommand implements CommandExecutor{

	private List<SubCommand> cmds = new ArrayList<SubCommand>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("You have to be a Player");
			return false;
		}
		
		Player p = (Player)sender;
		if(p.hasPermission("Panomenal.*") | p.hasPermission("Panomenal.Lobby.use")){
			if(args.length == 0) {
				p.sendMessage("§8§m----------"+Lobby.getInstance().getPrefix()+"§8§m----------");
				for(SubCommand sc : cmds) {
					p.sendMessage("�8/�5Lobby "+aliases(sc)+ " " + sc.getUsage() + " �8- �7"+sc.getMessage());
				}
				p.sendMessage("§8§m--------------------------");
				return true;
			}
			
			SubCommand sc = getCommand(args[0]);
			
			if(sc == null) {
				p.sendMessage(Lobby.getInstance().getPrefix() + Lobby.getInstance().messageAPI.msg(p, "Lobby_noCMD"));
				return true;
			}
			
			Vector<String> a = new Vector<String>(Arrays.asList(args));
			a.remove(0);
			args = a.toArray(new String[a.size()]);
			
			sc.onCommand(p, args);
			return true;
		}else{
			//No Perms
		}
		return false;
	}
	
	private String aliases(SubCommand sc) {
		String fin = "";
		
		for(String a : sc.getAliases()) {
			fin += a + " | ";
		}
		
		return fin.substring(0, fin.lastIndexOf(" | "));
	}
	
	private SubCommand getCommand(String name) {
		for(SubCommand sc : cmds) {
			if(sc.getClass().getSimpleName().equalsIgnoreCase(name)) return sc;
			for(String alias : sc.getAliases()) if(alias.equalsIgnoreCase(name)) return sc;
		}
		return null;
	}


}