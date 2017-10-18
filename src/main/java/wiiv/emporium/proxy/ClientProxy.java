package wiiv.emporium.proxy;

import static wiiv.emporium.client.render.EnumParticles.RenderType.CUSTOM;
import static wiiv.emporium.client.render.EnumParticles.RenderType.VANILLA;

import com.google.common.collect.ImmutableMap;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.client.model.animation.AnimationTESR;
import net.minecraftforge.common.animation.Event;
import net.minecraftforge.common.animation.ITimeValue;
import net.minecraftforge.common.model.animation.IAnimationStateMachine;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import wiiv.emporium.block.tile.TileApplianceMicrowave;
import wiiv.emporium.block.tile.TileStorageCabinetWood1;
import wiiv.emporium.client.particle.ParticleGlowFlame;
import wiiv.emporium.client.particle.ParticleTinyFlame;
import wiiv.emporium.client.render.EnumParticles;
import wiiv.emporium.client.render.ParticleRenderer;
import wiiv.emporium.client.render.RenderFallingChandelier;
import wiiv.emporium.entity.EntityFallingChandelier;
import wiiv.emporium.init.ModBlocks;
import wiiv.emporium.init.ModItems;
import wiiv.emporium.util.RenderUtils;

public class ClientProxy extends CommonProxy {

	public static ParticleRenderer particleRenderer = new ParticleRenderer();

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
		ModItems.initModels();
		ModBlocks.initModels();
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileApplianceMicrowave.class, new AnimationTESR<TileApplianceMicrowave>() {

            /**
             * Event Handler for events triggered from animations. Normally we just hand this off to the tileEntity for processing.
             * This gets called mid {@link AnimationTESR#renderTileEntityFast(TileEntity, double, double, double, float, int, VertexBuffer)}
             * before rendering occurs for the frame.
             * @param tileEntity our tileEntity
             * @param time The global world time for the current tick + partial tick progress, in seconds.
             * @param pastEvents List of events that were triggered since last tick.
             */
            @Override
            public void handleEvents(TileApplianceMicrowave tileEntity, float time, Iterable<Event> pastEvents) {
                super.handleEvents(tileEntity, time, pastEvents);
                tileEntity.handleEvents(time, pastEvents);
            }
        });
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileStorageCabinetWood1.class, new AnimationTESR<TileStorageCabinetWood1>() {

            /**
             * Event Handler for events triggered from animations. Normally we just hand this off to the tileEntity for processing.
             * This gets called mid {@link AnimationTESR#renderTileEntityFast(TileEntity, double, double, double, float, int, VertexBuffer)}
             * before rendering occurs for the frame.
             * @param tileEntity our tileEntity
             * @param time The global world time for the current tick + partial tick progress, in seconds.
             * @param pastEvents List of events that were triggered since last tick.
             */
            @Override
            public void handleEvents(TileStorageCabinetWood1 tileEntity, float time, Iterable<Event> pastEvents) {
                super.handleEvents(tileEntity, time, pastEvents);
                tileEntity.handleEvents(time, pastEvents);
            }
        });
	}

	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);
		RenderManager rm = RenderUtils.getRenderManager();
		rm.entityRenderMap.put(EntityFallingChandelier.class, new RenderFallingChandelier(rm));
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);
	}
	
	@Override
    public IAnimationStateMachine load(ResourceLocation location, ImmutableMap<String, ITimeValue> parameters) {
        return ModelLoaderRegistry.loadASM(location, parameters);
    }

	@Override
	public void spawnCustomParticle(EnumParticles particleType, World world, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn) {
		if (Minecraft.getMinecraft().world == null) {
			return;
		}
		Particle particle = null;
		switch (particleType) {
		case TINY_FLAME:
			particle = new ParticleTinyFlame(world, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
			break;
		case GLOW_FLAME:
			int g = Math.max(100, world.rand.nextInt(255));
			float scale = Math.max(0.45f, Minecraft.getMinecraft().world.rand.nextFloat() - 0.3f);
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
