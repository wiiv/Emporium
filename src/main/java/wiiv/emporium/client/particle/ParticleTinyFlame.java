package wiiv.emporium.client.particle;

import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFlame;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @author p455w0rd
 *
 */
public class ParticleTinyFlame extends ParticleFlame {

	public ParticleTinyFlame(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn) {
		super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
		motionX = motionX * 0.009999999776482582D + xSpeedIn;
		motionY = motionY * 0.009999999776482582D + ySpeedIn;
		motionZ = motionZ * 0.009999999776482582D + zSpeedIn;
		posX += (rand.nextFloat() - rand.nextFloat()) * 0.05F;
		posY += (rand.nextFloat() - rand.nextFloat()) * 0.05F;
		posZ += (rand.nextFloat() - rand.nextFloat()) * 0.05F;
		particleRed = 1.0F;
		particleGreen = 1.0F;
		particleBlue = 1.0F;
		particleMaxAge = (int) (8.0D / (Math.random() * 0.8D + 0.2D)) + 4;
		setParticleTextureIndex(48);
	}

	@Override
	public void moveEntity(double x, double y, double z) {
		setEntityBoundingBox(getEntityBoundingBox().offset(x, y, z));
		resetPositionToBB();
	}

	/**
	 * Renders the particle
	 */
	@Override
	public void renderParticle(VertexBuffer worldRendererIn, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
		float f = particleTextureIndexX / 16.0F;
		float f1 = f + 0.0624375F;
		float f2 = particleTextureIndexY / 16.0F;
		float f3 = f2 + 0.0624375F;
		float f4 = 0.1F * particleScale / 2;

		if (particleTexture != null) {
			f = particleTexture.getMinU();
			f1 = particleTexture.getMaxU();
			f2 = particleTexture.getMinV();
			f3 = particleTexture.getMaxV();
		}

		float f5 = (float) (prevPosX + (posX - prevPosX) * partialTicks - interpPosX);
		float f6 = (float) (prevPosY + (posY - prevPosY) * partialTicks - interpPosY);
		float f7 = (float) (prevPosZ + (posZ - prevPosZ) * partialTicks - interpPosZ);
		int i = getBrightnessForRender(partialTicks);
		int j = i >> 16 & 65535;
		int k = i & 65535;
		Vec3d[] avec3d = new Vec3d[] {
				new Vec3d(-rotationX * f4 - rotationXY * f4, -rotationZ * f4, -rotationYZ * f4 - rotationXZ * f4), new Vec3d(-rotationX * f4 + rotationXY * f4, rotationZ * f4, -rotationYZ * f4 + rotationXZ * f4), new Vec3d(rotationX * f4 + rotationXY * f4, rotationZ * f4, rotationYZ * f4 + rotationXZ * f4), new Vec3d(rotationX * f4 - rotationXY * f4, -rotationZ * f4, rotationYZ * f4 - rotationXZ * f4)
		};

		if (field_190014_F != 0.0F) {
			float f8 = field_190014_F + (field_190014_F - field_190015_G) * partialTicks;
			float f9 = MathHelper.cos(f8 * 0.5F);
			float f10 = MathHelper.sin(f8 * 0.5F) * (float) field_190016_K.xCoord;
			float f11 = MathHelper.sin(f8 * 0.5F) * (float) field_190016_K.yCoord;
			float f12 = MathHelper.sin(f8 * 0.5F) * (float) field_190016_K.zCoord;
			Vec3d vec3d = new Vec3d(f10, f11, f12);

			for (int l = 0; l < 4; ++l) {
				avec3d[l] = vec3d.scale(2.0D * avec3d[l].dotProduct(vec3d)).add(avec3d[l].scale(f9 * f9 - vec3d.dotProduct(vec3d))).add(vec3d.crossProduct(avec3d[l]).scale(2.0F * f9));
			}
		}

		worldRendererIn.pos(f5 + avec3d[0].xCoord, f6 + avec3d[0].yCoord, f7 + avec3d[0].zCoord).tex(f1, f3).color(particleRed, particleGreen, particleBlue, particleAlpha).lightmap(j, k).endVertex();
		worldRendererIn.pos(f5 + avec3d[1].xCoord, f6 + avec3d[1].yCoord, f7 + avec3d[1].zCoord).tex(f1, f2).color(particleRed, particleGreen, particleBlue, particleAlpha).lightmap(j, k).endVertex();
		worldRendererIn.pos(f5 + avec3d[2].xCoord, f6 + avec3d[2].yCoord, f7 + avec3d[2].zCoord).tex(f, f2).color(particleRed, particleGreen, particleBlue, particleAlpha).lightmap(j, k).endVertex();
		worldRendererIn.pos(f5 + avec3d[3].xCoord, f6 + avec3d[3].yCoord, f7 + avec3d[3].zCoord).tex(f, f3).color(particleRed, particleGreen, particleBlue, particleAlpha).lightmap(j, k).endVertex();
	}

	@Override
	public int getBrightnessForRender(float p_189214_1_) {
		float f = (particleAge + p_189214_1_) / particleMaxAge;
		f = MathHelper.clamp_float(f, 0.0F, 1.0F);
		int i = super.getBrightnessForRender(p_189214_1_);
		int j = i & 255;
		int k = i >> 16 & 255;
		j = j + (int) (f * 15.0F * 16.0F);

		if (j > 240) {
			j = 240;
		}

		return j | k << 16;
	}

	@Override
	public void onUpdate() {
		prevPosX = posX;
		prevPosY = posY;
		prevPosZ = posZ;

		if (particleAge++ >= particleMaxAge) {
			setExpired();
		}

		moveEntity(motionX, motionY, motionZ);
		motionX *= 0.9599999785423279D;
		motionY *= 0.9599999785423279D;
		motionZ *= 0.9599999785423279D;

		if (isCollided) {
			motionX *= 0.699999988079071D;
			motionZ *= 0.699999988079071D;
		}
	}

	@SideOnly(Side.CLIENT)
	public static class Factory implements IParticleFactory {
		@Override
		public Particle getEntityFX(int particleID, World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int... p_178902_15_) {
			return new ParticleTinyFlame(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
		}
	}

}
