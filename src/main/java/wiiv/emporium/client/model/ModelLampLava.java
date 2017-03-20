package wiiv.emporium.client.model;

import net.minecraft.client.model.*;
import net.minecraft.entity.Entity;

/**
 * lamp_Lava - wiiv
 * Created using Tabula 4.1.1
 */
public class ModelLampLava extends ModelBase {
	public ModelRenderer tankTop;
	public ModelRenderer tank;
	public ModelRenderer fluid;
	public ModelRenderer glob1;
	public ModelRenderer glob2;
	public ModelRenderer glob3;
	public ModelRenderer tankBottom;

	public ModelLampLava() {
		textureWidth = 64;
		textureHeight = 32;
		glob1 = new ModelRenderer(this, 44, 16);
		glob1.setRotationPoint(0.0F, 16.0F, 0.0F);
		glob1.addBox(-1.5F, 2.0F, -1.5F, 3, 3, 3, 0.0F);
		glob2 = new ModelRenderer(this, 44, 22);
		glob2.setRotationPoint(0.0F, 16.0F, 0.0F);
		glob2.addBox(-1.5F, -1.0F, -1.5F, 2, 2, 2, 0.0F);
		glob3 = new ModelRenderer(this, 44, 26);
		glob3.setRotationPoint(0.0F, 16.0F, 0.0F);
		glob3.addBox(0.5F, -3.0F, 0.5F, 1, 1, 1, 0.0F);
		tankTop = new ModelRenderer(this, 0, 0);
		tankTop.setRotationPoint(0.0F, 8.0F, 0.0F);
		tankTop.addBox(-3.0F, 0.0F, -3.0F, 6, 4, 6, 0.0F);
		tank = new ModelRenderer(this, 24, 0);
		tank.setRotationPoint(0.0F, 16.0F, 0.0F);
		tank.addBox(-3.0F, -4.0F, -3.0F, 6, 10, 6, 0.0F);
		fluid = new ModelRenderer(this, 24, 16);
		fluid.setRotationPoint(0.0F, 16.0F, 0.0F);
		fluid.addBox(-2.5F, -3.5F, -2.5F, 5, 9, 5, 0.0F);
		tankBottom = new ModelRenderer(this, 0, 10);
		tankBottom.setRotationPoint(0.0F, 16.0F, 0.0F);
		tankBottom.addBox(-3.0F, 6.0F, -3.0F, 6, 2, 6, 0.0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		glob1.render(f5);
		glob2.render(f5);
		glob3.render(f5);
		tankTop.render(f5);
		tank.render(f5);
		fluid.render(f5);
		tankBottom.render(f5);
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
