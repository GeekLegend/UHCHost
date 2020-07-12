package fr.geeklegend.game.preset;

import lombok.Getter;
import org.bukkit.Material;

public class Preset
{

    @Getter
    private String name;

    @Getter
    private Material icon;

    public Preset(String name, Material icon)
    {
        this.name = name;
        this.icon = icon;
    }
}
