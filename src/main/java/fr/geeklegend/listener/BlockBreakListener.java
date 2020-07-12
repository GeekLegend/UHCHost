package fr.geeklegend.listener;

import fr.geeklegend.Main;
import fr.geeklegend.game.GameState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener
{
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event)
    {
        GameState gameState = Main.getPlugin().getGameManager().getGameState();

        if (gameState.equals(GameState.PREPARATION))
        {
            event.setCancelled(true);
        }
    }
}
