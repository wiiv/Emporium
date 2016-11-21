package wiiv.emporium.block;

import java.util.List;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockLampGlowrescentCube extends BlockBaseColorable16 {
	
	private static final AxisAlignedBB BOUNDING_BOX = new AxisAlignedBB((0.0625D * 3), 0.0D, (0.0625D * 3), (0.0625D * 13), (0.0625D * 12), (0.0625D * 13));
	private static final AxisAlignedBB COLLISION_BOX = new AxisAlignedBB((0.0625D * 3), 0.0D, (0.0625D * 3), (0.0625D * 13), (0.0625D * 12), (0.0625D * 13));


	public BlockLampGlowrescentCube(int color) {
		super(Material.CIRCUITS, "glowrescent_cube_lamp", 1.0F, color);
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
    public boolean canRenderInLayer(BlockRenderLayer layer) {
        return layer == BlockRenderLayer.SOLID || layer == BlockRenderLayer.TRANSLUCENT;
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
