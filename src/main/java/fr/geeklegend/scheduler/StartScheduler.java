package fr.geeklegend.scheduler;

import fr.geeklegend.Main;
import fr.geeklegend.game.GameManager;
import fr.geeklegend.game.GameState;
import fr.geeklegend.server.ServerSettings;
import fr.geeklegend.util.Constant;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class StartScheduler extends BukkitRunnable implements IScheduler
{

    @Getter
    private static int timer = Constant.SCHEDULER_START_TIMER;

    @Getter
    @Setter
    private static boolean isRunning = false;

    @Override
    public void run()
    {
        GameManager gameManager = Main.getPlugin().getGameManager();

        timer--;

        if (gameManager.getPlayers().isEmpty() || gameManager.getPlayers().size() < 2)
        {
            ServerSettings serverSettings = Main.getPlugin().getServerSettings();

            stop();

            Bukkit.broadcastMessage(Constant.START_SCHEDULER_CANCELLED_MESSAGE.replace("%online%", String.valueOf(serverSettings.getSlots() / 2)));
        } else
        {
            gameManager.getPlayers().stream().filter(player -> player != null).forEach(player -> player.setLevel(timer));

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
        isRunning = false;
        cancel();
        reset();
    }

    @Override
    public void reset()
    {
        timer = Constant.SCHEDULER_START_TIMER;
    }

}
