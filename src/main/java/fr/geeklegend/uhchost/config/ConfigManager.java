package fr.geeklegend.uhchost.config;

import fr.geeklegend.uhchost.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class ConfigManager
{
	private Main plugin;

	private FileConfiguration hostConfig, banConfig;
	
	public ConfigManager(Main plugin)
	{
		this.plugin = plugin;
		this.hostConfig = load("hosts");
		this.banConfig = load("bans");

		create("hosts");
		create("bans");

		addDefaults();
	}

	private void addDefaults()
	{
		hostConfig.options().copyDefaults(true);
		hostConfig.addDefault("hosts", Arrays.asList("GeekLegend", "Alexdolphinus"));

		save(hostConfig, "hosts");
	}

	private void create(String fileName)
	{
		File dataFolder = plugin.getDataFolder();
		
		if (!dataFolder.exists())
		{
			dataFolder.mkdir();
		}
		
		File file = new File(dataFolder, fileName + ".yml");
		
		if (!file.exists())
		{
			try
			{
				file.createNewFile();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public FileConfiguration load(String fileName)
	{
		return YamlConfiguration.loadConfiguration(getFile(fileName));
	}
	
	public void save(FileConfiguration config, String fileName)
	{
		try
		{
			config.save(getFile(fileName));
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public File getFile(String fileName)
	{
		return new File(plugin.getDataFolder(), fileName + ".yml");
	}

	public FileConfiguration getHostConfig()
	{
		return hostConfig;
	}

	public FileConfiguration getBanConfig()
	{
		return banConfig;
	}
}
