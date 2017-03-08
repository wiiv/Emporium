package wiiv.emporium.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.*;
import net.minecraft.block.state.*;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;

public class BlockMedievalTable extends BlockBase {

	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	public static final PropertyBool LEFT = PropertyBool.create("left");
	public static final PropertyBool RIGHT = PropertyBool.create("right");

	public BlockMedievalTable() {
		super(Material.GLASS, "medieval_table", 1.0F);
		setSoundType(SoundType.WOOD);
		setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(LEFT, false).withProperty(RIGHT, false));
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		EnumFacing facing = state.getValue(FACING);
		if (facing == EnumFacing.NORTH || facing == EnumFacing.SOUTH) {
			IBlockState right = worldIn.getBlockState(pos.east());
			IBlockState left = worldIn.getBlockState(pos.west());

			if (worldIn.getBlockState(pos.east()).getBlock() == this && (right.getValue(FACING) == EnumFacing.NORTH | right.getValue(FACING) == EnumFacing.SOUTH)) {
				state = state.withProperty(RIGHT, true);
			}
			if (worldIn.getBlockState(pos.west()).getBlock() == this && (left.getValue(FACING) == EnumFacing.NORTH | left.getValue(FACING) == EnumFacing.SOUTH)) {
				state = state.withProperty(LEFT, true);
			}

			return state;
		}

		if (facing == EnumFacing.EAST || facing == EnumFacing.WEST) {
			IBlockState right = worldIn.getBlockState(pos.north());
			IBlockState left = worldIn.getBlockState(pos.south());

			if (right.getBlock() == this && (right.getValue(FACING) == EnumFacing.EAST | right.getValue(FACING) == EnumFacing.WEST)) {
				state = state.withProperty(RIGHT, true);
			}
			if (left.getBlock() == this && (left.getValue(FACING) == EnumFacing.EAST | left.getValue(FACING) == EnumFacing.WEST)) {
				state = state.withProperty(LEFT, true);
			}

			return state;
		}

		return state;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {
				FACING, LEFT, RIGHT
		});
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

		return FULL_BLOCK_AABB;
	}

	@Override
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facingIn, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		EnumFacing facing = (placer == null) ? EnumFacing.NORTH : EnumFacing.fromAngle(placer.rotationYaw);
		return getDefaultState().withProperty(FACING, facing);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(FACING).getHorizontalIndex();
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		EnumFacing facing = EnumFacing.getHorizontal(meta);
		return getDefaultState().withProperty(FACING, facing);
	}

	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.SOLID;
	}

}
