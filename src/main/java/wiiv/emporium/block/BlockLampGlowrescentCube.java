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
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockLampGlowrescentCube extends BlockBaseColorable16 {
	
	public static final PropertyDirection FACING = PropertyDirection.create("facing");
	
	public BlockLampGlowrescentCube(int color) {
		super(Material.CIRCUITS, "lamp_glowrescent_cube", 1.0F, color);
		setSoundType(SoundType.METAL);
		setLightLevel(0.895F);
		setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.UP));
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
		case DOWN:
			return new AxisAlignedBB((0.0625D * 3), 1 - (0.0625D * 12), (0.0625D * 3), (0.0625D * 13), 1.0D, (0.0625D * 13));
		case EAST:
			return new AxisAlignedBB((0.0625D * 12), (0.0625D * 3), (0.0625D * 3), 0.0D, (0.0625D * 13), (0.0625D * 13));
		case NORTH:
			return new AxisAlignedBB((0.0625D * 13), (0.0625D * 3), 1 - (0.0625D * 12), (0.0625D * 3), (0.0625D * 13), 1.0D);
		case SOUTH:
			return new AxisAlignedBB((0.0625D * 13), (0.0625D * 3), 0.0D, (0.0625D * 3), (0.0625D * 13), (0.0625D * 12));
		case WEST:
			return new AxisAlignedBB(1 - (0.0625D * 12), (0.0625D * 3), (0.0625D * 3), 1.0D, (0.0625D * 13), (0.0625D * 13));
		default:
		case UP:
			return new AxisAlignedBB((0.0625D * 3), 0.0D, (0.0625D * 3), (0.0625D * 13), (0.0625D * 12), (0.0625D * 13));
		}
	}

	@Override
    public boolean canRenderInLayer(IBlockState state, BlockRenderLayer layer) {
        return layer == BlockRenderLayer.SOLID || layer == BlockRenderLayer.TRANSLUCENT;
    }
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {FACING});
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
}