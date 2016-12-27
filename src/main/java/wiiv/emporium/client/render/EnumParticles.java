package wiiv.emporium.client.render;

/**
 * @author p455w0rd
 *
 */
public enum EnumParticles {
	TINY_FLAME(RenderType.VANILLA), GLOW_FLAME(RenderType.CUSTOM);

	RenderType renderer;

	EnumParticles(RenderType renderer) {
		this.renderer = renderer;
	}

	public RenderType getRenderer() {
		return renderer;
	}

	public static enum RenderType {
		VANILLA, CUSTOM
	}
}
