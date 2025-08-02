package org.nano.utilsLib.gui;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryHolder;

public interface Gui extends InventoryHolder {
	void handleClick(InventoryClickEvent e);
	void mold();
	void internal();
}
