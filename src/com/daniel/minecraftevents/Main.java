package com.daniel.minecraftevents;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	@Override
	public void onEnable() {
		// when the plugin is ran for first time it will run this block of code.

		System.out.println("PLUGIN ENABLED!");
	}

	@Override
	public void onDisable() {
		System.out.println("PLUGIN DISABLED!");
	}
}
