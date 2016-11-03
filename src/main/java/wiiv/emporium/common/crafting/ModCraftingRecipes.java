package wiiv.emporium.common.crafting;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import wiiv.emporium.common.block.base.ModBlocks;

public class ModCraftingRecipes {
	
	public static void register() {
		
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.jar), " I ", "G G", "GIG", 'I', Items.IRON_INGOT, 'G', new ItemStack(Blocks.STAINED_GLASS_PANE, 1, 0));
	}
}
