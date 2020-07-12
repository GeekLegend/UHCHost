package fr.geeklegend.uhchost.game.scenario;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;

import java.util.List;

public class Scenario
{

    @Getter
    private String name;

    @Getter
    private Material icon;

    @Getter
    private List<String> description;

    @Getter
    @Setter
    private boolean isEnabled;

    public Scenario(String name, Material icon, List<String> description, boolean isEnabled)
    {
        this.name = name;
        this.icon = icon;
        this.description = description;
        this.isEnabled = isEnabled;
    }

    public String getStatus()
    {
        return isEnabled ? "§aActiver" : "§cDésactiver";
    }

}
