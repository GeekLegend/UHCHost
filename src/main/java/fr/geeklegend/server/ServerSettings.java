package fr.geeklegend.server;

import fr.geeklegend.config.ConfigManager;
import fr.geeklegend.Main;
import fr.geeklegend.util.Constant;
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
    private int defaultSlots, slots, maxSlots;

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

    public ServerSettings()
    {
        this.name = Constant.DEFAULT_SERVER_NAME;
        this.defaultSlots = 24;
        this.slots = defaultSlots;
        this.maxSlots = 60;
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
        hostConfig.getStringList("hosts").stream().filter(hosts -> hosts != null).forEach(hosts -> addHost(hosts, HostRank.MAIN));
    }

    public void loadBans()
    {
        banConfig.getStringList("bans").stream().filter(banned -> banned != null).forEach(banned -> addBan(banned));
    }

    public void saveBans()
    {
        bans.stream().filter(banned -> banned != null).forEach(banned -> banConfig.set("bans", banned));

        configManager.save(banConfig, "bans");
    }

    public HostRank getHostRank(String playerName)
    {
        HostRank hostRank = hosts.get(playerName);

        return hostRank != null ? hostRank : null;
    }

    public void addSlots(int amount)
    {
        if (slots < maxSlots)
        {
            this.slots += amount;
        } else
        {
            this.slots = maxSlots;
        }
    }

    public void removeSlots(int amount)
    {
        if (slots > defaultSlots)
        {
            this.slots -= amount;
        } else
        {
            this.slots = defaultSlots;
        }
    }
}
