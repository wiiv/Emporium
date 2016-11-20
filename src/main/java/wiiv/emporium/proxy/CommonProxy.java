package wiiv.emporium.proxy;

import net.minecraftforge.fml.common.event.*;
import wiiv.emporium.crafting.ModCraftingRecipes;
import wiiv.emporium.init.*;

public class CommonProxy {

	public void preInit(FMLPreInitializationEvent event) {
		ModItems.init();
		ModBlocks.init();
	}

	public void init(FMLInitializationEvent event) {
		ModCraftingRecipes.register();
		ModEntities.init();
	}

}