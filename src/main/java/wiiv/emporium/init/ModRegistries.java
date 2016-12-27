package wiiv.emporium.init;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * @author p455w0rd
 *
 */
public class ModRegistries {

	private static Map<BlockPos, World> TICKABLE_BLOCK_CACHE = new HashMap<BlockPos, World>();

	public static void registerTickableBlock(World world, BlockPos pos) {
		if (!isBlockCached(pos)) {
			TICKABLE_BLOCK_CACHE.put(pos, world);
		}
	}

	public static void unregisterTickableBlock(BlockPos pos) {
		if (isBlockCached(pos)) {
			TICKABLE_BLOCK_CACHE.remove(pos);
		}
	}

	public static boolean isBlockCached(BlockPos pos) {
		return TICKABLE_BLOCK_CACHE.containsKey(pos);
	}

	public static Map<BlockPos, World> getBlockCache() {
		return TICKABLE_BLOCK_CACHE;
	}

}
