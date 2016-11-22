package wiiv.emporium.block;

import java.util.Random;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.*;

public class BlockLampCandelabra extends BlockBaseColorable16 {

	public BlockLampCandelabra(int color) {
		super(Material.ANVIL, "candelabra_lamp", 1.0F, color);
		setSoundType(SoundType.ANVIL);
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
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		worldIn.spawnParticle(EnumParticleTypes.FLAME, pos.getX() + 0.1D, pos.getY() + 0.8D, pos.getZ() + 0.5D, 0.0D, 0.0D, 0.0D, new int[0]);
		worldIn.spawnParticle(EnumParticleTypes.FLAME, pos.getX() + 0.5D, pos.getY() + 0.8D, pos.getZ() + 0.1D, 0.0D, 0.0D, 0.0D, new int[0]);
		worldIn.spawnParticle(EnumParticleTypes.FLAME, pos.getX() + 0.9D, pos.getY() + 0.8D, pos.getZ() + 0.5D, 0.0D, 0.0D, 0.0D, new int[0]);
		worldIn.spawnParticle(EnumParticleTypes.FLAME, pos.getX() + 0.5D, pos.getY() + 0.8D, pos.getZ() + 0.9D, 0.0D, 0.0D, 0.0D, new int[0]);
		worldIn.spawnParticle(EnumParticleTypes.FLAME, pos.getX() + 0.5D, pos.getY() + 0.9D, pos.getZ() + 0.5D, 0.0D, 0.0D, 0.0D, new int[0]);
	}

}
