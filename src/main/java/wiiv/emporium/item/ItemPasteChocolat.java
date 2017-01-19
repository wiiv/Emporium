package wiiv.emporium.item;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import wiiv.emporium.api.ICookie;
import wiiv.emporium.block.BlockJar;
import wiiv.emporium.util.CookieType;

public class ItemPasteChocolat extends ItemBaseFood {

	public ItemPasteChocolat() {
		super(2, 0.0F, false, "paste_chocolat");
		setMaxStackSize(16);
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		IBlockState state = worldIn.getBlockState(pos);
		Block block = state.getBlock();
		if (block instanceof BlockJar) {
			return block.onBlockActivated(worldIn, pos, state, playerIn, hand, playerIn.getHeldItem(hand), facing, hitX, hitY, hitZ) ? EnumActionResult.SUCCESS : EnumActionResult.FAIL;
		}
		return EnumActionResult.PASS;
	}
}
