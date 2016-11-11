package wiiv.emporium.init;

import java.util.*;

import wiiv.emporium.api.IModelHolder;
import wiiv.emporium.block.*;
import wiiv.emporium.util.EnumColor;

public class ModBlocks {

	public static List<IModelHolder> BLOCK_LIST = new ArrayList<IModelHolder>();
	public static BlockJar JAR;
	public static BlockLampLibrary[] LIBRARY_LAMP = new BlockLampLibrary[16];
	public static BlockLampChandelier LAMP_CHANDELIER;

	public static void init() {
		BLOCK_LIST.add(JAR = new BlockJar());
		BLOCK_LIST.add(LAMP_CHANDELIER = new BlockLampChandelier());
		for (EnumColor color : EnumColor.values()) {
			LIBRARY_LAMP[color.getMetadata()] = new BlockLampLibrary(color.getMetadata());
			BLOCK_LIST.add(LIBRARY_LAMP[color.getMetadata()]);
		}

	}

	public static void initModels() {
		for (IModelHolder block : BLOCK_LIST) {
			block.initModel();
		}
	}

}