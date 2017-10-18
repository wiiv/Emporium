package wiiv.emporium.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

/**
 * kitchen_cabinet_doors_top - wiiv
 * Created using Tabula 4.1.1
 */
public class ModelApplianceRefrigerator extends ModelBase {

	private static final ModelApplianceRefrigerator INSTANCE = new ModelApplianceRefrigerator();
	
	public ModelRenderer bottom;
    public ModelRenderer top;
    public ModelRenderer sideLeft;
    public ModelRenderer sideRight;
    public ModelRenderer back;
    
    public ModelRenderer door= (new ModelRenderer(this, 0, 64)).setTextureSize(64, 128);
    public ModelRenderer handle;
    
    public ModelRenderer shelf_1;
    public ModelRenderer shelf_2;
    public ModelRenderer shelf_3;
    public ModelRenderer shelf_4;
    public ModelRenderer shelf_5;
    
	public static ModelApplianceRefrigerator getInstance() {
		return INSTANCE;
	}

	public static ModelApplianceRefrigerator newInstance() {
		return new ModelApplianceRefrigerator();
	}

	public ModelApplianceRefrigerator() {
		
		this.textureWidth = 64;
        this.textureHeight = 128;
        
        this.bottom = new ModelRenderer(this, 0, 82);
        this.bottom.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.bottom.addBox(-8.0F, 3.0F, -7.0F, 16, 5, 15, 0.0F);
        this.top = new ModelRenderer(this, 0, 0);
        this.top.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.top.addBox(-8.0F, -24.0F, -8.0F, 16, 3, 16, 0.0F);
        this.sideRight = new ModelRenderer(this, 36, 44);
        this.sideRight.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.sideRight.addBox(7.0F, -21.0F, -6.0F, 1, 24, 13, 0.0F);
		this.sideLeft = new ModelRenderer(this, 0, 19);
        this.sideLeft.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.sideLeft.addBox(-8.0F, -21.0F, -6.0F, 1, 24, 13, 0.0F);
        this.back = new ModelRenderer(this, 28, 19);
        this.back.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.back.addBox(-8.0F, -21.0F, 7.0F, 16, 24, 1, 0.0F);
        
        this.shelf_1 = new ModelRenderer(this, -12, 102);
        this.shelf_1.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.shelf_1.addBox(-7.5F, -17.0F, -6.0F, 15, 0, 12, 0.0F);
		this.shelf_2 = new ModelRenderer(this, -12, 102);
        this.shelf_2.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.shelf_2.addBox(-7.5F, -13.0F, -6.0F, 15, 0, 12, 0.0F);
		this.shelf_3 = new ModelRenderer(this, -12, 102);
        this.shelf_3.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.shelf_3.addBox(-7.5F, -9.0F, -6.0F, 15, 0, 12, 0.0F);
        this.shelf_4 = new ModelRenderer(this, -12, 102);
        this.shelf_4.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.shelf_4.addBox(-7.5F, -5.0F, -6.0F, 15, 0, 12, 0.0F);
        this.shelf_5 = new ModelRenderer(this, -12, 102);
        this.shelf_5.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.shelf_5.addBox(-7.5F, -1.0F, -6.0F, 15, 0, 12, 0.0F);
        
        this.door = new ModelRenderer(this, 0, 56);
        this.door.setRotationPoint(-8.0F, 8.0F, -6.0F);
        this.door.addBox(0.0F, -13.0F, -2.0F, 16, 24, 2, 0.0F);
        this.handle = new ModelRenderer(this, 28, 44);
        this.handle.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.handle.addBox(15.0F, -3.0F, -3.0F, 1, 6, 1, 0.0F);
        
        this.door.addChild(this.handle);
	}

	public void render(float f5) {
        
       	this.bottom.render(f5);
       	this.top.render(f5);
        this.sideLeft.render(f5);
        this.sideRight.render(f5);
        this.back.render(f5);
		
		this.shelf_1.render(f5);
		this.shelf_2.render(f5);
        this.shelf_3.render(f5);
        this.shelf_4.render(f5);
		this.shelf_5.render(f5);
		
		this.door.render(f5);
	}
}
