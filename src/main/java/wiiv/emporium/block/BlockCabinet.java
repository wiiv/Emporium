package wiiv.emporium.block;

import codechicken.lib.model.ModelRegistryHelper;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import wiiv.emporium.Globals;
import wiiv.emporium.block.tile.TileCabinet;
import wiiv.emporium.client.render.tile.RenderTileCabinet;
import wiiv.emporium.init.ModGuiHandler;
import wiiv.emporium.init.ModNetworkHandler;
import wiiv.emporium.network.PacketCabinetDoors;

public class BlockCabinet extends BlockBase {

	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

	public BlockCabinet() {
		super(Material.WOOD, "cabinet", 1.0F);
		setSoundType(SoundType.WOOD);
		GameRegistry.registerTileEntity(TileCabinet.class, Globals.MOD_ID + ":TileCabinet");
	}

	private AxisAlignedBB[] getBoundingBox() {
		return new AxisAlignedBB[] {
				new AxisAlignedBB(0.0D, 0.0D, 0.125D, 1.0D, 1.0D, 1.0D),
				new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.875D, 1.0D, 1.0D),
				new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.875D),
				new AxisAlignedBB(0.125D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D)
		};
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return getBoundingBox()[getMetaFromState(state)];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void initModel() {
		ModelRegistryHelper.registerItemRenderer(Item.getItemFromBlock(this), new RenderTileCabinet());
		ModelRegistryHelper.setParticleTexture(this, RenderTileCabinet.CABINET_SPRITE);
		ClientRegistry.bindTileEntitySpecialRenderer(TileCabinet.class, new RenderTileCabinet());
	}

	@Override
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facingIn, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		EnumFacing facing = (placer == null) ? EnumFacing.NORTH : EnumFacing.fromAngle(placer.rotationYaw);
		return getDefaultState().withProperty(FACING, facing);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(FACING).getHorizontalIndex();
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		EnumFacing facing = EnumFacing.getHorizontal(meta);
		return getDefaultState().withProperty(FACING, facing);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {
				FACING
		});
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
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
	}

	public TileCabinet getTE(World world, BlockPos pos) {
		return (TileCabinet) world.getTileEntity(pos);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			if (getTE(world, pos) != null) {
				if (!player.isSneaking()) {
					if (hand == EnumHand.MAIN_HAND) {
						if (player.getHeldItem(hand) != null) {
							mergeHeldItem(player, EnumHand.MAIN_HAND, world, pos);
						}
						else {
							ModGuiHandler.launchGui(0, player, getTE(world, pos));
						}
					}
				}
			}
		}
		else {
			if (getTE(world, pos) != null) {
				if (player.isSneaking()) {
					if (hand == EnumHand.MAIN_HAND) {
						if (player.getHeldItem(hand) == null) {
							if (!GuiScreen.isCtrlKeyDown()) {
								boolean doorsOpen = getTE(world, pos).doorsOpen();
								getTE(world, pos).setDoorsOpen(!doorsOpen);
								ModNetworkHandler.INSTANCE.sendToServer(new PacketCabinetDoors(pos, !doorsOpen, world.provider.getDimension()));
							}
						}

					}
				}

			}
		}
		return true;
	}

	@Override
	public void onBlockClicked(World world, BlockPos pos, EntityPlayer player) {
		RayTraceResult raytrace = net.minecraftforge.common.ForgeHooks.rayTraceEyes(player, 6);
		if (raytrace == null || !player.isSneaking()) {
			return;
		}
		EnumFacing side = raytrace.sideHit;
		float hitX = (float) (raytrace.hitVec.xCoord - pos.getX());
		float hitY = (float) (raytrace.hitVec.yCoord - pos.getY());
		float hitZ = (float) (raytrace.hitVec.zCoord - pos.getZ());
		if (side != EnumFacing.UP && side != EnumFacing.DOWN) {
			String x = String.format("%.2f", hitX).split("\\.")[1];
			String y = String.format("%.2f", hitY).split("\\.")[1];
			String z = String.format("%.2f", hitZ).split("\\.")[1];
			int slot = getSlotForFace(side, Integer.parseInt(x), Integer.parseInt(y), Integer.parseInt(z));
			if (slot > 0) {
				int slotIndex = slot - 1;
				TileCabinet tile = getTE(world, pos);
				if (slotIndex >= tile.getInventory().getSizeInventory()) {
					return;
				}
				ItemStack slotStack = tile.getInventory().getStackInSlot(slotIndex);
				if (slotStack != null) {
					if (!player.inventory.addItemStackToInventory(slotStack)) {
						world.spawnEntityInWorld(new EntityItem(world, player.posX, player.posY, player.posZ, slotStack));
					}
					if (!world.isRemote) {
						((EntityPlayerMP) player).sendContainerToPlayer(player.inventoryContainer);
					}
					tile.getInventory().setInventorySlotContents(slotIndex, null);
					tile.markDirty();
				}
			}
		}
	}

	public int getSlotForFace(EnumFacing facing, int x, int y, int z) {
		int slot = -1;
		switch (facing) {
		case WEST:
			//System.out.println(getColumn(z) + " x " + getRow(y) + " = Slot " + getSlot(getColumn(z), getRow(y)));
			slot = getSlot(getColumn(z), getRow(y));
			break;
		case EAST:
			//System.out.println((5 - getColumn(z)) + " x " + getRow(y) + " = Slot " + getSlot((5 - getColumn(z)), getRow(y)));
			slot = getSlot((5 - getColumn(z)), getRow(y));
			break;
		case NORTH:
			//System.out.println((5 - getColumn(x)) + " x " + getRow(y) + " = Slot " + getSlot((5 - getColumn(x)), getRow(y)));
			slot = getSlot((5 - getColumn(x)), getRow(y));
			break;
		case SOUTH:
			//System.out.println(getColumn(x) + " x " + getRow(y) + " = Slot " + getSlot(getColumn(x), getRow(y)));
			slot = getSlot(getColumn(x), getRow(y));
			break;
		default:
		case UP:
		case DOWN:
			break;
		}
		return slot;
	}

	private int getSlot(int x, int y) {
		return x > 0 && y > 0 ? (y - 1) * 4 + x : 0;
	}

	private int getColumn(int y) {
		if (y > 4 && y < 25) {
			return 1;
		}
		else if (y >= 25 && y < 52) {
			return 2;
		}
		else if (y >= 52 && y < 77) {
			return 3;
		}
		else if (y >= 77 && y < 100) {
			return 4;
		}
		return 0;
	}

	private int getRow(int x) {
		if (x < 89 && x > 69) {
			return 1;
		}
		else if (x <= 69 && x > 50) {
			return 2;
		}
		else if (x <= 50 && x > 28) {
			return 3;
		}
		else if (x <= 28 && x > 10) {
			return 4;
		}
		return 0;
	}

	public boolean mergeHeldItem(EntityPlayer player, EnumHand hand, World world, BlockPos pos) {
		boolean didMerge = false;
		//boolean triedMerge = false;
		int nullSlots = 0;
		if (world.getTileEntity(pos) != null && world.getTileEntity(pos) instanceof TileCabinet) {
			ItemStack heldStack = player.getHeldItem(hand);
			TileCabinet tile = (TileCabinet) world.getTileEntity(pos);
			IInventory inv = tile;
			for (int i = 0; i < inv.getSizeInventory(); i++) {
				if (inv.getStackInSlot(i) == null) {
					nullSlots++;
				}
				else {
					//triedMerge = true;
					if (inv.getStackInSlot(i).stackSize != inv.getStackInSlot(i).getMaxStackSize()) {
						if (ItemStack.areItemsEqual(inv.getStackInSlot(i), heldStack)) {

							int heldStackSize = heldStack.stackSize;
							int slotStackSize = inv.getStackInSlot(i).stackSize;
							if (heldStackSize + slotStackSize <= heldStack.getMaxStackSize()) {
								inv.setInventorySlotContents(i, heldStack);
								inv.getStackInSlot(i).stackSize = heldStackSize + slotStackSize;
								player.setHeldItem(hand, null);
								didMerge = true;
								break;
							}
							else {
								heldStack.stackSize = (heldStackSize + slotStackSize) - heldStackSize;
								inv.getStackInSlot(i).stackSize = inv.getStackInSlot(i).getMaxStackSize();
								break;
							}
						}
					}
				}
			}
			if (nullSlots > 0 && !didMerge) {
				for (int i = 0; i < inv.getSizeInventory(); i++) {
					if (inv.getStackInSlot(i) == null) {
						inv.setInventorySlotContents(i, heldStack);
						player.setHeldItem(hand, null);
						didMerge = true;
						break;
					}
				}
			}
		}
		return didMerge;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileCabinet();
	}

	@SideOnly(Side.CLIENT)
	@Override
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		return true;
	}

}