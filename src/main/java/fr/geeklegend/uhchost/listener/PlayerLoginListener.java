package fr.geeklegend.uhchost.listener;

import fr.geeklegend.uhchost.Main;
import fr.geeklegend.uhchost.server.ServerSettings;
import fr.geeklegend.uhchost.util.Constant;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerLoginListener implements Listener
{
    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event)
    {
        Player player = event.getPlayer();
        ServerSettings serverSettings = Main.getPlugin().getServerSettings();

        if (serverSettings.isBan(player.getName()))
        {
            event.setResult(PlayerLoginEvent.Result.KICK_OTHER);
            event.setKickMessage(Constant.HOST_BAN_TARGET_MESSAGE);
        }
    }
}
