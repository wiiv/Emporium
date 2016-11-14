package wiiv.emporium.block;

import java.util.List;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.*;
import wiiv.emporium.Globals;
import wiiv.emporium.block.tile.TileJar;
import wiiv.emporium.item.ItemCookie;
import wiiv.emporium.render.tile.RenderTileJar;

public class BlockLampCandleTall extends BlockBaseColorable {
	
	private static final AxisAlignedBB BOUNDING_BOX = new AxisAlignedBB((0.0625D * 7), 0.0D, (0.0625D * 7), (0.0625D * 9), (0.0625D * 16), (0.0625D * 9));
	private static final AxisAlignedBB COLLISION_BOX = new AxisAlignedBB((0.0625D * 7), 0.0D, (0.0625D * 7), (0.0625D * 9), (0.0625D * 16), (0.0625D * 9));

	public BlockLampCandleTall(int color) {
		super(Material.ANVIL, "tall_candle", 1.0F, color);
		setSoundType(SoundType.METAL);
		setLightLevel(0.625F);
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {

		return BOUNDING_BOX;
	}
	
	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, Entity entityIn) {
		super.addCollisionBoxToList(pos, entityBox, collidingBoxes, COLLISION_BOX);
	}
}
