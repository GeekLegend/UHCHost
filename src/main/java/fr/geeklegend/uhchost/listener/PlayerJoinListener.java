package fr.geeklegend.uhchost.listener;

import fr.geeklegend.uhchost.Main;
import fr.geeklegend.uhchost.game.GameManager;
import fr.geeklegend.uhchost.game.GameState;
import fr.geeklegend.uhchost.player.PlayerDataManager;
import fr.geeklegend.uhchost.scoreboard.ScoreboardManager;
import fr.geeklegend.uhchost.server.ServerSettings;
import fr.geeklegend.uhchost.util.Constant;
import fr.geeklegend.uhchost.util.ItemBuilder;
import fr.geeklegend.uhchost.util.Title;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener
{
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        Player player = event.getPlayer();
        ScoreboardManager scoreboardManager = Main.getPlugin().getScoreboardManager();
        GameManager gameManager = Main.getPlugin().getGameManager();
        GameState gameState = gameManager.getGameState();

        scoreboardManager.onLogin(player);

        if (!gameState.equals(GameState.GAME))
        {
            PlayerDataManager playerDataManager = gameManager.getPlayerDataManager();

            playerDataManager.create(player);

            gameManager.add(player);

            setup(player);

            for (Player players : gameManager.getPlayers())
            {
                Title.sendActionBar(players, Constant.JOIN_MESSAGE.replace("%playername%", player.getName()).replace("%online%", String.valueOf(gameManager.getPlayers().size())).replace("%maxonline%", String.valueOf(Bukkit.getMaxPlayers())));
            }
        } else
        {

        }

        event.setJoinMessage(null);
    }

    private void setup(Player player)
    {
        ServerSettings serverSettings = Main.getPlugin().getServerSettings();

        player.setHealth(20.0);
        player.setFoodLevel(20);
        player.setLevel(0);
        player.setGameMode(Constant.JOIN_GAMEMODE);
        player.teleport(Constant.JOIN_LOCATION);
        player.getInventory().clear();
        player.getInventory().setHelmet(new ItemBuilder(Material.AIR).toItemStack());
        player.getInventory().setChestplate(new ItemBuilder(Material.AIR).toItemStack());
        player.getInventory().setLeggings(new ItemBuilder(Material.AIR).toItemStack());
        player.getInventory().setBoots(new ItemBuilder(Material.AIR).toItemStack());

        if (serverSettings.isHost(player.getName()))
        {
            player.getInventory().setItem(Constant.JOIN_ITEM_HOST_SETTINGS_SLOT, Constant.JOIN_ITEM_HOST_SETTINGS_ITEM);
            player.getInventory().setItem(Constant.JOIN_ITEM_HOST_START_SLOT, Constant.JOIN_ITEM_HOST_START_ITEM);
        }

        player.getInventory().setItem(Constant.JOIN_ITEM_SCENARIOS_SLOT, Constant.JOIN_ITEM_SCENARIOS_ITEM);
        player.getInventory().setItem(Constant.JOIN_ITEM_PATCHNOTE_SLOT, Constant.JOIN_ITEM_PATCHNOTE_ITEM);
    }
}
