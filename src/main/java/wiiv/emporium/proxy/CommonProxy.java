package wiiv.emporium.proxy;

import com.google.common.collect.ImmutableMap;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.animation.ITimeValue;
import net.minecraftforge.common.model.animation.IAnimationStateMachine;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import wiiv.emporium.client.render.EnumParticles;
import wiiv.emporium.crafting.ModCraftingRecipes;
import wiiv.emporium.init.ModBlocks;
import wiiv.emporium.init.ModEntities;
import wiiv.emporium.init.ModEvents;
import wiiv.emporium.init.ModGuiHandler;
import wiiv.emporium.init.ModItems;
import wiiv.emporium.init.ModNetworkHandler;

public class CommonProxy {

	public void preInit(FMLPreInitializationEvent event) {
		ModItems.init();
		ModBlocks.init();
		ModEntities.init();
		ModEvents.init();
		ModNetworkHandler.init();
	}

	public void init(FMLInitializationEvent event) {
		ModCraftingRecipes.register();

	}

	public void postInit(FMLPostInitializationEvent event) {
		ModGuiHandler.init();
	}
	
	public IAnimationStateMachine load(ResourceLocation location, ImmutableMap<String, ITimeValue> parameters) {
        return null;
    }

	public void spawnCustomParticle(EnumParticles particle, World world, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn) {
	}

}