package wiiv.emporium.init;

import java.util.ArrayList;
import java.util.List;

import wiiv.emporium.api.IModelHolder;
import wiiv.emporium.block.BlockApplianceComputer;
import wiiv.emporium.block.BlockApplianceCooktop1;
import wiiv.emporium.block.BlockApplianceCooktop2;
import wiiv.emporium.block.BlockApplianceFireplace;
import wiiv.emporium.block.BlockApplianceMac;
import wiiv.emporium.block.BlockApplianceMicrowave;
import wiiv.emporium.block.BlockApplianceOven1;
import wiiv.emporium.block.BlockApplianceTelevision;
import wiiv.emporium.block.BlockBathroomBathtub;
import wiiv.emporium.block.BlockBathroomBidet;
import wiiv.emporium.block.BlockBathroomShower;
import wiiv.emporium.block.BlockBathroomSink;
import wiiv.emporium.block.BlockBathroomToilet;
import wiiv.emporium.block.BlockBenchStone;
import wiiv.emporium.block.BlockBenchStone2;
import wiiv.emporium.block.BlockCandleBig;
import wiiv.emporium.block.BlockCandleCandelabraColorable;
import wiiv.emporium.block.BlockCandleTall;
import wiiv.emporium.block.BlockChairWood1;
import wiiv.emporium.block.BlockChairWood2;
import wiiv.emporium.block.BlockChairWood3;
import wiiv.emporium.block.BlockChairWood4;
import wiiv.emporium.block.BlockChairWood5;
import wiiv.emporium.block.BlockCounterBar;
import wiiv.emporium.block.BlockGardenBirdBath;
import wiiv.emporium.block.BlockIndustrialAutoCrafter;
import wiiv.emporium.block.BlockIndustrialConveyorBelt;
import wiiv.emporium.block.BlockIndustrialStackScanner;
import wiiv.emporium.block.BlockKitchenCuttingBoard;
import wiiv.emporium.block.BlockKitchenOvenHood;
import wiiv.emporium.block.BlockKitchenSmokeDetector;
import wiiv.emporium.block.BlockLampFloating;
import wiiv.emporium.block.BlockLampGlowrescentAxis;
import wiiv.emporium.block.BlockLampGlowrescentCaged;
import wiiv.emporium.block.BlockLampGlowrescentCube;
import wiiv.emporium.block.BlockLampLibrary;
import wiiv.emporium.block.BlockMedievalBench;
import wiiv.emporium.block.BlockMedievalChair;
import wiiv.emporium.block.BlockMedievalChairKing;
import wiiv.emporium.block.BlockMedievalChairQueen;
import wiiv.emporium.block.BlockMedievalTable;
import wiiv.emporium.block.BlockSofaBlac;
import wiiv.emporium.block.BlockSofaBluve;
import wiiv.emporium.block.BlockSofaBrog;
import wiiv.emporium.block.BlockSofaCyna;
import wiiv.emporium.block.BlockSofaGreed;
import wiiv.emporium.block.BlockSofaGris;
import wiiv.emporium.block.BlockSofaHeart;
import wiiv.emporium.block.BlockSofaLiminus;
import wiiv.emporium.block.BlockSofaMagen;
import wiiv.emporium.block.BlockSofaOchre;
import wiiv.emporium.block.BlockSofaPurplus;
import wiiv.emporium.block.BlockSofaSilver;
import wiiv.emporium.block.BlockSofaWight;
import wiiv.emporium.block.BlockSofaYell;
import wiiv.emporium.block.BlockStoolBarCushion;
import wiiv.emporium.block.BlockStoolBarPlastic;
import wiiv.emporium.block.BlockStoolWood1;
import wiiv.emporium.block.BlockStoolWood2;
import wiiv.emporium.block.BlockStoolWood3;
import wiiv.emporium.block.BlockStoolWood4;
import wiiv.emporium.block.BlockStoolWood5;
import wiiv.emporium.block.BlockStorageBarrelWood;
import wiiv.emporium.block.BlockStorageCabinetWood1;
import wiiv.emporium.block.BlockTableGlass1;
import wiiv.emporium.block.BlockTableGlass2;
import wiiv.emporium.block.BlockTableGlass3;
import wiiv.emporium.block.BlockTableStone;
import wiiv.emporium.block.BlockTableWood1;
import wiiv.emporium.block.BlockTableWood2;
import wiiv.emporium.util.EnumColor16;
import wiiv.emporium.util.EnumColor8;

public class ModBlocks {

	public static List<IModelHolder> BLOCK_LIST = new ArrayList<IModelHolder>();
	
	//public static BlockJarCookie JAR;

	//medieval set
	public static BlockMedievalTable MEDIEVAL_TABLE;
	public static BlockMedievalBench MEDIEVAL_BENCH;
	public static BlockMedievalChair MEDIEVAL_CHAIR;
	public static BlockMedievalChairKing MEDIEVAL_CHAIR_KING;
	public static BlockMedievalChairQueen MEDIEVAL_CHAIR_QUEEN;
	
	//park set
	//public static BlockParkTable PARK_TABLE;
	//public static BlockParkBench PARK_BENCH;
	//public static BlockParkChair PARK_CHAIR;

	public static BlockTableStone TABLE_STONE;
	
	public static BlockTableWood1 TABLE_WOOD_BASIC;
	public static BlockTableWood2 TABLE_WOOD_COLORABLE;
	
	public static BlockTableGlass1 TABLE_GLASS_MODERN;
	public static BlockTableGlass2 TABLE_GLASS_CLASSIC;
	public static BlockTableGlass3 TABLE_GLASS_COLORABLE;
	
	public static BlockBenchStone BENCH_STONE;
	public static BlockBenchStone2 BENCH_STONE_MASON;
	
	public static BlockChairWood1 CHAIR_WOOD_BASIC;
	public static BlockChairWood2 CHAIR_WOOD_UPHOLSTERY;
	public static BlockChairWood3 CHAIR_WOOD_WICKER;
	public static BlockChairWood4 CHAIR_WOOD_BACK;
	public static BlockChairWood5 CHAIR_WOOD_SPAULDERS;
	
	//stool
	public static BlockStoolWood1 STOOL_WOOD_BASIC;
	public static BlockStoolWood2 STOOL_WOOD_UPHOLSTERY;
	public static BlockStoolWood3 STOOL_WOOD_WICKER;
	public static BlockStoolWood4 STOOL_WOOD_BACK;
	public static BlockStoolWood5 STOOL_WOOD_SLIM;
	public static BlockStoolBarCushion[] STOOL_BAR_CUSHION = new BlockStoolBarCushion[8];
	public static BlockStoolBarPlastic[] STOOL_BAR_PLASTIC = new BlockStoolBarPlastic[8];
	
	public static BlockGardenBirdBath STONE_BIRD_BATH;
	
	//public static BlockCabinet CABINET;

	public static BlockApplianceMicrowave MICROWAVE;
	public static BlockApplianceCooktop1 COOKTOP_ELECTRIC;
	public static BlockApplianceCooktop2 COOKTOP_GAS;
	public static BlockApplianceOven1 OVEN_ELECTRIC;
	public static BlockApplianceFireplace FIREPLACE;
	public static BlockApplianceTelevision TELEVISION_1990;
	public static BlockApplianceComputer COMPUTER_1980;
	public static BlockApplianceMac MAC_1980;
	
	public static BlockStorageBarrelWood BARREL;
	public static BlockStorageCabinetWood1 CABINET_WOOD_DRAWER;

	public static BlockSofaHeart SOFA_HEART;
	public static BlockSofaYell SOFA_YELL;
	public static BlockSofaWight SOFA_WIGHT;
	public static BlockSofaOchre SOFA_OCHRE;
	public static BlockSofaBlac SOFA_BLAC;
	public static BlockSofaPurplus SOFA_PURPLUS;
	public static BlockSofaGris SOFA_GRIS;
	public static BlockSofaSilver SOFA_SILVER;
	public static BlockSofaBluve SOFA_BLUVE;
	public static BlockSofaLiminus SOFA_LIMINUS;
	public static BlockSofaGreed SOFA_GREED;
	public static BlockSofaBrog SOFA_BROG;
	public static BlockSofaMagen SOFA_MAGEN;
	public static BlockSofaCyna SOFA_CYNA;
	
	public static BlockCounterBar COUNTER_BAR;

	public static BlockLampLibrary[] LIBRARY_LAMP = new BlockLampLibrary[16];

	public static BlockLampFloating[] PAPER_FLOATING_LAMP = new BlockLampFloating[8];
	
	public static BlockLampGlowrescentAxis[] GLOWRESCENT_AXIS_LAMP = new BlockLampGlowrescentAxis[16];
	public static BlockLampGlowrescentCaged[] GLOWRESCENT_CAGED_LAMP = new BlockLampGlowrescentCaged[16];
	public static BlockLampGlowrescentCube[] GLOWRESCENT_CUBE_LAMP = new BlockLampGlowrescentCube[16];
	
	public static BlockCandleBig CANDLE_BIG;
	public static BlockCandleTall CANDLE_TALL;
	
	//public static BlockCandleChandelierColorable[] CANDLE_CHANDELIER_COLORABLE = new BlockCandleChandelierColorable[16];
	public static BlockCandleCandelabraColorable[] CANDLE_CANDELABRA_COLORABLE = new BlockCandleCandelabraColorable[16];
	
	public static BlockIndustrialConveyorBelt INDUSTRIAL_CONVEYOR_BELT;
	public static BlockIndustrialAutoCrafter INDUSTRIAL_AUTO_CRAFTER;
	public static BlockIndustrialStackScanner INDUSTRIAL_STACK_SCANNER;
	
	public static BlockBathroomBathtub BATHROOM_BATHTUB;
	public static BlockBathroomShower BATHROOM_SHOWER;
	public static BlockBathroomSink BATHROOM_SINK;
	public static BlockBathroomBidet BATHROOM_BIDET;
	public static BlockBathroomToilet BATHROOM_TOILET;
	
	public static BlockKitchenCuttingBoard KITCHEN_CUTTING_BOARD;
	public static BlockKitchenOvenHood KITCHEN_OVEN_HOOD;
	public static BlockKitchenSmokeDetector KITCHEN_SMOKE_DETECTOR;
	
	public static void init() {
		//BLOCK_LIST.add(JAR = new BlockJarCookie());
		BLOCK_LIST.add(STONE_BIRD_BATH = new BlockGardenBirdBath());

		//medieval set
		BLOCK_LIST.add(MEDIEVAL_TABLE = new BlockMedievalTable());
		BLOCK_LIST.add(MEDIEVAL_BENCH = new BlockMedievalBench());
		BLOCK_LIST.add(MEDIEVAL_CHAIR = new BlockMedievalChair());
		BLOCK_LIST.add(MEDIEVAL_CHAIR_KING = new BlockMedievalChairKing());
		BLOCK_LIST.add(MEDIEVAL_CHAIR_QUEEN = new BlockMedievalChairQueen());
		
		//park set
		//BLOCK_LIST.add(PARK_TABLE = new BlockParkTable());
		//BLOCK_LIST.add(PARK_BENCH = new BlockParkBench());
		//BLOCK_LIST.add(PARK_CHAIR = new BlockParkChair());
		
		//table
		BLOCK_LIST.add(TABLE_STONE = new BlockTableStone());
		BLOCK_LIST.add(TABLE_WOOD_BASIC = new BlockTableWood1());
		BLOCK_LIST.add(TABLE_WOOD_COLORABLE = new BlockTableWood2());
		
		BLOCK_LIST.add(TABLE_GLASS_MODERN = new BlockTableGlass1());
		BLOCK_LIST.add(TABLE_GLASS_CLASSIC = new BlockTableGlass2());
		BLOCK_LIST.add(TABLE_GLASS_COLORABLE = new BlockTableGlass3());

		//bench
		BLOCK_LIST.add(BENCH_STONE = new BlockBenchStone());
		BLOCK_LIST.add(BENCH_STONE_MASON = new BlockBenchStone2());
		
		//chair
		BLOCK_LIST.add(CHAIR_WOOD_BASIC = new BlockChairWood1());
		BLOCK_LIST.add(CHAIR_WOOD_UPHOLSTERY = new BlockChairWood2());
		BLOCK_LIST.add(CHAIR_WOOD_WICKER = new BlockChairWood3());
		BLOCK_LIST.add(CHAIR_WOOD_BACK = new BlockChairWood4());
		BLOCK_LIST.add(CHAIR_WOOD_SPAULDERS = new BlockChairWood5());
		
		//stool
		BLOCK_LIST.add(STOOL_WOOD_BASIC = new BlockStoolWood1());
		BLOCK_LIST.add(STOOL_WOOD_UPHOLSTERY = new BlockStoolWood2());
		BLOCK_LIST.add(STOOL_WOOD_WICKER = new BlockStoolWood3());
		BLOCK_LIST.add(STOOL_WOOD_BACK = new BlockStoolWood4());
		BLOCK_LIST.add(STOOL_WOOD_SLIM = new BlockStoolWood5());
		
		for (EnumColor8 color : EnumColor8.values()) {
			STOOL_BAR_CUSHION[color.getMetadata()] = new BlockStoolBarCushion(color.getMetadata());
			BLOCK_LIST.add(STOOL_BAR_CUSHION[color.getMetadata()]);
		}
		for (EnumColor8 color : EnumColor8.values()) {
			STOOL_BAR_PLASTIC[color.getMetadata()] = new BlockStoolBarPlastic(color.getMetadata());
			BLOCK_LIST.add(STOOL_BAR_PLASTIC[color.getMetadata()]);
		}
		
		//lamps
		for (EnumColor16 color : EnumColor16.values()) {
			GLOWRESCENT_AXIS_LAMP[color.getMetadata()] = new BlockLampGlowrescentAxis(color.getMetadata());
			BLOCK_LIST.add(GLOWRESCENT_AXIS_LAMP[color.getMetadata()]);
		}
		for (EnumColor16 color : EnumColor16.values()) {
			GLOWRESCENT_CAGED_LAMP[color.getMetadata()] = new BlockLampGlowrescentCaged(color.getMetadata());
			BLOCK_LIST.add(GLOWRESCENT_CAGED_LAMP[color.getMetadata()]);
		}
		for (EnumColor16 color : EnumColor16.values()) {
			GLOWRESCENT_CUBE_LAMP[color.getMetadata()] = new BlockLampGlowrescentCube(color.getMetadata());
			BLOCK_LIST.add(GLOWRESCENT_CUBE_LAMP[color.getMetadata()]);
		}
		
		for (EnumColor8 color : EnumColor8.values()) {
			PAPER_FLOATING_LAMP[color.getMetadata()] = new BlockLampFloating(color.getMetadata());
			BLOCK_LIST.add(PAPER_FLOATING_LAMP[color.getMetadata()]);
		}
		for (EnumColor16 color : EnumColor16.values()) {
			LIBRARY_LAMP[color.getMetadata()] = new BlockLampLibrary(color.getMetadata());
			BLOCK_LIST.add(LIBRARY_LAMP[color.getMetadata()]);
		}
		
		//candles
		BLOCK_LIST.add(CANDLE_BIG = new BlockCandleBig());
		BLOCK_LIST.add(CANDLE_TALL = new BlockCandleTall());
		/*
		for (EnumColor16 color : EnumColor16.values()) {
			CANDLE_CHANDELIER_COLORABLE[color.getMetadata()] = new BlockCandleChandelierColorable(color.getMetadata());
			BLOCK_LIST.add(CANDLE_CHANDELIER_COLORABLE[color.getMetadata()]);
		}
		*/
		for (EnumColor16 color : EnumColor16.values()) {
			CANDLE_CANDELABRA_COLORABLE[color.getMetadata()] = new BlockCandleCandelabraColorable(color.getMetadata());
			BLOCK_LIST.add(CANDLE_CANDELABRA_COLORABLE[color.getMetadata()]);
		}
		
		//cabinet
		//BLOCK_LIST.add(CABINET = new BlockCabinet());

		BLOCK_LIST.add(MICROWAVE = new BlockApplianceMicrowave());
		BLOCK_LIST.add(COOKTOP_ELECTRIC = new BlockApplianceCooktop1());
		BLOCK_LIST.add(COOKTOP_GAS = new BlockApplianceCooktop2());
		BLOCK_LIST.add(OVEN_ELECTRIC = new BlockApplianceOven1());
		BLOCK_LIST.add(FIREPLACE = new BlockApplianceFireplace());
		BLOCK_LIST.add(TELEVISION_1990 = new BlockApplianceTelevision());
		BLOCK_LIST.add(COMPUTER_1980 = new BlockApplianceComputer());
		BLOCK_LIST.add(MAC_1980 = new BlockApplianceMac());
		
		BLOCK_LIST.add(BARREL = new BlockStorageBarrelWood());
		BLOCK_LIST.add(CABINET_WOOD_DRAWER = new BlockStorageCabinetWood1());
		
		BLOCK_LIST.add(COUNTER_BAR = new BlockCounterBar());

		BLOCK_LIST.add(SOFA_HEART = new BlockSofaHeart());
		BLOCK_LIST.add(SOFA_YELL = new BlockSofaYell());
		BLOCK_LIST.add(SOFA_WIGHT = new BlockSofaWight());
		BLOCK_LIST.add(SOFA_OCHRE = new BlockSofaOchre());
		BLOCK_LIST.add(SOFA_BLAC = new BlockSofaBlac());
		BLOCK_LIST.add(SOFA_PURPLUS = new BlockSofaPurplus());
		BLOCK_LIST.add(SOFA_GRIS = new BlockSofaGris());
		BLOCK_LIST.add(SOFA_SILVER = new BlockSofaSilver());
		BLOCK_LIST.add(SOFA_BLUVE = new BlockSofaBluve());
		BLOCK_LIST.add(SOFA_LIMINUS = new BlockSofaLiminus());
		BLOCK_LIST.add(SOFA_GREED = new BlockSofaGreed());
		BLOCK_LIST.add(SOFA_BROG = new BlockSofaBrog());
		BLOCK_LIST.add(SOFA_MAGEN = new BlockSofaMagen());
		BLOCK_LIST.add(SOFA_CYNA = new BlockSofaCyna());
		
		BLOCK_LIST.add(INDUSTRIAL_CONVEYOR_BELT = new BlockIndustrialConveyorBelt());
		BLOCK_LIST.add(INDUSTRIAL_AUTO_CRAFTER = new BlockIndustrialAutoCrafter());
		BLOCK_LIST.add(INDUSTRIAL_STACK_SCANNER = new BlockIndustrialStackScanner());
		
		BLOCK_LIST.add(BATHROOM_BATHTUB = new BlockBathroomBathtub());
		BLOCK_LIST.add(BATHROOM_SHOWER = new BlockBathroomShower());
		BLOCK_LIST.add(BATHROOM_SINK = new BlockBathroomSink());
		BLOCK_LIST.add(BATHROOM_BIDET = new BlockBathroomBidet());
		BLOCK_LIST.add(BATHROOM_TOILET = new BlockBathroomToilet());
		
		BLOCK_LIST.add(KITCHEN_CUTTING_BOARD = new BlockKitchenCuttingBoard());
		BLOCK_LIST.add(KITCHEN_OVEN_HOOD = new BlockKitchenOvenHood());
		BLOCK_LIST.add(KITCHEN_SMOKE_DETECTOR = new BlockKitchenSmokeDetector());
	}

	public static void initModels() {
		for (IModelHolder block : BLOCK_LIST) {
			block.initModel();
		}
	}

}