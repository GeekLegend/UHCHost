package fr.geeklegend.listener;

import fr.geeklegend.Main;
import fr.geeklegend.game.GameState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class FoodLevelChangeListener implements Listener
{
    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event)
    {
        GameState gameState = Main.getPlugin().getGameManager().getGameState();

        if (gameState.equals(GameState.PREPARATION))
        {
            event.setCancelled(true);
        }
    }
}
