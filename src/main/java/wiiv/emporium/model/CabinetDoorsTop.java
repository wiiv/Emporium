package model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * kitchen_cabinet_doors_top - wiiv
 * Created using Tabula 4.1.1
 */
public class kitchen_cabinet_doors_top extends ModelBase {
    public ModelRenderer top;
    public ModelRenderer back;
    public ModelRenderer lside;
    public ModelRenderer rside;
    public ModelRenderer bottom;
    public ModelRenderer shelf1;
    public ModelRenderer shelf2;
    public ModelRenderer ldoor;
    public ModelRenderer rdoor;
    public ModelRenderer shape1;
    public ModelRenderer shape1_1;

    public kitchen_cabinet_doors_top() {
        this.textureWidth = 64;
        this.textureHeight = 128;
        this.shape1 = new ModelRenderer(this, 0, 0);
        this.shape1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape1.addBox(5.0F, 2.0F, -2.0F, 1, 4, 1, 0.0F);
        this.back = new ModelRenderer(this, 0, 50);
        this.back.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.back.addBox(-8.0F, -6.0F, 6.0F, 16, 12, 2, 0.0F);
        this.bottom = new ModelRenderer(this, 0, 34);
        this.bottom.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.bottom.addBox(-8.0F, 6.0F, -6.0F, 16, 2, 14, 0.0F);
        this.top = new ModelRenderer(this, 0, 18);
        this.top.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.top.addBox(-8.0F, -8.0F, -6.0F, 16, 2, 14, 0.0F);
        this.shelf2 = new ModelRenderer(this, 0, 102);
        this.shelf2.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.shelf2.addBox(-7.0F, 0.0F, -6.0F, 14, 1, 12, 0.0F);
        this.rside = new ModelRenderer(this, 26, 78);
        this.rside.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.rside.addBox(7.0F, -6.0F, -6.0F, 1, 12, 12, 0.0F);
        this.rdoor = new ModelRenderer(this, 18, 64);
        this.rdoor.setRotationPoint(7.0F, 16.0F, -6.0F);
        this.rdoor.addBox(-7.0F, -6.0F, -1.0F, 8, 13, 1, 0.0F);
        this.shape1_1 = new ModelRenderer(this, 0, 0);
        this.shape1_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape1_1.addBox(-6.0F, 2.0F, -2.0F, 1, 4, 1, 0.0F);
        this.shelf1 = new ModelRenderer(this, 0, 115);
        this.shelf1.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.shelf1.addBox(-7.0F, -6.0F, -6.0F, 14, 1, 12, 0.0F);
        this.lside = new ModelRenderer(this, 0, 78);
        this.lside.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.lside.addBox(-8.0F, -6.0F, -6.0F, 1, 12, 12, 0.0F);
        this.ldoor = new ModelRenderer(this, 0, 64);
        this.ldoor.setRotationPoint(-7.0F, 16.0F, -6.0F);
        this.ldoor.addBox(-1.0F, -6.0F, -1.0F, 8, 13, 1, 0.0F);
        this.ldoor.addChild(this.shape1);
        this.rdoor.addChild(this.shape1_1);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.back.render(f5);
        this.bottom.render(f5);
        this.top.render(f5);
        this.shelf2.render(f5);
        this.rside.render(f5);
        this.rdoor.render(f5);
        this.shelf1.render(f5);
        this.lside.render(f5);
        this.ldoor.render(f5);
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
