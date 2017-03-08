package wiiv.emporium.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

/**
 * @author p455w0rd
 *
 */
public class ModelCookieJar extends ModelBase {

	public ModelRenderer lid;
	public ModelRenderer glass;
	public ModelRenderer bottom;

	public ModelCookieJar() {
		textureWidth = 64;
		textureHeight = 32;
		lid = new ModelRenderer(this, 32, 0);
		lid.setRotationPoint(0.0F, 16.0F, 0.0F);
		lid.addBox(-3.0F, -4.0001F, -3.0F, 6, 2, 6, 0.0F);
		glass = new ModelRenderer(this, 0, 10);
		glass.setRotationPoint(0.0F, 16.0F, 0.0F);
		glass.addBox(-4.0F, -2.0F, -4.0F, 8, 8, 8, 0.0F);
		bottom = new ModelRenderer(this, 0, 0);
		bottom.setRotationPoint(0.0F, 16.0F, 0.0F);
		bottom.addBox(-4.0F, 6.0F, -4.0F, 8, 2, 8, 0.0F);
	}

	public void render(float scale) {
		lid.render(scale);
		glass.render(scale);
		bottom.render(scale);
	}

}