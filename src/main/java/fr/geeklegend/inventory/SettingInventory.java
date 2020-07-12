package fr.geeklegend.inventory;

import fr.geeklegend.Main;
import fr.geeklegend.game.GameManager;
import fr.geeklegend.game.GameState;
import fr.geeklegend.inventory.manager.InventoryManager;
import fr.geeklegend.server.ServerAccess;
import fr.geeklegend.server.ServerSettings;
import fr.geeklegend.util.Constant;
import fr.geeklegend.util.InventoryBuilder;
import fr.geeklegend.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class SettingInventory extends InventoryBuilder implements IInventory, Listener
{

    private ServerSettings serverSettings;

    private ItemStack serverNameItem, serverAccessItem, serverSlotItem;

    private ItemMeta serverNameItemMeta, serverAccessItemMeta, serverSlotItemMeta;

    public SettingInventory()
    {
        super(Constant.SETTINGS_INVENTORY_SIZE, Constant.SETTINGS_INVENTORY_NAME);

        this.serverNameItem = Constant.SETTINGS_INVENTORY_SERVER_NAME_ITEM;
        this.serverAccessItem = Constant.SETTINGS_INVENTORY_SERVER_ACCESS_ITEM;
        this.serverSlotItem = Constant.SETTINGS_INVENTORY_SERVER_SLOTS_ITEM;
        this.serverNameItemMeta = serverNameItem.getItemMeta();
        this.serverAccessItemMeta = serverAccessItem.getItemMeta();
        this.serverSlotItemMeta = serverSlotItem.getItemMeta();
    }

    private void updateStatus()
    {
        serverNameItemMeta.setLore(Arrays.asList("§fActuel: " + serverSettings.getName()));
        serverAccessItemMeta.setLore(Arrays.asList("§fActuel: " + serverSettings.getServerAccess().getName()));
        serverSlotItemMeta.setLore(Arrays.asList("§fActuel: §a" + serverSettings.getSlots()));

        serverNameItem.setItemMeta(serverNameItemMeta);
        serverAccessItem.setItemMeta(serverAccessItemMeta);
        serverSlotItem.setItemMeta(serverSlotItemMeta);
    }

    @Override
    public Inventory create(Player player)
    {
        ItemStack pinkGlassItem = new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 2).setName(" ").toItemStack();
        ItemStack whiteGlassItem = new ItemBuilder(Material.STAINED_GLASS_PANE).setName(" ").toItemStack();
        serverSettings = Main.getPlugin().getServerSettings();
        inventory = Bukkit.createInventory(player, size, name);
        inventory.clear();

        updateStatus();

        for (int i = 0; i < 9; i++)
        {
            inventory.setItem(i, pinkGlassItem);
        }

        for (int i = 12; i < 15; i++)
        {
            inventory.setItem(i, whiteGlassItem);
        }

        inventory.setItem(21, whiteGlassItem);
        inventory.setItem(23, whiteGlassItem);
        inventory.setItem(30, whiteGlassItem);
        inventory.setItem(32, whiteGlassItem);

        for (int i = 39; i < 42; i++)
        {
            inventory.setItem(i, whiteGlassItem);
        }

        for (int i = 45; i < 54; i++)
        {
            inventory.setItem(i, pinkGlassItem);
        }

        inventory.setItem(Constant.SETTINGS_INVENTORY_TEAMS_SLOT, Constant.SETTINGS_INVENTORY_TEAMS_ITEM);
        inventory.setItem(Constant.SETTINGS_INVENTORY_SPAWN_STUFF_SLOT, Constant.SETTINGS_INVENTORY_SPAWN_STUFF_ITEM);
        inventory.setItem(Constant.SETTINGS_INVENTORY_DEATH_STUFF_SLOT, Constant.SETTINGS_INVENTORY_DEATH_STUFF_ITEM);
        inventory.setItem(Constant.SETTINGS_INVENTORY_WORLD_SLOT, Constant.SETTINGS_INVENTORY_WORLD_ITEM);
        inventory.setItem(Constant.SETTINGS_INVENTORY_PRESETS_SLOT, Constant.SETTINGS_INVENTORY_PRESETS_ITEM);
        inventory.setItem(Constant.SETTINGS_INVENTORY_SCENARIOS_SLOT, Constant.SETTINGS_INVENTORY_SCENARIOS_ITEM);
        inventory.setItem(Constant.SETTINGS_INVENTORY_SERVER_NAME_SLOT, serverNameItem);
        inventory.setItem(Constant.SETTINGS_INVENTORY_SERVER_ACCESS_SLOT, serverAccessItem);
        inventory.setItem(Constant.SETTINGS_INVENTORY_SERVER_SLOTS_SLOT, serverSlotItem);
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
        for (Player players : Bukkit.getOnlinePlayers())
        {
            InventoryView inventoryView = players.getOpenInventory();

            if (inventoryView.getTitle().equalsIgnoreCase(name))
            {
                Inventory contents = inventoryView.getTopInventory();
                ItemStack pinkGlassItem = new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 2).setName(" ").toItemStack();
                ItemStack whiteGlassItem = new ItemBuilder(Material.STAINED_GLASS_PANE).setName(" ").toItemStack();
                serverSettings = Main.getPlugin().getServerSettings();

                updateStatus();

                contents.clear();

                for (int i = 0; i < 9; i++)
                {
                    contents.setItem(i, pinkGlassItem);
                }

                for (int i = 12; i < 15; i++)
                {
                    contents.setItem(i, whiteGlassItem);
                }

                contents.setItem(21, whiteGlassItem);
                contents.setItem(23, whiteGlassItem);
                contents.setItem(30, whiteGlassItem);
                contents.setItem(32, whiteGlassItem);

                for (int i = 39; i < 42; i++)
                {
                    contents.setItem(i, whiteGlassItem);
                }

                for (int i = 45; i < 54; i++)
                {
                    contents.setItem(i, pinkGlassItem);
                }

                contents.setItem(Constant.SETTINGS_INVENTORY_TEAMS_SLOT, Constant.SETTINGS_INVENTORY_TEAMS_ITEM);
                contents.setItem(Constant.SETTINGS_INVENTORY_SPAWN_STUFF_SLOT, Constant.SETTINGS_INVENTORY_SPAWN_STUFF_ITEM);
                contents.setItem(Constant.SETTINGS_INVENTORY_DEATH_STUFF_SLOT, Constant.SETTINGS_INVENTORY_DEATH_STUFF_ITEM);
                contents.setItem(Constant.SETTINGS_INVENTORY_WORLD_SLOT, Constant.SETTINGS_INVENTORY_WORLD_ITEM);
                contents.setItem(Constant.SETTINGS_INVENTORY_PRESETS_SLOT, Constant.SETTINGS_INVENTORY_PRESETS_ITEM);
                contents.setItem(Constant.SETTINGS_INVENTORY_SCENARIOS_SLOT, Constant.SETTINGS_INVENTORY_SCENARIOS_ITEM);
                contents.setItem(Constant.SETTINGS_INVENTORY_SERVER_NAME_SLOT, serverNameItem);
                contents.setItem(Constant.SETTINGS_INVENTORY_SERVER_ACCESS_SLOT, serverAccessItem);
                contents.setItem(Constant.SETTINGS_INVENTORY_SERVER_SLOTS_SLOT, serverSlotItem);
                contents.setItem(Constant.GLOBAL_INVENTORY_CLOSE_SLOT, Constant.GLOBAL_INVENTORY_CLOSE_ITEM);
            }
        }
    }

    @Override
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event)
    {
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getInventory();
        ItemStack item = event.getCurrentItem();
        ServerSettings serverSettings = Main.getPlugin().getServerSettings();
        GameManager gameManager = Main.getPlugin().getGameManager();
        InventoryManager inventoryManager = Main.getPlugin().getInventoryManager();
        GameState gameState = gameManager.getGameState();

        if (gameState.equals(GameState.PREPARATION))
        {
            if (inventory != null && inventory.getName().equalsIgnoreCase(name))
            {
                if (item != null)
                {
                    if (serverSettings.isHost(player.getName()))
                    {
                        if (item.equals(Constant.SETTINGS_INVENTORY_SPAWN_STUFF_ITEM))
                        {
                            openInventory(player, InventoryType.SPAWN, gameManager, inventoryManager);

                        } else if (item.equals(Constant.SETTINGS_INVENTORY_DEATH_STUFF_ITEM))
                        {
                            openInventory(player, InventoryType.DEATH, gameManager, inventoryManager);
                        } else if (item.equals(Constant.SETTINGS_INVENTORY_SERVER_NAME_ITEM))
                        {
                            AnvilInventory anvilInventory = new AnvilInventory(player, event2 ->
                            {
                                if (event2.getSlot() == AnvilInventory.AnvilSlot.OUTPUT)
                                {
                                    String name = event2.getName();

                                    if (name.isEmpty() || name.length() > 40)
                                    {
                                        return;
                                    } else
                                    {
                                        event2.setWillClose(true);
                                        event2.setWillDestroy(true);

                                        serverSettings.setName(name.replace("&", "§"));

                                        player.playSound(player.getLocation(), Sound.ANVIL_USE, 1, 1);
                                        player.sendMessage(Constant.HOST_SERVER_NAME_MESSAGE.replace("%servername%", serverSettings.getName()));

                                        update();
                                    }
                                }
                            });

                            anvilInventory.setSlot(AnvilInventory.AnvilSlot.INPUT_LEFT, new ItemBuilder(Constant.SETTINGS_INVENTORY_SERVER_NAME_ITEM.getType()).setName("Nom du serveur").toItemStack());
                            anvilInventory.open();
                        } else if (item.equals(Constant.SETTINGS_INVENTORY_PRESETS_ITEM))
                        {
                            player.openInventory(new PresetInventory().create(player));
                        } else if (item.equals(Constant.SETTINGS_INVENTORY_SCENARIOS_ITEM))
                        {
                            player.openInventory(new ScenarioInventory().create(player));
                        } else if (item.equals(Constant.SETTINGS_INVENTORY_SERVER_ACCESS_ITEM))
                        {
                            switch (serverSettings.getServerAccess())
                            {
                                case CLOSE:
                                    serverSettings.setServerAccess(ServerAccess.WHITELIST);
                                    break;
                                case WHITELIST:
                                    serverSettings.setServerAccess(ServerAccess.OPEN);
                                    break;
                                case OPEN:
                                    serverSettings.setServerAccess(ServerAccess.CLOSE);
                                    break;
                                default:
                                    break;
                            }

                            player.playSound(player.getLocation(), Sound.LEVEL_UP, 1, 1);
                            player.sendMessage(Constant.HOST_SERVER_ACCESS_MESSAGE.replace("%status%", serverSettings.getServerAccess().getName()));
                        } else if (item.equals(Constant.SETTINGS_INVENTORY_SERVER_SLOTS_ITEM))
                        {
                            player.openInventory(new SlotInventory().create(player));
                        } else if (item.equals(Constant.GLOBAL_INVENTORY_CLOSE_ITEM))
                        {
                            player.closeInventory();
                        }
                    }

                    update();
                }
            }
        }
    }

    private void openInventory(Player player, InventoryType inventoryType, GameManager gameManager, InventoryManager inventoryManager)
    {
        for (Player pl : gameManager.getPlayers())
        {
            if (inventoryManager.isEditKitContains(pl))
            {
                player.sendMessage(Constant.HOST_INVENTORY_ALREADY_MESSAGE);

                return;
            }
        }

        if (!inventoryManager.isEditKitContains(player))
        {
            inventoryManager.addEditKit(player);
            inventoryManager.savePlayerContents(player);
            inventoryManager.loadGameContents(inventoryType, player);

            player.closeInventory();
            player.setGameMode(GameMode.CREATIVE);
            player.sendMessage(Constant.HOST_INVENTORY_HELP_SAVE_MESSAGE);
        }
    }
}
