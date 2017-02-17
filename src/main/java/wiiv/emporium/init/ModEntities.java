package wiiv.emporium.init;

import net.minecraftforge.fml.common.registry.EntityRegistry;
import wiiv.emporium.Emporium;
import wiiv.emporium.entity.EntityFallingChandelier;
import wiiv.emporium.entity.EntityMountable;

public class ModEntities {

	public static void init() {
		EntityRegistry.registerModEntity(EntityMountable.class, "MountableBlock", 0, Emporium.instance, 80, 1, false);
		EntityRegistry.registerModEntity(EntityFallingChandelier.class, "FallingChandelier", 1, Emporium.instance, 80, 1, true);
	}

}
