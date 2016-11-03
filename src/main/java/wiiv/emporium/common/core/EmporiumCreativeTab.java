package wiiv.emporium.common.core;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import wiiv.emporium.common.block.base.ModBlocks;

public class EmporiumCreativeTab extends CreativeTabs{

	public EmporiumCreativeTab() {
		super("tabDecwiivrations");
	}

	@Override
	public Item getTabIconItem() {
		// TODO Auto-generated method stub
		return Item.getItemFromBlock(ModBlocks.jar);
	}

	
}
