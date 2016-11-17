package wiiv.emporium.util;

import net.minecraft.util.IStringSerializable;

/**
 * @author p455w0rd
 *
 */
public enum EnumColor16 implements IStringSerializable {
	WHITE, ORANGE, MAGENTA, LIGHT_BLUE, YELLOW, LIME, PINK, GRAY, SILVER, CYAN, PURPLE, BLUE, BROWN, GREEN, RED, BLACK;

	public int getMetadata() {
		return meta;
	}

	@Override
	public String toString() {
		return name;
	}

	public static EnumColor16 byMetadata(int meta) {
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
	private static final EnumColor16[] META_LOOKUP = new EnumColor16[values().length];

	private EnumColor16() {
		meta = ordinal();
		name = name().toLowerCase();
	}

	static {
		for (EnumColor16 color : values()) {
			META_LOOKUP[color.getMetadata()] = color;
		}
	}
}