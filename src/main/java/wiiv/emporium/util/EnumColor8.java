package wiiv.emporium.util;

import net.minecraft.util.IStringSerializable;

/**
 * @author p455w0rd
 *
 */
public enum EnumColor8 implements IStringSerializable {
	ORANGE, MAGENTA, YELLOW, WHITE, PURPLE, BLUE, GREEN, RED;

	public int getMetadata() {
		return meta;
	}

	@Override
	public String toString() {
		return name;
	}

	public static EnumColor8 byMetadata(int meta) {
		if (meta < 0 || meta >= META_LOOKUP.length) {
			meta = 0;
		}

		return META_LOOKUP[meta];
	}

	@Override
	public String getName() {
		return name;
	}

	private final int meta;
	private final String name;
	private static final EnumColor8[] META_LOOKUP = new EnumColor8[values().length];

	private EnumColor8() {
		meta = ordinal();
		name = name().toLowerCase();
	}

	static {
		for (EnumColor8 color : values()) {
			META_LOOKUP[color.getMetadata()] = color;
		}
	}
}