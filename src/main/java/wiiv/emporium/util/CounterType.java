package wiiv.emporium.util;

import net.minecraft.util.IStringSerializable;

/**
 * @author p455w0rd
 *
 */
public enum CounterType implements IStringSerializable {
		SINGLE, CENTER, LEFT, RIGHT, CORNER;

	@Override
	public String getName() {
		return toString().toLowerCase();
	}
}