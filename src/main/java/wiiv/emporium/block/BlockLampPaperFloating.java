package wiiv.emporium.block;

import java.util.List;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.*;
import net.minecraft.world.World;

public class BlockLampPaperFloating extends BlockBaseColorable8 {

	private static final AxisAlignedBB COLLISION_BOX = new AxisAlignedBB((0.0625D * 4), 0.0D, (0.0625D * 4), (0.0625D * 12), (0.0625D * 12), (0.0625D * 12));

	public BlockLampPaperFloating(int color) {
		super(Material.SNOW, "floating_paper_lamp", 1.0F, color);
		setSoundType(SoundType.SNOW);
		setLightLevel(0.75F);
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, Entity entityIn) {
		super.addCollisionBoxToList(pos, entityBox, collidingBoxes, COLLISION_BOX);
	}

	@Override
	public BlockRenderLayer getBlockLayer() {

		return BlockRenderLayer.TRANSLUCENT;
	}
}
