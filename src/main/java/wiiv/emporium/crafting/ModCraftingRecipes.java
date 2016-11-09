package wiiv.emporium.crafting;

import net.minecraft.init.*;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import wiiv.emporium.init.ModBlocks;

public class ModCraftingRecipes {

	public static void register() {

		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.JAR), " I ", "G G", "GIG", 'I', Items.IRON_INGOT, 'G', new ItemStack(Blocks.STAINED_GLASS_PANE, 1, 0));
	}
}
