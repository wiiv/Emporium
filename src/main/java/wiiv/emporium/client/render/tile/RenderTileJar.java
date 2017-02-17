package wiiv.emporium.client.render.tile;

import java.util.ArrayList;
import java.util.List;

import javax.vecmath.Matrix4f;

import org.apache.commons.lang3.tuple.Pair;

import codechicken.lib.render.item.IItemRenderer;
import codechicken.lib.render.state.GlStateManagerHelper;
import codechicken.lib.util.TransformUtils;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IPerspectiveAwareModel;
import wiiv.emporium.Globals;
import wiiv.emporium.block.tile.TileCookieJar;
import wiiv.emporium.client.model.ModelCookieJar;
import wiiv.emporium.init.ModBlocks;
import wiiv.emporium.util.RenderUtils;

public class RenderTileJar extends TileEntitySpecialRenderer<TileCookieJar> implements IItemRenderer, IPerspectiveAwareModel {

	public static final ResourceLocation JAR_TEXTURE = new ResourceLocation(Globals.MOD_ID, "textures/models/jar_metal.png");
	public static final ResourceLocation JAR_SPRITE = new ResourceLocation(Globals.MOD_ID, "models/jar_metal_sprite");
	private final ModelCookieJar MODEL = new ModelCookieJar();

	@Override
	public void renderItem(ItemStack item) {
		if (Block.getBlockFromItem(item.getItem()) == ModBlocks.JAR) {

			GlStateManager.pushMatrix();
			//GlStateManager.translate(x, y, z);
			renderItems(item);
			GlStateManager.popMatrix();

			GlStateManagerHelper.pushState();
			RenderUtils.bindTexture(JAR_TEXTURE);
			GlStateManager.enableLighting();
			GlStateManager.enableBlend();
			GlStateManager.rotate(180, 0, 0, 180);
			GlStateManager.translate(-0.5, -1.5, 0.5);
			MODEL.render(0.0625F);
			GlStateManager.translate(0.5, 1.5, -0.5);
			GlStateManager.disableBlend();
			GlStateManager.disableLighting();
			GlStateManagerHelper.popState();

		}
	}

	@Override
	public void renderTileEntityAt(TileCookieJar jar, double x, double y, double z, float partialTicks, int destroyStage) {
		super.renderTileEntityAt(jar, x, y, z, partialTicks, destroyStage);
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y, z);
		renderItems(jar);
		GlStateManager.popMatrix();
	}

	private void renderItems(ItemStack jarItem) {

		if (jarItem.hasTagCompound() && jarItem.getTagCompound().hasKey("BlockEntityTag")) {
			NBTTagCompound tag = jarItem.getTagCompound().getCompoundTag("BlockEntityTag").getCompoundTag("item");
			NBTTagList list = tag.getTagList("Items", 10);
			ItemStack stack = ItemStack.loadItemStackFromNBT(list.getCompoundTagAt(0));

			if (stack != null) {
				GlStateManager.enableLighting();
				GlStateManager.pushMatrix();
				GlStateManager.enableAlpha();
				GlStateManager.enableBlend();

				GlStateManager.scale(0.5F, 0.5F, 0.5F);
				GlStateManager.rotate(90F, 1, 0, 0);
				GlStateManager.translate(1.0, 1.0, -0.2);

				for (int i = 0; i < stack.stackSize; i++) {

					GlStateManager.rotate(TileCookieJar.getAngle(i), TileCookieJar.getXRot(i), TileCookieJar.getYRot(i), TileCookieJar.getZRot(i));
					GlStateManager.translate(TileCookieJar.getXOffset(i), TileCookieJar.getZOffset(i), (TileCookieJar.getYOffset(i) - 0.06F));
					Minecraft.getMinecraft().getRenderItem().renderItem(stack, ItemCameraTransforms.TransformType.FIXED);
					GlStateManager.rotate(-TileCookieJar.getAngle(i), -TileCookieJar.getXRot(i), -TileCookieJar.getYRot(i), -TileCookieJar.getZRot(i));

				}
				GlStateManager.disableBlend();
				GlStateManager.disableAlpha();
				GlStateManager.popMatrix();
			}
		}
	}

	private void renderItems(TileCookieJar jar) {

		ItemStack stack = jar.getStack();

		if (stack != null) {
			RenderHelper.enableStandardItemLighting();
			GlStateManager.enableLighting();
			GlStateManager.pushMatrix();

			GlStateManager.scale(0.5F, 0.5F, 0.5F);
			GlStateManager.rotate(90F, 1, 0, 0);
			GlStateManager.translate(1.0, 1.0, -0.25);

			for (int i = 0; i < jar.getStack().stackSize; i++) {

				GlStateManager.rotate(TileCookieJar.getAngle(i), TileCookieJar.getXRot(i), TileCookieJar.getYRot(i), TileCookieJar.getZRot(i));
				GlStateManager.translate(TileCookieJar.getXOffset(i), TileCookieJar.getZOffset(i), (TileCookieJar.getYOffset(i) - 0.06F));
				Minecraft.getMinecraft().getRenderItem().renderItem(stack, ItemCameraTransforms.TransformType.FIXED);
				GlStateManager.rotate(-TileCookieJar.getAngle(i), -TileCookieJar.getXRot(i), -TileCookieJar.getYRot(i), -TileCookieJar.getZRot(i));

			}

			GlStateManager.popMatrix();
		}
	}

	@Override
	public List<BakedQuad> getQuads(IBlockState state, EnumFacing side, long rand) {
		return new ArrayList<BakedQuad>();
	}

	@Override
	public boolean isAmbientOcclusion() {
		return false;
	}

	@Override
	public boolean isGui3d() {
		return false;
	}

	@Override
	public boolean isBuiltInRenderer() {
		return true;
	}

	@Override
	public TextureAtlasSprite getParticleTexture() {
		return RenderUtils.getSprite(JAR_SPRITE);
	}

	@Override
	public ItemCameraTransforms getItemCameraTransforms() {
		return ItemCameraTransforms.DEFAULT;
	}

	@Override
	public ItemOverrideList getOverrides() {
		return ItemOverrideList.NONE;
	}

	@Override
	public Pair<? extends IBakedModel, Matrix4f> handlePerspective(TransformType cameraTransformType) {
		return MapWrapper.handlePerspective(this, TransformUtils.DEFAULT_BLOCK.getTransforms(), cameraTransformType);
	}

}
