package fr.geeklegend.uhchost.util;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class Constant
{

    /*
     * Server settings contants
     */
    public static final String DEFAULT_SERVER_NAME = "§d§lUHC HOST";

    public static final String HOST_RANK_MAIN_NAME = "§a§lHOTE PRINCIPAL";
    public static final String HOST_RANK_SECONDARY_NAME = "§e§lHOTE SECONDAIRE";
    /*
     * Global contants
     */
    public static final String PREFIX = DEFAULT_SERVER_NAME + " §8»";

    public static final String NO_PERMISSION_MESSAGE = "§cVous n'avez pas la permission.";

    /*
     * Preparation constants
     */

    public static final String JOIN_MESSAGE = "§d%playername% §fa rejoint la partie. §d(%online%/%maxonline%)";

    public static final String DISCONNECTED_MESSAGE = PREFIX + " §cCe joueur n'est pas en ligne.";

    public static final String HOST_ALREADY_SENDER_MESSAGE = PREFIX + " §cVous êtes déja un hôte.";
    public static final String HOST_ALREADY_TARGET_MESSAGE = PREFIX + " §cCe joueur est déja un hôte.";
    public static final String HOST_ALREADY_BANNED_MESSAGE = PREFIX + " §cCe joueur est déja banni.";
    public static final String HOST_NOT_BANNED_MESSAGE = PREFIX + " §cCe joueur n'est pas banni.";
    public static final String HOST_NOT_MESSAGE = PREFIX + " §cVous n'êtes pas un hôte.";
    public static final String HOST_CANT_MESSAGE = PREFIX + " §cVous ne pouvez pas faire cela sur vous.";
    public static final String HOST_EMPTY_MESSAGE = PREFIX + " §cIl n'y a aucun hôte.";
    public static final String HOST_BANS_EMPTY_MESSAGE = PREFIX + " §cIl n'y a aucun ban.";
    public static final String HOST_OP_SENDER_MESSAGE = PREFIX + " §fLe joueur §d%playername% §fest désormais un §dhôte secondaire.";
    public static final String HOST_OP_TARGET_MESSAGE = PREFIX + " §fVous êtes désormais un §dhôte secondaire.";
    public static final String HOST_DEOP_SENDER_MESSAGE = PREFIX + " §fLe joueur §d%playername% §fn'est plus un §dhôte secondaire.";
    public static final String HOST_DEOP_TARGET_MESSAGE = PREFIX + " §fVous n'êtes plus un §dhôte secondaire.";
    public static final String HOST_DEOP_CANT_MESSAGE = PREFIX + " §cVous ne pouvez pas retirer un §dhôte principal.";
    public static final String HOST_KICK_SENDER_MESSAGE = PREFIX + " §fLe joueur §d%playername% §fa été éjecté.";
    public static final String HOST_KICK_TARGET_MESSAGE = "§dVous avez été éjecté du serveur.";
    public static final String HOST_BAN_SENDER_MESSAGE = PREFIX + " §fLe joueur §d%playername% §fa été banni.";
    public static final String HOST_BAN_TARGET_MESSAGE = "§dVous avez été banni du serveur.\n \n§d§lDemande de déban sur Discord:\nhttps://discord.gg/rcfFcmm";
    public static final String HOST_UNBAN_SENDER_MESSAGE = PREFIX + " §fLe joueur §d%playername% §fa été débanni.";
    public static final String HOST_INVENTORY_HELP_SAVE_MESSAGE = PREFIX + " §fFaites §d/save §fpour sauvegarder l'inventaire.";
    public static final String HOST_INVENTORY_SAVED_MESSAGE = PREFIX + " §fInventaire sauvegarder.";
    public static final String HOST_INVENTORY_ALREADY_MESSAGE = PREFIX + " §cUn autre hôte est déjà dans cette inventaire.";
    public static final String HOST_INVENTORY_NOT_MESSAGE = PREFIX + " §cVous n'êtes pas en edit kit.";
    public static final String HOST_SERVER_NAME_MESSAGE = PREFIX + " §fLe nom du serveur est désormais %servername%.";
    public static final String HOST_SERVER_ACCESS_MESSAGE = PREFIX + " §fLe serveur est désormais %status%.";

    public static final String NO_FINISH_GENERATION_MESSAGE = PREFIX + " §cGénération non terminée, veuillez patienter.";

    public static final String START_SCHEDULER_CANCELLED_MESSAGE = PREFIX + " §cIl manque %online% joueurs pour commencer la partie.";

    public static final GameMode JOIN_GAMEMODE = GameMode.ADVENTURE;

    public static final Location JOIN_LOCATION = new Location(Bukkit.getWorlds().get(0), 0, 200, 0);

    public static final int JOIN_ITEM_HOST_SETTINGS_SLOT = 0;
    public static final int JOIN_ITEM_HOST_START_SLOT = 4;
    public static final int JOIN_ITEM_SCENARIOS_SLOT = 7;
    public static final int JOIN_ITEM_PATCHNOTE_SLOT = 8;

    public static final ItemStack JOIN_ITEM_HOST_SETTINGS_ITEM = new ItemBuilder(Material.ANVIL).setName("§eParamètres §8• §f(Clique droit)").toItemStack();
    public static final ItemStack JOIN_ITEM_HOST_START_ITEM = new ItemBuilder(Material.REDSTONE_TORCH_ON).setName("§eDémarrer §8• §f(Clique droit)").toItemStack();
    public static final ItemStack JOIN_ITEM_SCENARIOS_ITEM = new ItemBuilder(Material.PAPER).setName("§eScénarios §8• §f(Clique droit)").toItemStack();
    public static final ItemStack JOIN_ITEM_PATCHNOTE_ITEM = new ItemBuilder(Material.WRITTEN_BOOK).setAuthor("Serveur").setTitle("§ePatchnote §8• §f(Clique droit)").setPages(Arrays.asList("Patchnote 0.1")).toItemStack();

    public static final int GLOBAL_INVENTORY_ALL_ENABLED_SLOT = 46;
    public static final int GLOBAL_INVENTORY_ALL_DISABLED_SLOT = 47;
    public static final int GLOBAL_INVENTORY_BACK_SLOT = 48;
    public static final int GLOBAL_INVENTORY_CLOSE_SLOT = 49;

    public static final ItemStack GLOBAL_INVENTORY_ALL_ENABLED_ITEM = new ItemBuilder(Material.SLIME_BALL).setName("§aActiver tout").toItemStack();
    public static final ItemStack GLOBAL_INVENTORY_ALL_DISABLED_ITEM = new ItemBuilder(Material.INK_SACK).setDurability((byte) 1).setName("§cDésactiver tout").toItemStack();
    public static final ItemStack GLOBAL_INVENTORY_BACK_ITEM = new ItemBuilder(Material.ARROW).setName("§aRetour").toItemStack();
    public static final ItemStack GLOBAL_INVENTORY_CLOSE_ITEM = new ItemBuilder(Material.REDSTONE).setName("§cFermer").toItemStack();

    public static final int SETTINGS_INVENTORY_SIZE = 6 * 9;
    public static final int SCENARIOS_INVENTORY_SIZE = 6 * 9;
    public static final int SCENARIOS_VIEW_INVENTORY_SIZE = 6 * 9;
    public static final int PRESETS_INVENTORY_SIZE = 6 * 9;

    public static final String SETTINGS_INVENTORY_NAME = "Paramètres";
    public static final String SCENARIOS_INVENTORY_NAME = "Scénarios";
    public static final String SCENARIOS_VIEW_INVENTORY_NAME = "Liste des scénarios";
    public static final String PRESETS_INVENTORY_NAME = "Presets";

    public static final int SETTINGS_INVENTORY_SPAWN_STUFF_SLOT = 10;
    public static final int SETTINGS_INVENTORY_DEATH_STUFF_SLOT = 11;
    public static final int SETTINGS_INVENTORY_WORLD_SLOT = 15;
    public static final int SETTINGS_INVENTORY_PRESETS_SLOT = 22;
    public static final int SETTINGS_INVENTORY_SCENARIOS_SLOT = 31;
    public static final int SETTINGS_INVENTORY_SERVER_NAME_SLOT = 42;
    public static final int SETTINGS_INVENTORY_SERVER_ACCESS_SLOT = 43;
    public static final int SETTINGS_INVENTORY_SERVER_SLOTS_SLOT = 44;

    public static final ItemStack SETTINGS_INVENTORY_SPAWN_STUFF_ITEM = new ItemBuilder(Material.CHEST).setName("§eInventaire de départ").toItemStack();
    public static final ItemStack SETTINGS_INVENTORY_DEATH_STUFF_ITEM = new ItemBuilder(Material.ENDER_CHEST).setName("§eInventaire de mort").toItemStack();
    public static final ItemStack SETTINGS_INVENTORY_WORLD_ITEM = new ItemBuilder(Material.GRASS).setName("§eMonde").toItemStack();
    public static final ItemStack SETTINGS_INVENTORY_PRESETS_ITEM = new ItemBuilder(Material.WORKBENCH).setName("§ePresets").toItemStack();
    public static final ItemStack SETTINGS_INVENTORY_SCENARIOS_ITEM = new ItemBuilder(Material.PAPER).setName("§eScénarios").toItemStack();
    public static final ItemStack SETTINGS_INVENTORY_SERVER_NAME_ITEM = new ItemBuilder(Material.NAME_TAG).setName("§eNom du serveur").toItemStack();
    public static final ItemStack SETTINGS_INVENTORY_SERVER_ACCESS_ITEM = new ItemBuilder(Material.STORAGE_MINECART).setName("§eAccès du serveur").toItemStack();
    public static final ItemStack SETTINGS_INVENTORY_SERVER_SLOTS_ITEM = new ItemBuilder(Material.REDSTONE_COMPARATOR).setName("§eGestion des slots").toItemStack();

    public static final int SCHEDULER_START_TIMER = 10;

    /*
     * Pregame constants
     */

    /*
     * Finish constants
     */
}
