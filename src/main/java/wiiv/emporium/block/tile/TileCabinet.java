package wiiv.emporium.block.tile;

import javax.annotation.Nullable;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.InvWrapper;
import wiiv.emporium.container.ContainerCabinet;

public class TileCabinet extends TileEntity implements ITickable, IInventory {

	public float doorAngle;
	public float prevDoorAngle;
	private boolean doorsOpen = false;
	private final String TAG_INVENTORY = "Inventory";
	private final String TAG_SLOT = "Slot";
	private final String TAG_ISOPEN = "DoorsOpen";

	private ItemStack[] inventory = new ItemStack[16];

	public IInventory getInventory() {
		return this;
	}

	public ItemStack[] getInventoryArray() {
		return inventory;
	}

	public IItemHandlerModifiable getHandler() {
		return new InvWrapper(this);
	}

	public boolean doorsOpen() {
		return doorsOpen;
	}

	public void setDoorsOpen(boolean open) {
		doorsOpen = open;
	}

	@Override
	public NBTTagCompound getUpdateTag() {
		return writeToNBT(new NBTTagCompound());
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(getHandler()) : super.getCapability(capability, facing);
	}

	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound nbtTag = new NBTTagCompound();
		writeToNBT(nbtTag);
		return new SPacketUpdateTileEntity(getPos(), 1, nbtTag);
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
		readFromNBT(packet.getNbtCompound());
		markDirty();
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		NBTTagList tagList = compound.getTagList(TAG_INVENTORY, 10);
		for (int i = 0; i < tagList.tagCount(); i++) {
			NBTTagCompound slotNBT = tagList.getCompoundTagAt(i);
			if (slotNBT != null) {
				getHandler().setStackInSlot(slotNBT.getInteger(TAG_SLOT), ItemStack.loadItemStackFromNBT(slotNBT));
			}
		}
		setDoorsOpen(compound.getBoolean(TAG_ISOPEN));
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		NBTTagList nbtList = new NBTTagList();
		for (int i = 0; i < getHandler().getSlots(); i++) {
			if (getHandler().getStackInSlot(i) != null) {
				NBTTagCompound slotNBT = new NBTTagCompound();
				slotNBT.setInteger(TAG_SLOT, i);
				getHandler().getStackInSlot(i).writeToNBT(slotNBT);
				nbtList.appendTag(slotNBT);
			}
		}
		compound.setTag(TAG_INVENTORY, nbtList);
		compound.setBoolean(TAG_ISOPEN, doorsOpen());
		return super.writeToNBT(compound);
	}

	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
		return oldState.getBlock() != newSate.getBlock();
	}

	@Override
	public void update() {
		prevDoorAngle = doorAngle;

		if (!isInUse() && doorAngle > 0.0F || isInUse() && doorAngle < 1.0F || doorsOpen() && doorAngle < 1.0F) {
			//float f2 = doorAngle;

			if (isInUse() || doorsOpen()) {
				doorAngle += 0.1F;
			}
			else {
				if (!doorsOpen()) {
					doorAngle -= 0.1F;
				}
			}

			if (doorAngle > 1.0F) {
				doorAngle = 1.0F;
			}
		}

		if (doorAngle < 0.0F) {
			doorAngle = 0.0F;
		}
	}

	public boolean isInUse() {
		int i = pos.getX();
		int j = pos.getY();
		int k = pos.getZ();
		for (EntityPlayer player : worldObj.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(i - 5.0F, j - 5.0F, k - 5.0F, i + 1 + 5.0F, j + 1 + 5.0F, k + 1 + 5.0F))) {
			if (player.openContainer instanceof ContainerCabinet) {
				if (((ContainerCabinet) player.openContainer).getCabinetInventory() == getInventory()) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public String getName() {
		return "cabinet";
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	@Override
	public int getSizeInventory() {
		return inventory.length;
	}

	@Nullable
	@Override
	public ItemStack getStackInSlot(int index) {
		return inventory[index];
	}

	@Nullable
	@Override
	public ItemStack decrStackSize(int index, int count) {
		ItemStack stack = ItemStackHelper.getAndSplit(inventory, index, count);
		markDirty();
		return stack;
	}

	@Nullable
	@Override
	public ItemStack removeStackFromSlot(int index) {
		ItemStack stack = ItemStackHelper.getAndRemove(inventory, index);
		markDirty();
		return stack;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		inventory[index] = stack;

		if (stack != null && stack.stackSize > getInventoryStackLimit()) {
			stack.stackSize = getInventoryStackLimit();
		}
		markDirty();
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return true;
	}

	@Override
	public void openInventory(EntityPlayer player) {
	}

	@Override
	public void closeInventory(EntityPlayer player) {
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return true;
	}

	@Override
	public int getField(int id) {
		return 0;
	}

	@Override
	public void setField(int id, int value) {
	}

	@Override
	public int getFieldCount() {
		return 0;
	}

	@Override
	public void clear() {
	}

	@Override
	public void markDirty() {
		super.markDirty();
		if (getWorld() != null) {
			IBlockState state = getWorld().getBlockState(pos);
			if (state != null) {
				state.getBlock().updateTick(getWorld(), getPos(), state, getWorld().rand);
				getWorld().notifyBlockUpdate(pos, state, state, 3);
			}
		}
	}

}