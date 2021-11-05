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
import org.bukkit.command.*;

public class Main extends JavaPlugin implements Listener {

	@Override
	public void onEnable() {
		// when the plugin is ran for first time it will run this block of code.

		System.out.println("EVENTS PLUGIN ENABLED!");

		// referencing the Main class for config.yml
		this.getConfig().options().copyDefaults(); // getting options and copying the default config here
		saveDefaultConfig();

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

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		String cmdName = cmd.getName();
		Player player = (Player) sender;

		switch (cmdName) {
		case "config":
			this.getConfigData(player, true);
			break;

		case "edit_config":
			this.editConfigData();
			break;
		default:
			break;
		}
		return false;
	}

	public void getConfigData(Player player, boolean isSendMessage) {
		String word = this.getConfig().getString("Word");
		int number = this.getConfig().getInt("Number");

		if (isSendMessage) {
			player.sendMessage(ChatColor.GRAY + "The word is " + ChatColor.GREEN + word + ChatColor.GRAY
					+ " And the number " + ChatColor.GREEN + number);
		}
	}

	public void editConfigData() {
		this.getConfig().set("Word", "Apple");
	}
}
