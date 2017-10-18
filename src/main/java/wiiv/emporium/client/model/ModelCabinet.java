package wiiv.emporium.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

/**
 * kitchen_cabinet_doors_top - wiiv
 * Created using Tabula 4.1.1
 */
public class ModelCabinet extends ModelBase {

	private static final ModelCabinet INSTANCE = new ModelCabinet();
	
	//ceiling cabinet top
    public ModelRenderer topc;
    //floor cabinet top
    public ModelRenderer topf;
    
    public ModelRenderer back;
    public ModelRenderer lside;
    public ModelRenderer rside;
    public ModelRenderer bottom;
    public ModelRenderer shelft;
    public ModelRenderer shelfb;
    
    //ceiling cabinet doors
    public ModelRenderer ldoor;
    public ModelRenderer rdoor;
    public ModelRenderer lhandle;
    public ModelRenderer rhandle;
    
    //floor cabinet doors
    public ModelRenderer fldoor;
    public ModelRenderer frdoor;
    public ModelRenderer flhandle;
    public ModelRenderer frhandle;
    
    //ceiling cabinet doors
    public ModelRenderer ldoorc= (new ModelRenderer(this, 0, 64)).setTextureSize(64, 128);
    public ModelRenderer lhandlec;
    public ModelRenderer rdoorc = (new ModelRenderer(this, 18, 64)).setTextureSize(64, 128);
    public ModelRenderer rhandlec;
    
    //floor cabinet door
    public ModelRenderer ldoorf;
    public ModelRenderer lhandlef;
    public ModelRenderer rdoorf;
    public ModelRenderer rhandlef;
    

	public static ModelCabinet getInstance() {
		return INSTANCE;
	}

	public static ModelCabinet newInstance() {
		return new ModelCabinet();
	}

	public ModelCabinet() {
		this.textureWidth = 64;
        this.textureHeight = 128;
        
        //ceiling cabinet top
        this.topc = new ModelRenderer(this, 0, 18);
        this.topc.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.topc.addBox(-8.0F, -8.0F, -6.0F, 16, 2, 14, 0.0F);
        
        //floor cabinet top
        this.topf = new ModelRenderer(this, 0, 0);
        this.topf.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.topf.addBox(-8.0F, -8.0F, -8.0F, 16, 2, 16, 0.0F);
        
        this.back = new ModelRenderer(this, 0, 50);
        this.back.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.back.addBox(-8.0F, -6.0F, 6.0F, 16, 12, 2, 0.0F);
        this.lside = new ModelRenderer(this, 0, 90);
        this.lside.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.lside.addBox(-8.0F, -6.0F, -6.0F, 1, 12, 12, 0.0F);
        this.rside = new ModelRenderer(this, 26, 90);
        this.rside.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.rside.addBox(7.0F, -6.0F, -6.0F, 1, 12, 12, 0.0F);
        this.bottom = new ModelRenderer(this, 0, 34);
        this.bottom.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.bottom.addBox(-8.0F, 6.0F, -6.0F, 16, 2, 14, 0.0F);
        this.shelft = new ModelRenderer(this, 0, 114);
        this.shelft.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.shelft.addBox(-7.0F, -6.0F, -6.0F, 14, 1, 12, 0.0F);
        this.shelfb = new ModelRenderer(this, 0, 114);
        this.shelfb.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.shelfb.addBox(-7.0F, 0.0F, -6.0F, 14, 1, 12, 0.0F);
        
        //ceiling cabinet doors
        this.ldoorc = new ModelRenderer(this, 0, 64);
        this.ldoorc.setRotationPoint(-7.0F, 16.0F, -7.0F);
        this.ldoorc.addBox(-1.0F, -6.0F, 0.0F, 8, 12, 1, 0.0F);
        this.lhandlec = new ModelRenderer(this, 0, 0);
        this.lhandlec.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.lhandlec.addBox(5.0F, 1.0F, -1.0F, 1, 4, 1, 0.0F);
        this.rdoorc = new ModelRenderer(this, 18, 64);
        this.rdoorc.setRotationPoint(7.0F, 16.0F, -7.0F);
        this.rdoorc.addBox(-7.0F, -6.0F, 0.0F, 8, 12, 1, 0.0F);
        this.rhandlec = new ModelRenderer(this, 0, 0);
        this.rhandlec.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rhandlec.addBox(-6.0F, 1.0F, -1.0F, 1, 4, 1, 0.0F);
        
      	//floor cabinet doors
      	this.ldoorf = new ModelRenderer(this, 0, 64);
        this.ldoorf.setRotationPoint(-7.0F, 16.0F, -7.0F);
        this.ldoorf.addBox(-1.0F, -6.0F, 0.0F, 8, 12, 1, 0.0F);
        this.lhandlef = new ModelRenderer(this, 0, 0);
        this.lhandlef.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.lhandlef.addBox(5.0F, -5.0F, -1.0F, 1, 4, 1, 0.0F);
        this.rdoorf = new ModelRenderer(this, 18, 64);
        this.rdoorf.setRotationPoint(7.0F, 16.0F, -7.0F);
        this.rdoorf.addBox(-7.0F, -6.0F, 0.0F, 8, 12, 1, 0.0F);
        this.rhandlef = new ModelRenderer(this, 0, 0);
        this.rhandlef.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rhandlef.addBox(-6.0F, -5.0F, -1.0F, 1, 4, 1, 0.0F);
        
        //floor cabinet door
        this.rdoor = new ModelRenderer(this, 0, 77);
        this.rdoor.setRotationPoint(7.0F, 16.0F, -7.0F);
        this.rdoor.addBox(-15.0F, -6.0F, 0.0F, 16, 12, 1, 0.0F);
        this.rhandle = new ModelRenderer(this, 4, 0);
        this.rhandle.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rhandle.addBox(-14.0F, -1.0F, -1.0F, 1, 6, 1, 0.0F);
        this.ldoor = new ModelRenderer(this, 0, 77);
        this.ldoor.setRotationPoint(-7.0F, 16.0F, -7.0F);
        this.ldoor.addBox(-1.0F, -6.0F, 0.0F, 16, 12, 1, 0.0F);
        this.lhandle = new ModelRenderer(this, 4, 0);
        this.lhandle.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.lhandle.addBox(13.0F, -1.0F, -1.0F, 1, 6, 1, 0.0F);
        
        this.ldoorc.addChild(this.lhandlec);
        this.rdoorc.addChild(this.rhandlec);
        this.ldoorf.addChild(this.lhandlef);
        this.rdoorf.addChild(this.rhandlef);
        this.ldoor.addChild(this.lhandle);
        this.rdoor.addChild(this.rhandle);
	}

	public void render(float f5) {
		//this.topc.render(f5);
		this.topf.render(f5);
		
		this.back.render(f5);
		this.lside.render(f5);
		this.rside.render(f5);
		this.bottom.render(f5);
		this.shelft.render(f5);
		this.shelfb.render(f5);
		
		//this.ldoorc.render(f5);
		//this.rdoorc.render(f5);
		
        this.ldoorf.render(f5);
        this.rdoorf.render(f5);

        //this.ldoor.render(f5);
        
        //this.rdoor.render(f5);
		
		//current setup renders a ceiling cabinet with double doors
	}
}
