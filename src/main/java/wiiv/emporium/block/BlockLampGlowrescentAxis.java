package wiiv.emporium.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;

public class BlockLampGlowrescentAxis extends BlockBaseColorable16 {

	public BlockLampGlowrescentAxis(int color) {
		super(Material.CIRCUITS, "glowrescent_axis_lamp", 1.0F, color);
		setSoundType(SoundType.METAL);
		setLightLevel(0.75F);
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
	public BlockRenderLayer getBlockLayer() {

		return BlockRenderLayer.TRANSLUCENT;
	}

}
