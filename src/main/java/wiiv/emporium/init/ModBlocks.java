package wiiv.emporium.init;

import java.util.ArrayList;
import java.util.List;

import wiiv.emporium.api.IModelHolder;
import wiiv.emporium.block.BlockJar;
import wiiv.emporium.block.BlockLampCandelabra;
import wiiv.emporium.block.BlockLampCandleBig;
import wiiv.emporium.block.BlockLampCandleTall;
import wiiv.emporium.block.BlockLampChandelier;
import wiiv.emporium.block.BlockLampLibrary;
import wiiv.emporium.block.BlockLampPaperFloating;
import wiiv.emporium.block.BlockMedievalBench;
import wiiv.emporium.block.BlockMedievalChairKing;
import wiiv.emporium.block.BlockMedievalChairQueen;
import wiiv.emporium.block.BlockMedievalTable;
import wiiv.emporium.util.EnumColor16;
import wiiv.emporium.util.EnumColor8;

public class ModBlocks {

	public static List<IModelHolder> BLOCK_LIST = new ArrayList<IModelHolder>();
	public static BlockJar JAR;
	
	public static BlockMedievalTable MEDIEVAL_TABLE;
	public static BlockMedievalBench MEDIEVAL_BENCH;
	public static BlockMedievalChairKing MEDIEVAL_CHAIR_KING;
	public static BlockMedievalChairQueen MEDIEVAL_CHAIR_QUEEN;
	public static BlockLampLibrary[] LIBRARY_LAMP = new BlockLampLibrary[16];
	
	public static BlockLampPaperFloating[] PAPER_LAMP_FLOATING = new BlockLampPaperFloating[8];
	
	public static BlockLampChandelier[] CHANDELIER_LAMP = new BlockLampChandelier[16];
	public static BlockLampCandelabra[] CANDELABRA_LAMP = new BlockLampCandelabra[16];
	
	public static BlockLampCandleBig[] CANDLE_BIG_LAMP = new BlockLampCandleBig[16];
	public static BlockLampCandleTall[] CANDLE_TALL_LAMP = new BlockLampCandleTall[16];
	
	public static void init() {
		BLOCK_LIST.add(JAR = new BlockJar());
		
		BLOCK_LIST.add(MEDIEVAL_TABLE = new BlockMedievalTable());
		BLOCK_LIST.add(MEDIEVAL_BENCH = new BlockMedievalBench());
		BLOCK_LIST.add(MEDIEVAL_CHAIR_KING = new BlockMedievalChairKing());
		BLOCK_LIST.add(MEDIEVAL_CHAIR_QUEEN = new BlockMedievalChairQueen());
		
		
		
		for (EnumColor16 color : EnumColor16.values()) {
			LIBRARY_LAMP[color.getMetadata()] = new BlockLampLibrary(color.getMetadata());
			BLOCK_LIST.add(LIBRARY_LAMP[color.getMetadata()]);
		}

		
		for (EnumColor16 color : EnumColor16.values()) {
			CHANDELIER_LAMP[color.getMetadata()] = new BlockLampChandelier(color.getMetadata());
			BLOCK_LIST.add(CHANDELIER_LAMP[color.getMetadata()]);
		}
		
		for (EnumColor16 color : EnumColor16.values()) {
			CANDELABRA_LAMP[color.getMetadata()] = new BlockLampCandelabra(color.getMetadata());
			BLOCK_LIST.add(CANDELABRA_LAMP[color.getMetadata()]);
		}
		
		for (EnumColor16 color : EnumColor16.values()) {
			CANDLE_BIG_LAMP[color.getMetadata()] = new BlockLampCandleBig(color.getMetadata());
			BLOCK_LIST.add(CANDLE_BIG_LAMP[color.getMetadata()]);
		}
		
		for (EnumColor16 color : EnumColor16.values()) {
			CANDLE_TALL_LAMP[color.getMetadata()] = new BlockLampCandleTall(color.getMetadata());
			BLOCK_LIST.add(CANDLE_TALL_LAMP[color.getMetadata()]);
		}
		
		for (EnumColor8 color : EnumColor8.values()) {
			PAPER_LAMP_FLOATING[color.getMetadata()] = new BlockLampPaperFloating(color.getMetadata());
			BLOCK_LIST.add(PAPER_LAMP_FLOATING[color.getMetadata()]);
		}
	}

	public static void initModels() {
		for (IModelHolder block : BLOCK_LIST) {
			block.initModel();
		}
	}

}