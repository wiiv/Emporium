package wiiv.emporium.init;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class EmporiumCreativeTab extends CreativeTabs {

	public EmporiumCreativeTab() {
		super("tabDecwiivrations");
	}

	@Override
	public ItemStack getTabIconItem() {
		// TODO Auto-generated method stub
		return new ItemStack(ModBlocks.SOFA_HEART);
	}

}
