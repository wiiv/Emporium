package wiiv.emporium.common.block;

import java.util.List;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import wiiv.emporium.common.Emporium;
import wiiv.emporium.common.lib.LibMisc;

public class BlockLampLibrary extends BlockHorizontal{
	
	private static final AxisAlignedBB BOUNDING_BOX = new AxisAlignedBB((0.0625D*2), 0.0D, (0.0625D*2), (0.0625D*14), (0.0625D*15), (0.0625D*14));
	private static final AxisAlignedBB COLLISION_BOX = new AxisAlignedBB((0.0625D*4), 0.0D, (0.0625D*4), (0.0625D*12), (0.0625D*12), (0.0625D*12));

	public BlockLampLibrary() {
		super(Material.GLASS);
		setUnlocalizedName(LibMisc.DecBlocks.LAMP_LIBRARY.getUnlocalizedName());
		setRegistryName(LibMisc.DecBlocks.LAMP_LIBRARY.getRegistryName());
		
		setHardness(1.0F);
		setSoundType(blockSoundType.GLASS);
		setLightLevel(15.0F);
		
		setCreativeTab(Emporium.CREATIVE_TAB);
		// TODO Auto-generated constructor stub
		setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	/*
	@Override
	public BlockRenderLayer getBlockLayer() {
		
		return BlockRenderLayer.TRANSLUCENT;
	}
	*/
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {

		return BOUNDING_BOX;
	}
	
	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, Entity entityIn) {
		super.addCollisionBoxToList(pos, entityBox, collidingBoxes, COLLISION_BOX);
	}
	
	@Override
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		IBlockState state = super.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer);
		return state.withProperty(FACING, placer.getHorizontalFacing());
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(FACING).getHorizontalIndex();
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta));
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, FACING);
	}
}
