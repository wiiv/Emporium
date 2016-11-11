package wiiv.emporium.model;

import net.minecraft.client.model.*;
import net.minecraft.entity.Entity;

/**
 * lamp_Paper3 - wiiv
 * Created using Tabula 4.1.1
 */
public class LampPaperFloating extends ModelBase {
	public ModelRenderer paper;
	public ModelRenderer support;
	public ModelRenderer light;

	public LampPaperFloating() {
		textureWidth = 64;
		textureHeight = 64;
		light = new ModelRenderer(this, 0, 38);
		light.setRotationPoint(0.0F, 16.0F, 0.0F);
		light.addBox(-1.0F, 5.0F, -1.0F, 2, 1, 2, 0.0F);
		paper = new ModelRenderer(this, 0, 0);
		paper.setRotationPoint(0.0F, 16.0F, 0.0F);
		paper.addBox(-6.0F, -8.0F, -6.0F, 12, 14, 12, 0.0F);
		support = new ModelRenderer(this, -12, 26);
		support.mirror = true;
		support.setRotationPoint(0.0F, 16.0F, 0.0F);
		support.addBox(-6.0F, 5.5F, -6.0F, 12, 0, 12, 0.0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		light.render(f5);
		paper.render(f5);
		support.render(f5);
	}

	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
