package wiiv.emporium.util;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.*;
import net.minecraft.world.World;
import wiiv.emporium.entity.EntityMountable;

public class MountableUtil {

	public static boolean sitOnBlock(World world, BlockPos pos, EntityPlayer player, double yOffset) {
		if (!checkForExistingEntity(world, pos, player)) {
			EntityMountable nemb = new EntityMountable(world, pos, yOffset);
			world.spawnEntityInWorld(nemb);
			player.startRiding(nemb);
		}
		return true;
	}

	public static boolean checkForExistingEntity(World par1World, BlockPos pos, EntityPlayer par5EntityPlayer) {
		double x = pos.getX();
		double y = pos.getY();
		double z = pos.getZ();
		List<EntityMountable> listEMB = par1World.getEntitiesWithinAABB(EntityMountable.class, new AxisAlignedBB(x, y, z, x + 1.0D, y + 1.0D, z + 1.0D).expand(1D, 1D, 1D));
		for (EntityMountable mountable : listEMB) {
			if (mountable.pos.getX() == x && mountable.pos.getY() == y && mountable.pos.getZ() == z) {
				if (!mountable.isBeingRidden()) {
					par5EntityPlayer.startRiding(mountable);
				}
				return true;
			}
		}
		return false;
	}

	public static boolean isSomeoneSitting(World world, BlockPos pos) {
		double x = pos.getX();
		double y = pos.getY();
		double z = pos.getZ();
		List<EntityMountable> listEMB = world.getEntitiesWithinAABB(EntityMountable.class, new AxisAlignedBB(x, y, z, x + 1.0D, y + 1.0D, z + 1.0D).expand(1D, 1D, 1D));
		for (EntityMountable mount : listEMB) {
			if (mount.pos.getX() == x && mount.pos.getY() == y && mount.pos.getZ() == z) {
				return mount.isBeingRidden();
			}
		}
		return false;
	}
}
