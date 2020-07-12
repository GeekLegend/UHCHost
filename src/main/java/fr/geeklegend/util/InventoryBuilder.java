package fr.geeklegend.util;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.inventory.Inventory;

public class InventoryBuilder
{

    @Getter
    public Inventory inventory;

    @Getter
    @Setter
    public int size;

    @Getter
    @Setter
    public String name;

    public InventoryBuilder(int size, String name)
    {
        this.size = size;
        this.name = name;
    }
}