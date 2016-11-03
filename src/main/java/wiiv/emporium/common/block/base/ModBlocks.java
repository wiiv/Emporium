package wiiv.emporium.common.block.base;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import wiiv.emporium.common.block.BlockJar;
import wiiv.emporium.common.block.BlockLampChandelier;
import wiiv.emporium.common.block.BlockLampLibrary;
import wiiv.emporium.common.block.tile.TileJar;
import wiiv.emporium.common.lib.LibMisc;

public class ModBlocks {
	
	public static Block jar;
	public static Block lamp_library;
	public static Block lamp_chandelier;
	
	public static void init(){
		jar = new BlockJar();
		lamp_library = new BlockLampLibrary();
		lamp_chandelier = new BlockLampChandelier();
	}
	
	public static void register(){
		registerBlock(jar);
		registerBlock(lamp_library);
		registerBlock(lamp_chandelier);
	}
	
	public static void registerBlock(Block block){
		
		GameRegistry.register(block);
		ItemBlock item = new ItemBlock(block);
		item.setRegistryName(block.getRegistryName());
		GameRegistry.register(item);
	}
	
	public static void registerRenders(){
		registerRender(jar);
		registerRender(lamp_library);
		registerRender(lamp_chandelier);
	}
	
	
	public static void registerRender(Block block){
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
	}
	
	public static void registerTiles() {
		
		GameRegistry.registerTileEntity(TileJar.class, LibMisc.MOD_ID + "TileJar");
	}
}