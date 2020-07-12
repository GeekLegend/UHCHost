package fr.geeklegend.uhchost.inventory;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public interface IInventory
{
    Inventory create(Player player);

    Inventory create(Player player, boolean bool);

    void onInventoryClick(InventoryClickEvent event);

    void update();
}
