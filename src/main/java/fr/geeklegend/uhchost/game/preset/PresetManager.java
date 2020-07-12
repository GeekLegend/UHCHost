package fr.geeklegend.uhchost.game.preset;

import lombok.Getter;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class PresetManager
{

    @Getter
    private BingoPreset bingoPreset;

    @Getter
    private List<Preset> presets;

    public PresetManager()
    {
        this.bingoPreset = new BingoPreset("Bingo", Material.WORKBENCH);
        this.presets = new ArrayList<>();

        loadAll();
    }

    private void loadAll()
    {
        presets.add(bingoPreset);
    }
}
