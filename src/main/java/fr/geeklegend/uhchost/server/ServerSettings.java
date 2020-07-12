package fr.geeklegend.uhchost.server;

import fr.geeklegend.uhchost.Main;
import fr.geeklegend.uhchost.config.ConfigManager;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerSettings
{
    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private int maxOnline;

    @Getter
    @Setter
    private boolean isStarted;

    @Getter
    @Setter
    private ServerAccess serverAccess;

    @Getter
    private Map<String, HostRank> hosts;

    @Getter
    private List<String> bans;

    private ConfigManager configManager = Main.getPlugin().getConfigManager();

    private FileConfiguration hostConfig = configManager.getHostConfig(),
                              banConfig = configManager.getBanConfig();

    public ServerSettings(String name, int maxOnline)
    {
        this.name = name;
        this.maxOnline = maxOnline;
        this.isStarted = false;
        this.serverAccess = ServerAccess.CLOSE;
        this.hosts = new HashMap<>();
        this.bans = new ArrayList<>();
    }

    public void addHost(String playerName, HostRank hostRank)
    {
        hosts.put(playerName, hostRank);
    }

    public void removeHost(String playerName)
    {
        hosts.remove(playerName);
    }

    public boolean isHost(String playerName)
    {
        return hosts.containsKey(playerName);
    }

    public void addBan(String playerName)
    {
        bans.add(playerName);
    }

    public void removeBan(String playerName)
    {
        bans.remove(playerName);
    }

    public boolean isBan(String playerName)
    {
        return bans.contains(playerName);
    }

    public void loadHosts()
    {
        for (String hosts : hostConfig.getStringList("hosts"))
        {
            if (hosts != null)
            {
                addHost(hosts, HostRank.MAIN);
            }
        }
    }

    public void loadBans()
    {
        for (String banned : banConfig.getStringList("bans"))
        {
            if (banned != null)
            {
                addBan(banned);
            }
        }
    }

    public void saveBans()
    {
        for (String banned : bans)
        {
            if (banned != null)
            {
                banConfig.set("bans", banned);
            }
        }

        configManager.save(banConfig, "bans");
    }

    public HostRank getHostRank(String playerName)
    {
        HostRank hostRank = hosts.get(playerName);

        if (hostRank != null)
        {
            return hostRank;
        }
        return null;
    }
}
