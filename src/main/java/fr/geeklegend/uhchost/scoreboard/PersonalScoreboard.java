package fr.geeklegend.uhchost.scoreboard;

import fr.geeklegend.uhchost.Main;
import fr.geeklegend.uhchost.game.GameManager;
import fr.geeklegend.uhchost.game.GameState;
import fr.geeklegend.uhchost.server.ServerSettings;
import fr.geeklegend.uhchost.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PersonalScoreboard
{
    private final UUID uuid;
    private final ObjectiveSign objectiveSign;
    private Player player;

    public PersonalScoreboard(Player player)
    {
        this.player = player;
        uuid = player.getUniqueId();
        objectiveSign = new ObjectiveSign("sidebar", "DevPlugin");

        reloadData();
        objectiveSign.addReceiver(player);
    }

    public void reloadData()
    {
    }

    public void setLines(String ip)
    {
        ServerSettings serverSettings = Main.getPlugin().getServerSettings();
        GameManager gameManager = Main.getPlugin().getGameManager();
        GameState gameState = gameManager.getGameState();

        objectiveSign.setDisplayName(serverSettings.getName());
        objectiveSign.setLine(0, "" + Util.getDate());

        if (gameState.equals(GameState.PREPARATION))
        {
            objectiveSign.setLine(1, "񗉶 ");
            objectiveSign.setLine(2, "Joueur(s): " + gameManager.getPlayers().size() + "/" + serverSettings.getMaxOnline());
            objectiveSign.setLine(3, ip);
        }

        objectiveSign.updateLines();
    }

    public void onLogout()
    {
        objectiveSign.removeReceiver(Bukkit.getServer().getOfflinePlayer(uuid));
    }
}