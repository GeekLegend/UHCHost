package fr.geeklegend.uhchost.world;

import fr.geeklegend.uhchost.util.Util;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import java.io.File;

public class WorldManager
{

    @Getter
    public static final World WORLD = Bukkit.getWorlds().get(0);

    @Getter
    @Setter
    private int size;

    @Getter
    private MapGenerator mapGenerator;

    public WorldManager(int size)
    {
        setup();
        clearEntities();
        createSpawn();

        this.size = size;
        this.mapGenerator = new MapGenerator(WORLD, size);
    }

    private void setup()
    {
        WORLD.setGameRuleValue("naturalRegeneration", "false");
        WORLD.setGameRuleValue("doDaylightCycle", "false");
        WORLD.setThundering(false);
        WORLD.setTime(1000);
    }

    public void clearEntities()
    {
        for (Entity entity : WORLD.getEntities())
        {
            if (entity != null && !entity.getType().equals(EntityType.PLAYER))
            {
                entity.remove();
            }
        }
    }

    public void createSpawn()
    {
        Location block;

        for (int y = 199; y <= 204; y++)
        {
            for (int x = -20; x <= 20; x++)
            {
                for (int z = -20; z <= 20; z++)
                {
                    block = new Location(WORLD, x, y, z);
                    block.getBlock().setType(Material.STAINED_GLASS_PANE);
                }
            }
        }

        for (int x = -19; x <= 19; x++)
        {
            for (int z = -19; z <= 19; z++)
            {
                block = new Location(WORLD, x, 200, z);
                block.getBlock().setType(Material.AIR);
            }
        }

        for (int y = 199; y <= 199; y++)
        {
            for (int x = -20; x <= 20; x++)
            {
                for (int z = -20; z <= 20; z++)
                {
                    block = new Location(WORLD, x, y, z);
                    block.getBlock().setType(Material.STAINED_GLASS);
                    block.getBlock().setData((byte) Util.getRandomColor());
                }
            }
        }

        for (int x = -18; x <= 18; x++)
        {
            for (int z = -18; z <= 18; z++)
            {
                block = new Location(WORLD, x, 200, z);
                block.getBlock().setType(Material.AIR);
            }
        }

        for (int y = 201; y <= 204; y++)
        {
            for (int x = -19; x <= 19; x++)
            {
                for (int z = -19; z <= 19; z++)
                {
                    block = new Location(WORLD, x, y, z);
                    block.getBlock().setType(Material.AIR);
                }
            }
        }
    }

    public void removeSpawn()
    {
        for (int y = 204; y >= 199; y--)
        {
            for (int x = -20; x <= 20; x++)
            {
                for (int z = -20; z <= 20; z++)
                {
                    Location block = new Location(WORLD, x, y, z);

                    if (block != null)
                    {
                        if (block.getBlock() != null)
                        {
                            block.getBlock().setType(Material.AIR);
                        }
                    }
                }
            }
        }
    }

    public boolean deleteWorld(File path)
    {
        if (path.exists())
        {
            File files[] = path.listFiles();
            for (int i = 0; i < files.length; i++)
            {
                if (files[i].isDirectory())
                {
                    deleteWorld(files[i]);
                } else
                {
                    files[i].delete();
                }
            }
        }
        return (path.delete());
    }

}
