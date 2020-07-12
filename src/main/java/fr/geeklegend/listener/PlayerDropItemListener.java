package fr.geeklegend.listener;

import fr.geeklegend.Main;
import fr.geeklegend.game.GameState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class PlayerDropItemListener implements Listener
{
    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event)
    {
        GameState gameState = Main.getPlugin().getGameManager().getGameState();

        if (gameState.equals(GameState.PREPARATION))
        {
            event.setCancelled(true);
        }
    }
}
