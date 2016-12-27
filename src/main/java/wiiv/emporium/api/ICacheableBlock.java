package wiiv.emporium.api;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * @author p455w0rd
 *
 */
public interface ICacheableBlock {

	void onBlockAdded(World worldIn, BlockPos pos, IBlockState state);

	void breakBlock(World worldIn, BlockPos pos, IBlockState state);

}
