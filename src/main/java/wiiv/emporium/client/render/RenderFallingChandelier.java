package wiiv.emporium.client.render;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.*;
import wiiv.emporium.Globals;
import wiiv.emporium.entity.EntityFallingChandelier;

@SideOnly(Side.CLIENT)
public class RenderFallingChandelier extends Render<EntityFallingChandelier> {
	public RenderFallingChandelier(RenderManager renderManagerIn) {
		super(renderManagerIn);
		shadowSize = 0.5F;
	}

	/**
	 * Renders the desired {@code T} type Entity.
	 */
	@Override
	public void doRender(EntityFallingChandelier entity, double x, double y, double z, float entityYaw, float partialTicks) {
		if (entity.getBlock() != null) {
			IBlockState iblockstate = entity.getBlock();

			//if (iblockstate.getRenderType() == EnumBlockRenderType.MODEL) {
			World world = entity.getWorldObj();
			if (world.rand.nextInt(20) == 0) {
				world.spawnParticle(EnumParticleTypes.FLAME, entity.posX - 0.4D, entity.posY + 0.8D, entity.posZ + 0.1D, 0.0D, 0.0D, 0.0D, new int[0]);
				world.spawnParticle(EnumParticleTypes.FLAME, entity.posX + 0.4D, entity.posY + 0.8D, entity.posZ + 0.1D, 0.0D, 0.0D, 0.0D, new int[0]);
				world.spawnParticle(EnumParticleTypes.FLAME, entity.posX, entity.posY + 0.8D, entity.posZ - 0.4D, 0.0D, 0.0D, 0.0D, new int[0]);
				world.spawnParticle(EnumParticleTypes.FLAME, entity.posX, entity.posY + 0.8D, entity.posZ + 0.4D, 0.0D, 0.0D, 0.0D, new int[0]);
			}

			if (iblockstate != world.getBlockState(new BlockPos(entity)) && iblockstate.getRenderType() != EnumBlockRenderType.INVISIBLE) {
				bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
				GlStateManager.pushMatrix();
				GlStateManager.disableLighting();
				Tessellator tessellator = Tessellator.getInstance();
				VertexBuffer vertexbuffer = tessellator.getBuffer();

				if (renderOutlines) {
					GlStateManager.enableColorMaterial();
					GlStateManager.enableOutlineMode(getTeamColor(entity));
				}

				vertexbuffer.begin(7, DefaultVertexFormats.BLOCK);
				BlockPos blockpos = new BlockPos(entity.posX, entity.getEntityBoundingBox().maxY, entity.posZ);
				GlStateManager.translate((float) (x - blockpos.getX() - 0.5D), (float) (y - blockpos.getY()), (float) (z - blockpos.getZ() - 0.5D));
				BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
				blockrendererdispatcher.getBlockModelRenderer().renderModel(world, blockrendererdispatcher.getModelForState(iblockstate), iblockstate, blockpos, vertexbuffer, false, MathHelper.getPositionRandom(entity.getOrigin()));
				tessellator.draw();

				if (renderOutlines) {
					GlStateManager.disableOutlineMode();
					GlStateManager.disableColorMaterial();
				}

				GlStateManager.enableLighting();
				GlStateManager.popMatrix();

				super.doRender(entity, x, y, z, entityYaw, partialTicks);

			}
			//}
		}
	}

	/**
	 * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
	 */
	@Override
	protected ResourceLocation getEntityTexture(EntityFallingChandelier entity) {

		return new ResourceLocation(Globals.MOD_ID, "textures/blocks/lamp_chandelier_crystal_black.png/");
	}
}