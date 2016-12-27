package wiiv.emporium.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import wiiv.emporium.api.ICacheableBlock;
import wiiv.emporium.client.render.EnumParticles;
import wiiv.emporium.init.ModRegistries;
import wiiv.emporium.util.ParticleUtil;

public class BlockLampCandleBig extends BlockBaseColorable16 implements ICacheableBlock {

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
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		ModRegistries.registerTickableBlock(worldIn, pos);
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		ModRegistries.unregisterTickableBlock(pos);
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState stateIn, Random rand) {
		if (rand.nextDouble() < 0.1D) {
			//worldIn.playSound(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
		}
		ParticleUtil.spawn(EnumParticles.GLOW_FLAME, worldIn, pos.getX() + 0.5D, pos.getY() + 0.8D, pos.getZ() + 0.5d, 0.0D, 0.0D, 0.0D);
	}
}
