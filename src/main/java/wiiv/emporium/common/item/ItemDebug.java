package wiiv.emporium.common.item;

import net.minecraft.item.ItemFood;
import wiiv.emporium.common.Emporium;
import wiiv.emporium.common.lib.LibMisc;

public class ItemDebug extends ItemFood{
	
	public ItemDebug() {
		super(0, 0.0F, false);
		
		setUnlocalizedName(LibMisc.DecItems.DEBUG.getUnlocalizedName());
		setRegistryName(LibMisc.DecItems.DEBUG.getRegistryName());
		
		setCreativeTab(Emporium.CREATIVE_TAB);
	}
}
