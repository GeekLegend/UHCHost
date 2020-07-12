package fr.geeklegend.util;

import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ItemBuilder
{

    private ItemStack is;

    private ArrayList<Pattern> patterns;

    public ItemBuilder(Material m)
    {
        this(m, 1);
    }

    public ItemBuilder(ItemStack is)
    {
        this.is = is;
    }

    public ItemBuilder(Material m, int amount)
    {
        this.is = new ItemStack(m, amount);
    }

    public ItemBuilder(Material m, int amount, byte durability)
    {
        this.is = new ItemStack(m, amount, durability);
    }

    public ItemBuilder clone()
    {
        return new ItemBuilder(this.is);
    }

    public enum BannerPreset
    {
        BAR, BACK, NEXT, HEART, CIRCLE_STAR, CROSS, YIN_YANG, LOSANGE, SUBSTRACT, ADD;
    }

    public ItemBuilder setDurability(short dur)
    {
        this.is.setDurability(dur);
        return this;
    }

    public ItemBuilder setName(String name)
    {
        ItemMeta im = this.is.getItemMeta();
        im.setDisplayName(name);
        this.is.setItemMeta(im);
        return this;
    }

    public ItemBuilder addStoredEnchantment(Enchantment ench, int level)
    {
        ItemMeta im = this.is.getItemMeta();
        ((EnchantmentStorageMeta) im).addStoredEnchant(ench, level, true);
        this.is.setItemMeta(im);
        return this;
    }

    public ItemBuilder addUnsafeEnchantment(Enchantment ench, int level)
    {
        this.is.addUnsafeEnchantment(ench, level);
        return this;
    }

    public ItemBuilder removeEnchantment(Enchantment ench)
    {
        this.is.removeEnchantment(ench);
        return this;
    }

    public ItemBuilder removePotionLore()
    {
        ItemMeta meta = this.is.getItemMeta();
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_POTION_EFFECTS});
        this.is.setItemMeta(meta);

        return this;
    }

    public ItemBuilder hideAllFlags()
    {
        ItemMeta meta = is.getItemMeta();
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS});
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_PLACED_ON});
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_POTION_EFFECTS});
        is.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setSkullOwner(String owner)
    {
        SkullMeta im = (SkullMeta) this.is.getItemMeta();
        im.setOwner(owner);
        this.is.setItemMeta(im);

        return this;
    }

    public ItemBuilder addEnchant(Enchantment ench, int level)
    {
        ItemMeta im = this.is.getItemMeta();
        im.addEnchant(ench, level, true);
        this.is.setItemMeta(im);
        return this;
    }

    public ItemBuilder addEnchantments(Map<Enchantment, Integer> enchantments)
    {
        this.is.addEnchantments(enchantments);
        return this;
    }

    public ItemBuilder setGlowing()
    {
        try
        {
            ItemMeta meta = this.is.getItemMeta();
            meta.addEnchant(Enchantment.DURABILITY, 0, true);
            meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS});
            this.is.setItemMeta(meta);
        } catch (Exception localException)
        {
        }
        return this;
    }

    public ItemBuilder setBaseColor(DyeColor baseColor) {
        if (is.getType().equals(Material.BANNER)) {
            BannerMeta meta = (BannerMeta) is.getItemMeta();
            meta.setBaseColor(baseColor);
            is.setItemMeta(meta);
        }
        return this;
    }

    public ItemBuilder setPatterns(ArrayList<Pattern> patterns)
    {
        if (is.getType().equals(Material.BANNER))
        {
            BannerMeta bannerMeta = (BannerMeta) is.getItemMeta();
            bannerMeta.setPatterns(patterns);
            is.setItemMeta(bannerMeta);
        }
        return this;
    }

    public ItemBuilder addBannerPreset(BannerPreset bannerPreset, DyeColor patternColor)
    {
        if (bannerPreset == null) return this;
        if (is.getType().equals(Material.BANNER))
        {
            BannerMeta bannerMeta = (BannerMeta) is.getItemMeta();
            DyeColor dyeColor = bannerMeta.getBaseColor();
            switch (bannerPreset)
            {
                case BAR:
                    addAsyncPattern(new Pattern(patternColor, PatternType.STRIPE_DOWNRIGHT), true);
                    break;
                case BACK:
                    addAsyncPattern(new Pattern(patternColor, PatternType.RHOMBUS_MIDDLE), false);
                    addAsyncPattern(new Pattern(dyeColor, PatternType.SQUARE_BOTTOM_RIGHT), false);
                    addAsyncPattern(new Pattern(dyeColor, PatternType.SQUARE_TOP_RIGHT), false);
                    addAsyncPattern(new Pattern(dyeColor, PatternType.STRIPE_RIGHT), true);
                    break;
                case NEXT:
                    addAsyncPattern(new Pattern(patternColor, PatternType.RHOMBUS_MIDDLE), false);
                    addAsyncPattern(new Pattern(dyeColor, PatternType.SQUARE_BOTTOM_LEFT), false);
                    addAsyncPattern(new Pattern(dyeColor, PatternType.SQUARE_TOP_LEFT), false);
                    addAsyncPattern(new Pattern(dyeColor, PatternType.STRIPE_LEFT), true);
                    break;
                case HEART:
                    addAsyncPattern(new Pattern(patternColor, PatternType.RHOMBUS_MIDDLE), false);
                    addAsyncPattern(new Pattern(dyeColor, PatternType.TRIANGLE_TOP), true);
                    break;
                case CIRCLE_STAR:
                    addAsyncPattern(new Pattern(patternColor, PatternType.RHOMBUS_MIDDLE), false);
                    addAsyncPattern(new Pattern(patternColor, PatternType.FLOWER), false);
                    addAsyncPattern(new Pattern(dyeColor, PatternType.CIRCLE_MIDDLE), true);
                    break;
                case CROSS:
                    addAsyncPattern(new Pattern(patternColor, PatternType.CROSS), false);
                    addAsyncPattern(new Pattern(dyeColor, PatternType.CURLY_BORDER), true);
                    break;
                case YIN_YANG:
                    addAsyncPattern(new Pattern(patternColor, PatternType.SQUARE_BOTTOM_RIGHT), false);
                    addAsyncPattern(new Pattern(dyeColor, PatternType.STRIPE_RIGHT), false);
                    addAsyncPattern(new Pattern(patternColor, PatternType.DIAGONAL_LEFT), false);
                    addAsyncPattern(new Pattern(dyeColor, PatternType.SQUARE_TOP_LEFT), false);
                    addAsyncPattern(new Pattern(patternColor, PatternType.TRIANGLE_TOP), false);
                    addAsyncPattern(new Pattern(dyeColor, PatternType.STRIPE_RIGHT), false);
                    addAsyncPattern(new Pattern(dyeColor, PatternType.TRIANGLE_BOTTOM), false);
                    addAsyncPattern(new Pattern(patternColor, PatternType.STRIPE_LEFT), true);
                    break;
                case LOSANGE:
                    addAsyncPattern(new Pattern(patternColor, PatternType.RHOMBUS_MIDDLE), true);
                    break;
                case SUBSTRACT:
                    addAsyncPattern(new Pattern(patternColor, PatternType.STRIPE_MIDDLE), false);
                    addAsyncPattern(new Pattern(dyeColor, PatternType.BORDER), true);
                    break;
                case ADD:
                    addAsyncPattern(new Pattern(patternColor, PatternType.STRAIGHT_CROSS), false);
                    addAsyncPattern(new Pattern(dyeColor, PatternType.STRIPE_TOP), false);
                    addAsyncPattern(new Pattern(dyeColor, PatternType.STRIPE_BOTTOM), false);
                    addAsyncPattern(new Pattern(dyeColor, PatternType.BORDER), true);
                    break;
            }
        }
        return this;
    }

    private void addAsyncPattern(Pattern pattern, boolean calcul)
    {
        if (patterns == null) patterns = new ArrayList();
        if (calcul)
        {
            this.patterns.add(pattern);
            BannerMeta meta = (BannerMeta) is.getItemMeta();
            for (Pattern currentPattern : patterns)
            {
                meta.addPattern(currentPattern);
            }
            patterns.clear();
            is.setItemMeta(meta);
        } else
        {
            patterns.add(pattern);
        }
    }

    public ItemBuilder setInfinityDurability()
    {
        this.is.setDurability((short) Short.MAX_VALUE);
        return this;
    }

    public ItemBuilder unbreakable(boolean unbreakable)
    {
        ItemMeta im = this.is.getItemMeta();
        im.spigot().setUnbreakable(unbreakable);
        return this;
    }

    public ItemBuilder setLore(String... lore)
    {
        ItemMeta im = this.is.getItemMeta();
        im.setLore(Arrays.asList(lore));
        this.is.setItemMeta(im);
        return this;
    }

    public ItemBuilder setLore(List<String> lore)
    {
        ItemMeta im = this.is.getItemMeta();
        im.setLore(lore);
        this.is.setItemMeta(im);
        return this;
    }

    public ItemBuilder removeLoreLine(String line)
    {
        ItemMeta im = this.is.getItemMeta();
        List<String> lore = new ArrayList<String>(im.getLore());
        if (!lore.contains(line))
        {
            return this;
        }
        lore.remove(line);
        im.setLore(lore);
        this.is.setItemMeta(im);
        return this;
    }

    public ItemBuilder removeLoreLine(int index)
    {
        ItemMeta im = this.is.getItemMeta();
        List<String> lore = new ArrayList<String>(im.getLore());
        if ((index < 0) || (index > lore.size()))
        {
            return this;
        }
        lore.remove(index);
        im.setLore(lore);
        this.is.setItemMeta(im);
        return this;
    }

    public ItemBuilder addLoreLine(String line)
    {
        ItemMeta im = this.is.getItemMeta();
        List<String> lore = new ArrayList<String>();
        if (im.hasLore())
        {
            lore = new ArrayList<String>(im.getLore());
        }
        lore.add(line);
        im.setLore(lore);
        this.is.setItemMeta(im);
        return this;
    }

    public ItemBuilder addLoreLine(String line, int pos)
    {
        ItemMeta im = this.is.getItemMeta();
        List<String> lore = new ArrayList<String>(im.getLore());
        lore.set(pos, line);
        im.setLore(lore);
        this.is.setItemMeta(im);
        return this;
    }

    public ItemBuilder setDyeColor(DyeColor color)
    {
        this.is.setDurability(color.getData());
        return this;
    }

    @Deprecated
    public ItemBuilder setWoolColor(DyeColor color)
    {
        if (!this.is.getType().equals(Material.WOOL))
        {
            return this;
        }
        this.is.setDurability(color.getData());
        return this;
    }

    public ItemBuilder setLeatherArmorColor(Color color)
    {
        try
        {
            LeatherArmorMeta im = (LeatherArmorMeta) this.is.getItemMeta();
            im.setColor(color);
            this.is.setItemMeta(im);
        } catch (ClassCastException localClassCastException)
        {
        }
        return this;
    }

    public ItemBuilder setAuthor(String author)
    {
        BookMeta bookMeta = (BookMeta) is.getItemMeta();
        bookMeta.setAuthor(author);

        is.setItemMeta(bookMeta);

        return this;
    }

    public ItemBuilder setTitle(String title)
    {
        BookMeta bookMeta = (BookMeta) is.getItemMeta();
        bookMeta.setTitle(title);

        is.setItemMeta(bookMeta);

        return this;
    }

    public ItemBuilder setPages(List<String> pages)
    {
        BookMeta bookMeta = (BookMeta) is.getItemMeta();
        bookMeta.setPages(pages);

        is.setItemMeta(bookMeta);

        return this;
    }

    public ItemStack toItemStack()
    {
        return this.is;
    }
}