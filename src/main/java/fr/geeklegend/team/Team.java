package fr.geeklegend.team;

import fr.geeklegend.util.Constant;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Team
{

    @Getter
    private String name;

    @Getter
    private ChatColor nameColor;

    @Getter
    private Material icon;

    @Getter
    private byte iconData;

    @Getter
    private List<Player> players;

    public Team(String name, ChatColor nameColor, byte iconData)
    {
        this.name = name;
        this.nameColor = nameColor;
        this.icon = Constant.TEAMS_ICON;
        this.iconData = iconData;
        this.players = new ArrayList<>();
    }

    public void add(Player player)
    {
        players.add(player);
    }

    public void remove(Player player)
    {
        players.remove(player);
    }
}
