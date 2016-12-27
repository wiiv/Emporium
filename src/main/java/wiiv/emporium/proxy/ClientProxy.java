package wiiv.emporium.proxy;

import static wiiv.emporium.client.render.EnumParticles.RenderType.CUSTOM;
import static wiiv.emporium.client.render.EnumParticles.RenderType.VANILLA;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import wiiv.emporium.client.particle.ParticleGlowFlame;
import wiiv.emporium.client.particle.ParticleTinyFlame;
import wiiv.emporium.client.render.EnumParticles;
import wiiv.emporium.client.render.ParticleRenderer;
import wiiv.emporium.client.render.RenderFallingChandelier;
import wiiv.emporium.entity.EntityFallingChandelier;
import wiiv.emporium.init.ModBlocks;
import wiiv.emporium.init.ModItems;

public class ClientProxy extends CommonProxy {

	public static ParticleRenderer particleRenderer = new ParticleRenderer();

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
		ModItems.initModels();
		ModBlocks.initModels();
	}

	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);
		RenderManager rm = Minecraft.getMinecraft().getRenderManager();
		rm.entityRenderMap.put(EntityFallingChandelier.class, new RenderFallingChandelier(rm));
	}

	@Override
	public void spawnCustomParticle(EnumParticles particleType, World world, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn) {
		if (Minecraft.getMinecraft().theWorld == null) {
			return;
		}
		Particle particle = null;
		switch (particleType) {
		case TINY_FLAME:
			particle = new ParticleTinyFlame(world, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
			break;
		case GLOW_FLAME:
			int g = Math.max(100, world.rand.nextInt(255));
			float scale = Math.max(0.45f, Minecraft.getMinecraft().theWorld.rand.nextFloat() - 0.3f);
			particle = new ParticleGlowFlame(world, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn, 255, g, Math.max(51, world.rand.nextInt(100)), scale);
		default:
			break;
		}
		if (particle != null) {
			if (particleType.getRenderer() == VANILLA) {
				Minecraft.getMinecraft().effectRenderer.addEffect(particle);
			}
			else if (particleType.getRenderer() == CUSTOM) {
				particleRenderer.addParticle(particle);
			}
		}
	}
}
