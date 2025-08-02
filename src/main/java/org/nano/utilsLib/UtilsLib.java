package org.nano.utilsLib;

import org.bukkit.plugin.java.JavaPlugin;

public final class UtilsLib extends JavaPlugin {

	public static JavaPlugin getPlugin() {
		return UtilsLib.getProvidingPlugin(UtilsLib.class);
	}

	@Override
	public void onEnable() {
		// Plugin startup logic

	}

	@Override
	public void onDisable() {
		// Plugin shutdown logic
	}
}
