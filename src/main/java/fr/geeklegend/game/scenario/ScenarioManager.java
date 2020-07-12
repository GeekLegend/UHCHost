package fr.geeklegend.game.scenario;

import lombok.Getter;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScenarioManager
{

    @Getter
    private BackpackScenario backpackScenario;
    private BareboneScenario bareBoneScenario;
    private BloodDiamondScenario bloodDiamondScenario;
    private BloodEnchantScenario bloodEnchantScenario;
    private BowlessScenario bowLessScenario;
    private CoallessScenario coalLessScenario;
    private ColdWeaponScenario coldWeaponScenario;
    private CutcleanScenario cutCleanScenario;
    private DiamondLessScenario diamondLessScenario;
    private FirelessScenario fireLessScenario;
    private GoldlessScenario goldLessScenario;
    private HorselessScenario horseLessScenario;
    private IronlessScenario ironLessScenario;
    private LimitationScenario limitationScenario;
    private EnchantlessScenario enchantLessScenario;
    private NineSlotScenario nineSlotScenario;
    private NoCleanScenario noCleanScenario;
    private OpUhcScenario opUhcScenario;
    private RodlessScenario rodlessScenario;
    private SharedHealthScenario sharedHealthScenario;
    private SoupScenario soupScenario;
    private StatlessScenario statLessScenario;
    private TimeBombScenario timeBombScenario;
    private VanillaPlusScenario vanillaPlusScenario;

    @Getter
    private List<Scenario> scenarios;

    public ScenarioManager()
    {
        this.scenarios = new ArrayList<>();
        this.backpackScenario = new BackpackScenario("Backpacks", Material.CHEST, Arrays.asList("�fToutes les �quipes ont acc�s � un inventaire", "�fpartag�, vous pouvez donc vous partager", "�fdes objets sans �tre proche de votre co�quipier.", "�fLes objets de cet inventaire", "�fseront jet� une fois que la derni�re", "�fpersonne de votre �quipe sera morte.", "�fUtilisez /bp pour ouvrir cet inventaire."), false);
        this.bareBoneScenario = new BareboneScenario("Barebones", Material.BONE, Arrays.asList("�fTous les minerais hormis le fer", "�fet le charbon vous donne du fer.", "�fUn diamant, une pomme d'or, x32 fl�ches", "�fet deux fils seront jet�s au sol", "�f� la mort d'un joueur.", "�fLes tables d'enchantements, les enclumes", "�fet les pommes d'or ne peuvent", "�fpas �tre fabriqu�."), false);
        this.bloodDiamondScenario = new BloodDiamondScenario("Blood Diamonds", Material.REDSTONE, Arrays.asList("�fA chaque fois que vous minez", "�fun minerai de diamant,", "�fvous perdez la moiti� d'un coeur."), false);
        this.bloodEnchantScenario = new BloodEnchantScenario("Blood Enchants", Material.ENCHANTMENT_TABLE, Arrays.asList("�fSi vous avez des coeurs d'absorption,", "�fvous perdez premi�rement les coeurs d'absorptions."), false);
        this.bowLessScenario = new BowlessScenario("Bowless", Material.BOW, Arrays.asList("�fVous ne pouvez pas fabriquer d'arc.", "�fLes arcs ne peuvent pas �tre", "�fr�cup�r�s en tuant un mob (squelette par exemple)."), false);
        this.coalLessScenario = new CoallessScenario("Coalless", Material.COAL, Arrays.asList("�fVous ne pouvez pas miner", "�fou exploser le charbon.", "�fVous obtiendrez deux charbons si vous tuez quelqu'un."), false);
        this.coldWeaponScenario = new ColdWeaponScenario("Cold Weapons", Material.IRON_SWORD, Arrays.asList("�fVous ne pouvez pas obtenir d'enchantement", "�fAura de feu ou Flamme.", "�fVous ne pouvez pas trouver de livre", "�favec l'enchantement Aura de feu ou Flamme."), false);
        this.cutCleanScenario = new CutcleanScenario("Cutclean", Material.SHEARS, Arrays.asList("�fLes minerais sont cuits", "�florsque vous les minez.", "�fLa nourriture est d�j� cuite.", "�fLe pourcentage de chance d'obtenir", "�fdu Silex, Plumes, Cuir est de 100%."), false);
        this.diamondLessScenario = new DiamondLessScenario("Diamondless", Material.DIAMOND, Arrays.asList("�fVous ne pouvez pas miner", "�fou exploser du dimamant.", "�fVous obtiendrez un diamant", "�fsi vous tuez quelqu'un."), false);
        this.fireLessScenario = new FirelessScenario("Fireless", Material.FLINT_AND_STEEL, Arrays.asList("�fAucun d�g�ts de feu ou de lave."), false);
        this.goldLessScenario = new GoldlessScenario("Goldless", Material.GOLD_INGOT, Arrays.asList("�fVous ne pouvez pas miner", "�fou exploser de l'or.", "�fVous obtiendrez une Golden Head", "�fet x16 or si vous tuez quelqu'un."), false);
        this.horseLessScenario = new HorselessScenario("Horseless", Material.SADDLE, Arrays.asList("�fVous ne pouvez pas adopter de chevaux."), false);
        this.ironLessScenario = new IronlessScenario("Ironless", Material.IRON_INGOT, Arrays.asList("�fVous ne pouvez pas miner", "�fou exploser du fer.", "�fVous obtiendrez x8 fer", "�fsi vous tuez quelqu'un."), false);
        this.limitationScenario = new LimitationScenario("Limitations", Material.BARRIER, Arrays.asList("�fVous ne pouvez miner que:", "�fx16 diamants", "�fx32 or", "�fx64 fer"), false);
        this.enchantLessScenario = new EnchantlessScenario("Enchantless", Material.ENCHANTMENT_TABLE, Arrays.asList("�fVous ne pouvez pas fabriquer", "�fde table d'enchantement."), false);
        this.nineSlotScenario = new NineSlotScenario("Nine Slot", Material.NAME_TAG, Arrays.asList("�fVous ne pouvez utiliser que votre hotbar (les 9 premiers slots)"), false);
        this.noCleanScenario = new NoCleanScenario("No Clean", Material.BEDROCK, Arrays.asList("�fVous avez 30 secondes d'invincibilit�", "�florsque vous tuez un joueur.", "�fSi vous essayer d'infliger des", "�fd�g�ts � un joueur durant", "�fvotre invincibilit�, vous allez donc", "�fperdre votre invincibilit�.", "�fPoser de la lave, du feu ou", "�fde la TNT vous fera perdre", "�fvotre invincibilit�.", "�fSi vous �tes invincible, votre nom", "�fdans le TAB & au dessus", "�fde votre t�te sera rouge."), false);
        this.opUhcScenario = new OpUhcScenario("OP UHC", Material.DIAMOND_CHESTPLATE, Arrays.asList("�fTous les minerais sont tripl�s", "�fVous obtiendrez al�atoirement des", "�feffets comme Vitesse, Resistance, Soin, Force"), false);
        this.rodlessScenario = new RodlessScenario("Rodless", Material.FISHING_ROD, Arrays.asList("�fLes cannes � p�ches sont d�sactiv�s."), false);
        this.sharedHealthScenario = new SharedHealthScenario("Shared Health", Material.REDSTONE_BLOCK, Arrays.asList("�fTous les d�g�ts et la", "�fr�g�n�ration sont partag�s", "�fentre �quipiers."), false);
        this.soupScenario = new SoupScenario("Soup", Material.MUSHROOM_SOUP, Arrays.asList("�fLes soupes de champignons vous", "�fdonnent 2 coeurs d�s que", "�fvous en utilisez une."), false);
        this.statLessScenario = new StatlessScenario("Statless", Material.PAPER, Arrays.asList("�fLes stats ne sont pas", "�fcompt�es durant la partie."), false);
        this.timeBombScenario = new TimeBombScenario("TimeBomb", Material.TNT, Arrays.asList("�fTous les objets d'un joueur mort", "�fsont stock�s dans un double coffre", "�fCe coffre explose 30 secondes", "�fapr�s la mort du joueur."), false);
        this.vanillaPlusScenario = new VanillaPlusScenario("Vanilla+", Material.GRASS, Arrays.asList("�fLe pourcentage de chance", "�fd'avoir des pommes et", "�fdes silex est augment�."), false);

        loadAll();
    }

    private void loadAll()
    {
        scenarios.add(backpackScenario);
        scenarios.add(bareBoneScenario);
        scenarios.add(bloodDiamondScenario);
        scenarios.add(bloodEnchantScenario);
        scenarios.add(bowLessScenario);
        scenarios.add(coalLessScenario);
        scenarios.add(coldWeaponScenario);
        scenarios.add(cutCleanScenario);
        scenarios.add(diamondLessScenario);
        scenarios.add(fireLessScenario);
        scenarios.add(goldLessScenario);
        scenarios.add(horseLessScenario);
        scenarios.add(ironLessScenario);
        scenarios.add(limitationScenario);
        scenarios.add(enchantLessScenario);
        scenarios.add(nineSlotScenario);
        scenarios.add(noCleanScenario);
        scenarios.add(opUhcScenario);
        scenarios.add(rodlessScenario);
        scenarios.add(sharedHealthScenario);
        scenarios.add(soupScenario);
        scenarios.add(statLessScenario);
        scenarios.add(timeBombScenario);
        scenarios.add(vanillaPlusScenario);
    }
}
