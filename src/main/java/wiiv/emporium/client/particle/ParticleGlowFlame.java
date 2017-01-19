package wiiv.emporium.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import wiiv.emporium.Globals.Textures;
import wiiv.emporium.api.ICustomParticle;

/**
 * @author p455w0rd
 *
 */
public class ParticleGlowFlame extends Particle implements ICustomParticle {

	public float colorR = 0;
	public float colorG = 0;
	public float colorB = 0;
	public float initScale = 0;

	public ParticleGlowFlame(World worldIn, double x, double y, double z, double vx, double vy, double vz, float r, float g, float b, float scale) {
		super(worldIn, x, y, z, 0, 0, 0);
		canCollide = false;
		colorR = r;
		colorG = g;
		colorB = b;
		if (colorR > 1.0) {
			colorR = colorR / 255.0f;
		}
		if (colorG > 1.0) {
			colorG = colorG / 255.0f;
		}
		if (colorB > 1.0) {
			colorB = colorB / 255.0f;
		}
		setRBGColorF(colorR, colorG, colorB);
		particleMaxAge = 24;
		particleScale = scale;
		initScale = scale;
		motionX = vx;
		motionY = vy;
		motionZ = vz;
		particleAngle = 2.0f * (float) Math.PI;
		TextureAtlasSprite sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(Textures.GLOW_FLAME.toString());
		setParticleTexture(sprite);
	}

	@Override
	public void renderParticle(VertexBuffer buffer, Entity entity, float partialTicks, float rotX, float rotZ, float rotYZ, float rotXY, float rotXZ) {
		//GlStateManager.enableBlend();
		//GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE);
		super.renderParticle(buffer, entity, partialTicks, rotX, rotZ, rotYZ, rotXY, rotXZ);
	}

	@Override
	public int getBrightnessForRender(float pTicks) {
		return 255;
	}

	@Override
	public boolean isTransparent() {
		return true;
	}

	@Override
	public int getFXLayer() {
		return 1;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		if (worldObj.rand.nextInt(4) == 0) {
			particleAge++;
		}
		int rand1 = worldObj.rand.nextInt(2);
		int rand2 = worldObj.rand.nextInt(2);
		if (rand1 == 0) {
			motionX = (rand1 == 0 ? 0.0009d : -0.0009d) + (rand1 == 0 ? motionX : -motionX);
			motionZ = (rand2 == 0 ? 0.0009d : -0.0009d) + (rand2 == 0 ? motionZ : -motionZ);
		}
		else {
			motionX = (rand1 == 0 ? 0.0009d : -0.0009d) + (rand1 == 0 ? -motionX : motionX);
			motionZ = (rand2 == 0 ? 0.0009d : -0.0009d) + (rand2 == 0 ? -motionZ : motionZ);
		}
		float lifeCoeff = (float) particleAge / (float) particleMaxAge;
		particleScale = initScale - initScale * lifeCoeff;
		particleAlpha = 1.0f - lifeCoeff;
		//particleAlpha = 1.0f;
		prevParticleAngle = particleAngle;
		particleAngle += 1.0f;
		motionY += particleAge / 100 + 0.002d;
		colorG = Math.min(colorG, lifeCoeff);
	}

	@Override
	public boolean alive() {
		return particleAge < particleMaxAge;
	}

}
