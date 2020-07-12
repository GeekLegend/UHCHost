package fr.geeklegend.player;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class PlayerDataManager
{
    private Map<Player, PlayerData> playerData;

    public PlayerDataManager()
    {
        this.playerData = new HashMap<>();
    }

    public void create(Player player)
    {
        playerData.put(player, new PlayerData(player.getName(), 0));
    }

    public void remove(Player player)
    {
        playerData.remove(player);
    }

    public PlayerData getPlayerData(Player player)
    {
        PlayerData pd = playerData.get(player);

        return pd != null ? pd : null;
    }
}
