package wiiv.emporium.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import wiiv.emporium.block.tile.TileCabinet;

/**
 * @author p455w0rd
 *
 */
public class ContainerCabinet extends Container {

	private InventoryPlayer playerInventory;
	private TileCabinet cabinet;

	public ContainerCabinet(EntityPlayer player, TileCabinet tile) {
		cabinet = tile;
		playerInventory = player.inventory;

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				addSlotToContainer(new Slot(getCabinetInventory(), j + i * 4, 50 + j * 18, 8 + i * 18));
			}
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, (16 * 5) + i * 18 + 4));
			}
		}

		for (int i = 0; i < 9; i++) {
			addSlotToContainer(new Slot(playerInventory, i, 8 + i * 18, (16 * 5) + 62));
		}

	}

	@Override
	public void addListener(IContainerListener listener) {
		super.addListener(listener);
		listener.sendAllWindowProperties(this, getTile().getInventory());
	}

	public IInventory getCabinetInventory() {
		return getTile();
	}

	public TileCabinet getTile() {
		return cabinet;
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		if (getSlot(index) != null && getSlot(index).getHasStack()) {
			if (index < 16) { // from cabinet
				if (mergeItemStack(getSlot(index).getStack(), 16, 39, false)) {
					putStackInSlot(index, null);
				}
			}
			else {
				if (mergeItemStack(getSlot(index).getStack(), 0, 16, false)) {
					putStackInSlot(index, null);
				}
			}
		}
		return null;
	}

}
