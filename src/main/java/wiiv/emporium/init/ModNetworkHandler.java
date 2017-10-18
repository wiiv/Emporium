package wiiv.emporium.init;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import wiiv.emporium.Globals;
import wiiv.emporium.network.PacketOpenDoor;
import wiiv.emporium.network.PacketRemoveItemFromCabinetFace;

/**
 * @author p455w0rd
 *
 */
public class ModNetworkHandler {

	private static int packetId = 0;
	public static SimpleNetworkWrapper INSTANCE = null;

	private static int nextID() {
		return packetId++;
	}

	public static void init() {
		INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Globals.MOD_ID);
		INSTANCE.registerMessage(PacketOpenDoor.Handler.class, PacketOpenDoor.class, nextID(), Side.SERVER);
		INSTANCE.registerMessage(PacketRemoveItemFromCabinetFace.Handler.class, PacketRemoveItemFromCabinetFace.class, nextID(), Side.SERVER);
	}

}
