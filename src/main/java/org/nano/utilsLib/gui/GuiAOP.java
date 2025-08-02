package org.nano.utilsLib.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.nano.utilsLib.item.factory.ItemContainerFactory;

import net.kyori.adventure.sound.Sound;

public class GuiAOP implements Listener {
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		InventoryHolder holder = e.getInventory().getHolder();
		if ( !(holder instanceof Gui gui )) return;

		e.setCancelled(true);
		if (e.getCurrentItem() == null) {
			return;
		}

		Player clicker = (Player)e.getWhoClicked();
		ItemStack item = e.getCurrentItem();

		Sound sound = ItemContainerFactory.INSTANCE.register(item).getSound();
		if (sound != null) {
			clicker.playSound(sound);
		}

		gui.handleClick(e);
	}
}
