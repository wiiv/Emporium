package wiiv.emporium.proxy;

import net.minecraft.world.World;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import wiiv.emporium.client.render.EnumParticles;
import wiiv.emporium.crafting.ModCraftingRecipes;
import wiiv.emporium.init.ModBlocks;
import wiiv.emporium.init.ModEntities;
import wiiv.emporium.init.ModEvents;
import wiiv.emporium.init.ModItems;

public class CommonProxy {

	public void preInit(FMLPreInitializationEvent event) {
		ModItems.init();
		ModBlocks.init();
		ModEntities.init();
		ModEvents.init();
	}

	public void init(FMLInitializationEvent event) {
		ModCraftingRecipes.register();

	}

	public void spawnCustomParticle(EnumParticles particle, World world, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn) {
	}

}