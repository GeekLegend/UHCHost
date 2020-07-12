package fr.geeklegend.scoreboard;

import fr.geeklegend.Main;
import fr.geeklegend.game.GameManager;
import fr.geeklegend.game.GameState;
import fr.geeklegend.server.ServerSettings;
import fr.geeklegend.util.Util;
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
            objectiveSign.setLine(2, "Joueur(s): " + gameManager.getPlayers().size() + "/" + serverSettings.getSlots());
            objectiveSign.setLine(3, ip);
        }

        objectiveSign.updateLines();
    }

    public void onLogout()
    {
        objectiveSign.removeReceiver(Bukkit.getServer().getOfflinePlayer(uuid));
    }
}