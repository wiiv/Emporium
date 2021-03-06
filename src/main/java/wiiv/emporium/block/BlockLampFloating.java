package wiiv.emporium.block;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import wiiv.emporium.api.ICacheableBlock;
import wiiv.emporium.client.render.EnumParticles;
import wiiv.emporium.init.ModRegistries;
import wiiv.emporium.util.ParticleUtil;

public class BlockLampFloating extends BlockBaseColorable8 implements ICacheableBlock {

	private static final AxisAlignedBB HIT_BOX = new AxisAlignedBB((0.0625D * 3), (0.0625D * 2), (0.0625D * 3), (0.0625D * 13), (0.0625D * 14), (0.0625D * 13));

	public BlockLampFloating(int color) {
		super(Material.CACTUS, "lamp_floating", 1.0F, color);
		setSoundType(SoundType.SNOW);
		setLightLevel(0.75F);
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
		return HIT_BOX;
	}
	
	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean p_185477_7_) {
		super.addCollisionBoxToList(pos, entityBox, collidingBoxes, HIT_BOX);
	}
	
	@Override
    public boolean canRenderInLayer(IBlockState state, BlockRenderLayer layer) {
        return layer == BlockRenderLayer.SOLID || layer == BlockRenderLayer.TRANSLUCENT;
    }

	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		worldIn.scheduleUpdate(pos, this, tickRate(worldIn));
		ModRegistries.registerTickableBlock(worldIn, pos);
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		ModRegistries.unregisterTickableBlock(pos);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateTick(World worldIn, BlockPos pos, IBlockState stateIn, Random rand) {
		if (worldIn.isAreaLoaded(pos.add(-32, -32, -32), pos.add(32, 32, 32))) {
			ParticleUtil.spawn(EnumParticles.GLOW_FLAME, worldIn, pos.getX() + 0.5D, pos.getY() + 0.25D, pos.getZ() + 0.5D, 0.0D, 0.0D, 0.0D);
		}
	}
}
