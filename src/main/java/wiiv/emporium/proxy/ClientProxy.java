package wiiv.emporium.proxy;

import net.minecraftforge.fml.common.event.*;
import wiiv.emporium.init.*;

public class ClientProxy extends CommonProxy {

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
		ModItems.initModels();
		ModBlocks.initModels();
	}

	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);
	}
}
