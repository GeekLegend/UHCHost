package fr.geeklegend.game.preset;

import lombok.Getter;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class PresetManager
{

    @Getter
    private TaupeGunPreset taupeGunPreset;

    @Getter
    private List<Preset> presets;

    public PresetManager()
    {
        this.taupeGunPreset = new TaupeGunPreset("Taupe Gun", Material.GRASS);
        this.presets = new ArrayList<>();

        loadAll();
    }

    private void loadAll()
    {
        presets.add(taupeGunPreset);
    }
}
