package fr.geeklegend.uhchost.listener;

import fr.geeklegend.uhchost.Main;
import fr.geeklegend.uhchost.game.GameManager;
import fr.geeklegend.uhchost.player.PlayerDataManager;
import fr.geeklegend.uhchost.scoreboard.ScoreboardManager;
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
