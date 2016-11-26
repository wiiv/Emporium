package wiiv.emporium.init;

import java.util.*;

import wiiv.emporium.api.IModelHolder;
import wiiv.emporium.block.*;
import wiiv.emporium.util.*;

public class ModBlocks {

	public static List<IModelHolder> BLOCK_LIST = new ArrayList<IModelHolder>();
	public static BlockJar JAR;

	public static BlockModernTable[] MODERN_TABLE = new BlockModernTable[16];

	public static BlockMedievalTable MEDIEVAL_TABLE;
	public static BlockMedievalBench MEDIEVAL_BENCH;
	public static BlockMedievalChairKing MEDIEVAL_CHAIR_KING;
	public static BlockMedievalChairQueen MEDIEVAL_CHAIR_QUEEN;

	public static BlockSofaHeart SOFA_HEART;

	public static BlockLampLibrary[] LIBRARY_LAMP = new BlockLampLibrary[16];

	public static BlockLampPaperFloating[] PAPER_FLOATING_LAMP = new BlockLampPaperFloating[8];

	public static BlockLampGlowrescentAxis[] GLOWRESCENT_AXIS_LAMP = new BlockLampGlowrescentAxis[16];
	public static BlockLampGlowrescentCaged[] GLOWRESCENT_CAGED_LAMP = new BlockLampGlowrescentCaged[16];
	public static BlockLampGlowrescentCube[] GLOWRESCENT_CUBE_LAMP = new BlockLampGlowrescentCube[16];
	public static BlockLampGlowrescentFixture[] GLOWRESCENT_FIXTURE_LAMP = new BlockLampGlowrescentFixture[16];

	public static BlockLampChandelier[] CHANDELIER_LAMP = new BlockLampChandelier[16];
	public static BlockLampCandelabra[] CANDELABRA_LAMP = new BlockLampCandelabra[16];

	public static BlockLampCandleBig[] CANDLE_BIG_LAMP = new BlockLampCandleBig[16];
	public static BlockLampCandleTall[] CANDLE_TALL_LAMP = new BlockLampCandleTall[16];

	public static void init() {
		BLOCK_LIST.add(JAR = new BlockJar());

		//medieval
		BLOCK_LIST.add(MEDIEVAL_TABLE = new BlockMedievalTable());
		BLOCK_LIST.add(MEDIEVAL_BENCH = new BlockMedievalBench());
		BLOCK_LIST.add(MEDIEVAL_CHAIR_KING = new BlockMedievalChairKing());
		BLOCK_LIST.add(MEDIEVAL_CHAIR_QUEEN = new BlockMedievalChairQueen());

		//modern
		for (EnumColor16 color : EnumColor16.values()) {
			MODERN_TABLE[color.getMetadata()] = new BlockModernTable(color.getMetadata());
			BLOCK_LIST.add(MODERN_TABLE[color.getMetadata()]);

			LIBRARY_LAMP[color.getMetadata()] = new BlockLampLibrary(color.getMetadata());
			BLOCK_LIST.add(LIBRARY_LAMP[color.getMetadata()]);

			CHANDELIER_LAMP[color.getMetadata()] = new BlockLampChandelier(color.getMetadata());
			BLOCK_LIST.add(CHANDELIER_LAMP[color.getMetadata()]);

			CANDELABRA_LAMP[color.getMetadata()] = new BlockLampCandelabra(color.getMetadata());
			BLOCK_LIST.add(CANDELABRA_LAMP[color.getMetadata()]);

			CANDLE_BIG_LAMP[color.getMetadata()] = new BlockLampCandleBig(color.getMetadata());
			BLOCK_LIST.add(CANDLE_BIG_LAMP[color.getMetadata()]);

			CANDLE_TALL_LAMP[color.getMetadata()] = new BlockLampCandleTall(color.getMetadata());
			BLOCK_LIST.add(CANDLE_TALL_LAMP[color.getMetadata()]);

			GLOWRESCENT_AXIS_LAMP[color.getMetadata()] = new BlockLampGlowrescentAxis(color.getMetadata());
			BLOCK_LIST.add(GLOWRESCENT_AXIS_LAMP[color.getMetadata()]);

			GLOWRESCENT_CAGED_LAMP[color.getMetadata()] = new BlockLampGlowrescentCaged(color.getMetadata());
			BLOCK_LIST.add(GLOWRESCENT_CAGED_LAMP[color.getMetadata()]);

			GLOWRESCENT_CUBE_LAMP[color.getMetadata()] = new BlockLampGlowrescentCube(color.getMetadata());
			BLOCK_LIST.add(GLOWRESCENT_CUBE_LAMP[color.getMetadata()]);

			GLOWRESCENT_FIXTURE_LAMP[color.getMetadata()] = new BlockLampGlowrescentFixture(color.getMetadata());
			BLOCK_LIST.add(GLOWRESCENT_FIXTURE_LAMP[color.getMetadata()]);
		}

		BLOCK_LIST.add(SOFA_HEART = new BlockSofaHeart());

		for (EnumColor8 color : EnumColor8.values()) {
			PAPER_FLOATING_LAMP[color.getMetadata()] = new BlockLampPaperFloating(color.getMetadata());
			BLOCK_LIST.add(PAPER_FLOATING_LAMP[color.getMetadata()]);
		}

	}

	public static void initModels() {
		for (IModelHolder block : BLOCK_LIST) {
			block.initModel();
		}
	}

}