package fr.geeklegend.listener;

import fr.geeklegend.Main;
import fr.geeklegend.game.GameManager;
import fr.geeklegend.game.GameState;
import fr.geeklegend.player.PlayerDataManager;
import fr.geeklegend.scoreboard.ScoreboardManager;
import fr.geeklegend.server.ServerSettings;
import fr.geeklegend.team.TeamManager;
import fr.geeklegend.util.Constant;
import fr.geeklegend.util.ItemBuilder;
import fr.geeklegend.util.Title;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener
{

    private GameManager gameManager = Main.getPlugin().getGameManager();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        Player player = event.getPlayer();
        ScoreboardManager scoreboardManager = Main.getPlugin().getScoreboardManager();
        GameState gameState = gameManager.getGameState();

        scoreboardManager.onLogin(player);

        if (!gameState.equals(GameState.GAME))
        {
            PlayerDataManager playerDataManager = gameManager.getPlayerDataManager();

            playerDataManager.create(player);

            gameManager.add(player);

            setup(player);

            gameManager.getPlayers().stream().filter(players -> players != null).forEach(players -> Title.sendActionBar(players, Constant.JOIN_MESSAGE.replace("%playername%", player.getName()).replace("%online%", String.valueOf(gameManager.getPlayers().size())).replace("%maxonline%", String.valueOf(Bukkit.getMaxPlayers()))));
        } else
        {

        }

        event.setJoinMessage(null);
    }

    private void setup(Player player)
    {

        ServerSettings serverSettings = Main.getPlugin().getServerSettings();
        TeamManager teamManager = gameManager.getTeamManager();

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

        if (teamManager.isEnabled())
        {
            player.getInventory().setItem(Constant.JOIN_ITEM_TEAMS_SLOT, Constant.JOIN_ITEM_TEAMS_ITEM);
        }

        if (serverSettings.isHost(player.getName()))
        {
            player.getInventory().setItem(Constant.JOIN_ITEM_HOST_SETTINGS_SLOT, Constant.JOIN_ITEM_HOST_SETTINGS_ITEM);
            player.getInventory().setItem(Constant.JOIN_ITEM_HOST_START_SLOT, Constant.JOIN_ITEM_HOST_START_ITEM);
        }

        player.getInventory().setItem(Constant.JOIN_ITEM_SCENARIOS_SLOT, Constant.JOIN_ITEM_SCENARIOS_ITEM);
        player.getInventory().setItem(Constant.JOIN_ITEM_PATCHNOTE_SLOT, Constant.JOIN_ITEM_PATCHNOTE_ITEM);
    }
}
