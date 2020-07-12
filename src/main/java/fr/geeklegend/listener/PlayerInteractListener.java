package fr.geeklegend.listener;

import fr.geeklegend.inventory.ScenarioViewInventory;
import fr.geeklegend.inventory.SettingInventory;
import fr.geeklegend.Main;
import fr.geeklegend.game.GameManager;
import fr.geeklegend.game.GameState;
import fr.geeklegend.util.Constant;
import fr.geeklegend.util.Util;
import fr.geeklegend.world.MapGenerator;
import fr.geeklegend.world.WorldManager;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class PlayerInteractListener implements Listener
{
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event)
    {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        Block block = event.getClickedBlock();
        Action action = event.getAction();
        GameManager gameManager = Main.getPlugin().getGameManager();
        GameState gameState = gameManager.getGameState();

        if (!gameState.equals(GameState.GAME))
        {
            if (action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK))
            {
                if (item != null)
                {
                    if (item.equals(Constant.JOIN_ITEM_HOST_SETTINGS_ITEM))
                    {
                        player.openInventory(new SettingInventory().create(player));
                    } else if (item.equals(Constant.JOIN_ITEM_HOST_START_ITEM))
                    {
                        //TODO: generate world
                    } else if (item.equals(Constant.JOIN_ITEM_SCENARIOS_ITEM))
                    {
                        player.openInventory(new ScenarioViewInventory().create(player));
                    }
                }
            }

            if (action.equals(Action.RIGHT_CLICK_BLOCK))
            {
                if (block != null)
                {
                    int random = new Random().nextInt(15);

                    if (block.getData() != random)
                    {
                        block.setData((byte) Util.getRandomColor());
                    }
                }
            }

        }
    }
}
