package wiiv.emporium.init;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class EmporiumCreativeTab extends CreativeTabs {

	public EmporiumCreativeTab() {
		super("tabDecwiivrations");
	}

	@Override
	public Item getTabIconItem() {
		// TODO Auto-generated method stub
		return Item.getItemFromBlock(ModBlocks.JAR);
	}

}
