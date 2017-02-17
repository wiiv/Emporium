package wiiv.emporium.util;

import javax.vecmath.Vector3f;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockModelShapes;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.model.TRSRTransformation;

/**
 * @author p455w0rd
 *
 */
public class RenderUtils {

	private static final TRSRTransformation flipX = new TRSRTransformation(null, null, new Vector3f(-1, 1, 1), null);

	public static TRSRTransformation get(float tx, float ty, float tz, float rx, float ry, float rz, float s) {
		return TRSRTransformation.blockCenterToCorner(new TRSRTransformation(new Vector3f(tx / 16, ty / 16, tz / 16), TRSRTransformation.quatFromXYZDegrees(new Vector3f(rx, ry, rz)), new Vector3f(s, s, s), null));
	}

	public static TRSRTransformation leftify(TRSRTransformation transform) {
		return TRSRTransformation.blockCenterToCorner(flipX.compose(TRSRTransformation.blockCornerToCenter(transform)).compose(flipX));
	}

	private static Minecraft mc() {
		return Minecraft.getMinecraft();
	}

	public static TextureManager getTextureManager() {
		return mc().renderEngine;
	}

	public static TextureAtlasSprite getSprite(ResourceLocation spriteLocation) {
		return getTextureMap().getAtlasSprite(spriteLocation.toString());
	}

	public static void bindTexture(ResourceLocation textureLocation) {
		getTextureManager().bindTexture(textureLocation);
	}

	public static ITextureObject getTexture(ResourceLocation resLoc) {
		return getTextureManager().getTexture(resLoc);
	}

	public static TextureMap getTextureMap() {
		return mc().getTextureMapBlocks();
	}

	public static RenderItem getRenderItem() {
		return mc().getRenderItem();
	}

	public static RenderManager getRenderManager() {
		return mc().getRenderManager();
	}

	public static ItemModelMesher getMesher() {
		return ReflectionUtils.getRenderItemModelMesher(getRenderItem());
	}

	public static ModelManager getModelManager() {
		return getMesher().getModelManager();
	}

	public static BlockModelShapes getBlockModelShapes() {
		return getModelManager().getBlockModelShapes();
	}

	public static void skipStateChecks(Block block) {
		getBlockModelShapes().registerBuiltInBlocks(block);
	}

}
