package wiiv.emporium.item;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import wiiv.emporium.block.BlockJar;

public class ItemCookie extends ItemBaseFood {

	public ItemCookie() {
		super(2, 0.0F, false, "cookie");
		setMaxStackSize(16);

		for (int i = 0; i < 16; ++i) {

		}
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
