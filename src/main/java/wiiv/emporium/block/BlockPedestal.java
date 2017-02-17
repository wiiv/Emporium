package wiiv.emporium.block;

import java.util.List;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import wiiv.emporium.Globals;
import wiiv.emporium.block.tile.TileCookieJar;
import wiiv.emporium.client.render.tile.RenderTileJar;
import wiiv.emporium.item.ItemBaseFood;
import wiiv.emporium.item.ItemCookieChocolat;

public class BlockPedestal extends BlockBase implements ITileEntityProvider {

	private static final AxisAlignedBB COLLISION_BOX = new AxisAlignedBB((0.0625D * 4), 0.0D, (0.0625D * 4), (0.0625D * 12), (0.0625D * 12), (0.0625D * 12));

	public BlockPedestal() {
		super(Material.GLASS, "jar", 1.0F);
		setSoundType(SoundType.GLASS);
		GameRegistry.registerTileEntity(TileCookieJar.class, Globals.MOD_ID + ":TileJar");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void initModel() {
		super.initModel();
		ClientRegistry.bindTileEntitySpecialRenderer(TileCookieJar.class, new RenderTileJar());
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public BlockRenderLayer getBlockLayer() {

		return BlockRenderLayer.TRANSLUCENT;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {

		return new AxisAlignedBB(0.253D, 0.0D, 0.253D, 0.7475D, 0.62D, 0.7475D);
	}

	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, Entity entityIn) {
		super.addCollisionBoxToList(pos, entityBox, collidingBoxes, COLLISION_BOX);
	}

	private TileCookieJar getTE(World world, BlockPos pos) {
		return (TileCookieJar) world.getTileEntity(pos);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			TileCookieJar jar = getTE(world, pos);
			if (!player.isSneaking()) {
				handleStack(jar, player, hand, heldItem, world, pos);
			}
			else {
				handleSingle(jar, player, hand, heldItem, world, pos);
			}
		}
		return true;
	}

	private void handleSingle(TileCookieJar jar, EntityPlayer player, EnumHand hand, ItemStack heldItem, World world, BlockPos pos) {
		if (player.getHeldItem(hand) != null && player.getHeldItem(hand).getItem() instanceof ItemBaseFood) {
			int returnSize = player.getHeldItem(hand).stackSize - 1;
			ItemStack ret = returnSize == 0 ? null : new ItemStack(player.getHeldItem(hand).getItem(), returnSize);
			jar.addCookies(new ItemStack(player.getHeldItem(hand).getItem(), 1));
			player.inventory.setInventorySlotContents(player.inventory.currentItem, ret);
		}
		else {
			if (jar.getStack() != null) { //dey be cookiez in da cookie jar :)
				ItemStack stack = jar.takeCookies(1); //we're not Cookie Monster, so we only takes one
				if (!player.inventory.addItemStackToInventory(stack)) { ///ermergerd, we tried to add to inv, but inv ist full!
					EntityItem entityItem = new EntityItem(world, pos.getX(), pos.getY() + 1, pos.getZ(), stack);
					world.spawnEntityInWorld(entityItem);
				}
				else {
					player.openContainer.detectAndSendChanges();
				}
			}
		}
	}

	private void handleStack(TileCookieJar jar, EntityPlayer player, EnumHand hand, ItemStack heldItem, World world, BlockPos pos) {
		if (jar.getStack() == null) {
			if (player.getHeldItem(hand) != null) {
				if (heldItem.getItem() instanceof ItemBaseFood) {
					player.inventory.setInventorySlotContents(player.inventory.currentItem, jar.addCookies(player.getHeldItem(hand)));
					player.openContainer.detectAndSendChanges();
				}
			}
		}
		else { //jar has cookies
			ItemStack stack = jar.getStack();
			if (stack.stackSize < stack.getMaxStackSize()) { //jar isn't full
				if (player.getHeldItem(hand) != null && player.getHeldItem(hand).getItem() instanceof ItemBaseFood) { //player is holding at least 1 cookie
					int jarStackSize = stack.stackSize;
					int heldStackSize = player.getHeldItem(hand).stackSize;
					if (jarStackSize + heldStackSize <= stack.getMaxStackSize()) {
						ItemStack ret = jar.addCookies(new ItemStack(player.getHeldItem(hand).getItem(), heldStackSize));
						player.inventory.setInventorySlotContents(player.inventory.currentItem, ret);
					}
				}
				else { //player isn't holding a cookie
					if (player.getHeldItem(hand) == null) { //player isn't holding anything
						player.inventory.setInventorySlotContents(player.inventory.currentItem, jar.getStack());
						jar.addCookies(null);
					}
					else { //player is holding something..but not a cookie - we don't care at this point, so do nothing
					}
				}
				player.openContainer.detectAndSendChanges();
			}
			else { //jar is full
				jar.addCookies(null); //pull this entire stack from the jar
				if (!player.inventory.addItemStackToInventory(stack)) { ///ermergerd, we tried to add to inv, but inv ist full!
					EntityItem entityItem = new EntityItem(world, pos.getX(), pos.getY() + 1, pos.getZ(), stack);
					world.spawnEntityInWorld(entityItem);
				}
				else {
					player.openContainer.detectAndSendChanges();
				}
			}
		}
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileCookieJar();
	}
}
