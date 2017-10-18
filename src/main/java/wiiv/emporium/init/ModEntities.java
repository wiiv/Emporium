package wiiv.emporium.init;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import wiiv.emporium.Emporium;
import wiiv.emporium.Globals;
import wiiv.emporium.entity.EntityFallingChandelier;
import wiiv.emporium.entity.EntityMountable;

public class ModEntities {

	public static void init() {
		EntityRegistry.registerModEntity(new ResourceLocation(Globals.MOD_ID, "mountable"), EntityMountable.class, "MountableBlock", 0, Emporium.instance, 80, 1, false);
		EntityRegistry.registerModEntity(new ResourceLocation(Globals.MOD_ID, "falling_chandelier"), EntityFallingChandelier.class, "FallingChandelier", 1, Emporium.instance, 80, 1, true);
	}

}
