package wiiv.emporium.common.item;

import net.minecraft.item.ItemFood;
import wiiv.emporium.common.Emporium;
import wiiv.emporium.common.lib.LibMisc;

public class ItemCookie extends ItemFood{
	
	public ItemCookie() {
		 super(2, 0.0F, false);
		 
		setUnlocalizedName(LibMisc.DecItems.COOKIE.getUnlocalizedName());
		setRegistryName(LibMisc.DecItems.COOKIE.getRegistryName());
		
		setCreativeTab(Emporium.CREATIVE_TAB);
	}
}
