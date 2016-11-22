package wiiv.emporium.block;

import java.util.*;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import net.minecraftforge.fml.relauncher.*;

public class BlockLampCandleTall extends BlockBaseColorable16 {

	private static final AxisAlignedBB BOUNDING_BOX = new AxisAlignedBB((0.0625D * 7), 0.0D, (0.0625D * 7), (0.0625D * 9), (0.0625D * 16), (0.0625D * 9));
	private static final AxisAlignedBB COLLISION_BOX = new AxisAlignedBB((0.0625D * 7), 0.0D, (0.0625D * 7), (0.0625D * 9), (0.0625D * 16), (0.0625D * 9));

	public BlockLampCandleTall(int color) {
		super(Material.ANVIL, "tall_candle", 1.0F, color);
		setSoundType(SoundType.METAL);
		setLightLevel(0.625F);
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
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {

		return BOUNDING_BOX;
	}

	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, Entity entityIn) {
		super.addCollisionBoxToList(pos, entityBox, collidingBoxes, COLLISION_BOX);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {

		if (rand.nextDouble() < 0.1D) {
			//worldIn.playSound(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
		}

		//worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, pos.getX(), pos.getY(), pos.getZ(), 0.0D, 0.0D, 0.0D, new int[0]);
		worldIn.spawnParticle(EnumParticleTypes.FLAME, pos.getX() + 0.5D, pos.getY() + 1.15D, pos.getZ() + 0.5D, 0.0D, 0.0D, 0.0D, new int[0]);
	}

	@Override
	@Deprecated
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		return side != EnumFacing.DOWN;
	}
}
