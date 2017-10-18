package wiiv.emporium.item;

public class ItemDebug extends ItemBaseFood {

	public ItemDebug() {
		super(0, 0.0F, false, "debug");
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
}
