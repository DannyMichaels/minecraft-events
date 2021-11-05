package com.daniel.minecraftevents;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

// events
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;

// entities
import org.bukkit.entity.Player;
import org.bukkit.entity.Egg;

public class Main extends JavaPlugin implements Listener {

	@Override
	public void onEnable() {
		// when the plugin is ran for first time it will run this block of code.

		System.out.println("EVENTS PLUGIN ENABLED!");

		// https://hub.spigotmc.org/javadocs/spigot/org/bukkit/Bukkit.html#getPluginManager()
		Bukkit.getPluginManager().registerEvents(this, this); // to make events work. args: (listener, plugin)
	}

	@EventHandler
	public void onMove(PlayerMoveEvent event) { // triggered when player moves
		Player player = event.getPlayer();

		if (!player.hasPermission("minecraftevents.allowmove")) {
			event.setCancelled(true); // cancels event, no player can move
			player.sendMessage("Movement blocked!, you don't have perms to move");
		}
	}

	@EventHandler
	public void onThrow(PlayerEggThrowEvent event) {
		Player player = event.getPlayer();
		Egg egg = event.getEgg();
		player.sendMessage(ChatColor.RED + "Egg thrown! " + egg.getEntityId());
	}
}
