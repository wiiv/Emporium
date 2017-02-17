package wiiv.emporium.util;

import net.minecraft.world.World;
import wiiv.emporium.Emporium;
import wiiv.emporium.client.render.EnumParticles;

/**
 * @author p455w0rd
 *
 */
public class ParticleUtil {

	public static void spawn(EnumParticles particleType, World world, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn) {
		Emporium.proxy.spawnCustomParticle(particleType, world, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
	}

}
