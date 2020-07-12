package fr.geeklegend.uhchost.scheduler;

import fr.geeklegend.uhchost.Main;
import fr.geeklegend.uhchost.game.GameManager;
import fr.geeklegend.uhchost.game.GameState;
import fr.geeklegend.uhchost.server.ServerSettings;
import fr.geeklegend.uhchost.util.Constant;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class StartScheduler extends BukkitRunnable implements IScheduler
{

    @Getter
    private static int timer;

    @Getter
    @Setter
    private static boolean isRunning;

    public StartScheduler()
    {
        this.timer = Constant.SCHEDULER_START_TIMER;
        this.isRunning = false;
    }

    @Override
    public void run()
    {
        GameManager gameManager = Main.getPlugin().getGameManager();

        timer--;

        if (gameManager.getPlayers().isEmpty() || gameManager.getPlayers().size() < 2)
        {
            ServerSettings serverSettings = Main.getPlugin().getServerSettings();

            stop();

            Bukkit.broadcastMessage(Constant.START_SCHEDULER_CANCELLED_MESSAGE.replace("%online%", String.valueOf(serverSettings.getMaxOnline() / 2)));
        } else
        {
            for (Player player : gameManager.getPlayers())
            {
                player.setLevel(timer);
            }

            if (timer == 0)
            {
                stop();

                gameManager.setGameState(GameState.PRE_GAME);

                Bukkit.broadcastMessage("PRE GAME STATE");
            }
        }
    }

    @Override
    public void stop()
    {
        cancel();
        reset();
        isRunning = false;
    }

    @Override
    public void reset()
    {
        timer = Constant.SCHEDULER_START_TIMER;
    }

}
