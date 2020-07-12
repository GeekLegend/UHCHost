package fr.geeklegend.command;

import fr.geeklegend.inventory.manager.InventoryManager;
import fr.geeklegend.server.ServerSettings;
import fr.geeklegend.Main;
import fr.geeklegend.util.Constant;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SaveCommand implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (sender instanceof Player)
        {
            Player player = (Player) sender;
            ServerSettings serverSettings = Main.getPlugin().getServerSettings();

            if (serverSettings.isHost(player.getName()))
            {
                InventoryManager inventoryManager = Main.getPlugin().getInventoryManager();

                if (inventoryManager.isEditKitContains(player))
                {
                    inventoryManager.removeEditKit(player);
                    inventoryManager.saveGameContents(player);
                    inventoryManager.loadPlayerContents(player);

                    player.setGameMode(Constant.JOIN_GAMEMODE);
                    player.sendMessage(Constant.HOST_INVENTORY_SAVED_MESSAGE);
                } else
                {
                    player.sendMessage(Constant.HOST_INVENTORY_NOT_MESSAGE);
                }
            } else
            {
                player.sendMessage(Constant.NO_PERMISSION_MESSAGE);
            }
        }
        return false;
    }
}
