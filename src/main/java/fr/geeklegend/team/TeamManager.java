package fr.geeklegend.team;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeamManager
{

    @Getter
    private List<Team> teams;

    @Getter
    private Map<Player, Team> team;

    @Getter
    @Setter
    private boolean isEnabled;

    @Getter
    @Setter
    private int maxPlayers;

    public TeamManager()
    {
        this.teams = new ArrayList<>();
        this.team = new HashMap<>();
        this.isEnabled = false;
        this.maxPlayers = 0;

        loadAll();
    }

    private void loadAll()
    {
        teams.add(new Team("Rouge", ChatColor.RED, (byte) 1));
        teams.add(new Team("Bleu", ChatColor.BLUE, (byte) 4));
        teams.add(new Team("Vert", ChatColor.GREEN, (byte) 10));
        teams.add(new Team("Rouge", ChatColor.RED, (byte) 1));
        teams.add(new Team("Bleu clair", ChatColor.AQUA, (byte) 12));
        teams.add(new Team("Jaune", ChatColor.YELLOW, (byte) 11));
        teams.add(new Team("Rose", ChatColor.LIGHT_PURPLE, (byte) 9));
        teams.add(new Team("Orange", ChatColor.GOLD, (byte) 14));
        teams.add(new Team("Rouge ?", ChatColor.RED, (byte) 1));
        teams.add(new Team("Bleu ?", ChatColor.BLUE, (byte) 4));
        teams.add(new Team("Vert ?", ChatColor.GREEN, (byte) 10));
        teams.add(new Team("Rouge ?", ChatColor.RED, (byte) 1));
        teams.add(new Team("Bleu clair ?", ChatColor.AQUA, (byte) 12));
        teams.add(new Team("Jaune ?", ChatColor.YELLOW, (byte) 11));
        teams.add(new Team("Rose ?", ChatColor.LIGHT_PURPLE, (byte) 9));
        teams.add(new Team("Orange ?", ChatColor.GOLD, (byte) 14));

    }

    public void add(Player player, Team t)
    {
        t.add(player);
        team.put(player, t);
    }

    public void remove(Player player)
    {
        Team t = getTeam(player);

        t.remove(player);
        team.remove(player);
    }

    public Team getTeam(Player player)
    {
        Team t = team.get(player);

        return t != null ? t : null;
    }
}
