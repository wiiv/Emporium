package wiiv.emporium.block.tile;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class TileChandelier extends TileEntity implements ITickable {

	@Override
	public void update() {
		if (isInvalid() || !getWorld().isBlockLoaded(getPos()) || getWorld().isRemote) {
			return;
		}
		onEntityUpdate();
	}

	public void onEntityUpdate() {
		/*
				worldObj.spawnParticle(EnumParticleTypes.FLAME, pos.getX() + 0.1D, pos.getY() + 0.8D, pos.getZ() + 0.5D, 0.0D, 0.0D, 0.0D, new int[0]);
				worldObj.spawnParticle(EnumParticleTypes.FLAME, pos.getX() + 0.5D, pos.getY() + 0.8D, pos.getZ() + 0.1D, 0.0D, 0.0D, 0.0D, new int[0]);
				worldObj.spawnParticle(EnumParticleTypes.FLAME, pos.getX() + 0.9D, pos.getY() + 0.8D, pos.getZ() + 0.5D, 0.0D, 0.0D, 0.0D, new int[0]);
				worldObj.spawnParticle(EnumParticleTypes.FLAME, pos.getX() + 0.5D, pos.getY() + 0.8D, pos.getZ() + 0.9D, 0.0D, 0.0D, 0.0D, new int[0]);

		if (worldObj.isBlockPowered(pos)) {
			if (!worldObj.getBlockState(pos).getValue(BlockLampChandelier.POWERED)) {
				worldObj.setBlockState(pos, worldObj.getBlockState(pos).withProperty(BlockLampChandelier.POWERED, true), 1 | 2);
				worldObj.scheduleUpdate(pos, getBlockType(), getBlockType().tickRate(worldObj));
			}
		}
		else if (worldObj.getBlockState(pos).getValue(BlockLampChandelier.POWERED)) {
			worldObj.setBlockState(pos, worldObj.getBlockState(pos).withProperty(BlockLampChandelier.POWERED, false), 1 | 2);
			worldObj.scheduleUpdate(pos, getBlockType(), getBlockType().tickRate(worldObj));
		}
		*/
	}

}
