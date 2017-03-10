package wiiv.emporium.client.render.tile;

import java.util.ArrayList;
import java.util.List;

import javax.vecmath.Matrix4f;

import org.apache.commons.lang3.tuple.Pair;

import codechicken.lib.render.item.IItemRenderer;
import codechicken.lib.render.state.GlStateManagerHelper;
import codechicken.lib.util.TransformUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPumpkin;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemSkull;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IPerspectiveAwareModel;
import wiiv.emporium.Globals;
import wiiv.emporium.block.BlockCabinetTopSingle;
import wiiv.emporium.block.tile.TileCabinetTopSingle;
import wiiv.emporium.client.model.ModelCabinetTopSingle;
import wiiv.emporium.init.ModBlocks;
import wiiv.emporium.util.RenderUtils;

public class RenderTileCabinetTopSingle extends TileEntitySpecialRenderer<TileCabinetTopSingle> implements IItemRenderer, IPerspectiveAwareModel {

	public static final ResourceLocation CABINET_TEXTURE = new ResourceLocation(Globals.MOD_ID, "textures/models/cabinet_top_single.png");
	public static final ResourceLocation CABINET_SPRITE = new ResourceLocation(Globals.MOD_ID, "models/cabinet1_sprite");
	private static final ResourceLocation RES_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");
	private final ModelCabinetTopSingle MODEL = new ModelCabinetTopSingle();

	@Override
	public void renderItem(ItemStack item) {
		if (Block.getBlockFromItem(item.getItem()) == ModBlocks.CABINET) {
			GlStateManagerHelper.pushState();
			RenderUtils.bindTexture(CABINET_TEXTURE);
			GlStateManager.enableLighting();
			GlStateManager.rotate(180, 0, 0, 180);
			GlStateManager.translate(-0.5, -1.5, 0.5);
			ModelCabinetTopSingle.getInstance().render(0.0625F);
			GlStateManager.translate(0.5, 1.5, -0.5);
			GlStateManager.disableLighting();
			GlStateManagerHelper.popState();
		}
	}

	@Override
	public void renderTileEntityAt(TileCabinetTopSingle te, double x, double y, double z, float partialTicks, int destroyStage) {
		//super.renderTileEntityAt(tile, x, y, z, partialTicks, destroyStage);
		int i = 0;
		if (te.hasWorldObj()) {
			i = te.getBlockMetadata();
		}
		int j = 0;

		switch (i) {
		default:
		case 0:
			j = 180;
			break;
		case 1:
			j = -90;
			break;
		case 2:
			j = 0;
			break;
		case 3:
			j = 90;
			break;
		}

		GlStateManager.enableDepth();
		GlStateManager.depthFunc(515);
		GlStateManager.depthMask(true);

		if (destroyStage >= 0) {
			bindTexture(DESTROY_STAGES[destroyStage]);
			GlStateManager.matrixMode(5890);
			GlStateManager.pushMatrix();
			GlStateManager.scale(4.0F, 4.0F, 1.0F);
			GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
			GlStateManager.matrixMode(5888);
		}
		else {
			RenderUtils.bindTexture(CABINET_TEXTURE);
		}
		GlStateManager.pushMatrix();
		GlStateManager.enableRescaleNormal();
		GlStateManager.translate((float) x, (float) y + 1.0F, (float) z + 1.0F);
		GlStateManager.scale(1.0F, -1.0F, -1.0F);

		GlStateManager.translate(0.5F, -0.5F, 0.5F);

		float f = te.prevDoorAngle + (te.doorAngle - te.prevDoorAngle) * partialTicks;
		f = 1.0F - f;
		f = 1.0F - f * f * f;
		MODEL.door.rotateAngleY = +(f * ((float) Math.PI / 2F));

		if (destroyStage < 0) {
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		}
		GlStateManager.rotate(j, 0.0F, 1.0F, 0.0F);
		MODEL.render(0.0625F);

		GlStateManager.translate(-0.5F, 0.5F, -0.5F);

		GlStateManager.disableRescaleNormal();
		GlStateManager.popMatrix();
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

		if (destroyStage >= 0) {
			GlStateManager.matrixMode(5890);
			GlStateManager.popMatrix();
			GlStateManager.matrixMode(5888);
		}
		GlStateManager.pushMatrix();
		GlStateManager.translate((float) x, (float) y, (float) z + 1.0F);
		GlStateManager.scale(0.2D, 0.2D, 0.2D);

		renderInventory(te);

		GlStateManager.popMatrix();

	}

	public void renderInventory(TileCabinetTopSingle tile) {
		int j = 0, ii = 0;
		double x = 0.0D, z = 0.0D;
		if (tile.hasWorldObj()) {
			ii = tile.getBlockMetadata();
		}

		switch (ii) {
		default:
		case 0:
			j = -180;
			break;
		case 1:
			j = 90;
			break;
		case 2:
			j = 0;
			break;
		case 3:
			j = -90;
			break;
		}
		for (int i = 0; i < tile.getInventory().getSizeInventory(); i++) {
			ItemStack stack = tile.getInventory().getStackInSlot(i);
			if (stack != null) {
				if (MODEL.door.rotateAngleY != 0.0f) {
					switch (ii) {
					default:
					case 0:
						x = -(0.9 + getColumn(i) + ((double) (getColumn(i)) / 10) - 5);
						z = (!frontOfShelf(i) ? -1.5 : -3.0) - 0.37;
						break;
					case 1:
						z = -(0.9 + getColumn(i) + ((double) getColumn(i) / 10) - 0.15);
						x = !frontOfShelf(i) ? 2.0 : 3.5;
						break;
					case 2:
						x = 0.9 + getColumn(i) + ((double) getColumn(i) / 10);
						z = frontOfShelf(i) ? -1.5 : -3.0;
						break;
					case 3:
						z = (0.9 + getColumn(i) + ((double) getColumn(i) / 10) - 5);
						x = frontOfShelf(i) ? 1.5 : 3.0;
						break;
					}

					GlStateManager.translate(x, i < 8 ? 3 : 1.15, z);
					if (stack.getItem() instanceof ItemSkull || Block.getBlockFromItem(stack.getItem()) instanceof BlockCabinetTopSingle || Block.getBlockFromItem(stack.getItem()) instanceof BlockPumpkin) {
						GlStateManager.rotate(180 - (ii % 2 == 0 ? j : -j), 0, 1, 0);
					}
					else {
						GlStateManager.rotate(j, 0, 1, 0);
					}

					GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
					GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

					Minecraft.getMinecraft().getItemRenderer().renderItem(Minecraft.getMinecraft().thePlayer, stack, ItemCameraTransforms.TransformType.NONE);

					if (stack.getItem() instanceof ItemSkull || Block.getBlockFromItem(stack.getItem()) instanceof BlockCabinetTopSingle || Block.getBlockFromItem(stack.getItem()) instanceof BlockPumpkin) {
						GlStateManager.rotate(-(180 - (ii % 2 == 0 ? j : -j)), 0, 1, 0);
					}
					else {
						GlStateManager.rotate(-j, 0, 1, 0);
					}
					GlStateManager.translate(-x, i < 8 ? -3 : -1.15, -z);
				}
				if (MODEL.door.rotateAngleY == 0.0f && Minecraft.getMinecraft().thePlayer.isSneaking()) {
					renderItemOnFace(stack, EnumFacing.values()[ii + 2], i, ii);
				}
			}
		}
	}

	private void renderItemOnFace(ItemStack itemStack, EnumFacing side, int index, int rotation) {
		if (side == EnumFacing.DOWN || side == EnumFacing.UP) {
			return;
		}
		float[] rot = {
				2,
				0,
				1,
				3
		};
		GlStateManager.pushMatrix();
		//alignRendering(side);
		GlStateManager.translate(.5f, .5f, .5f);
		GlStateManager.rotate(180, 0, 0, 1);
		GlStateManager.rotate(rot[rotation] * 90, 0, 1, 0);
		GlStateManager.translate(-.5f, -.5f, -.5f);

		switch (side) {
		default:
		case WEST:
			GlStateManager.rotate(90, 0, 1, 0);
			moveRendering(0.75f, (20f * getColumn(index)) - 12, (15.0f * getRow(index)) - 52.0f, index == 13 || index == 14 ? 1.0f : 1.25f);
			break;
		case EAST:
			moveRendering(0.75f, (20f * getColumn(index)) - 77f, (15.0f * getRow(index)) - 52.0f, index == 13 || index == 14 ? 0.0f : 0.25f);
			break;
		case NORTH:
			GlStateManager.rotate(180, 0, 1, 0);
			moveRendering(0.75f, ((20f * getColumn(index)) - 77f), (15.0f * getRow(index)) - 52.0f, index == 13 || index == 14 ? -6.0f : -5.75f);
			break;
		case SOUTH:
			GlStateManager.rotate(90, 0, 1, 0);
			moveRendering(0.75f, (20f * getColumn(index)) + 5f, (15.0f * getRow(index)) - 52.0f, index == 13 || index == 14 ? -4.0f : -3.75f);
			break;
		case DOWN:
		case UP:
			break;
		}

		GlStateManager.disableLighting();
		//GlStateManager.disableDepth();
		GlStateManager.enablePolygonOffset();
		GlStateManager.doPolygonOffset(-1, -1);
		Minecraft.getMinecraft().getRenderItem().renderItemAndEffectIntoGUI(itemStack, 0, 0);
		if (itemStack.stackSize > 1) {
			//GlStateManager.enableDepth();
			//GlStateManager.enableBlend();
			//GlStateManager.enableAlpha();
			GlStateManager.translate(8, 9, 300);
			GlStateManager.scale(0.5, 0.5, 0.5);
			//Minecraft.getMinecraft().getRenderItem().renderItemOverlayIntoGUI(getFontRenderer(), itemStack, 0, 0, itemStack.stackSize > 1 ? "" + itemStack.stackSize : "");
			getFontRenderer().drawStringWithShadow("" + itemStack.stackSize, 10, 7, 16777215);
			GlStateManager.scale(-0.5, -0.5, -0.5);
			GlStateManager.translate(-8, -9, -300);
			//GlStateManager.disableDepth();
		}
		//GlStateManager.enableDepth();
		GlStateManager.enableAlpha();
		GlStateManager.disableBlend();
		GlStateManager.disablePolygonOffset();
		GlStateManager.popMatrix();
	}

	private void moveRendering(float size, float offsetX, float offsetY, float offsetZ) {
		GlStateManager.translate(0, 0, offsetZ);
		GlStateManager.scale(1 / 16f, 1 / 16f, -.00001f);
		GlStateManager.translate(offsetX, offsetY, 0);
		GlStateManager.scale(size, size, 1);
	}

	private boolean frontOfShelf(int index) {
		return (index > 3 && index < 8) || index > 11;
	}

	private int getColumn(int index) {
		return index % 4;
	}

	private int getRow(int index) {
		if (index >= 0 && index < 4) {
			return 0;
		}
		else if (index >= 4 && index < 8) {
			return 1;
		}
		else if (index >= 8 && index < 12) {
			return 2;
		}
		else if (index >= 12 && index < 16) {
			return 3;
		}
		return 0;
	}

	@Override
	public Pair<? extends IBakedModel, Matrix4f> handlePerspective(ItemCameraTransforms.TransformType cameraTransformType) {
		return MapWrapper.handlePerspective(this, TransformUtils.DEFAULT_BLOCK.getTransforms(), cameraTransformType);
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
		return RenderUtils.getSprite(CABINET_SPRITE);
	}

	@Override
	public ItemCameraTransforms getItemCameraTransforms() {
		return ItemCameraTransforms.DEFAULT;
	}

	@Override
	public ItemOverrideList getOverrides() {
		return ItemOverrideList.NONE;
	}
}
