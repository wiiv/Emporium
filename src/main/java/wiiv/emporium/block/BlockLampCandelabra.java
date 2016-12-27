package wiiv.emporium.block;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import wiiv.emporium.api.ICacheableBlock;
import wiiv.emporium.client.render.EnumParticles;
import wiiv.emporium.init.ModRegistries;
import wiiv.emporium.util.ParticleUtil;

public class BlockLampCandelabra extends BlockBaseColorable16 implements ICacheableBlock {

	public BlockLampCandelabra(int color) {
		super(Material.ANVIL, "candelabra_lamp", 1.0F, color);
		//setSoundType(SoundType.ANVIL);
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
			ParticleUtil.spawn(EnumParticles.GLOW_FLAME, worldIn, pos.getX() + 0.13D, pos.getY() + 0.7D, pos.getZ() + 0.5D, 0.0D, 0.0D, 0.0D);
			ParticleUtil.spawn(EnumParticles.GLOW_FLAME, worldIn, pos.getX() + 0.5D, pos.getY() + 0.7D, pos.getZ() + 0.13D, 0.0D, 0.0D, 0.0D);
			ParticleUtil.spawn(EnumParticles.GLOW_FLAME, worldIn, pos.getX() + 0.87D, pos.getY() + 0.7D, pos.getZ() + 0.5D, 0.0D, 0.0D, 0.0D);
			ParticleUtil.spawn(EnumParticles.GLOW_FLAME, worldIn, pos.getX() + 0.5D, pos.getY() + 0.7D, pos.getZ() + 0.87D, 0.0D, 0.0D, 0.0D);
			ParticleUtil.spawn(EnumParticles.GLOW_FLAME, worldIn, pos.getX() + 0.5D, pos.getY() + 0.8D, pos.getZ() + 0.5D, 0.0D, 0.0D, 0.0D);
		}
	}

}
