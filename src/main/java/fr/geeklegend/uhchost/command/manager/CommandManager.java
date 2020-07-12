package fr.geeklegend.uhchost.command.manager;

import fr.geeklegend.uhchost.Main;
import fr.geeklegend.uhchost.command.HostCommand;
import fr.geeklegend.uhchost.command.SaveCommand;
import fr.geeklegend.uhchost.command.SayCommand;

public class CommandManager
{
    public CommandManager(Main plugin)
    {
        plugin.getCommand("host").setExecutor(new HostCommand());
        plugin.getCommand("say").setExecutor(new SayCommand());
        plugin.getCommand("save").setExecutor(new SaveCommand());
    }
}
