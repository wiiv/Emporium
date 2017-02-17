package wiiv.emporium.block;

import javax.annotation.Nullable;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import wiiv.emporium.Emporium;
import wiiv.emporium.api.IModelHolder;

/**
 * @author p455w0rd
 *
 */
public class BlockBase extends BlockContainer implements IModelHolder {

	public BlockBase(Material materialIn, MapColor color, String name, float hardness, float resistance) {
		super(materialIn, color);
		setUnlocalizedName(name);
		setRegistryName(name);
		setResistance(resistance);
		setHardness(hardness);
		GameRegistry.register(this);
		GameRegistry.register(new ItemBlock(this), getRegistryName());
		setCreativeTab(Emporium.CREATIVE_TAB);
	}

	public BlockBase(Material materialIn, String name, float hardness, float resistance) {
		super(materialIn);
		setUnlocalizedName(name);
		setRegistryName(name);
		setResistance(resistance);
		setHardness(hardness);
		GameRegistry.register(this);
		GameRegistry.register(new ItemBlock(this), getRegistryName());
		setCreativeTab(Emporium.CREATIVE_TAB);
	}

	public BlockBase(Material materialIn, String name, float hardness) {
		super(materialIn);
		setUnlocalizedName(name);
		setRegistryName(name);
		setHardness(hardness);
		GameRegistry.register(this);
		GameRegistry.register(new ItemBlock(this), getRegistryName());
		setCreativeTab(Emporium.CREATIVE_TAB);
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return null;
	}

	@Override
	public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, @Nullable ItemStack stack) {
		player.addStat(StatList.getBlockStats(this));
		player.addExhaustion(0.025F);
		ItemStack itemstack = new ItemStack(this);
		NBTTagCompound nbttagcompound = new NBTTagCompound();
		te.writeToNBT(nbttagcompound);
		itemstack.setTagInfo("BlockEntityTag", nbttagcompound);
		spawnAsEntity(worldIn, pos, itemstack);
	}
}
