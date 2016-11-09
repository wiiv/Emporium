package wiiv.emporium.render.tile;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import wiiv.emporium.block.tile.TileJar;

public class RenderTileJar extends TileEntitySpecialRenderer<TileJar> {

	@Override
	public void renderTileEntityAt(TileJar jar, double x, double y, double z, float partialTicks, int destroyStage) {
		super.renderTileEntityAt(jar, x, y, z, partialTicks, destroyStage);
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y, z);
		renderItems(jar);
		GlStateManager.popMatrix();
	}

	private void renderItems(TileJar jar) {

		ItemStack stack = jar.getStack();

		if (stack != null) {
			RenderHelper.enableStandardItemLighting();
			GlStateManager.enableLighting();
			GlStateManager.pushMatrix();

			GlStateManager.scale(0.5F, 0.5F, 0.5F);
			GlStateManager.rotate(90F, 1, 0, 0);
			//GlStateManager.translate(1.0, 1.0, -1.4);
			GlStateManager.translate(1.0, 1.0, -0.25);

			for (int i = 0; i < jar.getStack().stackSize; i++) {

				GlStateManager.rotate(TileJar.getAngle(i), TileJar.getXRot(i), TileJar.getYRot(i), TileJar.getZRot(i));
				GlStateManager.translate(TileJar.getXOffset(i), TileJar.getZOffset(i), (TileJar.getYOffset(i) - 0.06F));
				Minecraft.getMinecraft().getRenderItem().renderItem(stack, ItemCameraTransforms.TransformType.FIXED);
				//GlStateManager.translate(-TileJar.getXOffset(i), -TileJar.getZOffset(i), TileJar.getYOffset(i));
				GlStateManager.rotate(-TileJar.getAngle(i), -TileJar.getXRot(i), -TileJar.getYRot(i), -TileJar.getZRot(i));

			}

			GlStateManager.popMatrix();
		}
	}
}
