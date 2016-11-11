package model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
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
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.light = new ModelRenderer(this, 0, 38);
        this.light.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.light.addBox(-1.0F, 5.0F, -1.0F, 2, 1, 2, 0.0F);
        this.paper = new ModelRenderer(this, 0, 0);
        this.paper.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.paper.addBox(-6.0F, -8.0F, -6.0F, 12, 14, 12, 0.0F);
        this.support = new ModelRenderer(this, -12, 26);
        this.support.mirror = true;
        this.support.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.support.addBox(-6.0F, 5.5F, -6.0F, 12, 0, 12, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.light.render(f5);
        this.paper.render(f5);
        this.support.render(f5);
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
