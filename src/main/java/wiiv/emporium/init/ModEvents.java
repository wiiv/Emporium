package wiiv.emporium.init;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.world.ChunkEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import wiiv.emporium.Globals;
import wiiv.emporium.Globals.Textures;
import wiiv.emporium.api.ICacheableBlock;
import wiiv.emporium.proxy.ClientProxy;

/**
 * @author p455w0rd
 *
 */
public class ModEvents {

	public static void init() {
		MinecraftForge.EVENT_BUS.register(new ModEvents());
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onTextureStitch(TextureStitchEvent event) {
		event.getMap().registerSprite(Textures.GLOW_FLAME);
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void onClientTick(ClientTickEvent event) {
		if (event.side == Side.SERVER) {
			return;
		}
		if (ModRegistries.getBlockCache().size() > 0) {
			ClientProxy.particleRenderer.updateParticles();
			for (BlockPos pos : ModRegistries.getBlockCache().keySet()) {
				World world = ModRegistries.getBlockCache().get(pos);
				IBlockState state = world.getBlockState(pos);
				Random random = world.rand;
				Block block = state.getBlock();
				block.updateTick(world, pos, state, random);
			}
		}
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onRenderAfterWorld(RenderWorldLastEvent event) {
		ClientProxy.particleRenderer.renderParticles(Minecraft.getMinecraft().thePlayer, event.getPartialTicks());
	}

	private void loadChunk(World world, int chunkX, int chunkZ) {
		int minX = chunkX << 4;
		int minZ = chunkZ << 4;
		int maxX = minX + 16;
		int maxZ = minZ + 16;
		for (int y = 0; y <= 256; y++) {
			for (int x = minX; x < maxX; x++) {
				for (int z = minZ; z < maxZ; z++) {
					BlockPos pos = new BlockPos(x, y, z);
					Block block = world.getBlockState(pos).getBlock();
					if (block instanceof ICacheableBlock) {
						ModRegistries.registerTickableBlock(world, pos);
					}
				}
			}
		}
	}

	private void unloadChunk(World world, int chunkX, int chunkZ) {
		int minX = chunkX << 4;
		int minZ = chunkZ << 4;
		int maxX = minX + 16;
		int maxZ = minZ + 16;
		for (int y = 0; y <= 256; y++) {
			for (int x = minX; x < maxX; x++) {
				for (int z = minZ; z < maxZ; z++) {
					BlockPos pos = new BlockPos(x, y, z);
					Block block = world.getBlockState(pos).getBlock();
					if (block instanceof ICacheableBlock) {
						ModRegistries.unregisterTickableBlock(pos);
					}
				}
			}
		}
	}

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void onChunkLoadPopulate(PopulateChunkEvent.Post event) {
		Globals.THREAD_POOL.submit(() -> loadChunk(event.getWorld(), event.getChunkX(), event.getChunkZ()));
	}

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void onChunkLoad(ChunkEvent.Load event) {
		Globals.THREAD_POOL.submit(() -> loadChunk(event.getWorld(), event.getChunk().xPosition, event.getChunk().zPosition));
	}

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void onChunkUnload(ChunkEvent.Unload event) {
		Globals.THREAD_POOL.submit(() -> unloadChunk(event.getWorld(), event.getChunk().xPosition, event.getChunk().zPosition));
	}

}
