package wiiv.emporium;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import wiiv.emporium.init.EmporiumCreativeTab;
import wiiv.emporium.proxy.CommonProxy;

@Mod(modid = Globals.MOD_ID, name = Globals.MOD_NAME, version = Globals.VERSION, /*guiFactory = Globals.GUI_FACTORY,*/ dependencies = Globals.DEPENDENCIES, acceptedMinecraftVersions = Globals.ACCEPTED_VERSIONS)
public class Emporium {

	@Instance(Globals.MOD_ID)
	public static Emporium instance;

	@SidedProxy(clientSide = Globals.PROXY_CLIENT, serverSide = Globals.PROXY_COMMON)
	public static CommonProxy proxy;

	public static final CreativeTabs CREATIVE_TAB = new EmporiumCreativeTab();

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.preInit(event);
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init(event);
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
	}
}
