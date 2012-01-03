package uk.co.oliwali.MultiHome;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MHPlayerListener extends PlayerListener {
	
	public void onPlayerMove(PlayerMoveEvent event) {
		if (distance(event.getFrom(), event.getTo()) < 0.1) return;
		
		//Clone warmups to stop concurrent mods
		List<Warmup> temp = new ArrayList<Warmup>();
		for (Warmup warmup : MultiHome.warmups) temp.add(warmup);
		
		for (Warmup warmup : temp) { 
			if (warmup.player == event.getPlayer()) {
				Util.sendMessage(event.getPlayer(), "&cMovement detected - home teleport cancelled!");
				warmup.timer.cancel();
				MultiHome.warmups.remove(warmup);
				return;
			}
		}
	}
	
	public static double distance(Location from, Location to) {
		return Math.sqrt(Math.pow(from.getX() - to.getX(), 2) + Math.pow(from.getY() - to.getY(), 2) + Math.pow(from.getZ() - to.getZ(), 2));
	} 
	
}
