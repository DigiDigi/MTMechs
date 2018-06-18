package digidigi.mtm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

import com.google.common.io.Files;

import net.minecraft.block.Block;
import net.minecraft.command.CommandHandler;
import net.minecraft.creativetab.CreativeTabs;

import net.minecraft.item.Item;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.ServerStarting;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.Mod.ServerStopped;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppedEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import digidigi.mtm.common.BlockMT;
import digidigi.mtm.common.CraftHandler;
import digidigi.mtm.common.EntityMT;
import digidigi.mtm.common.EntityMoogle;
import digidigi.mtm.common.EntityPA;
import digidigi.mtm.common.EntityTA;
import digidigi.mtm.common.EntityTest;
import digidigi.mtm.common.GuiHandler;
import digidigi.mtm.common.MagitekBlocks;
import digidigi.mtm.common.TileEntityMT;

import digidigi.mtm.common.MagitekItems;
import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;

import digidigi.mtm.client.RenderFireBeam;
import digidigi.mtm.client.RenderFireBeams;
import digidigi.mtm.client.SoundHandler;
import digidigi.mtm.network.PacketHandler;
import digidigi.mtm.common.CommonProxy;
import digidigi.mtm.config.External;
import digidigi.mtm.config.Identities;
import digidigi.mtm.config.Recipes;
import digidigi.mtm.config.Reference;

import digidigi.mtm.item.ItemCustomRecord;
import digidigi.mtm.item.ItemMagicite;
import digidigi.mtm.item.ItemMechArm;
import digidigi.mtm.item.ItemMechLeg;
import digidigi.mtm.item.ItemMechPA;
import digidigi.mtm.item.ItemMechTorso;
import digidigi.mtm.item.ItemMechStack;
import digidigi.mtm.item.ItemMech;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = false,
            channels = {"mech", "mechanim", "mechfire", "mechfuel", "mechinvupdate", "mechinvreq", "mechmagi", "mechaux", "mechdock", "removeviewer", "mechsync", "renderFROMSERVER"}, packetHandler = PacketHandler.class)




public class MagitekMechs 
{

    @Instance(Reference.MOD_ID)
    public static MagitekMechs instance;
    public GuiHandler guihandler = new GuiHandler();
    public CraftHandler crafthandler = new CraftHandler();
    
	@SidedProxy(clientSide="digidigi.mtm.client.ClientProxy", serverSide="digidigi.mtm.common.CommonProxy")
	public static CommonProxy proxy;
    
	public static Block block_mt;
	
    private static List<ItemRecord> recordList = new ArrayList<ItemRecord>();

    @EventHandler
    public void serverStarting(FMLServerStartingEvent event)
    {
    CommandHandler commandManager = (CommandHandler) event.getServer().getCommandManager();
    //commandManager.registerCommand(new CommandBlendStyle());
    //commandManager.registerCommand(new CommandForwardDisplace());
    //commandManager.registerCommand(new CommandSideDisplace());
    //commandManager.registerCommand(new CommandVerticalDisplace());
    //commandManager.registerCommand(new CommandPitchModifier());
    //commandManager.registerCommand(new CommandYModifier());
    }
    
    @EventHandler
    public void serverStopped(FMLServerStoppedEvent event)
    {
    // Clear renders. Need to test if this works client side.
    RenderFireBeams.firebeams = new ArrayList<RenderFireBeam>();
    }
    
    public static CreativeTabs tabMTMechs = new CreativeTabs("MagitekMechs")
    {
        public ItemStack getIconItemStack() 
        {
                return new ItemStack(MagitekItems.magicite, 1, 0);
        }
    };
    
    @EventHandler
	public void preInit(FMLPreInitializationEvent event)
    {	    
	    
        // Language files.
        System.out.println("Magitek Mechs: Loading language files.");
        
        String[] languages = {"af_ZA", "ar_SA", "bg_BG", "ca_ES", "cs_CZ", "cy_GB", "da_DK", "de_DE", "el_GR", "en_CA", "en_GB", "en_US", "es_AR", "es_MX",
                              "es_UY", "es_VE", "et_EE", "eu_ES", "fr_CA", "fr_FR", "gl_ES", "he_IL", "hr_HR", "hu_HU", "is_IS", "it_IT", "ja_JP", "ka_GE", 
                              "ko_KR", "ky_KG", "lv_LV", "mi_NZ", "nb_NO", "nl_NL", "nn_NO", "pl_PL", "pt_BR", "pt_PT", "qya_AA", "ro_RO", "sk_SK", "sl_SI", 
                              "sr_SP", "sv_SE", "th_TH", "tlh_AA", "tr_TR", "uk_UA", "vi_VN", "zh_CN"};
        
        for (String language: languages)
        {
            try
            {
                ResourceBundle rb = ResourceBundle.getBundle("assets.mtm.lang." + language);
                Enumeration <String> keys = rb.getKeys();
                
                while (keys.hasMoreElements()) 
                {
                    String key = keys.nextElement();
                    String value = rb.getString(key);
                    //System.out.println(key + ": " + value);
                    LanguageRegistry.instance().addStringLocalization(key, language, value);
                    
                }
            }
            catch(MissingResourceException e)  
            {
                
            }

        }
        
	    // Configuration file.
	    Configuration config = new Configuration(event.getSuggestedConfigurationFile());
	    config.load();
	    
	    String ConfigVersion = config.get("config", "configlastupdate", External.configlastupdate).getString();
	    if (!ConfigVersion.equals(External.configlastupdate))
	    {
	        //Delete config file.
	        for(String name:config.getCategoryNames()) 
	        {
	            config.getCategory(name).clear();
	            config.removeCategory(config.getCategory(name));
	        }
	        config.get("config", "configlastupdate", External.configlastupdate).getString();

            config.save();
            System.out.println("New mtmechs.cfg file. Old one is being overwritten.");
	        
	    }
	    
	    
	    // config.get functions create default fields for the value if an existing one doesn't override it.
        External.customrecords= config.get("enable", "customrecords", false).getBoolean(false);
        
	    Identities.magiciteId = config.getItem("magicite", Identities.magiciteId).getInt();
        Identities.mecharmId = config.getItem("mecharm", Identities.mecharmId).getInt();
        Identities.mechlegId = config.getItem("mechleg", Identities.mechlegId).getInt();
        Identities.mechtorsoId = config.getItem("mechtorso", Identities.mechtorsoId).getInt();
        Identities.mechstackId = config.getItem("mechstack", Identities.mechstackId).getInt();
        Identities.mechitemId = config.getItem("mechitem", Identities.mechitemId).getInt();

        Identities.mechitemPAId = config.getItem("mechitemPAId", Identities.mechitemPAId).getInt();
        Identities.mtblockId = config.getBlock("mtblockId", Identities.mtblockId).getInt();
        
        Identities.recordId = config.getItem("customrecord", Identities.recordId).getInt();	    

        Recipes.magiciteTop = config.get("recipe", "magiciteA1", Recipes.magiciteTop).getString();
        Recipes.magiciteMid = config.get("recipe", "magiciteA2", Recipes.magiciteMid).getString();
        Recipes.magiciteBot = config.get("recipe", "magiciteA3", Recipes.magiciteBot).getString();
        Recipes.mecharmTop = config.get("recipe", "mecharm1", Recipes.mecharmTop).getString();
        Recipes.mecharmMid = config.get("recipe", "mecharm2", Recipes.mecharmMid).getString();
        Recipes.mecharmBot = config.get("recipe", "mecharm3", Recipes.mecharmBot).getString();
        Recipes.mechlegTop = config.get("recipe", "mechleg1", Recipes.mechlegTop).getString();
        Recipes.mechlegMid = config.get("recipe", "mechleg2", Recipes.mechlegMid).getString();
        Recipes.mechlegBot = config.get("recipe", "mechleg3", Recipes.mechlegBot).getString();
        Recipes.mechtorsoTop = config.get("recipe", "mechtorso1", Recipes.mechtorsoTop).getString();
        Recipes.mechtorsoMid = config.get("recipe", "mechtorso2", Recipes.mechtorsoMid).getString();
        Recipes.mechtorsoBot = config.get("recipe", "mechtorso3", Recipes.mechtorsoBot).getString();
        Recipes.mechstackTop = config.get("recipe", "mechstack1", Recipes.mechstackTop).getString();
        Recipes.mechstackMid = config.get("recipe", "mechstack2", Recipes.mechstackMid).getString();
        Recipes.mechstackBot = config.get("recipe", "mechstack3", Recipes.mechstackBot).getString();
        Recipes.mechATop = config.get("recipe", "mechitemA1", Recipes.mechATop).getString();
        Recipes.mechAMid = config.get("recipe", "mechitemA2", Recipes.mechAMid).getString();
        Recipes.mechABot = config.get("recipe", "mechitemA3", Recipes.mechABot).getString();
        Recipes.mechBTop = config.get("recipe", "mechitemB1", Recipes.mechBTop).getString();
        Recipes.mechBMid = config.get("recipe", "mechitemB2", Recipes.mechBMid).getString();
        Recipes.mechBBot = config.get("recipe", "mechitemB3", Recipes.mechBBot).getString();
        
	    
        config.addCustomCategoryComment("enable", "Disable the allocation of an id of a block or item if needed.");
        config.addCustomCategoryComment("item", "Note that customrecords will create an incremental id for every music file found in resources/mod/streaming if enabled." );
        config.addCustomCategoryComment("recipe", "Recipe materials can't be changed; only positions. Modify if a recipe overlaps in another mod." );
        config.addCustomCategoryComment("config", "config");
        
	    //GameRegistry.addRecipe(output, params)
        
        Item magicite = new ItemMagicite(Identities.magiciteId);
        magicite.setMaxStackSize(1).setUnlocalizedName("magicite");
        Item mecharm = new ItemMechArm(Identities.mecharmId);
        mecharm.setMaxStackSize(64).setUnlocalizedName("mecharm");
        Item mechleg = new ItemMechLeg(Identities.mechlegId);
        mechleg.setMaxStackSize(64).setUnlocalizedName("mechleg");
        Item mechtorso = new ItemMechTorso(Identities.mechtorsoId);
        mechtorso.setMaxStackSize(64).setUnlocalizedName("mechtorso");
        Item mechstack = new ItemMechStack(Identities.mechstackId);
        mechstack.setMaxStackSize(64).setUnlocalizedName("mechstack");
        Item mechitem = new ItemMech(Identities.mechitemId);
        mechitem.setMaxStackSize(64).setUnlocalizedName("mechitem");
        Item mechitemPA = new ItemMechPA(Identities.mechitemPAId);
        mechitemPA.setMaxStackSize(64).setUnlocalizedName("mechitemPA");

        GameRegistry.addRecipe(new ItemStack(magicite), new Object[] { Recipes.magiciteTop, Recipes.magiciteMid, Recipes.magiciteBot, 
        Character.valueOf('s'), Block.pumpkin,
        Character.valueOf('c'), Item.emerald });
        GameRegistry.addRecipe(new ItemStack(magicite), new Object[] { Recipes.magiciteTop, Recipes.magiciteMid, Recipes.magiciteBot, 
        Character.valueOf('s'), Block.slowSand,
        Character.valueOf('c'), Item.netherQuartz });
        GameRegistry.addRecipe(new ItemStack(mecharm), new Object[] { Recipes.mecharmTop, Recipes.mecharmMid,  
        Character.valueOf('i'), Item.ingotIron,
        Character.valueOf('g'), Item.ingotGold });
        GameRegistry.addRecipe(new ItemStack(mechleg), new Object[] { Recipes.mechlegTop, Recipes.mechlegMid, Recipes.mechlegBot, 
        Character.valueOf('i'), Item.ingotIron,
        Character.valueOf('g'), Item.ingotGold });
        GameRegistry.addRecipe(new ItemStack(mechtorso), new Object[] { Recipes.mechtorsoTop, Recipes.mechtorsoMid, Recipes.mechtorsoBot, 
        Character.valueOf('m'), Item.minecartPowered,
        Character.valueOf('c'), Item.redstoneRepeater,
        Character.valueOf('i'), Item.ingotIron,
        Character.valueOf('g'), Item.ingotGold,
        Character.valueOf('r'), Block.blockRedstone,
        Character.valueOf('d'), Item.diamond });
        GameRegistry.addRecipe(new ItemStack(mechstack), new Object[] { Recipes.mechstackTop, Recipes.mechstackMid, Recipes.mechstackBot, 
        Character.valueOf('i'), Item.ingotIron,
        Character.valueOf('g'), Item.ingotGold,
        Character.valueOf('o'), Block.furnaceIdle });
        GameRegistry.addRecipe(new ItemStack(mechitem), new Object[] { Recipes.mechATop, Recipes.mechAMid, Recipes.mechABot, 
        Character.valueOf('A'), mecharm,
        Character.valueOf('L'), mechleg,
        Character.valueOf('T'), mechtorso,
        Character.valueOf('S'), mechstack,
        Character.valueOf('M'), magicite });
        GameRegistry.addRecipe(new ItemStack(mechitem), new Object[] { Recipes.mechBTop, Recipes.mechBMid, Recipes.mechBBot, 
        Character.valueOf('A'), mecharm,
        Character.valueOf('L'), mechleg,
        Character.valueOf('T'), mechtorso,
        Character.valueOf('S'), mechstack,
        Character.valueOf('M'), magicite });

        GameRegistry.addRecipe(new ItemStack(mechitemPA), new Object[] { Recipes.mechPATop, Recipes.mechPAMid, Recipes.mechPABot, 
        Character.valueOf('A'), mecharm,
        Character.valueOf('L'), mechleg,
        Character.valueOf('T'), mechtorso,
        Character.valueOf('G'), Block.fenceIron,
        Character.valueOf('M'), magicite });

        LanguageRegistry.addName(magicite, "Magicite");
        LanguageRegistry.addName(mecharm, "Magitek Arm");
        LanguageRegistry.addName(mechleg, "Magitek Leg");
        LanguageRegistry.addName(mechtorso, "Magitek Torso");
        LanguageRegistry.addName(mechstack, "Magitek Stack");
        LanguageRegistry.addName(mechitem, "Magitek Armor");
        LanguageRegistry.addName(mechitemPA, "Proto Armor");
        
        MagitekItems.magicite = magicite;
        MagitekItems.mecharm = mecharm;
        MagitekItems.mechleg = mechleg;
        MagitekItems.mechtorso = mechtorso;
        MagitekItems.mechstack = mechstack;
        MagitekItems.mechitem = mechitem;
        MagitekItems.mechitemPA = mechitemPA;
        
        // Custom record importing.
	    if (External.customrecords)
	    {
            if (!Loader.isModLoaded("mMineTrax"))
            {
                System.out.println("Magitek Mechs: Looking for *.ogg files to turn into records.");
                File folder = new File("resources/mod/streaming");
                File[] listOfFiles = folder.listFiles();
                
                if (listOfFiles != null)
                {
                
                    for (int i = 0; i < listOfFiles.length; i++) 
                    {
                        if (listOfFiles[i] != null)
                        {
                            if (listOfFiles[i].isFile()) 
                            {
                                System.out.println("Found: " + listOfFiles[i].getName());
                                String songname = Files.getNameWithoutExtension(listOfFiles[i].getName());
                                ItemCustomRecord newrecord = new ItemCustomRecord(Identities.newRecordId(), songname);
                                LanguageRegistry.addName(newrecord, "Music Disc");
                                recordList.add(newrecord);
                            } 
                        }
                    }
                }
                else
                {
                    System.out.println("None found.");
                }
            }     
	    }
        
        config.save();
        
        //LanguageRegistry.instance().addStringLocalization("itemGroup.MTMechs", "en_US", "MTMechs");
        //LanguageRegistry.instance().addStringLocalization("entity.mtmechs.Magitek Armor.name", "en_US", "Magitek Armor");
        //LanguageRegistry.instance().addStringLocalization("entity.mtmechs.Proto Armor.name", "en_US", "Proto Armor");
        
        //EntityList.addMapping(EntityMT.class, "Magitek Armor", 1, 117799, 112288);
        //EntityList.addMapping(EntityPA.class, "Proto Armor", 2, 117799, 112288);
        EntityList.addMapping(EntityMoogle.class, "Moogle", 4, 0xFFEEEE, 0xFF8899);
        EntityList.addMapping(EntityTest.class, "Test", 5, 0xABBD3D, 0x337AAB);
        EntityList.addMapping(EntityTA.class, "TunnelArmor", 6, 0xABBD3D, 0x337AAB);
        
        EntityRegistry.addSpawn(EntityMoogle.class, 5, 1, 5, EnumCreatureType.creature);
        EntityRegistry.registerModEntity(EntityMT.class, "MagitekArmor", 1, this, 80, 1, true);
        EntityRegistry.registerModEntity(EntityPA.class, "ProtoArmor", 2, this, 80, 1, true);
        EntityRegistry.registerModEntity(EntityTA.class, "TunnelArmor", 3, this, 80, 1, true);
        EntityRegistry.registerModEntity(EntityMoogle.class, "Moogle", 4, this, 80, 1, true);
        EntityRegistry.registerModEntity(EntityTest.class, "Test", 5, this, 80, 1, true);
        //EntityRegistry.registerModEntity(EntityLaser.class, "Laser", 3, this, 80, 1, true);
	    MinecraftForge.EVENT_BUS.register(new SoundHandler());
	    
	    NetworkRegistry.instance().registerGuiHandler(this, guihandler);
	    
	    proxy.registerKeyBindingHandler();   
	    
        MagitekBlocks.blockmt = new BlockMT(Identities.mtblockId);
        
        GameRegistry.registerBlock(MagitekBlocks.blockmt, "blockmt");
        GameRegistry.registerTileEntity(TileEntityMT.class, "TileEntityMT");
        GameRegistry.registerCraftingHandler(crafthandler);
	}
	
    @EventHandler
	public void load(FMLInitializationEvent event)
	{
        //System.out.println(StringTranslate.getInstance().getCurrentLanguage());
	    proxy.registerRenders();
	    proxy.registerMisc();
	}
	
    @EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
	}
	
	
	
}