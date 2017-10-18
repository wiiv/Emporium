package wiiv.emporium.block;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;
import net.minecraftforge.common.property.Properties;
import wiiv.emporium.block.tile.TileStorageCabinetWood1;

public class BlockStorageCabinetWood1 extends BlockBase {

	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	
	public BlockStorageCabinetWood1() {
		super(Material.WOOD, "cabinet_wood_drawer", 1.0F);
		setSoundType(SoundType.WOOD);
		setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
	}
	
	private AxisAlignedBB getBox(EnumFacing facing) {
		switch (facing) {
		case SOUTH:
			return new AxisAlignedBB(0.0F, 0.0F, 0.125F, 1.0F, 0.875F, 1.0F);
		case EAST:
			return new AxisAlignedBB(0.125F, 0.0F, 0.0F, 1.0F, 0.875F, 1.0F);
		case WEST:
			return new AxisAlignedBB(0.0F, 0.0F, 0.0F, 0.875F, 0.875F, 1.0F);
		default:
		case NORTH:
			return new AxisAlignedBB(0.0F, 0.0F, 0.0F, 1.0F, 0.875F, 0.875F);
		}
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
	
	@Override
    public boolean canRenderInLayer(IBlockState state, BlockRenderLayer layer) {
        return layer == BlockRenderLayer.SOLID || layer == BlockRenderLayer.TRANSLUCENT;
    }
    
	/**
     *  We need an ExtendedBlockState to hold extra properties specifically for animation.
     *
     *  {@link Properties#AnimationProperty} is an unlisted property and holds the state for the animation. Unlisted
     *  properties do not need to be declared in your blockstate file.
     *
     *  {@link Properties#StaticProperty} is a boolean property that holds a special state for models. Models that are
     *  defined as static don't move and can be rendered faster. We split our model into separate parts for the static
     *  model in one json and the parts that will be animated in a separate json so we can render them in separate passes.
     *  See the 'static' section in {@link assets.anim.blockstates.lever.json} for an example.
     *  {@link assets.anim.models.block.lever.json} and {@link assets.anim.models.block.lever_slider.json} for an example
     *  on the splitting of the model files.
     *
     */
    @Nonnull
    @Override
    public ExtendedBlockState createBlockState() {
        return new ExtendedBlockState(this, new IProperty[]{ FACING, Properties.StaticProperty }, new IUnlistedProperty[]{ Properties.AnimationProperty });
    }

    /**
     * getActualState needs to add the property for {@link Properties#StaticProperty} so the animation system has
     * access to it.
     */
    @Override
    public IBlockState getActualState(@Nonnull IBlockState state, IBlockAccess world, BlockPos pos) {
        return state.withProperty(Properties.StaticProperty, true);
    }

    //region Boilerplate Code
    @Override
    public IBlockState getStateFromMeta(int meta) {
    	EnumFacing facing = EnumFacing.getHorizontal(meta);
		return getDefaultState().withProperty(FACING, facing);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
    	return state.getValue(FACING).getHorizontalIndex();
    }
    
    @Override
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facingIn, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		EnumFacing facing = (placer == null) ? EnumFacing.NORTH : EnumFacing.fromAngle(placer.rotationYaw);
		return getDefaultState().withProperty(FACING, facing);
	}

    /**
     * Simple method to handle block clicks. {@link TileEntityLever#click()} Shows an example of how to transition
     * from one state to another from code.
     */
    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        // We want to run on the client since the asm controller is client side only.
        if(worldIn.isRemote) {
            TileEntity te = worldIn.getTileEntity(pos);
            if(te instanceof TileStorageCabinetWood1) {
                ((TileStorageCabinetWood1)te).click();
            }
        }
        return true;
    }
    
    @Override
    public TileEntity createNewTileEntity(@Nonnull World worldIn, int meta) {
        return new TileStorageCabinetWood1();
    }
}
