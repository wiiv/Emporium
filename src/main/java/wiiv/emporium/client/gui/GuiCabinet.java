package wiiv.emporium.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import wiiv.emporium.Globals;
import wiiv.emporium.block.tile.TileCabinet;
import wiiv.emporium.container.ContainerCabinet;

/**
 * @author p455w0rd
 *
 */
public class GuiCabinet extends GuiContainer {

	public static final ResourceLocation BACKGROUND = new ResourceLocation(Globals.MOD_ID, "textures/gui/cabinet.png");

	public GuiCabinet(EntityPlayer player, TileCabinet cabinet) {
		super(new ContainerCabinet(player, cabinet));
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1, 1, 1, 1);
		bindTexture(BACKGROUND);
		GlStateManager.translate(0.0F, 0.0F, 0.0F);
		this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}

	private void bindTexture(ResourceLocation location) {
		mc.renderEngine.bindTexture(location);
	}

	@Override
	public void drawDefaultBackground() {
	}

}
