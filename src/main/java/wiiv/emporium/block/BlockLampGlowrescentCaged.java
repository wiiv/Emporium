package wiiv.emporium.block;

import java.util.List;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.*;
import net.minecraft.block.state.*;
import net.minecraft.entity.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;

public class BlockLampGlowrescentCaged extends BlockBaseColorable16 {

	public static final PropertyDirection FACING = PropertyDirection.create("facing");

	public BlockLampGlowrescentCaged(int color) {
		super(Material.CIRCUITS, "glowrescent_caged_lamp", 1.0F, color);
		setSoundType(SoundType.METAL);
		setLightLevel(0.75F);
		setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.UP));
	}

	private AxisAlignedBB getBox(EnumFacing facing) {
		switch (facing) {
		case DOWN:
			return new AxisAlignedBB((0.0625D * 5), 1 - (0.0625D * 7), (0.0625D * 5), (0.0625D * 11), 1.0D, (0.0625D * 11));
		case EAST:
			return new AxisAlignedBB((0.0625D * 7), (0.0625D * 5), (0.0625D * 5), 0.0D, (0.0625D * 11), (0.0625D * 11));
		case NORTH:
			return new AxisAlignedBB((0.0625D * 11), (0.0625D * 5), 1 - (0.0625D * 7), (0.0625D * 5), (0.0625D * 11), 1.0D);
		case SOUTH:
			return new AxisAlignedBB((0.0625D * 11), (0.0625D * 5), 0.0D, (0.0625D * 5), (0.0625D * 11), (0.0625D * 7));
		case WEST:
			return new AxisAlignedBB(1 - (0.0625D * 7), (0.0625D * 5), (0.0625D * 5), 1.0D, (0.0625D * 11), (0.0625D * 11));
		default:
		case UP:
			return new AxisAlignedBB((0.0625D * 5), 0.0D, (0.0625D * 5), (0.0625D * 11), (0.0625D * 7), (0.0625D * 11));
		}
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		IBlockState iblockstate = getDefaultState();

		switch (meta) {
		case 1:
			iblockstate = iblockstate.withProperty(FACING, EnumFacing.EAST);
			break;
		case 2:
			iblockstate = iblockstate.withProperty(FACING, EnumFacing.WEST);
			break;
		case 3:
			iblockstate = iblockstate.withProperty(FACING, EnumFacing.SOUTH);
			break;
		case 4:
			iblockstate = iblockstate.withProperty(FACING, EnumFacing.NORTH);
			break;
		case 5:
			iblockstate = iblockstate.withProperty(FACING, EnumFacing.DOWN);
			break;
		case 6:
		default:
			iblockstate = iblockstate.withProperty(FACING, EnumFacing.UP);
		}

		return iblockstate;
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		switch (state.getValue(FACING)) {
		case EAST:
			return 1;
		case WEST:
			return 2;
		case SOUTH:
			return 3;
		case NORTH:
			return 4;
		case DOWN:
			return 5;
		case UP:
		default:
			return 6;
		}
	}

	@Override
	public IBlockState withRotation(IBlockState state, Rotation rot) {
		return state.withProperty(FACING, rot.rotate(state.getValue(FACING)));
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {
				FACING
		});
	}

	@Override
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		return getDefaultState().withProperty(FACING, facing);
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
	public boolean canRenderInLayer(BlockRenderLayer layer) {
		return layer == BlockRenderLayer.CUTOUT || layer == BlockRenderLayer.TRANSLUCENT;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return getBox(state.getValue(FACING));
	}

	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, Entity entityIn) {
		super.addCollisionBoxToList(pos, entityBox, collidingBoxes, getBox(state.getValue(FACING)));
	}
}
