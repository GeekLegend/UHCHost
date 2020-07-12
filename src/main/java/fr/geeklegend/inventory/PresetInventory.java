package fr.geeklegend.inventory;

import fr.geeklegend.Main;
import fr.geeklegend.game.GameManager;
import fr.geeklegend.game.GameState;
import fr.geeklegend.game.preset.PresetManager;
import fr.geeklegend.util.Constant;
import fr.geeklegend.util.InventoryBuilder;
import fr.geeklegend.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

public class PresetInventory extends InventoryBuilder implements IInventory, Listener
{

    private PresetManager presetManager;

    public PresetInventory()
    {
        super(Constant.SETTINGS_PRESETS_INVENTORY_SIZE, Constant.SETTINGS_PRESETS_INVENTORY_NAME);
    }

    @Override
    public Inventory create(Player player)
    {
        presetManager = Main.getPlugin().getGameManager().getPresetManager();
        inventory = Bukkit.createInventory(player, size, name);
        inventory.clear();

        presetManager.getPresets().stream().filter(preset -> preset != null).forEach(preset -> inventory.addItem(new ItemBuilder(preset.getIcon()).setName("§e" + preset.getName()).toItemStack()));

        inventory.setItem(Constant.GLOBAL_INVENTORY_BACK_SLOT, Constant.GLOBAL_INVENTORY_BACK_ITEM);
        inventory.setItem(Constant.GLOBAL_INVENTORY_CLOSE_SLOT, Constant.GLOBAL_INVENTORY_CLOSE_ITEM);

        return inventory;
    }

    @Override
    public Inventory create(Player player, boolean bool)
    {
        return null;
    }

    @Override
    public void update()
    {
        Bukkit.getOnlinePlayers().forEach(player ->
        {
            InventoryView inventoryView = player.getOpenInventory();

            if (inventoryView.getTitle().equalsIgnoreCase(name))
            {
                Inventory contents = inventoryView.getTopInventory();
                presetManager = Main.getPlugin().getGameManager().getPresetManager();

                contents.clear();

                presetManager.getPresets().stream().filter(preset -> preset != null).forEach(preset -> contents.addItem(new ItemBuilder(preset.getIcon()).setName("§e" + preset.getName()).toItemStack()));

                contents.setItem(Constant.GLOBAL_INVENTORY_BACK_SLOT, Constant.GLOBAL_INVENTORY_BACK_ITEM);
                contents.setItem(Constant.GLOBAL_INVENTORY_CLOSE_SLOT, Constant.GLOBAL_INVENTORY_CLOSE_ITEM);
            }
        });
    }

    @Override
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event)
    {
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getInventory();
        ItemStack item = event.getCurrentItem();
        GameManager gameManager = Main.getPlugin().getGameManager();
        GameState gameState = gameManager.getGameState();

        if (gameState.equals(GameState.PREPARATION))
        {
            if (inventory != null && inventory.getName().equalsIgnoreCase(name))
            {
                if (item != null)
                {
                    presetManager = Main.getPlugin().getGameManager().getPresetManager();
                    presetManager.getPresets().stream().filter(preset -> preset != null).forEach(preset ->
                    {
                        if (item.getType().equals(preset.getIcon()))
                        {

                        }
                    });
                }

                if (item.equals(Constant.GLOBAL_INVENTORY_BACK_ITEM))
                {
                    player.openInventory(new SettingInventory().create(player));
                } else if (item.equals(Constant.GLOBAL_INVENTORY_CLOSE_ITEM))
                {
                    player.closeInventory();
                }

                update();
            }
        }
    }
}
