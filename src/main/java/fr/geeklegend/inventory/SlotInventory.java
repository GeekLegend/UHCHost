package fr.geeklegend.inventory;

import fr.geeklegend.Main;
import fr.geeklegend.game.GameManager;
import fr.geeklegend.game.GameState;
import fr.geeklegend.server.ServerSettings;
import fr.geeklegend.util.Constant;
import fr.geeklegend.util.InventoryBuilder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class SlotInventory extends InventoryBuilder implements IInventory, Listener
{

    private ServerSettings serverSettings;

    private ItemStack serverSlotItem;

    private ItemMeta serverSlotItemMeta;

    public SlotInventory()
    {
        super(Constant.SETTINGS_SERVER_SLOTS_INVENTORY_SIZE, Constant.SETTINGS_SERVER_SLOTS_INVENTORY_NAME);

        this.serverSlotItem = Constant.SETTINGS_INVENTORY_SERVER_SLOTS_ITEM;
        this.serverSlotItemMeta = serverSlotItem.getItemMeta();
    }

    private void updateStatus()
    {
        serverSlotItemMeta.setLore(Arrays.asList("§fActuel: §a" + serverSettings.getSlots()));
        serverSlotItem.setItemMeta(serverSlotItemMeta);
    }

    @Override
    public Inventory create(Player player)
    {
        serverSettings = Main.getPlugin().getServerSettings();
        inventory = Bukkit.createInventory(player, size, name);

        updateStatus();

        inventory.clear();
        inventory.setItem(0, Constant.GLOBAL_INVENTORY_SUBSTRACT_TEN_ITEM);
        inventory.setItem(1, Constant.GLOBAL_INVENTORY_SUBSTRACT_FIVE_ITEM);
        inventory.setItem(2, Constant.GLOBAL_INVENTORY_SUBSTRACT_ONE_ITEM);
        inventory.setItem(4, serverSlotItem);
        inventory.setItem(6, Constant.GLOBAL_INVENTORY_ADD_ONE_ITEM);
        inventory.setItem(7, Constant.GLOBAL_INVENTORY_ADD_FIVE_ITEM);
        inventory.setItem(8, Constant.GLOBAL_INVENTORY_ADD_TEN_ITEM);
        inventory.setItem(13, Constant.GLOBAL_INVENTORY_BACK_ITEM);

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

                updateStatus();

                contents.clear();
                contents.setItem(0, Constant.GLOBAL_INVENTORY_SUBSTRACT_TEN_ITEM);
                contents.setItem(1, Constant.GLOBAL_INVENTORY_SUBSTRACT_FIVE_ITEM);
                contents.setItem(2, Constant.GLOBAL_INVENTORY_SUBSTRACT_ONE_ITEM);
                contents.setItem(4, serverSlotItem);
                contents.setItem(6, Constant.GLOBAL_INVENTORY_ADD_ONE_ITEM);
                contents.setItem(7, Constant.GLOBAL_INVENTORY_ADD_FIVE_ITEM);
                contents.setItem(8, Constant.GLOBAL_INVENTORY_ADD_TEN_ITEM);
                contents.setItem(13, Constant.GLOBAL_INVENTORY_BACK_ITEM);
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
                    serverSettings = Main.getPlugin().getServerSettings();

                    if (item.equals(Constant.GLOBAL_INVENTORY_SUBSTRACT_TEN_ITEM))
                    {
                        serverSettings.removeSlots(10);
                    } else if (item.equals(Constant.GLOBAL_INVENTORY_SUBSTRACT_FIVE_ITEM))
                    {
                        serverSettings.removeSlots(5);
                    } else if (item.equals(Constant.GLOBAL_INVENTORY_SUBSTRACT_ONE_ITEM))
                    {
                        serverSettings.removeSlots(1);
                    } else if (item.equals(Constant.GLOBAL_INVENTORY_ADD_ONE_ITEM))
                    {
                        serverSettings.addSlots(1);
                    } else if (item.equals(Constant.GLOBAL_INVENTORY_ADD_FIVE_ITEM))
                    {
                        serverSettings.addSlots(5);
                    } else if (item.equals(Constant.GLOBAL_INVENTORY_ADD_TEN_ITEM))
                    {
                        serverSettings.addSlots(10);
                    } else if (item.equals(Constant.GLOBAL_INVENTORY_BACK_ITEM))
                    {
                        player.openInventory(new SettingInventory().create(player));
                    }

                    update();
                }
            }
        }
    }
}
