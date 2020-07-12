package fr.geeklegend.inventory.manager;

import fr.geeklegend.inventory.*;
import fr.geeklegend.Main;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InventoryManager
{

    @Getter
    private InventoryType inventoryType;

    private ArrayList<Player> editKit;

    private Map<Player, ItemStack[]> playerArmorContents, playerContents;

    private Map<InventoryType, ItemStack[]> gameArmorContents, gameContents;

    public InventoryManager(Main plugin)
    {
        this.editKit = new ArrayList<>();
        this.playerArmorContents = new HashMap<>();
        this.playerContents = new HashMap<>();
        this.gameArmorContents = new HashMap<>();
        this.gameContents = new HashMap<>();

        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new SettingInventory(), plugin);
        pluginManager.registerEvents(new ScenarioInventory(), plugin);
        pluginManager.registerEvents(new ScenarioViewInventory(), plugin);
        pluginManager.registerEvents(new PresetInventory(), plugin);
        pluginManager.registerEvents(new SlotInventory(), plugin);
    }

    public void loadPlayerContents(Player player)
    {
        ItemStack[] pac = getPlayerArmorContents(player);
        ItemStack[] pc = getPlayerContents(player);

        player.getInventory().clear();

        if (pac != null)
        {
            player.getInventory().setArmorContents(pac);
        }

        if (pc != null)
        {
            player.getInventory().setContents(pc);
        }
    }

    public void savePlayerContents(Player player)
    {
        playerArmorContents.put(player, player.getInventory().getArmorContents());
        playerContents.put(player, player.getInventory().getContents());

        player.getInventory().clear();
    }

    public void loadGameContents(InventoryType inventoryType, Player player)
    {
        this.inventoryType = inventoryType;

        ItemStack[] gac = getGameArmorContents(inventoryType);
        ItemStack[] gc = getGameContents(inventoryType);

        if (gac != null)
        {
            player.getInventory().setArmorContents(gac);
        }

        if (gc != null)
        {
            player.getInventory().setContents(gc);
        }
    }

    public void saveGameContents(Player player)
    {
        gameArmorContents.put(inventoryType, player.getInventory().getArmorContents());
        gameContents.put(inventoryType, player.getInventory().getContents());
    }

    public void addEditKit(Player player)
    {
        editKit.add(player);
    }

    public void removeEditKit(Player player)
    {
        editKit.remove(player);
    }

    public boolean isPlayerContains(Player player)
    {
        return playerArmorContents.containsKey(player) && playerContents.containsKey(player);
    }

    public boolean isGameContains(Player player)
    {
        return gameArmorContents.containsKey(player) && gameContents.containsKey(player);
    }

    public boolean isEditKitContains(Player player)
    {
        return editKit.contains(player);
    }

    public ItemStack[] getPlayerArmorContents(Player player)
    {
        return playerArmorContents.get(player);
    }

    public ItemStack[] getPlayerContents(Player player)
    {
        return playerContents.get(player);
    }

    public ItemStack[] getGameArmorContents(InventoryType inventoryType)
    {
        return gameArmorContents.get(inventoryType);
    }

    public ItemStack[] getGameContents(InventoryType inventoryType)
    {
        return gameContents.get(inventoryType);
    }
}
