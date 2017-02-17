package wiiv.emporium.init;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import wiiv.emporium.Emporium;
import wiiv.emporium.Globals;
import wiiv.emporium.block.tile.TileCabinet;
import wiiv.emporium.client.gui.GuiCabinet;
import wiiv.emporium.container.ContainerCabinet;

/**
 * @author p455w0rd
 *
 */
public class ModGuiHandler implements IGuiHandler {

	public static void init() {
		NetworkRegistry.INSTANCE.registerGuiHandler(Globals.MOD_ID, new ModGuiHandler());
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (world != null) {
			if (ID == 0) {
				if (world.getTileEntity(new BlockPos(x, y, z)) != null) {
					return new ContainerCabinet(player, (TileCabinet) world.getTileEntity(new BlockPos(x, y, z)));
				}
			}
			/*
			switch (GUI.values()[ID]) {
			case CABINET:
				if (world.getTileEntity(new BlockPos(x, y, z)) != null) {
					return new ContainerCabinet(player, (TileCabinet) world.getTileEntity(new BlockPos(x, y, z)));
				}
			}
			*/
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (world != null) {
			if (ID == 0) {
				if (world.getTileEntity(new BlockPos(x, y, z)) != null) {
					return new GuiCabinet(player, (TileCabinet) world.getTileEntity(new BlockPos(x, y, z)));
				}
			}
			/*
			switch (GUI.values()[ID]) {
			case CABINET:
				if (world.getTileEntity(new BlockPos(x, y, z)) != null) {
					return new GuiCabinet(player, (TileCabinet) world.getTileEntity(new BlockPos(x, y, z)));
				}
			}
			*/
		}
		return null;
	}

	public static void launchGui(int gui, EntityPlayer playerIn, World worldIn, BlockPos pos) {
		playerIn.openGui(Emporium.instance, gui, worldIn, pos.getX(), pos.getY(), pos.getZ());
	}

	public static void launchGui(GUI type, EntityPlayer playerIn, World worldIn, BlockPos pos) {
		playerIn.openGui(Emporium.instance, type.ordinal(), worldIn, pos.getX(), pos.getY(), pos.getZ());
	}

	public static void launchGui(int gui, EntityPlayer playerIn, TileEntity tile) {
		if (tile != null && tile.getWorld() != null && tile.getPos() != null) {
			World world = tile.getWorld();
			BlockPos pos = tile.getPos();
			playerIn.openGui(Emporium.instance, gui, world, pos.getX(), pos.getY(), pos.getZ());
		}
	}

	public static void launchGui(GUI type, EntityPlayer playerIn, TileEntity tile) {
		if (tile != null && tile.getWorld() != null && tile.getPos() != null) {
			World world = tile.getWorld();
			BlockPos pos = tile.getPos();
			playerIn.openGui(Emporium.instance, type.ordinal(), world, pos.getX(), pos.getY(), pos.getZ());
		}
	}

	public enum GUI {
			CABINET
	}

}
