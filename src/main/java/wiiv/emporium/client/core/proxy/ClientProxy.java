package wiiv.emporium.client.core.proxy;

import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import wiiv.emporium.client.render.tile.RenderTileJar;
import wiiv.emporium.common.block.base.ModBlocks;
import wiiv.emporium.common.block.tile.TileJar;
import wiiv.emporium.common.core.proxy.CommonProxy;
import wiiv.emporium.common.item.base.ModItems;

public class ClientProxy extends CommonProxy{
	
	public void init(FMLInitializationEvent event) {
		
		ModItems.registerRenders();
		ModBlocks.registerRenders();
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileJar.class, new RenderTileJar());
	}	
}
