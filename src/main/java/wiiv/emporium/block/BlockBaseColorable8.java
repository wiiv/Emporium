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
package wiiv.emporium.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.*;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.*;
import wiiv.emporium.Emporium;
import wiiv.emporium.api.IModelHolder;
import wiiv.emporium.util.EnumColor16;
import wiiv.emporium.util.EnumColor8;

/**
 * @author p455w0rd
 *
 */
public class BlockBaseColorable8 extends Block implements IModelHolder {

	public BlockBaseColorable8(Material materialIn, String nameIn, float hardness, int color) {
		super(materialIn);
		String name = nameIn + "_" + EnumColor8.byMetadata(color);
		setUnlocalizedName(name);
		setRegistryName(name);
		setHardness(hardness);
		GameRegistry.register(this);
		GameRegistry.register(new ItemBlock(this), getRegistryName());
		setCreativeTab(Emporium.CREATIVE_TAB);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}

}
