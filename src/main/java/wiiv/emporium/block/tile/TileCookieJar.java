package wiiv.emporium.block.tile;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import wiiv.emporium.api.ICookie;
import wiiv.emporium.api.ICookieJar;
import wiiv.emporium.util.CookieType;

public class TileCookieJar extends TileEntity implements ICookieJar {

	private ItemStackHandler handler = new ItemStackHandler(1) {
		@Override
		protected int getStackLimit(int slot, ItemStack stack) {
			return stack.getMaxStackSize();
		}
	};

	public ItemStack getStack() {
		return handler.getStackInSlot(0);
	}

	public ItemStack addCookies(ItemStack stack) {
		ItemStack ret;
		if (stack == null) {
			handler.setStackInSlot(0, null);
			ret = null;
		}
		else {
			ret = handler.insertItem(0, stack, false);
		}
		markDirty();
		if (worldObj != null) {
			IBlockState state = worldObj.getBlockState(getPos());
			worldObj.notifyBlockUpdate(getPos(), state, state, 3);
		}
		return ret;
	}

	public ItemStack takeCookies(int amount) {
		ItemStack ret;
		ItemStack currentStack = handler.getStackInSlot(0);
		if (currentStack == null) {
			return null;
		}
		if (amount >= currentStack.stackSize) {
			ret = currentStack;
			handler.setStackInSlot(0, null);
		}
		else {
			ret = handler.extractItem(0, amount, false);
		}
		markDirty();
		if (worldObj != null) {
			IBlockState state = worldObj.getBlockState(getPos());
			worldObj.notifyBlockUpdate(getPos(), state, state, 3);
		}
		return ret;
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
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(handler) : super.getCapability(capability, facing);
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
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		handler.deserializeNBT(compound.getCompoundTag("item"));
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setTag("item", handler.serializeNBT());
		return compound;
	}

	public static float getXOffset(int index) {
		float[] offsets = new float[16];
		offsets[0] = 0.06F;
		offsets[1] = -0.07F;
		offsets[2] = 0.0F;
		offsets[3] = -0.085F;
		offsets[4] = 0.0F;
		offsets[5] = 0.15F;
		offsets[6] = -0.05F;
		offsets[7] = 0.035F;
		offsets[8] = 0.07F;
		offsets[9] = -0.09F;
		offsets[10] = 0.07F;
		offsets[11] = -0.05F;
		offsets[12] = 0.0F;
		offsets[13] = -0.08F;
		offsets[14] = 0.0F;
		offsets[15] = 0.2F;
		return offsets[index];
	}

	public static float getZOffset(int index) {
		float[] offsets = new float[16];
		offsets[0] = 0.0F;
		offsets[1] = -0.1F;
		offsets[2] = 0.1F;
		offsets[3] = 0.2F;
		offsets[4] = 0.0F;
		offsets[5] = -0.1F;
		offsets[6] = 0.1F;
		offsets[7] = 0.0F;
		offsets[8] = -0.3F;
		offsets[9] = 0.1F;
		offsets[10] = 0.13F;
		offsets[11] = -0.01F;
		offsets[12] = 0.09F;
		offsets[13] = -0.2F;
		offsets[14] = 0.2F;
		offsets[15] = -0.2F;
		return offsets[index];
	}

	public static float getYOffset(int index) {
		float[] offsets = new float[16];
		offsets[0] = -0.0F;
		offsets[1] = -0.0F;
		offsets[2] = -0.0F;
		offsets[3] = -0.0F;
		offsets[4] = -0.0F;
		offsets[5] = -0.0F;
		offsets[6] = -0.0F;
		offsets[7] = -0.0F;
		offsets[8] = -0.0F;
		offsets[9] = -0.0F;
		offsets[10] = -0.0F;
		offsets[10] = -0.0F;
		offsets[11] = -0.0F;
		offsets[12] = -0.0F;
		offsets[13] = -0.0F;
		offsets[14] = -0.0F;
		offsets[15] = -0.0F;
		return offsets[index];
	}

	public static float getXRot(int index) {
		float[] offsets = new float[16];
		offsets[0] = 0.0F;
		offsets[1] = 0.0F;
		offsets[2] = 0.0F;
		offsets[3] = 0.0F;
		offsets[4] = 0.0F;
		offsets[5] = 0.0F;
		offsets[6] = 0.0F;
		offsets[7] = 0.0F;
		offsets[8] = 0.0F;
		offsets[9] = 0.0F;
		offsets[10] = 0.0F;
		offsets[11] = 0.0F;
		offsets[12] = 0.0F;
		offsets[13] = 0.0F;
		offsets[14] = 0.0F;
		offsets[15] = 0.0F;
		return offsets[index];
	}

	public static float getZRot(int index) {
		float[] offsets = new float[16];
		offsets[0] = 0.0F;
		offsets[1] = 0.0F;
		offsets[2] = 0.0F;
		offsets[3] = 0.0F;
		offsets[4] = 0.0F;
		offsets[5] = 0.0F;
		offsets[6] = 0.0F;
		offsets[7] = 0.0F;
		offsets[8] = 0.0F;
		offsets[9] = 0.0F;
		offsets[10] = 0.0F;
		offsets[11] = 0.0F;
		offsets[12] = 0.0F;
		offsets[13] = 0.0F;
		offsets[14] = 0.0F;
		offsets[15] = 0.0F;
		return offsets[index];
	}

	public static float getYRot(int index) {
		float[] offsets = new float[16];
		offsets[0] = 0.0F;
		offsets[1] = 0.0F;
		offsets[2] = 0.0F;
		offsets[3] = 0.0F;
		offsets[4] = 0.0F;
		offsets[5] = 0.0F;
		offsets[6] = 0.0F;
		offsets[7] = 0.0F;
		offsets[8] = 0.0F;
		offsets[9] = 0.0F;
		offsets[10] = 0.0F;
		offsets[11] = 0.0F;
		offsets[12] = 0.0F;
		offsets[13] = 0.0F;
		offsets[14] = 0.0F;
		offsets[15] = 0.0F;
		return offsets[index];
	}

	public static float getAngle(int index) {
		float[] offsets = new float[16];
		offsets[0] = 0.0F;
		offsets[1] = 0.0F;
		offsets[2] = 0.0F;
		offsets[3] = 0.0F;
		offsets[4] = 0.0F;
		offsets[5] = 0.0F;
		offsets[6] = 0.0F;
		offsets[7] = 0.0F;
		offsets[8] = 0.0F;
		offsets[9] = 0.0F;
		offsets[10] = 0.0F;
		offsets[11] = 0.0F;
		offsets[12] = 0.0F;
		offsets[13] = 0.0F;
		offsets[14] = 0.0F;
		offsets[15] = 0.0F;
		return offsets[index];
	}

	@Override
	public CookieType getContainedCookieType() {
		return getStack() != null && getStack().getItem() instanceof ICookie ? ((ICookie) getStack().getItem()).getType() : null;
	}
}