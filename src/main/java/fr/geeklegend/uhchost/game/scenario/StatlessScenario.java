package fr.geeklegend.uhchost.game.scenario;

import org.bukkit.Material;

import java.util.List;

public class StatlessScenario extends Scenario
{
    public StatlessScenario(String name, Material icon, List<String> description, boolean isEnabled)
    {
        super(name, icon, description, isEnabled);
    }
}
