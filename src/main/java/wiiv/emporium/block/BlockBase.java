package wiiv.emporium.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.*;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.*;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.*;
import wiiv.emporium.Emporium;
import wiiv.emporium.api.IModelHolder;

/**
 * @author p455w0rd
 *
 */
public class BlockBase extends Block implements IModelHolder {

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
	@SideOnly(Side.CLIENT)
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}
}
