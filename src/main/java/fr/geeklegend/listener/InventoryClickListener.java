package fr.geeklegend.listener;

import fr.geeklegend.inventory.manager.InventoryManager;
import fr.geeklegend.Main;
import fr.geeklegend.game.GameManager;
import fr.geeklegend.game.GameState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener implements Listener
{
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event)
    {
        Player player = (Player) event.getWhoClicked();
        GameManager gameManager = Main.getPlugin().getGameManager();
        InventoryManager inventoryManager = Main.getPlugin().getInventoryManager();
        GameState gameState = gameManager.getGameState();

        if (gameState.equals(GameState.PREPARATION))
        {
            if (!inventoryManager.isEditKitContains(player))
            {
                event.setCancelled(true);
            }
        }
    }
}
