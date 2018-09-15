package de.Panomenal.Lobby.Managers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class MySQL_zusatz {

	public Connection con;

	/**
	 * Check if the Player connects for the first time
	 * 
	 * @param Player p
	 * @return boolean
	 */
	public boolean isFirstJoin(Player p) {
		boolean back = false;
		String uuid = p.getUniqueId().toString();

		try {
			Statement st = con.createStatement();
			ResultSet rs = null;
			rs = st.executeQuery("SELECT * FROM player_settings WHERE uuid='" + uuid + "'");
			while (rs.next()) {
				if (rs != null) {
					back = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return back;
	}

	/**
	 * returns the prefix of the Lobby-System
	 * 
	 * @return
	 */
	public String getPrefix() {
		String prefix = null;
		try {
			Statement st = con.createStatement();
			ResultSet rs = null;
			rs = st.executeQuery("SELECT prefix FROM lobby_settings WHERE ID='1'");
			while (rs.next()) {
				prefix = rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prefix;
	}

	/**
	 * Update the Prefix of the Lobby-System
	 * 
	 * @param prefix
	 */
	public void updatePrefix(String prefix) {
		try {
			Statement st = con.createStatement();
			st.executeUpdate("UPDATE lobby_settings SET prefix='" + prefix + "' WHERE ID='1'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Returns the Location of the Lobby-Spawn
	 * 
	 * @return Location Lobby-Spawn
	 */
	public Location getLobbySpawn() {
		double x = 0;
		double y = 0;
		double z = 0;
		double yaw = 0;
		double pitch = 0;
		Location loc = null;

		try {
			Statement st = con.createStatement();
			ResultSet rs = null;
			rs = st.executeQuery(
					"SELECT lobbyspawnx, lobbyspawny, lobbyspawnz, lobbyspawnyaw, lobbyspawnpitch FROM lobby_settings WHERE uuid='1'");
			while (rs.next()) {
				x = rs.getDouble(1);
				y = rs.getDouble(2);
				z = rs.getDouble(3);
				yaw = rs.getDouble(4);
				pitch = rs.getDouble(5);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		loc = new Location(Bukkit.getWorld("lobby"), x, y, z);
		loc.setPitch((float) pitch);
		loc.setYaw((float) yaw);
		return loc;
	}

	/**
	 * Updates the LobbySpawn
	 * @param x
	 * @param y
	 * @param z
	 * @param yaw
	 * @param pitch
	 */
	public void updateLobbySpawn(double x, double y, double z, double yaw, double pitch) {
		try {
			Statement st = con.createStatement();
			st.executeUpdate(
					"UPDATE lobby_settings SET lobbyspawnx='" + x + "', lobbyspawny='" + y + "'" + ", lobbyspawnz='" + z
							+ "', lobbyspawnyaw='" + yaw + "', lobbyspawnpitch='" + pitch + "' " + "WHERE ID='1'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
