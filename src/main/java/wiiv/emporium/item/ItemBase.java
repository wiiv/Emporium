package wiiv.emporium.item;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.*;
import wiiv.emporium.api.IModelHolder;

/**
 * @author p455w0rd
 *
 */
public class ItemBase extends Item implements IModelHolder {

	public ItemBase(String name) {
		setRegistryName(name);
		setUnlocalizedName(name);
		GameRegistry.register(this);
		setMaxStackSize(64);
		setMaxDamage(0);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}

}
