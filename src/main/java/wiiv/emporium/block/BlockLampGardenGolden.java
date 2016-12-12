package wiiv.emporium.block;

import java.util.List;

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
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockLampGardenGolden extends BlockBaseColorable16 {
	
	public static final PropertyDirection FACING = PropertyDirection.create("facing");
	
	private static final AxisAlignedBB BOUNDING_BOX = new AxisAlignedBB((0.0625D * 3), 0.0D, (0.0625D * 3), (0.0625D * 13), (0.0625D * 12), (0.0625D * 13));
	private static final AxisAlignedBB COLLISION_BOX = new AxisAlignedBB((0.0625D * 3), 0.0D, (0.0625D * 3), (0.0625D * 13), (0.0625D * 12), (0.0625D * 13));


	public BlockLampGardenGolden(int color) {
		super(Material.CIRCUITS, "garden_golden_lamp", 1.0F, color);
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
        return layer == BlockRenderLayer.SOLID || layer == BlockRenderLayer.TRANSLUCENT;
    }
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {

		return BOUNDING_BOX;
	}
	
	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, Entity entityIn) {
		super.addCollisionBoxToList(pos, entityBox, collidingBoxes, COLLISION_BOX);
	}
}

