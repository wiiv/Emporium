package wiiv.emporium.util;

import java.util.Map;

import com.google.common.collect.Maps;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @author p455w0rd
 *
 */
public class ReflectionUtils {

	@SideOnly(Side.CLIENT)
	public static RenderItem getGuiScreenRenderItem(GuiScreen gui) {
		return ReflectionHelper.getPrivateValue(GuiScreen.class, gui, getObf("itemRender"));
	}

	@SideOnly(Side.CLIENT)
	public static float getGuiZLevel(Gui gui) {
		return ReflectionHelper.getPrivateValue(Gui.class, gui, determineZLevelSRG("zLevel", Gui.class));
	}

	@SideOnly(Side.CLIENT)
	public static void setGuiZLevel(Gui gui, float zLevel) {
		ReflectionHelper.setPrivateValue(Gui.class, gui, zLevel, determineZLevelSRG("zLevel", Gui.class));
	}

	@SideOnly(Side.CLIENT)
	public static float getGuiScreenRendererZLevel(GuiScreen gui) {
		return ReflectionHelper.getPrivateValue(RenderItem.class, getGuiScreenRenderItem(gui), determineZLevelSRG("zLevel", RenderItem.class));
	}

	@SideOnly(Side.CLIENT)
	public static void setGuiScreenRendererZLevel(GuiScreen gui, float zLevel) {
		ReflectionHelper.setPrivateValue(RenderItem.class, getGuiScreenRenderItem(gui), zLevel, determineZLevelSRG("zLevel", RenderItem.class));
	}

	@SideOnly(Side.CLIENT)
	public static float getRenderItemZLevel(RenderItem renderItem) {
		return ReflectionHelper.getPrivateValue(RenderItem.class, renderItem, determineZLevelSRG("zLevel", RenderItem.class));
	}

	@SideOnly(Side.CLIENT)
	public static void setRenderItemZLevel(RenderItem renderItem, float zLevel) {
		ReflectionHelper.setPrivateValue(RenderItem.class, renderItem, zLevel, determineZLevelSRG("zLevel", RenderItem.class));
	}

	@SideOnly(Side.CLIENT)
	public static void setMCRenderItem(RenderItem renderItemIn) {
		ReflectionHelper.setPrivateValue(Minecraft.class, Minecraft.getMinecraft(), renderItemIn, getObf("renderItem"));
	}

	@SideOnly(Side.CLIENT)
	public static TextureManager getRenderItemTextureManager(RenderItem renderItem) {
		return ReflectionHelper.getPrivateValue(RenderItem.class, renderItem, getObf("textureManager"));
	}

	@SideOnly(Side.CLIENT)
	public static ItemModelMesher getRenderItemModelMesher(RenderItem renderItem) {
		return ReflectionHelper.getPrivateValue(RenderItem.class, renderItem, getObf("itemModelMesher"));
	}

	@SideOnly(Side.CLIENT)
	public static void setRenderItemModelMesher(RenderItem renderItem, ItemModelMesher mesher) {
		ReflectionHelper.setPrivateValue(RenderItem.class, renderItem, mesher, getObf("itemModelMesher"));
	}

	@SideOnly(Side.CLIENT)
	public static ItemColors getRenderItemItemColors(RenderItem renderItem) {
		return ReflectionHelper.getPrivateValue(RenderItem.class, renderItem, getObf("itemColors"));
	}

	public static boolean isDeobf() {
		return (boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
	}

	private static Map<String, String> obfMap() {
		Map<String, String> obfMap = Maps.newHashMap();
		obfMap.put("renderItem", "field_175621_X");
		obfMap.put("itemModelMesher", "field_175059_m");
		obfMap.put("textureManager", "field_175057_n");
		obfMap.put("itemColors", "field_184395_f");
		return obfMap;
	}

	public static Map<Class<?>, String> zLevel() {
		Map<Class<?>, String> list = Maps.newHashMap();
		list.put(Gui.class, "field_73735_i");
		list.put(RenderItem.class, "field_77023_b");
		return list;
	}

	public static String getObf(String deObf) {
		if (!isDeobf()) {
			for (String dobf : obfMap().keySet()) {
				if (deObf.equals(dobf)) {
					return obfMap().get(dobf);
				}
			}
		}
		return deObf;
	}

	public static String determineZLevelSRG(String string, Class<?> clazz) {
		return isDeobf() ? string : zLevel().containsKey(clazz) ? zLevel().get(clazz) : "";
	}

}
