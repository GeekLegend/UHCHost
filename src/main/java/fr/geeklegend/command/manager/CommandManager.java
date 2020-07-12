package fr.geeklegend.command.manager;

import fr.geeklegend.command.HostCommand;
import fr.geeklegend.command.SaveCommand;
import fr.geeklegend.command.SayCommand;
import fr.geeklegend.Main;

public class CommandManager
{
    public CommandManager(Main plugin)
    {
        plugin.getCommand("host").setExecutor(new HostCommand());
        plugin.getCommand("say").setExecutor(new SayCommand());
        plugin.getCommand("save").setExecutor(new SaveCommand());
    }
}
