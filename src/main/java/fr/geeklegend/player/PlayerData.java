package fr.geeklegend.player;

import lombok.Getter;
import lombok.Setter;

public class PlayerData
{

    @Getter
    private String name;

    @Getter
    @Setter
    private int kills;

    public PlayerData(String name, int kills)
    {
        this.name = name;
        this.kills = kills;
    }
}
