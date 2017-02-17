package wiiv.emporium.init;

import java.util.ArrayList;
import java.util.List;

import wiiv.emporium.api.IModelHolder;
import wiiv.emporium.block.BlockCabinet;
import wiiv.emporium.block.BlockCookieJar;
import wiiv.emporium.block.BlockLampCandelabra;
import wiiv.emporium.block.BlockLampCandleBig;
import wiiv.emporium.block.BlockLampCandleTall;
import wiiv.emporium.block.BlockLampChandelier;
import wiiv.emporium.block.BlockLampGardenGolden;
import wiiv.emporium.block.BlockLampGlowrescentAxis;
import wiiv.emporium.block.BlockLampGlowrescentCaged;
import wiiv.emporium.block.BlockLampGlowrescentCube;
import wiiv.emporium.block.BlockLampGlowrescentFixture;
import wiiv.emporium.block.BlockLampLibrary;
import wiiv.emporium.block.BlockLampPaperFloating;
import wiiv.emporium.block.BlockMedievalBench;
import wiiv.emporium.block.BlockMedievalChair;
import wiiv.emporium.block.BlockMedievalChairKing;
import wiiv.emporium.block.BlockMedievalChairQueen;
import wiiv.emporium.block.BlockMedievalTable;
import wiiv.emporium.block.BlockModernTable;
import wiiv.emporium.block.BlockModernWoodenTable;
import wiiv.emporium.block.BlockSofaBlac;
import wiiv.emporium.block.BlockSofaBluve;
import wiiv.emporium.block.BlockSofaGris;
import wiiv.emporium.block.BlockSofaHeart;
import wiiv.emporium.block.BlockSofaOchre;
import wiiv.emporium.block.BlockSofaPurplus;
import wiiv.emporium.block.BlockSofaSilver;
import wiiv.emporium.block.BlockSofaWight;
import wiiv.emporium.block.BlockSofaYell;
import wiiv.emporium.block.BlockStoneBench;
import wiiv.emporium.block.BlockStoneBenchMason;
import wiiv.emporium.block.BlockStoneBirdBath;
import wiiv.emporium.block.BlockStoneTable;
import wiiv.emporium.util.EnumColor16;
import wiiv.emporium.util.EnumColor8;

public class ModBlocks {

	public static List<IModelHolder> BLOCK_LIST = new ArrayList<IModelHolder>();
	public static BlockCookieJar JAR;

	public static BlockModernTable[] MODERN_TABLE = new BlockModernTable[16];

	public static BlockMedievalTable MEDIEVAL_TABLE;
	public static BlockMedievalBench MEDIEVAL_BENCH;
	public static BlockMedievalChair MEDIEVAL_CHAIR;
	public static BlockMedievalChairKing MEDIEVAL_CHAIR_KING;
	public static BlockMedievalChairQueen MEDIEVAL_CHAIR_QUEEN;

	public static BlockStoneTable STONE_TABLE;
	public static BlockStoneBench STONE_BENCH;
	public static BlockStoneBenchMason STONE_BENCH_MASON;
	public static BlockStoneBirdBath STONE_BIRD_BATH;

	public static BlockModernWoodenTable MODERN_WOODEN_TABLE;
	public static BlockCabinet CABINET;

	public static BlockSofaHeart SOFA_HEART;
	public static BlockSofaYell SOFA_YELL;
	public static BlockSofaWight SOFA_WIGHT;
	public static BlockSofaOchre SOFA_OCHRE;
	public static BlockSofaBlac SOFA_BLAC;
	public static BlockSofaPurplus SOFA_PURPLUS;
	public static BlockSofaGris SOFA_GRIS;
	public static BlockSofaSilver SOFA_SILVER;
	public static BlockSofaBluve SOFA_BLUVE;

	public static BlockLampLibrary[] LIBRARY_LAMP = new BlockLampLibrary[16];

	public static BlockLampPaperFloating[] PAPER_FLOATING_LAMP = new BlockLampPaperFloating[8];

	public static BlockLampGlowrescentAxis[] GLOWRESCENT_AXIS_LAMP = new BlockLampGlowrescentAxis[16];
	public static BlockLampGlowrescentCaged[] GLOWRESCENT_CAGED_LAMP = new BlockLampGlowrescentCaged[16];
	public static BlockLampGlowrescentCube[] GLOWRESCENT_CUBE_LAMP = new BlockLampGlowrescentCube[16];
	public static BlockLampGlowrescentFixture[] GLOWRESCENT_FIXTURE_LAMP = new BlockLampGlowrescentFixture[16];

	public static BlockLampGardenGolden[] GARDEN_GOLDEN_LAMP = new BlockLampGardenGolden[16];

	public static BlockLampChandelier[] CHANDELIER_LAMP = new BlockLampChandelier[16];
	public static BlockLampCandelabra[] CANDELABRA_LAMP = new BlockLampCandelabra[16];

	public static BlockLampCandleBig[] CANDLE_BIG_LAMP = new BlockLampCandleBig[16];
	public static BlockLampCandleTall[] CANDLE_TALL_LAMP = new BlockLampCandleTall[16];

	public static void init() {
		BLOCK_LIST.add(JAR = new BlockCookieJar());

		//medieval
		BLOCK_LIST.add(MEDIEVAL_TABLE = new BlockMedievalTable());
		BLOCK_LIST.add(MEDIEVAL_BENCH = new BlockMedievalBench());
		BLOCK_LIST.add(MEDIEVAL_CHAIR = new BlockMedievalChair());
		BLOCK_LIST.add(MEDIEVAL_CHAIR_KING = new BlockMedievalChairKing());
		BLOCK_LIST.add(MEDIEVAL_CHAIR_QUEEN = new BlockMedievalChairQueen());

		//stone
		BLOCK_LIST.add(STONE_TABLE = new BlockStoneTable());
		BLOCK_LIST.add(STONE_BENCH = new BlockStoneBench());
		BLOCK_LIST.add(STONE_BENCH_MASON = new BlockStoneBenchMason());
		BLOCK_LIST.add(STONE_BIRD_BATH = new BlockStoneBirdBath());

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

			GARDEN_GOLDEN_LAMP[color.getMetadata()] = new BlockLampGardenGolden(color.getMetadata());
			BLOCK_LIST.add(GARDEN_GOLDEN_LAMP[color.getMetadata()]);
		}

		BLOCK_LIST.add(MODERN_WOODEN_TABLE = new BlockModernWoodenTable());

		BLOCK_LIST.add(CABINET = new BlockCabinet());

		BLOCK_LIST.add(SOFA_HEART = new BlockSofaHeart());
		BLOCK_LIST.add(SOFA_YELL = new BlockSofaYell());
		BLOCK_LIST.add(SOFA_WIGHT = new BlockSofaWight());
		BLOCK_LIST.add(SOFA_OCHRE = new BlockSofaOchre());
		BLOCK_LIST.add(SOFA_BLAC = new BlockSofaBlac());
		BLOCK_LIST.add(SOFA_PURPLUS = new BlockSofaPurplus());
		BLOCK_LIST.add(SOFA_GRIS = new BlockSofaGris());
		BLOCK_LIST.add(SOFA_SILVER = new BlockSofaSilver());
		BLOCK_LIST.add(SOFA_BLUVE = new BlockSofaBluve());

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