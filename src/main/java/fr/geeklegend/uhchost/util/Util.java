package fr.geeklegend.uhchost.util;

import com.google.common.collect.Lists;
import org.bukkit.DyeColor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Util
{

    public static String getTimerFormat(String pattern, int timer)
    {
        return new SimpleDateFormat(pattern).format(Integer.valueOf(timer * 1000));
    }

    public static String getDate()
    {
        return new SimpleDateFormat("dd/MM/yyyy").format(new Date());
    }

    public static int getRandomColor()
    {
        return new Random().nextInt(15);
    }

    public static DyeColor getRandomDyeColor()
    {
        List<DyeColor> givenList = Lists.newArrayList(DyeColor.RED, DyeColor.YELLOW, DyeColor.BLACK, DyeColor.BLUE,
                DyeColor.BROWN, DyeColor.CYAN, DyeColor.GRAY, DyeColor.GREEN,
                DyeColor.LIGHT_BLUE, DyeColor.LIME, DyeColor.MAGENTA, DyeColor.ORANGE,
                DyeColor.PINK, DyeColor.PURPLE, DyeColor.SILVER, DyeColor.WHITE);
        int randomIndex = new Random().nextInt(givenList.size());
        DyeColor randomDyeColor = givenList.get(randomIndex);

        givenList.remove(randomIndex);

        if (randomDyeColor != null)
        {
            return randomDyeColor;
        }

        return null;
    }

}
