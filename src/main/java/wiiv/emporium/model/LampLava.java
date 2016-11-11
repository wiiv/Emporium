package model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * lamp_Lava - wiiv
 * Created using Tabula 4.1.1
 */
public class LampLava extends ModelBase {
    public ModelRenderer tankTop;
    public ModelRenderer tank;
    public ModelRenderer fluid;
    public ModelRenderer glob1;
    public ModelRenderer glob2;
    public ModelRenderer glob3;
    public ModelRenderer tankBottom;

    public LampLava() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.glob1 = new ModelRenderer(this, 44, 16);
        this.glob1.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.glob1.addBox(-1.5F, 2.0F, -1.5F, 3, 3, 3, 0.0F);
        this.glob2 = new ModelRenderer(this, 44, 22);
        this.glob2.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.glob2.addBox(-1.5F, -1.0F, -1.5F, 2, 2, 2, 0.0F);
        this.glob3 = new ModelRenderer(this, 44, 26);
        this.glob3.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.glob3.addBox(0.5F, -3.0F, 0.5F, 1, 1, 1, 0.0F);
        this.tankTop = new ModelRenderer(this, 0, 0);
        this.tankTop.setRotationPoint(0.0F, 8.0F, 0.0F);
        this.tankTop.addBox(-3.0F, 0.0F, -3.0F, 6, 4, 6, 0.0F);
        this.tank = new ModelRenderer(this, 24, 0);
        this.tank.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.tank.addBox(-3.0F, -4.0F, -3.0F, 6, 10, 6, 0.0F);
        this.fluid = new ModelRenderer(this, 24, 16);
        this.fluid.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.fluid.addBox(-2.5F, -3.5F, -2.5F, 5, 9, 5, 0.0F);
        this.tankBottom = new ModelRenderer(this, 0, 10);
        this.tankBottom.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.tankBottom.addBox(-3.0F, 6.0F, -3.0F, 6, 2, 6, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.glob1.render(f5);
        this.glob2.render(f5);
        this.glob3.render(f5);
        this.tankTop.render(f5);
        this.tank.render(f5);
        this.fluid.render(f5);
        this.tankBottom.render(f5);
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
