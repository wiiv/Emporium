package wiiv.emporium.item;

import wiiv.emporium.api.ICookie;
import wiiv.emporium.util.CookieType;

public class ItemCookiePlain extends ItemBaseFood implements ICookie {

	public ItemCookiePlain() {
		super(2, 0.0F, false, "cookie_plain");
		setMaxStackSize(16);
	}
	/*
	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		IBlockState state = worldIn.getBlockState(pos);
		Block block = state.getBlock();
		if (block instanceof BlockJarCookie) {
			return block.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ) ? EnumActionResult.SUCCESS : EnumActionResult.FAIL;
		}
		return EnumActionResult.PASS;
	}
	*/
	@Override
	public CookieType getType() {
		return CookieType.PLAIN;
	}

}
