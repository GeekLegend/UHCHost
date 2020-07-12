package fr.geeklegend.inventory;

import fr.geeklegend.Main;
import fr.geeklegend.game.GameManager;
import fr.geeklegend.game.GameState;
import fr.geeklegend.game.scenario.ScenarioManager;
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

public class ScenarioInventory extends InventoryBuilder implements IInventory, Listener
{

    private ScenarioManager scenarioManager;

    public ScenarioInventory()
    {
        super(Constant.SCENARIOS_INVENTORY_SIZE, Constant.SCENARIOS_INVENTORY_NAME);
    }

    @Override
    public Inventory create(Player player)
    {
        scenarioManager = Main.getPlugin().getGameManager().getScenarioManager();
        inventory = Bukkit.createInventory(player, size, name);
        inventory.clear();

        scenarioManager.getScenarios().stream().filter(scenario -> scenario != null).forEach(scenario -> inventory.addItem(new ItemBuilder(scenario.getIcon()).setName("§e" + scenario.getName() + " §f(" + scenario.getStatus() + "§f)").setLore(scenario.getDescription()).toItemStack()));

        inventory.setItem(Constant.GLOBAL_INVENTORY_ALL_ENABLED_SLOT, Constant.GLOBAL_INVENTORY_ALL_ENABLED_ITEM);
        inventory.setItem(Constant.GLOBAL_INVENTORY_ALL_DISABLED_SLOT, Constant.GLOBAL_INVENTORY_ALL_DISABLED_ITEM);
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
                scenarioManager = Main.getPlugin().getGameManager().getScenarioManager();

                contents.clear();

                scenarioManager.getScenarios().stream().filter(scenario -> scenario != null).forEach(scenario -> contents.addItem(new ItemBuilder(scenario.getIcon()).setName("§e" + scenario.getName() + " §f(" + scenario.getStatus() + "§f)").setLore(scenario.getDescription()).toItemStack()));

                contents.setItem(Constant.GLOBAL_INVENTORY_ALL_ENABLED_SLOT, Constant.GLOBAL_INVENTORY_ALL_ENABLED_ITEM);
                contents.setItem(Constant.GLOBAL_INVENTORY_ALL_DISABLED_SLOT, Constant.GLOBAL_INVENTORY_ALL_DISABLED_ITEM);
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
                    scenarioManager = Main.getPlugin().getGameManager().getScenarioManager();
                    scenarioManager.getScenarios().stream().filter(scenario -> scenario != null).forEach(scenario ->
                    {
                        if (item.getType().equals(scenario.getIcon()))
                        {
                            if (scenario.isEnabled())
                            {
                                scenario.setEnabled(false);
                            } else
                            {
                                scenario.setEnabled(true);
                            }
                        } else if (item.equals(Constant.GLOBAL_INVENTORY_ALL_ENABLED_ITEM))
                        {
                            scenario.setEnabled(true);
                        } else if (item.equals(Constant.GLOBAL_INVENTORY_ALL_DISABLED_ITEM))
                        {
                            scenario.setEnabled(false);
                        }
                    });

                    if (item.equals(Constant.GLOBAL_INVENTORY_BACK_ITEM))
                    {
                        player.openInventory(new SettingInventory().create(player));
                    }
                }

                if (item.equals(Constant.GLOBAL_INVENTORY_CLOSE_ITEM))
                {
                    player.closeInventory();
                }

                update();
            }
        }
    }
}
