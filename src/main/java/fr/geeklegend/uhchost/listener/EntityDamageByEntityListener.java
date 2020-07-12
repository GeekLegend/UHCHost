package fr.geeklegend.uhchost.listener;

import fr.geeklegend.uhchost.Main;
import fr.geeklegend.uhchost.game.GameManager;
import fr.geeklegend.uhchost.game.GameState;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageByEntityListener implements Listener
{
    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event)
    {
        Entity damaged = event.getEntity();
        Entity damager = event.getDamager();
        GameManager gameManager = Main.getPlugin().getGameManager();
        GameState gameState = gameManager.getGameState();

        if (damaged != null && damager != null)
        {
            if (damager instanceof Player)
            {
                if (gameState.equals(GameState.PREPARATION))
                {
                }
            }
        }
    }
}
