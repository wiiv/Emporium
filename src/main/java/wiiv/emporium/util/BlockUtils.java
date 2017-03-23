package wiiv.emporium.util;

import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;

/**
 * @author p455w0rd
 *
 */
public class BlockUtils {

	public static EnumFacing getFacing(IBlockState state, PropertyDirection dirProp) {
		return state.getValue(dirProp);
	}

}
