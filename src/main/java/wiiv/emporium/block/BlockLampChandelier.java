package wiiv.emporium.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import wiiv.emporium.api.ICacheableBlock;
import wiiv.emporium.client.render.EnumParticles;
import wiiv.emporium.entity.EntityFallingChandelier;
import wiiv.emporium.init.ModRegistries;
import wiiv.emporium.util.ParticleUtil;

public class BlockLampChandelier extends BlockBaseColorable16 implements ICacheableBlock {

	public static boolean fallInstantly;

	public BlockLampChandelier(int color) {
		super(Material.ANVIL, "chandelier_lamp", 1.0F, color);
		setLightLevel(0.75F);
		setDefaultState(blockState.getBaseState());
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {});
	}

	@Override
	public int tickRate(World worldIn) {
		return 1;
	}

	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn) {
		worldIn.scheduleUpdate(pos, this, tickRate(worldIn));
	}

	private void checkFallable(World worldIn, BlockPos pos) {
		if ((worldIn.isAirBlock(pos.down()) || canFallThrough(worldIn.getBlockState(pos.down()))) && pos.getY() >= 0) {
			//int i = 32;

			if (!fallInstantly && worldIn.isAreaLoaded(pos.add(-32, -32, -32), pos.add(32, 32, 32))) {
				if (!worldIn.isRemote) {
					EntityFallingChandelier entityfallingblock = new EntityFallingChandelier(worldIn, pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, worldIn.getBlockState(pos));
					onStartFalling(entityfallingblock);
					worldIn.spawnEntityInWorld(entityfallingblock);
				}
			}
			else {
				IBlockState state = worldIn.getBlockState(pos);
				worldIn.setBlockToAir(pos);
				BlockPos blockpos;

				for (blockpos = pos.down(); (worldIn.isAirBlock(blockpos) || canFallThrough(worldIn.getBlockState(blockpos))) && blockpos.getY() > 0; blockpos = blockpos.down()) {
					;
				}

				if (blockpos.getY() > 0) {
					worldIn.setBlockState(blockpos.up(), state); //Forge: Fix loss of state information during world gen.
				}
			}
		}
	}

	public static boolean canFallThrough(IBlockState state) {
		Block block = state.getBlock();
		Material material = state.getMaterial();
		return block == Blocks.FIRE || material == Material.AIR || material == Material.WATER || material == Material.LAVA;
	}

	protected void onStartFalling(EntityFallingChandelier fallingEntity) {
		fallingEntity.setHurtEntities(true);
	}

	public void onEndFalling(World worldIn, BlockPos pos) {
		worldIn.playEvent(1031, pos, 0);
	}

	@Override
	public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side) {
		return side == EnumFacing.DOWN && canPlaceBlockAt(worldIn, pos);
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
		if (!worldIn.isRemote && worldIn.isBlockPowered(pos)) {
			checkFallable(worldIn, pos);
		}
		if (worldIn.isAreaLoaded(pos.add(-32, -32, -32), pos.add(32, 32, 32))) {
			ParticleUtil.spawn(EnumParticles.GLOW_FLAME, worldIn, pos.getX() + 0.12D, pos.getY() + 0.68D, pos.getZ() + 0.5D, 0.0D, 0.0D, 0.0D);
			ParticleUtil.spawn(EnumParticles.GLOW_FLAME, worldIn, pos.getX() + 0.5D, pos.getY() + 0.68D, pos.getZ() + 0.12D, 0.0D, 0.0D, 0.0D);
			ParticleUtil.spawn(EnumParticles.GLOW_FLAME, worldIn, pos.getX() + 0.87D, pos.getY() + 0.68D, pos.getZ() + 0.5D, 0.0D, 0.0D, 0.0D);
			ParticleUtil.spawn(EnumParticles.GLOW_FLAME, worldIn, pos.getX() + 0.5D, pos.getY() + 0.68D, pos.getZ() + 0.87D, 0.0D, 0.0D, 0.0D);
		}
	}

}
