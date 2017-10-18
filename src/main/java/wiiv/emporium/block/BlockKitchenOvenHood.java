package wiiv.emporium.block;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockKitchenOvenHood extends BlockBase {

	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

	public BlockKitchenOvenHood() {
		super(Material.WOOD, "kitchen_oven_hood", 1.0F);
		setSoundType(SoundType.WOOD);
		setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
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
		return getBox(state.getValue(FACING));
	}
	
	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean p_185477_7_) {
		super.addCollisionBoxToList(pos, entityBox, collidingBoxes, getBox(state.getValue(FACING)));
	}
	
	private AxisAlignedBB getBox(EnumFacing facing) {
		switch (facing) {
		case SOUTH:
			return new AxisAlignedBB((0.0625D * 3), (0.0F), (0.0625D * 2), 1 - (0.0625D * 3), (0.0625D * 8), 1 - (0.0625D * 4));
		case EAST:
			return new AxisAlignedBB((0.0625D * 2), (0.0F), (0.0625D * 3), 1 - (0.0625D * 4), (0.0625D * 8), 1 - (0.0625D * 3));
		case WEST:
			return new AxisAlignedBB((0.0625D * 4), (0.0F), (0.0625D * 3), 1 - (0.0625D * 2), (0.0625D * 8), 1 - (0.0625D * 3));
		default:
		case NORTH:
			return new AxisAlignedBB((0.0625D * 3), (0.0F), (0.0625D * 4), 1 - (0.0625D * 3), (0.0625D * 8), 1 - (0.0625D * 2));
		}
	}
	
	@Override
	public BlockRenderLayer getBlockLayer() {

		return BlockRenderLayer.SOLID;
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {FACING});
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
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facingIn, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		EnumFacing facing = (placer == null) ? EnumFacing.NORTH : EnumFacing.fromAngle(placer.rotationYaw);
		return getDefaultState().withProperty(FACING, facing);
	}
}
