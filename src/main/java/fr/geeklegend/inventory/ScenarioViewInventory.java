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

public class ScenarioViewInventory extends InventoryBuilder implements IInventory, Listener
{

    private ScenarioManager scenarioManager;

    public ScenarioViewInventory()
    {
        super(Constant.SCENARIOS_VIEW_INVENTORY_SIZE, Constant.SCENARIOS_VIEW_INVENTORY_NAME);
    }

    @Override
    public Inventory create(Player player)
    {
        scenarioManager = Main.getPlugin().getGameManager().getScenarioManager();
        inventory = Bukkit.createInventory(player, size, name);
        inventory.clear();

        scenarioManager.getScenarios().stream().filter(scenario -> scenario != null).forEach(scenario ->
        {
            if (scenario.isEnabled())
            {
                inventory.addItem(new ItemBuilder(scenario.getIcon()).setName("§e" + scenario.getName() + " §f(" + scenario.getStatus() + "§f)").setLore(scenario.getDescription()).toItemStack());
            }
        });

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

                scenarioManager.getScenarios().stream().filter(scenario -> scenario != null && scenario.isEnabled()).forEach(scenario -> contents.addItem(new ItemBuilder(scenario.getIcon()).setName("§e" + scenario.getName() + " §f(" + scenario.getStatus() + "§f)").setLore(scenario.getDescription()).toItemStack()));

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
                    if (item.equals(Constant.GLOBAL_INVENTORY_CLOSE_ITEM))
                    {
                        player.closeInventory();
                    }
                }
            }
        }
    }
}