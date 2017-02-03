package wiiv.emporium.render.tile;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import wiiv.emporium.block.tile.TileCabinet;

public class RenderTileCabinet extends TileEntitySpecialRenderer<TileCabinet> {

	@Override
	public void renderTileEntityAt(TileCabinet Cabinet, double x, double y, double z, float partialTicks, int destroyStage) {
		super.renderTileEntityAt(Cabinet, x, y, z, partialTicks, destroyStage);
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y, z);
		renderItems(Cabinet);
		GlStateManager.popMatrix();
	}

	private void renderItems(TileCabinet Cabinet) {

		ItemStack stack = Cabinet.getStack();

		if (stack != null) {
			RenderHelper.enableStandardItemLighting();
			GlStateManager.enableLighting();
			GlStateManager.pushMatrix();

			GlStateManager.scale(0.5F, 0.5F, 0.5F);
			GlStateManager.rotate(90F, 1, 0, 0);
			//GlStateManager.translate(1.0, 1.0, -1.4);
			GlStateManager.translate(1.0, 1.0, -0.25);

			for (int i = 0; i < Cabinet.getStack().stackSize; i++) {

				GlStateManager.rotate(TileCabinet.getAngle(i), TileCabinet.getXRot(i), TileCabinet.getYRot(i), TileCabinet.getZRot(i));
				GlStateManager.translate(TileCabinet.getXOffset(i), TileCabinet.getZOffset(i), (TileCabinet.getYOffset(i) - 0.06F));
				Minecraft.getMinecraft().getRenderItem().renderItem(stack, ItemCameraTransforms.TransformType.FIXED);
				//GlStateManager.translate(-TileCabinet.getXOffset(i), -TileCabinet.getZOffset(i), TileCabinet.getYOffset(i));
				GlStateManager.rotate(-TileCabinet.getAngle(i), -TileCabinet.getXRot(i), -TileCabinet.getYRot(i), -TileCabinet.getZRot(i));

			}

			GlStateManager.popMatrix();
		}
	}
}
