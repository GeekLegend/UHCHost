package fr.geeklegend.listener;

import fr.geeklegend.player.PlayerDataManager;
import fr.geeklegend.scoreboard.ScoreboardManager;
import fr.geeklegend.Main;
import fr.geeklegend.game.GameManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener
{
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event)
    {
        Player player = event.getPlayer();
        ScoreboardManager scoreboardManager = Main.getPlugin().getScoreboardManager();
        GameManager gameManager = Main.getPlugin().getGameManager();
        PlayerDataManager playerDataManager = gameManager.getPlayerDataManager();

        scoreboardManager.onLogout(player);

        gameManager.cleanUp(player);

        playerDataManager.remove(player);

        event.setQuitMessage(null);
    }
}
