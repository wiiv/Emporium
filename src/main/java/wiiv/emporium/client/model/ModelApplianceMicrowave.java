package wiiv.emporium.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

/**
 * kitchen_cabinet_doors_top - wiiv
 * Created using Tabula 4.1.1
 */
public class ModelApplianceMicrowave extends ModelBase {

	private static final ModelApplianceMicrowave INSTANCE = new ModelApplianceMicrowave();
	
	public ModelRenderer bottom;
    public ModelRenderer top;
    public ModelRenderer sideLeft;
    public ModelRenderer sideRight;
    public ModelRenderer back;
    public ModelRenderer controller;
    
    public ModelRenderer door= (new ModelRenderer(this, 0, 64)).setTextureSize(64, 128);
    public ModelRenderer handle;
    
    public ModelRenderer plate;
    
	public static ModelApplianceMicrowave getInstance() {
		return INSTANCE;
	}

	public static ModelApplianceMicrowave newInstance() {
		return new ModelApplianceMicrowave();
	}

	public ModelApplianceMicrowave() {
		
		this.textureWidth = 64;
        this.textureHeight = 64;
        
        this.bottom = new ModelRenderer(this, 0, 47);
        this.bottom.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.bottom.addBox(-8.0F, 7.0F, -2.0F, 12, 1, 10, 0.0F);
        this.top = new ModelRenderer(this, 0, 0);
        this.top.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.top.addBox(-8.0F, -2.0F, -2.0F, 12, 1, 10, 0.0F);
        this.sideLeft = new ModelRenderer(this, 0, 11);
        this.sideLeft.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.sideLeft.addBox(-8.0F, -1.0F, -2.0F, 1, 8, 10, 0.0F);
        this.sideRight = new ModelRenderer(this, 0, 29);
        this.sideRight.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.sideRight.addBox(3.0F, -1.0F, -2.0F, 1, 8, 10, 0.0F);
        this.back = new ModelRenderer(this, 34, 45);
        this.back.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.back.addBox(-7.0F, -1.0F, 7.0F, 10, 8, 1, 0.0F);
        this.controller = new ModelRenderer(this, 22, 11);
        this.controller.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.controller.addBox(4.0F, -2.0F, -4.0F, 4, 10, 12, 0.0F);
        
        this.plate = new ModelRenderer(this, 34, 0);
        this.plate.setRotationPoint(-2.0F, 23.0F, 2.6F);
        this.plate.addBox(-3.0F, -0.5F, -3.0F, 6, 1, 6, 0.0F);
        
        this.door = new ModelRenderer(this, 24, 33);
        this.door.setRotationPoint(-8.0F, 19.0F, -2.0F);
        this.door.addBox(0.0F, -5.0F, -2.0F, 12, 10, 2, 0.0F);
        this.handle = new ModelRenderer(this, 52, 33);
        this.handle.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.handle.addBox(11.0F, -3.0F, -3.0F, 1, 6, 1, 0.0F);
        
        this.door.addChild(this.handle);
	}

	public void render(float f5) {
        
       	this.bottom.render(f5);
       	this.top.render(f5);
        this.sideLeft.render(f5);
        this.sideRight.render(f5);
        this.back.render(f5);
        this.controller.render(f5);
		
		this.plate.render(f5);
		
		this.door.render(f5);
	}
}
