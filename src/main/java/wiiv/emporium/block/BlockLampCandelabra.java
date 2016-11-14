package wiiv.emporium.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;

public class BlockLampCandelabra extends BlockBaseColorable {

	public BlockLampCandelabra(int color) {
		super(Material.ANVIL, "candelabra_lamp", 1.0F, color);
		setSoundType(SoundType.ANVIL);
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
