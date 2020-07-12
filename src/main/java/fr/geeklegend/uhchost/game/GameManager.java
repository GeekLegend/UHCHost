package fr.geeklegend.uhchost.game;

import fr.geeklegend.uhchost.game.preset.PresetManager;
import fr.geeklegend.uhchost.game.scenario.ScenarioManager;
import fr.geeklegend.uhchost.player.PlayerDataManager;
import fr.geeklegend.uhchost.world.WorldManager;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class GameManager
{

    @Getter
    private PlayerDataManager playerDataManager;

    @Getter
    private WorldManager worldManager;

    @Getter
    private PresetManager presetManager;

    @Getter
    private ScenarioManager scenarioManager;

    @Getter
    @Setter
    private GameState gameState;

    @Getter
    private List<Player> players;

    public GameManager()
    {
        this.playerDataManager = new PlayerDataManager();
        this.worldManager = new WorldManager(800);
        this.presetManager = new PresetManager();
        this.scenarioManager = new ScenarioManager();
        this.gameState = GameState.PREPARATION;
        this.players = new ArrayList<>();
    }

    public void add(Player player)
    {
        players.add(player);
    }

    public void remove(Player player)
    {
        players.remove(player);
    }

    public boolean isContains(Player player)
    {
        return players.contains(player);
    }

    public void cleanUp(Player player)
    {
        remove(player);
    }

    private String getDistance(Location playerLocation, Location centerLocation)
    {
        if (playerLocation.getWorld() == centerLocation.getWorld())
        {
            double xp = playerLocation.getBlockX();
            double zp = playerLocation.getBlockZ();

            double xl = centerLocation.getBlockX();
            double zl = centerLocation.getBlockZ();

            double distancex = xp - xl;
            if (distancex < 0.0D)
                distancex = -distancex;
            double distancez = zp - zl;
            if (distancez < 0.0D)
            {
                distancez = -distancez;
            }
            double distance = Math.sqrt(Math.pow(distancex, 2.0D) + Math.pow(distancez, 2.0D));
            return new StringBuilder().append((int) distance).toString();
        }
        return "?";
    }


    private double getAngle(Player player, Location centerLocation)
    {
        Location playerLocation = player.getLocation();

        if (playerLocation.getWorld() == centerLocation.getWorld())
        {
            if (playerLocation.getBlockX() != centerLocation.getBlockX() || playerLocation.getBlockZ() != centerLocation.getBlockZ())
            {

                double distancecz, distancecx, xp = playerLocation.getBlockX();
                double zp = playerLocation.getBlockZ();

                double xl = centerLocation.getBlockX();
                double zl = centerLocation.getBlockZ();

                double distancex = xp - xl;

                if (distancex < 0.0D)
                {
                    distancecx = -distancex;
                } else
                {
                    distancecx = distancex;
                }

                double distancez = zp - zl;

                if (distancez < 0.0D)
                {
                    distancecz = -distancez;
                } else
                {
                    distancecz = distancez;
                }

                double angle = 180.0D * Math.atan(distancecz / distancecx) / Math.PI;

                if (distancex < 0.0D || distancez < 0.0D)
                {
                    if (distancex < 0.0D && distancez >= 0.0D)
                    {
                        angle = 90.0D - angle + 90.0D;
                    } else if (distancex <= 0.0D && distancez < 0.0D)
                    {
                        angle += 180.0D;
                    } else if (distancex > 0.0D && distancez < 0.0D)
                    {
                        angle = 90.0D - angle + 270.0D;
                    }
                }
                angle += 270.0D;
                if (angle >= 360.0D)
                {
                    angle -= 360.0D;
                }
                double yaw = (player.getEyeLocation().getYaw() + 180.0F);

                angle -= yaw;

                if (angle <= 0.0D)
                    angle += 360.0D;
                if (angle <= 0.0D)
                {
                    angle += 360.0D;
                }
                return angle;
            }
            return -1.0D;
        }

        return -2.0D;
    }

    public String getArrow(double angle)
    {
        String c = "";
        if (angle == -2.0)
        {
            c = "";
        } else if (angle == -1.0)
        {
            c = "X";
        } else if ((angle < 22.5 && angle >= 0.0) || angle > 337.5)
        {
            c = "\u2b06";
        } else if (angle < 67.5 && angle > 22.5)
        {
            c = "\u2b08";
        } else if (angle < 112.5 && angle > 67.5)
        {
            c = "\u27a1";
        } else if (angle < 157.5 && angle > 112.5)
        {
            c = "\u2b0a";
        } else if (angle < 202.5 && angle > 157.5)
        {
            c = "\u2b07";
        } else if (angle < 247.5 && angle > 202.5)
        {
            c = "\u2b0b";
        } else if (angle < 292.5 && angle > 247.5)
        {
            c = "\u2b05";
        } else if (angle < 337.5 && angle > 292.5)
        {
            c = "\u2b09";
        }
        return c;
    }

}
