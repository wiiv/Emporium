package wiiv.emporium.block;

import java.util.List;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import wiiv.emporium.util.MountableUtil;

public class BlockSofaGris extends BlockBase {

	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	public static final PropertyBool LEFT = PropertyBool.create("left");
	public static final PropertyBool RIGHT = PropertyBool.create("right");

	private static final AxisAlignedBB BOUNDING_BOX = new AxisAlignedBB((0.0625D * 2), 0.0D, (0.0625D * 1), (0.0625D * 16), (0.0625D * 8), (0.0625D * 15));
	private static final AxisAlignedBB BOUNDING_BOX2 = new AxisAlignedBB((0.0625D * 1), 0.0D, (0.0625D * 2), (0.0625D * 15), (0.0625D * 8), (0.0625D * 16));
	private static final AxisAlignedBB BOUNDING_BOX3 = new AxisAlignedBB((0.0625D * 2), 0.0D, (0.0625D * 1), (0.0625D * 16), (0.0625D * 8), (0.0625D * 15));
	private static final AxisAlignedBB BOUNDING_BOX4 = new AxisAlignedBB((0.0625D * 1), 0.0D, (0.0625D * 0), (0.0625D * 15), (0.0625D * 8), (0.0625D * 14));

	private static final AxisAlignedBB COLLISION_BOX = new AxisAlignedBB((0.0625D * 2), 0.0D, (0.0625D * 1), (0.0625D * 16), (0.0625D * 8), (0.0625D * 15));
	private static final AxisAlignedBB COLLISION_BOX2 = new AxisAlignedBB((0.0625D * 1), 0.0D, (0.0625D * 0), (0.0625D * 15), (0.0625D * 8), (0.0625D * 14));
	private static final AxisAlignedBB COLLISION_BOX3 = new AxisAlignedBB((0.0625D * 2), 0.0D, (0.0625D * 1), (0.0625D * 16), (0.0625D * 8), (0.0625D * 15));
	private static final AxisAlignedBB COLLISION_BOX4 = new AxisAlignedBB((0.0625D * 1), 0.0D, (0.0625D * 0), (0.0625D * 15), (0.0625D * 8), (0.0625D * 14));

	public BlockSofaGris() {
		super(Material.GLASS, "sofa_gris", 1.0F);
		setSoundType(SoundType.WOOD);
		setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(LEFT, false).withProperty(RIGHT, false));
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		return MountableUtil.sitOnBlock(worldIn, pos, playerIn, 0.25, true);
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
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, Entity entityIn) {
		if (getRotationState(worldIn, pos) == 1) {
			super.addCollisionBoxToList(pos, entityBox, collidingBoxes, COLLISION_BOX);
			return;
		}
		if (getRotationState(worldIn, pos) == 2) {
			super.addCollisionBoxToList(pos, entityBox, collidingBoxes, COLLISION_BOX2);
			return;
		}
		if (getRotationState(worldIn, pos) == 3) {
			super.addCollisionBoxToList(pos, entityBox, collidingBoxes, COLLISION_BOX3);
			return;
		}
		super.addCollisionBoxToList(pos, entityBox, collidingBoxes, COLLISION_BOX4);
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
				LEFT,
				RIGHT
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
		if (getRotationState(source, pos) == 1) {
			return BOUNDING_BOX;
		}
		if (getRotationState(source, pos) == 2) {
			return BOUNDING_BOX2;
		}
		if (getRotationState(source, pos) == 3) {
			return BOUNDING_BOX3;
		}
		return BOUNDING_BOX4;
	}

	@Override
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facingIn, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		EnumFacing facing = (placer == null) ? EnumFacing.NORTH : EnumFacing.fromAngle(placer.rotationYaw);
		for (EnumFacing nearFacing : EnumFacing.HORIZONTALS) {
			IBlockState nearState = worldIn.getBlockState(pos.offset(nearFacing));
			if (nearState.getBlock() == this) {
				facing = nearState.getValue(FACING);
				break;
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
