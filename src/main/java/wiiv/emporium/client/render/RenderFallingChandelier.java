/*
 * This file is part of p455w0rd's Things.
 * Copyright (c) 2016, p455w0rd (aka TheRealp455w0rd), All rights reserved
 * unless
 * otherwise stated.
 *
 * p455w0rd's Things is free software: you can redistribute it and/or modify
 * it under the terms of the MIT License.
 *
 * p455w0rd's Things is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * MIT License for more details.
 *
 * You should have received a copy of the MIT License
 * along with p455w0rd's Things. If not, see
 * <https://opensource.org/licenses/MIT>.
 */
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
import wiiv.emporium.entity.EntityFallingChandelier;

/**
 * @author p455w0rd
 *
 */
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

			if (iblockstate.getRenderType() == EnumBlockRenderType.MODEL) {
				World world = entity.getWorldObj();

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
			}
		}
	}

	/**
	 * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
	 */
	@Override
	protected ResourceLocation getEntityTexture(EntityFallingChandelier entity) {
		return TextureMap.LOCATION_BLOCKS_TEXTURE;
	}
}