package wiiv.emporium.entity;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.*;
import net.minecraft.util.*;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.*;
import wiiv.emporium.block.BlockLampChandelier;
import wiiv.emporium.init.ModBlocks;

/**
 * @author p455w0rd
 *
 */
public class EntityFallingChandelier extends Entity {
	public IBlockState fallTile;
	public int fallTime;
	public boolean shouldDropItem = true;
	private boolean canSetAsBlock;
	private boolean hurtEntities;
	private int fallHurtMax = 40;
	private float fallHurtAmount = 2.0F;
	public NBTTagCompound tileEntityData;
	protected static final DataParameter<BlockPos> ORIGIN = EntityDataManager.<BlockPos>createKey(EntityFallingChandelier.class, DataSerializers.BLOCK_POS);

	public EntityFallingChandelier(World worldIn) {
		super(worldIn);
	}

	public EntityFallingChandelier(World worldIn, double x, double y, double z, IBlockState fallingBlockState) {
		super(worldIn);
		fallTile = fallingBlockState;
		preventEntitySpawning = true;
		setSize(0.98F, 0.98F);
		setPosition(x, y + (1.0F - height) / 2.0F, z);
		motionX = 0.0D;
		motionY = 0.0D;
		motionZ = 0.0D;
		prevPosX = x;
		prevPosY = y;
		prevPosZ = z;
		setOrigin(new BlockPos(this));
	}

	public void setOrigin(BlockPos p_184530_1_) {
		dataManager.set(ORIGIN, p_184530_1_);
	}

	@SideOnly(Side.CLIENT)
	public BlockPos getOrigin() {
		return dataManager.get(ORIGIN);
	}

	/**
	 * returns if this entity triggers Block.onEntityWalking on the blocks they walk on. used for spiders and wolves to
	 * prevent them from trampling crops
	 */
	@Override
	protected boolean canTriggerWalking() {
		return false;
	}

	@Override
	protected void entityInit() {
		dataManager.register(ORIGIN, BlockPos.ORIGIN);
	}

	/**
	 * Returns true if other Entities should be prevented from moving through this Entity.
	 */
	@Override
	public boolean canBeCollidedWith() {
		return !isDead;
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	@Override
	public void onUpdate() {
		if (fallTile == null) {
			return;
		}
		Block block = fallTile.getBlock();

		if (fallTile.getMaterial() == Material.AIR) {
			setDead();
		}
		else {
			prevPosX = posX;
			prevPosY = posY;
			prevPosZ = posZ;

			if (fallTime++ == 0) {
				BlockPos blockpos = new BlockPos(this);

				if (worldObj.getBlockState(blockpos).getBlock() == block) {
					worldObj.setBlockToAir(blockpos);
				}
				else if (!worldObj.isRemote) {
					setDead();
					return;
				}
			}

			if (!func_189652_ae()) {
				motionY -= 0.03999999910593033D;
			}

			moveEntity(motionX, motionY, motionZ);
			motionX *= 0.9800000190734863D;
			motionY *= 0.9800000190734863D;
			motionZ *= 0.9800000190734863D;

			if (!worldObj.isRemote) {
				BlockPos blockpos1 = new BlockPos(this);

				if (onGround) {
					IBlockState iblockstate = worldObj.getBlockState(blockpos1);

					if (worldObj.isAirBlock(new BlockPos(posX, posY - 0.009999999776482582D, posZ))) {
						//if (BlockLampChandelier.canFallThrough(worldObj.getBlockState(new BlockPos(posX, posY - 0.009999999776482582D, posZ)))) {
						//	onGround = false;
						//	return;
						//}
					}

					motionX *= 0.699999988079071D;
					motionZ *= 0.699999988079071D;
					motionY *= -0.5D;

					if (iblockstate.getBlock() != Blocks.PISTON_EXTENSION) {
						setDead();

						if (!canSetAsBlock) {/*
												if (worldObj.canBlockBePlaced(block, blockpos1, true, EnumFacing.UP, (Entity) null, (ItemStack) null) && !BlockLampChandelier.canFallThrough(worldObj.getBlockState(blockpos1.down())) && worldObj.setBlockState(blockpos1, fallTile, 3)) {
												if (isChandelier(block)) {
												((BlockLampChandelier) block).onEndFalling(worldObj, blockpos1);
												}
												
												if (tileEntityData != null && block instanceof ITileEntityProvider) {
												TileEntity tileentity = worldObj.getTileEntity(blockpos1);
												
												if (tileentity != null) {
												NBTTagCompound nbttagcompound = tileentity.writeToNBT(new NBTTagCompound());
												
												for (String s : tileEntityData.getKeySet()) {
												NBTBase nbtbase = tileEntityData.getTag(s);
												
												if (!"x".equals(s) && !"y".equals(s) && !"z".equals(s)) {
												nbttagcompound.setTag(s, nbtbase.copy());
												}
												}
												
												tileentity.readFromNBT(nbttagcompound);
												tileentity.markDirty();
												}
												}
												}
												else if (shouldDropItem && worldObj.getGameRules().getBoolean("doEntityDrops")) {
												entityDropItem(new ItemStack(block, 1, block.damageDropped(fallTile)), 0.0F);
												}
												*/
						}
					}
				}
				else if (fallTime > 100 && !worldObj.isRemote && (blockpos1.getY() < 1 || blockpos1.getY() > 256) || fallTime > 600) {
					if (shouldDropItem && worldObj.getGameRules().getBoolean("doEntityDrops")) {
						entityDropItem(new ItemStack(block, 1, block.damageDropped(fallTile)), 0.0F);
					}

					setDead();
				}
			}
		}
	}

	@Override
	public void fall(float distance, float damageMultiplier) {
		Block block = fallTile.getBlock();

		if (hurtEntities) {
			int i = MathHelper.ceiling_float_int(distance - 1.0F);

			if (i > 0) {
				List<Entity> list = Lists.newArrayList(worldObj.getEntitiesWithinAABBExcludingEntity(this, getEntityBoundingBox()));
				boolean flag = isChandelier(block);
				DamageSource damagesource = flag ? DamageSource.anvil : DamageSource.fallingBlock;

				for (Entity entity : list) {
					entity.attackEntityFrom(damagesource, Math.min(MathHelper.floor_float(i * fallHurtAmount), fallHurtMax));
				}
				canSetAsBlock = true;
			}
		}
	}

	private boolean isChandelier(Block block) {
		for (BlockLampChandelier chandelier : ModBlocks.CHANDELIER_LAMP) {
			if (block == chandelier) {
				return true;
			}
		}
		return false;
	}

	private BlockLampChandelier getChandelier(Block block) {
		for (BlockLampChandelier chandelier : ModBlocks.CHANDELIER_LAMP) {
			if (block == chandelier) {
				return chandelier;
			}
		}
		return null;
	}

	public static void func_189741_a(DataFixer p_189741_0_) {
	}

	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {
		Block block = fallTile != null ? fallTile.getBlock() : Blocks.AIR;
		ResourceLocation resourcelocation = Block.REGISTRY.getNameForObject(block);
		compound.setString("Block", resourcelocation == null ? "" : resourcelocation.toString());
		compound.setByte("Data", (byte) block.getMetaFromState(fallTile));
		compound.setInteger("Time", fallTime);
		compound.setBoolean("DropItem", shouldDropItem);
		compound.setBoolean("HurtEntities", hurtEntities);
		compound.setFloat("FallHurtAmount", fallHurtAmount);
		compound.setInteger("FallHurtMax", fallHurtMax);

		if (tileEntityData != null) {
			compound.setTag("TileEntityData", tileEntityData);
		}
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {
		int i = compound.getByte("Data") & 255;

		if (compound.hasKey("Block", 8)) {
			fallTile = Block.getBlockFromName(compound.getString("Block")).getStateFromMeta(i);
		}
		else if (compound.hasKey("TileID", 99)) {
			fallTile = Block.getBlockById(compound.getInteger("TileID")).getStateFromMeta(i);
		}
		else {
			fallTile = Block.getBlockById(compound.getByte("Tile") & 255).getStateFromMeta(i);
		}

		fallTime = compound.getInteger("Time");
		Block block = fallTile.getBlock();

		if (compound.hasKey("HurtEntities", 99)) {
			hurtEntities = compound.getBoolean("HurtEntities");
			fallHurtAmount = compound.getFloat("FallHurtAmount");
			fallHurtMax = compound.getInteger("FallHurtMax");
		}
		else if (isChandelier(block)) {
			hurtEntities = true;
		}

		if (compound.hasKey("DropItem", 99)) {
			shouldDropItem = compound.getBoolean("DropItem");
		}

		if (compound.hasKey("TileEntityData", 10)) {
			tileEntityData = compound.getCompoundTag("TileEntityData");
		}

		if (block == null || block.getDefaultState().getMaterial() == Material.AIR) {
			fallTile = Blocks.SAND.getDefaultState();
		}
	}

	public void setHurtEntities(boolean p_145806_1_) {
		hurtEntities = p_145806_1_;
	}

	@Override
	public void addEntityCrashInfo(CrashReportCategory category) {
		super.addEntityCrashInfo(category);

		if (fallTile != null) {
			Block block = fallTile.getBlock();
			category.addCrashSection("Immitating block ID", Integer.valueOf(Block.getIdFromBlock(block)));
			category.addCrashSection("Immitating block data", Integer.valueOf(block.getMetaFromState(fallTile)));
		}
	}

	@SideOnly(Side.CLIENT)
	public World getWorldObj() {
		return worldObj;
	}

	/**
	 * Return whether this entity should be rendered as on fire.
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public boolean canRenderOnFire() {
		return false;
	}

	@Nullable
	public IBlockState getBlock() {
		return fallTile;
	}

	@Override
	public boolean ignoreItemEntityData() {
		return true;
	}
}