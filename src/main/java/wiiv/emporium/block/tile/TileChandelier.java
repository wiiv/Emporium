/*
 * This file is part of p455w0rd's Things.
 * Copyright (c) 2016, p455w0rd (aka TheRealp455w0rd), All rights reserved
 * unless
 * otherwise stated.
 *
 * p455w0rd's Things is free software: you can redistribute it and/or modify
 * it under the terms of the MIT License.
 *
 * p455w0rd's Things is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * MIT License for more details.
 *
 * You should have received a copy of the MIT License
 * along with p455w0rd's Things. If not, see
 * <https://opensource.org/licenses/MIT>.
 */
package wiiv.emporium.block.tile;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

/**
 * @author p455w0rd
 *
 */
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
