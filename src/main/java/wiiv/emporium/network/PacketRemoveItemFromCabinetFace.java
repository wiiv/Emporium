package wiiv.emporium.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import wiiv.emporium.block.tile.TileCabinet;

/**
 * @author p455w0rd
 *
 */
public class PacketRemoveItemFromCabinetFace implements IMessage {

	long pos;
	int slotId, dimension;

	public PacketRemoveItemFromCabinetFace() {
	}

	public PacketRemoveItemFromCabinetFace(BlockPos posIn, int slot, int dim) {
		pos = posIn.toLong();
		slotId = slot;
		dimension = dim;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		pos = buf.readLong();
		slotId = buf.readInt();
		dimension = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeLong(pos);
		buf.writeInt(slotId);
		buf.writeInt(dimension);
	}

	public static class Handler implements IMessageHandler<PacketRemoveItemFromCabinetFace, IMessage> {
		@Override
		public IMessage onMessage(PacketRemoveItemFromCabinetFace message, MessageContext ctx) {
			FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> {
				handle(message, ctx);
			});
			return null;
		}

		private void handle(PacketRemoveItemFromCabinetFace message, MessageContext ctx) {
			World world = DimensionManager.getWorld(message.dimension);
			if (world != null && world.getTileEntity(BlockPos.fromLong(message.pos)) != null && world.getTileEntity(BlockPos.fromLong(message.pos)) instanceof TileCabinet) {
				TileCabinet tile = (TileCabinet) world.getTileEntity(BlockPos.fromLong(message.pos));
				//ItemStack stack = ItemStackHelper.getAndRemove(tile.getInventoryArray(), message.slotId);
				ItemStack stack = tile.getInventory().getStackInSlot(message.slotId);
				EntityPlayerMP player = ctx.getServerHandler().playerEntity;
				if (player.inventory.addItemStackToInventory(stack)) {
					world.spawnEntityInWorld(new EntityItem(world, player.posX, player.posY, player.posZ, stack));
				}
				player.sendContainerToPlayer(player.inventoryContainer);
				tile.getInventory().setInventorySlotContents(message.slotId, null);
				//tile.markDirty();
				world.notifyBlockUpdate(BlockPos.fromLong(message.pos), world.getBlockState(BlockPos.fromLong(message.pos)), world.getBlockState(BlockPos.fromLong(message.pos)), 3);
				//player.connection.sendPacket(tile.getUpdatePacket());
			}
		}
	}

}
