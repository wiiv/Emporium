package wiiv.emporium.common.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
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
import wiiv.emporium.common.Emporium;
import wiiv.emporium.common.block.tile.TileJar;
import wiiv.emporium.common.item.ItemCookie;
import wiiv.emporium.common.lib.LibMisc;

public class BlockJar extends Block implements ITileEntityProvider{
	
	private static final AxisAlignedBB BOUNDING_BOX = new AxisAlignedBB((0.0625D*3), 0.0D, (0.0625D*3), (0.0625D*13), (0.0625D*13), (0.0625D*13));
	private static final AxisAlignedBB COLLISION_BOX = new AxisAlignedBB((0.0625D*4), 0.0D, (0.0625D*4), (0.0625D*12), (0.0625D*12), (0.0625D*12));
	
	public BlockJar() {
		
		super(Material.GLASS);
		setUnlocalizedName(LibMisc.DecBlocks.JAR.getUnlocalizedName());
		setRegistryName(LibMisc.DecBlocks.JAR.getRegistryName());
		
		setHardness(1.0F);
		setSoundType(blockSoundType.GLASS);
		
		setCreativeTab(Emporium.CREATIVE_TAB);
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

		return BOUNDING_BOX;
	}
	
	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, Entity entityIn) {
		super.addCollisionBoxToList(pos, entityBox, collidingBoxes, COLLISION_BOX);
	}
	
	private TileJar getTE(World world, BlockPos pos) {
        return (TileJar) world.getTileEntity(pos);
    }
	/*
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
	
		if(!world.isRemote) {
			
			TileJar jar = getTE(world, pos);
			
			if(jar.getStack() == null) {
				if(player.getHeldItem(hand) != null) {
					if(heldItem.getItem() instanceof ItemFood){
						jar.setStack(player.getHeldItem(hand));
						player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
						player.openContainer.detectAndSendChanges();
					}
				}
			} else {
				ItemStack stack = jar.getStack();
                jar.setStack(null);
                if (!player.inventory.addItemStackToInventory(stack)) {
                    
                    EntityItem entityItem = new EntityItem(world, pos.getX(), pos.getY()+1, pos.getZ(), stack);
                    world.spawnEntityInWorld(entityItem);
                } else {
                    player.openContainer.detectAndSendChanges();
                }
			}
		}
		return true;
	}
*/
	@Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {

        if(!world.isRemote) {

            TileJar jar = getTE(world, pos);

            if(jar.getStack() == null) {
                if(player.getHeldItem(hand) != null) {
                    if(heldItem.getItem() instanceof ItemCookie){
                        player.setHeldItem(hand, jar.setStack(player.getHeldItem(hand)));
                        player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
                        player.openContainer.detectAndSendChanges();
                    }
                }
            } else {
                ItemStack stack = jar.getStack();
                jar.setStack(null);
                if (!player.inventory.addItemStackToInventory(stack)) {

                    EntityItem entityItem = new EntityItem(world, pos.getX(), pos.getY()+1, pos.getZ(), stack);
                    world.spawnEntityInWorld(entityItem);
                } else {
                    player.openContainer.detectAndSendChanges();
                }
            }
        }
        return true;
    }
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileJar();
	}
}
