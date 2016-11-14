package wiiv.emporium.init;

import java.util.ArrayList;
import java.util.List;

import wiiv.emporium.api.IModelHolder;
import wiiv.emporium.block.BlockJar;
import wiiv.emporium.block.BlockLampCandelabra;
import wiiv.emporium.block.BlockLampCandleBig;
import wiiv.emporium.block.BlockLampChandelier;
import wiiv.emporium.block.BlockLampLibrary;
import wiiv.emporium.util.EnumColor;

public class ModBlocks {

	public static List<IModelHolder> BLOCK_LIST = new ArrayList<IModelHolder>();
	public static BlockJar JAR;
	
	public static BlockLampLibrary[] LIBRARY_LAMP = new BlockLampLibrary[16];
	
	public static BlockLampChandelier[] CHANDELIER_LAMP = new BlockLampChandelier[16];
	public static BlockLampCandelabra[] CANDELABRA_LAMP = new BlockLampCandelabra[16];
	
	public static BlockLampCandleBig[] CANDLE_BIG_LAMP = new BlockLampCandleBig[16];
	
	public static void init() {
		BLOCK_LIST.add(JAR = new BlockJar());
		
		for (EnumColor color : EnumColor.values()) {
			LIBRARY_LAMP[color.getMetadata()] = new BlockLampLibrary(color.getMetadata());
			BLOCK_LIST.add(LIBRARY_LAMP[color.getMetadata()]);
		}

		
		for (EnumColor color : EnumColor.values()) {
			CHANDELIER_LAMP[color.getMetadata()] = new BlockLampChandelier(color.getMetadata());
			BLOCK_LIST.add(CHANDELIER_LAMP[color.getMetadata()]);
		}
		
		for (EnumColor color : EnumColor.values()) {
			CANDELABRA_LAMP[color.getMetadata()] = new BlockLampCandelabra(color.getMetadata());
			BLOCK_LIST.add(CANDELABRA_LAMP[color.getMetadata()]);
		}
		
		for (EnumColor color : EnumColor.values()) {
			CANDLE_BIG_LAMP[color.getMetadata()] = new BlockLampCandleBig(color.getMetadata());
			BLOCK_LIST.add(CANDLE_BIG_LAMP[color.getMetadata()]);
		}
		
	}

	public static void initModels() {
		for (IModelHolder block : BLOCK_LIST) {
			block.initModel();
		}
	}

}