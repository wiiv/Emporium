package wiiv.emporium.common;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import wiiv.emporium.common.block.base.ModBlocks;
import wiiv.emporium.common.core.EmporiumCreativeTab;
import wiiv.emporium.common.core.proxy.CommonProxy;
import wiiv.emporium.common.crafting.ModCraftingRecipes;
import wiiv.emporium.common.item.base.ModItems;
import wiiv.emporium.common.lib.LibMisc;

@Mod(modid = LibMisc.MOD_ID, name = LibMisc.MOD_NAME, version = LibMisc.VERSION, /*guiFactory = LibMisc.GUI_FACTORY,*/ dependencies = LibMisc.DEPENDENCIES, acceptedMinecraftVersions = LibMisc.ACCEPTED_VERSIONS)
public class Emporium {
	
	@Instance(LibMisc.MOD_ID)
	public static Emporium instance;
	
	@SidedProxy(clientSide = LibMisc.PROXY_CLIENT, serverSide = LibMisc.PROXY_COMMON)
	public static CommonProxy proxy;
	
	public static final CreativeTabs CREATIVE_TAB = new EmporiumCreativeTab();
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent preEvent) {
		System.out.println("PreInit");
		
		ModItems.init();
		ModItems.register();
		
		ModBlocks.init();
		ModBlocks.register();
	}
	
	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		System.out.println("Init");
		proxy.init(event);
		
		ModCraftingRecipes.register();
		
		ModBlocks.registerTiles();
	}
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent postEvent) {
		System.out.println("PostInit");
	}
}
