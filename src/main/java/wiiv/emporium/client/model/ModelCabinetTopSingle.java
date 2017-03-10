package wiiv.emporium.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

/**
 * kitchen_cabinet_doors_top - wiiv
 * Created using Tabula 4.1.1
 */
public class ModelCabinetTopSingle extends ModelBase {

	private static final ModelCabinetTopSingle INSTANCE = new ModelCabinetTopSingle();

	public ModelRenderer top;
	public ModelRenderer back;
	public ModelRenderer lside;
	public ModelRenderer rside;
	public ModelRenderer bottom;
	public ModelRenderer shelf1;
	public ModelRenderer shelf2;
	public ModelRenderer door = (new ModelRenderer(this, 0, 64)).setTextureSize(64, 128);
	public ModelRenderer handle;

	public static ModelCabinetTopSingle getInstance() {
		return INSTANCE;
	}

	public static ModelCabinetTopSingle newInstance() {
		return new ModelCabinetTopSingle();
	}

	public ModelCabinetTopSingle() {
		textureWidth = 64;
		textureHeight = 128;
		handle = new ModelRenderer(this, 0, 0);
		handle.setRotationPoint(0.0F, 0.0F, 0.0F);
		handle.addBox(13.0F, 0.0F, -1.0F, 1, 6, 1, 0.0F);
		back = new ModelRenderer(this, 0, 50);
		back.setRotationPoint(0.0F, 16.0F, 0.0F);
		back.addBox(-8.0F, -6.0F, 6.0F, 16, 12, 2, 0.0F);
		bottom = new ModelRenderer(this, 0, 34);
		bottom.setRotationPoint(0.0F, 16.0F, 0.0F);
		bottom.addBox(-8.0F, 6.0F, -6.0F, 16, 2, 14, 0.0F);
		top = new ModelRenderer(this, 0, 18);
		top.setRotationPoint(0.0F, 16.0F, 0.0F);
		top.addBox(-8.0F, -8.0F, -6.0F, 16, 2, 14, 0.0F);
		shelf2 = new ModelRenderer(this, 0, 102);
		shelf2.setRotationPoint(0.0F, 16.0F, 0.0F);
		shelf2.addBox(-7.0F, 0.0F, -6.0F, 14, 1, 12, 0.0F);
		rside = new ModelRenderer(this, 26, 78);
		rside.setRotationPoint(0.0F, 16.0F, 0.0F);
		rside.addBox(7.0F, -6.0F, -6.0F, 1, 12, 12, 0.0F);
		shelf1 = new ModelRenderer(this, 0, 115);
		shelf1.setRotationPoint(0.0F, 16.0F, 0.0F);
		shelf1.addBox(-7.0F, -6.0F, -6.0F, 14, 1, 12, 0.0F);
		lside = new ModelRenderer(this, 0, 78);
		lside.setRotationPoint(0.0F, 16.0F, 0.0F);
		lside.addBox(-8.0F, -6.0F, -6.0F, 1, 12, 12, 0.0F);
		door.setRotationPoint(-7.0F, 16.0F, -7.0F);
		door.addBox(-1.0F, -6.0F, 0.0F, 16, 13, 1, 0.0F);
		door.addChild(handle);
	}

	public void render(float f5) {
		back.render(f5);
		bottom.render(f5);
		top.render(f5);
		shelf2.render(f5);
		rside.render(f5);
		shelf1.render(f5);
		lside.render(f5);
		door.render(f5);
	}

}
