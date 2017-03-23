package wiiv.emporium.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import wiiv.emporium.util.CounterType;

public class BlockCounterBar extends BlockBase {

	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	public static final PropertyEnum<CounterType> TYPE = PropertyEnum.create("type", CounterType.class);

	public BlockCounterBar() {
		super(Material.GLASS, "counter_bar", 1.0F);
		setSoundType(SoundType.WOOD);
		setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(TYPE, CounterType.SINGLE));
	}

	/*
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
	
	
	
		return state;
	}
	*/

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
		EnumFacing facing = state.getValue(FACING);
		EnumFacing blockU = null;
		EnumFacing blockV = null;
		for (EnumFacing nearFacing : EnumFacing.HORIZONTALS) {
			IBlockState nearState = world.getBlockState(pos.offset(nearFacing));
			if (nearState.getBlock() == this) {
				if (nearFacing == EnumFacing.NORTH || nearFacing == EnumFacing.SOUTH) {
					blockU = nearFacing;
				}
				if (nearFacing == EnumFacing.EAST || nearFacing == EnumFacing.WEST) {
					blockV = nearFacing;
				}
			}
		}
		if (blockU != null && blockV != null) {
			if (blockU == EnumFacing.NORTH) {
				if (blockV == EnumFacing.EAST) {
					state = getDefaultState().withProperty(FACING, EnumFacing.WEST);
				}
				else if (blockV == EnumFacing.WEST) {
					state = getDefaultState().withProperty(FACING, EnumFacing.SOUTH);
				}
			}
			else if (blockU == EnumFacing.SOUTH) {
				if (blockV == EnumFacing.EAST) {
					state = getDefaultState().withProperty(FACING, EnumFacing.NORTH);
				}
				else if (blockV == EnumFacing.WEST) {
					state = getDefaultState().withProperty(FACING, EnumFacing.EAST);
				}
			}
			return state.withProperty(TYPE, CounterType.CORNER);
		}
		if (facing == EnumFacing.NORTH || facing == EnumFacing.SOUTH) {
			IBlockState right = world.getBlockState(pos.east());
			IBlockState left = world.getBlockState(pos.west());
			if (right.getBlock() == this && left.getBlock() == this) {
				return state.withProperty(TYPE, CounterType.CENTER);
			}

			if (world.getBlockState(pos.east()).getBlock() == this && (right.getValue(FACING) == EnumFacing.NORTH | right.getValue(FACING) == EnumFacing.SOUTH)) {
				state = state.withProperty(TYPE, CounterType.RIGHT);
			}
			if (world.getBlockState(pos.west()).getBlock() == this && (left.getValue(FACING) == EnumFacing.NORTH | left.getValue(FACING) == EnumFacing.SOUTH)) {
				state = state.withProperty(TYPE, CounterType.LEFT);
			}
			return state;
		}

		if (facing == EnumFacing.EAST || facing == EnumFacing.WEST) {
			IBlockState right = world.getBlockState(pos.north());
			IBlockState left = world.getBlockState(pos.south());
			if (right.getBlock() == this && left.getBlock() == this) {
				return state.withProperty(TYPE, CounterType.CENTER);
			}
			if (right.getBlock() == this && (right.getValue(FACING) == EnumFacing.EAST | right.getValue(FACING) == EnumFacing.WEST)) {
				state = state.withProperty(TYPE, CounterType.RIGHT);
			}
			if (left.getBlock() == this && (left.getValue(FACING) == EnumFacing.EAST | left.getValue(FACING) == EnumFacing.WEST)) {
				state = state.withProperty(TYPE, CounterType.LEFT);
			}
			return state;
		}
		return state;
	}

	public int getRotationState(IBlockAccess source, BlockPos pos) {
		if (source.isAirBlock(pos)) {
			return 0;
		}
		return getMetaFromState(source.getBlockState(pos)) % 2;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {
				FACING,
				TYPE
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
	public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facingIn, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		EnumFacing facing = (placer == null) ? EnumFacing.NORTH : EnumFacing.fromAngle(placer.rotationYaw).getOpposite();
		if (placer != null && placer instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) placer;
			boolean nearCounters = false;
			if (player.isSneaking()) {
				for (EnumFacing near : EnumFacing.HORIZONTALS) {
					if (world.getBlockState(pos.offset(near)).getBlock() == this) {
						nearCounters = true;
						break;
					}
				}
				if (!nearCounters) {
					facing = facing.getOpposite();
				}
			}
		}
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
