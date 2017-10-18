package wiiv.emporium.network;

import io.netty.buffer.ByteBuf;
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
public class PacketOpenDoor implements IMessage {

	long pos;
	int dimension;
	boolean doorOpen;

	public PacketOpenDoor() {
	}

	public PacketOpenDoor(BlockPos posIn, boolean open, int dim) {
		pos = posIn.toLong();
		doorOpen = open;
		dimension = dim;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		pos = buf.readLong();
		doorOpen = buf.readBoolean();
		dimension = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeLong(pos);
		buf.writeBoolean(doorOpen);
		buf.writeInt(dimension);
	}

	public static class Handler implements IMessageHandler<PacketOpenDoor, IMessage> {
		@Override
		public IMessage onMessage(PacketOpenDoor message, MessageContext ctx) {
			FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> {
				handle(message, ctx);
			});
			return null;
		}

		private void handle(PacketOpenDoor message, MessageContext ctx) {
			World world = DimensionManager.getWorld(message.dimension);
			if (world != null) {
				TileCabinet tile = (TileCabinet) world.getTileEntity(BlockPos.fromLong(message.pos));
				if (tile != null) {
					tile.setDoorsOpen(message.doorOpen);
				}
			}
		}
	}
}
