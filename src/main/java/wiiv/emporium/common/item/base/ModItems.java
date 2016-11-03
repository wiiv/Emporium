package wiiv.emporium.common.item.base;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import wiiv.emporium.common.item.ItemCookie;
import wiiv.emporium.common.item.ItemDebug;

public class ModItems {
	
	public static Item bottle;
	public static Item cookie;
	public static Item debug;
	
	public static void init(){
		cookie = new ItemCookie();
		debug = new ItemDebug();
	}
	
	public static void register(){
		GameRegistry.register(cookie);
		GameRegistry.register(debug);
		
	}
	
	public static void registerRenders(){
		registerRender(cookie);
		registerRender(debug);
		
	}
	
	public static void registerRender(Item item){
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
}
