package wiiv.emporium.entity;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityMountable extends Entity {

	public BlockPos pos;
	private final String TAG_POS = "Position";

	public EntityMountable(World world) {
		super(world);
		noClip = true;
		height = 0.01F;
		width = 0.01F;
	}

	public EntityMountable(World world, BlockPos posIn, double y0ffset) {
		this(world);
		pos = posIn;
		double x = pos.getX();
		double y = pos.getY();
		double z = pos.getZ();
		setPosition(x + 0.5D, y + y0ffset, z + 0.5D);
	}

	public EntityMountable(World world, BlockPos posIn, double y0ffset, int rotation, double rotationOffset) {
		this(world);
		pos = posIn;

		setPostionConsideringRotation(pos, rotation, rotationOffset);
		//setRotation(180F, rotationPitch);
	}

	public void setPostionConsideringRotation(BlockPos posIn, int rotation, double rotationOffset) {
		double x = posIn.getX();
		double y = posIn.getY();
		double z = posIn.getZ();
		switch (rotation) {
		case 2:
			z += rotationOffset;
			break;
		case 0:
			z -= rotationOffset;
			break;
		case 3:
			x -= rotationOffset;
			break;
		case 1:
			x += rotationOffset;
			break;
		}
		setPosition(x, y, z);
	}

	@Override
	public double getMountedYOffset() {
		return height * 0.0D;
	}

	@Override
	protected boolean shouldSetPosAfterLoading() {
		return false;
	}

	@Override
	public void onEntityUpdate() {
		if (getEntityWorld() != null && !getEntityWorld().isRemote) {
			if (!isBeingRidden() || getEntityWorld().isAirBlock(pos)) {
				setDead();
			}
		}
	}

	@Override
	protected void entityInit() {
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {
		if (compound.hasKey(TAG_POS)) {
			pos = BlockPos.fromLong(compound.getLong(TAG_POS));
		}
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {
		if (pos != null) {
			compound.setLong(TAG_POS, pos.toLong());
		}
	}

}
