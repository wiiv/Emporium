package wiiv.emporium.client.render.tile;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import wiiv.emporium.common.block.tile.TileJar;

public class RenderTileJar extends TileEntitySpecialRenderer<TileJar>{
	
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
			GlStateManager.translate(1.0, 1.0, -(0.3125 + 0.03125));
            
            for (int i = 0; i < jar.getStack().stackSize; i++){
	           	Minecraft.getMinecraft().getRenderItem().renderItem(stack, ItemCameraTransforms.TransformType.FIXED);
	           	GlStateManager.translate(0.0, 0.0, -0.125);
			}
            GlStateManager.popMatrix();
        }
    }
}
