package wiiv.emporium.util;

import java.util.List;
import java.util.Map;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import wiiv.emporium.entity.EntityMountable;

public class MountableUtil {

	public static boolean sitOnBlock(World world, BlockPos pos, EntityPlayer player, double yOffset) {
		return sitOnBlock(world, pos, player, yOffset, false);
	}

	public static boolean sitOnBlock(World world, BlockPos pos, EntityPlayer player, double yOffset, boolean directional) {
		if (isPlayerMounting(player)) {
			player.dismountEntity(player.getRidingEntity());
		}
		if (!checkForExistingEntity(world, pos, player)) {
			EntityMountable nemb = new EntityMountable(world, pos, yOffset);
			world.spawnEntity(nemb);
			if (directional) {
				IBlockState state = world.getBlockState(pos);
				Map<IProperty<?>, Comparable<?>> propMap = state.getProperties();
				for (IProperty<?> prop : propMap.keySet()) {
					if (prop instanceof PropertyDirection) {
						switch ((EnumFacing) propMap.get(prop)) {
						case EAST:
							player.rotationYaw = 90;
							break;
						case NORTH:
							player.rotationYaw = 360;
							break;
						case SOUTH:
							player.rotationYaw = 180;
							break;
						case WEST:
							player.rotationYaw = 270;
							break;
						default:
							break;
						}
					}
				}
			}
			player.rotationPitch = 20;
			player.startRiding(nemb);
		}
		return true;
	}

	public static boolean checkForExistingEntity(World world, BlockPos pos, EntityPlayer player) {
		double x = pos.getX();
		double y = pos.getY();
		double z = pos.getZ();
		List<EntityMountable> listEMB = world.getEntitiesWithinAABB(EntityMountable.class, new AxisAlignedBB(x, y, z, x + 1.0D, y + 1.0D, z + 1.0D).expand(1D, 1D, 1D));
		for (EntityMountable mountable : listEMB) {
			if (mountable.pos != null && (mountable.pos.getX() == x && mountable.pos.getY() == y && mountable.pos.getZ() == z)) {
				if (!mountable.isBeingRidden()) {
					player.startRiding(mountable);
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

	public static boolean isPlayerMounting(EntityPlayer player) {
		return player.isRiding();
	}
}
