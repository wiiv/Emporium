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
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import wiiv.emporium.block.BlockCandleChandelierColorable;
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
	private float fallHurtAmount = 40.0F;
	public NBTTagCompound tileEntityData;
	protected static final DataParameter<BlockPos> ORIGIN = EntityDataManager.<BlockPos>createKey(EntityFallingChandelier.class, DataSerializers.BLOCK_POS);
	protected static final DataParameter<Integer> TYPE = EntityDataManager.<Integer>createKey(EntityFallingChandelier.class, DataSerializers.VARINT);

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
		setType(fallTile.getBlock());
	}

	public void setType(Block block) {
		int i = 0;
		for (BlockCandleChandelierColorable chandelier : ModBlocks.CANDLE_CHANDELIER_COLORABLE) {
			++i;
			if (block == chandelier) {
				dataManager.set(TYPE, i - 1);
			}
		}
	}

	public int getType() {
		return dataManager.get(TYPE);
	}

	public void setOrigin(BlockPos pos) {
		dataManager.set(ORIGIN, pos);
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
		dataManager.register(TYPE, 0);
	}

	/**
	 * Returns true if other Entities should be prevented from moving through this Entity.
	 */
	@Override
	public boolean canBeCollidedWith() {
		return !isDead;
	}

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

				if (world.getBlockState(blockpos).getBlock() == block) {
					world.setBlockToAir(blockpos);
				}
				else if (!world.isRemote) {
					setDead();
					return;
				}
			}

			if (!hasNoGravity()) {
				motionY -= 0.03999999910593033D;
			}

			moveEntity(motionX, motionY, motionZ);
			motionX *= 0.9800000190734863D;
			motionY *= 0.9800000190734863D;
			motionZ *= 0.9800000190734863D;

			if (!world.isRemote) {
				BlockPos blockpos1 = new BlockPos(this);

				if (onGround) {
					IBlockState iblockstate = world.getBlockState(blockpos1);

					if (world.isAirBlock(new BlockPos(posX, posY - 0.009999999776482582D, posZ))) {
						if (BlockCandleChandelierColorable.canFallThrough(world.getBlockState(new BlockPos(posX, posY - 0.009999999776482582D, posZ)))) {
							onGround = false;
							return;
						}
					}

					motionX *= 0.699999988079071D;
					motionZ *= 0.699999988079071D;
					motionY *= -0.5D;

					if (iblockstate.getBlock() != Blocks.PISTON_EXTENSION) {
						//setDead();

						//if (!canSetAsBlock) {
						//if (world.canBlockBePlaced(block, blockpos1, true, EnumFacing.UP, (Entity) null, (ItemStack) null) && !BlockLampChandelier.canFallThrough(world.getBlockState(blockpos1.down())) && world.setBlockState(blockpos1, fallTile, 3)) {
						//		if (block instanceof BlockLampChandelier) {
						//((BlockLampChandelier) block).onEndFalling(world, blockpos1);
						//setDead();
						//		}
						//}
						//else if (shouldDropItem && world.getGameRules().getBoolean("doEntityDrops")) {
						entityDropItem(new ItemStack(block, 1), 0.0F);
						((BlockCandleChandelierColorable) block).onEndFalling(world, blockpos1);
						setDead();
						//}
						//}
					}
				}
				else if (fallTime > 100 && !world.isRemote && (blockpos1.getY() < 1 || blockpos1.getY() > 256) || fallTime > 600) {
					if (shouldDropItem && world.getGameRules().getBoolean("doEntityDrops")) {
						entityDropItem(new ItemStack(block, 1), 0.0F);
					}
					((BlockCandleChandelierColorable) block).onEndFalling(world, blockpos1);

					setDead();
				}
			}
		}
	}

	@Override
	public void fall(float distance, float damageMultiplier) {
		Block block = fallTile.getBlock();

		if (hurtEntities) {
			int i = MathHelper.ceil(distance - 1.0F);

			if (i > 0) {
				List<Entity> list = Lists.newArrayList(world.getEntitiesWithinAABBExcludingEntity(this, getEntityBoundingBox()));
				DamageSource damagesource = DamageSource.ANVIL;

				for (Entity entity : list) {
					entity.attackEntityFrom(damagesource, Math.min(MathHelper.floor(i * fallHurtAmount), fallHurtMax));
				}

				if (rand.nextFloat() < 0.05000000074505806D + i * 0.05D) {
					canSetAsBlock = true;
				}
			}
		}
	}

	private boolean isChandelier(Block block) {
		for (BlockCandleChandelierColorable chandelier : ModBlocks.CANDLE_CHANDELIER_COLORABLE) {
			if (block == chandelier) {
				return true;
			}
		}
		return false;
	}

	@Nullable
	public IBlockState getBlock() {
		return ModBlocks.CANDLE_CHANDELIER_COLORABLE[getType()].getDefaultState();
	}

	private BlockCandleChandelierColorable getChandelier(Block block) {
		for (BlockCandleChandelierColorable chandelier : ModBlocks.CANDLE_CHANDELIER_COLORABLE) {
			if (block == chandelier) {
				return chandelier;
			}
		}
		return null;
	}

	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {
		/*
		Block block = fallTile != null ? fallTile.getBlock() : Blocks.AIR;
		ResourceLocation resourcelocation = Block.REGISTRY.getNameForObject(block);
		compound.setString("Block", resourcelocation == null ? "" : resourcelocation.toString());
		//compound.setByte("Data", (byte) block.getMetaFromState(fallTile));
		compound.setInteger("Time", fallTime);
		compound.setBoolean("DropItem", shouldDropItem);
		compound.setBoolean("HurtEntities", hurtEntities);
		compound.setFloat("FallHurtAmount", fallHurtAmount);
		compound.setInteger("FallHurtMax", fallHurtMax);
		
		if (tileEntityData != null) {
			compound.setTag("TileEntityData", tileEntityData);
		}
		*/
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {
		//int i = compound.getByte("Data") & 255;
		/*
				if (compound.hasKey("Block", 8)) {
					fallTile = Block.getBlockFromName(compound.getString("Block")).getDefaultState();
				}
				else if (compound.hasKey("TileID", 99)) {
					fallTile = Block.getBlockById(compound.getInteger("TileID")).getDefaultState();
				}
				else {
					fallTile = Block.getBlockById(compound.getByte("Tile") & 255).getDefaultState();
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
				*/
	}

	public void setHurtEntities(boolean shouldHurtEntities) {
		hurtEntities = shouldHurtEntities;
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
		return world;
	}

	/**
	 * Return whether this entity should be rendered as on fire.
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public boolean canRenderOnFire() {
		return false;
	}

	@Override
	public boolean ignoreItemEntityData() {
		return true;
	}
}