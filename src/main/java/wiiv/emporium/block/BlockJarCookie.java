package wiiv.emporium.block;

import java.util.List;

import javax.annotation.Nullable;

import codechicken.lib.model.ModelRegistryHelper;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
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
import wiiv.emporium.api.ICookie;
import wiiv.emporium.block.tile.TileJarCookie;
import wiiv.emporium.client.render.tile.RenderTileJar;
import wiiv.emporium.item.ItemBaseFood;

public class BlockJarCookie extends BlockBase {

	private static final AxisAlignedBB COLLISION_BOX = new AxisAlignedBB((0.0625D * 4), 0.0D, (0.0625D * 4), (0.0625D * 12), (0.0625D * 12), (0.0625D * 12));

	public BlockJarCookie() {
		super(Material.GLASS, "jar_metal", 1.0F);
		setSoundType(SoundType.GLASS);
		GameRegistry.registerTileEntity(TileJarCookie.class, Globals.MOD_ID + ":TileJar");
		Item.getItemFromBlock(this).setMaxStackSize(1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void initModel() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileJarCookie.class, new RenderTileJar());
		ModelRegistryHelper.registerItemRenderer(Item.getItemFromBlock(this), new RenderTileJar());
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
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
    public boolean canRenderInLayer(IBlockState state, BlockRenderLayer layer) {
        return layer == BlockRenderLayer.SOLID || layer == BlockRenderLayer.TRANSLUCENT;
    }

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 0.625D, 0.75D);
	}

	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean p_185477_7_) {
		super.addCollisionBoxToList(pos, entityBox, collidingBoxes, COLLISION_BOX);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack jarItem, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		if (jarItem.hasTagCompound() && jarItem.getTagCompound().hasKey("BlockEntityTag")) {
			NBTTagCompound tag = jarItem.getTagCompound().getCompoundTag("BlockEntityTag").getCompoundTag("item");
			NBTTagList list = tag.getTagList("Items", 10);
			ItemStack stack = ItemStack.loadItemStackFromNBT(list.getCompoundTagAt(0));
			if (stack != null) {
				if (stack.getItem() instanceof ICookie) {
					String cookieType = ((ICookie) stack.getItem()).getType().name();
					String cookieName = cookieType.substring(0, 1).toUpperCase() + cookieType.substring(1).toLowerCase();
					tooltip.add("Contains " + stack.stackSize + " " + cookieName + " Cookie" + (stack.stackSize != 1 ? "s" : ""));
				}
			}
		}
	}

	private TileJarCookie getTE(World world, BlockPos pos) {
		return (TileJarCookie) world.getTileEntity(pos);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			TileJarCookie jar = getTE(world, pos);
			if (heldItem != null && jar.getContainedCookieType() != null && (jar.getContainedCookieType() != ((ICookie) heldItem.getItem()).getType())) {
				return false;
			}
			if (!player.isSneaking()) {
				handleStack(jar, player, hand, heldItem, world, pos);
			}
			else {
				handleSingle(jar, player, hand, heldItem, world, pos);
			}
		}
		return true;
	}

	private void handleSingle(TileJarCookie jar, EntityPlayer player, EnumHand hand, ItemStack heldItem, World world, BlockPos pos) {
		if (player.getHeldItem(hand) != null && player.getHeldItem(hand).getItem() instanceof ItemBaseFood) {
			if (jar.getStack() != null && jar.getStack().getItem() != player.getHeldItem(hand).getItem()) {
				return;
			}
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
					world.spawnEntity(entityItem);
				}
				else {
					player.openContainer.detectAndSendChanges();
				}
			}
		}
	}

	private void handleStack(TileJarCookie jar, EntityPlayer player, EnumHand hand, ItemStack heldItem, World world, BlockPos pos) {
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
					if (stack.getItem() != player.getHeldItem(hand).getItem()) {
						return;
					}
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
					world.spawnEntity(entityItem);
				}
				else {
					player.openContainer.detectAndSendChanges();
				}
			}
		}
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileJarCookie();
	}
}