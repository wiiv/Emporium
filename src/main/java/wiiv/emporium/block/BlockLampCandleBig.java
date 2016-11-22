package wiiv.emporium.block;

import java.util.*;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import net.minecraftforge.fml.relauncher.*;

public class BlockLampCandleBig extends BlockBaseColorable16 {

	private static final AxisAlignedBB BOUNDING_BOX = new AxisAlignedBB((0.0625D * 6), 0.0D, (0.0625D * 6), (0.0625D * 10), (0.0625D * 12), (0.0625D * 10));
	private static final AxisAlignedBB COLLISION_BOX = new AxisAlignedBB((0.0625D * 6), 0.0D, (0.0625D * 6), (0.0625D * 10), (0.0625D * 12), (0.0625D * 10));

	public BlockLampCandleBig(int color) {
		super(Material.ANVIL, "big_candle", 1.0F, color);
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

		//worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, pos.getX() + 0.5D, pos.getY() + 1, pos.getZ() + 0.5D, 0.0D, 0.0D, 0.0D, new int[0]);
		worldIn.spawnParticle(EnumParticleTypes.FLAME, pos.getX() + 0.5D, pos.getY() + 0.95D, pos.getZ() + 0.5d, 0.0D, 0.0D, 0.0D, new int[0]);
	}
}
