package fr.geeklegend.uhchost.command;

import fr.geeklegend.uhchost.util.Constant;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SayCommand implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (sender instanceof Player)
        {
            Player player = (Player) sender;

            if (player.isOp())
            {
                if (args.length >= 1)
                {
                    StringBuilder stringBuilder = new StringBuilder();

                    for (int i = 0; i < args.length; i++)
                    {
                        stringBuilder.append(args[i]).append(" ");
                    }

                    Bukkit.broadcastMessage("§d§lHOTE §d" + player.getName() + " §8» §f" + stringBuilder.toString());
                }
            } else
            {
                player.sendMessage(Constant.NO_PERMISSION_MESSAGE);
            }
        }
        return false;
    }
}
