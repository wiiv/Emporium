package wiiv.emporium.common.lib;

public class LibMisc {
	
	public static final String MOD_ID = "emporium";
	public static final String MOD_NAME = "Emporium";
	public static final String BUILD = "GRADLE:BUILD";
	public static final String VERSION = "GRADLE:VERSION-" + BUILD;
	public static final String ACCEPTED_VERSIONS = "[1.10.2]";
	public static final String DEPENDENCIES = "";

	// Network Contants
	public static final String NETWORK_CHANNEL = MOD_ID;

	// Proxy Constants
	public static final String PROXY_COMMON = "wiiv.emporium.common.core.proxy.CommonProxy";
	public static final String PROXY_CLIENT = "wiiv.emporium.client.core.proxy.ClientProxy";
	//public static final String GUI_FACTORY = "wiiv.emporium.client.core.proxy.GuiFactory";
	
	
	//Items
	
	public static enum DecItems {
		
		COOKIE("cookie", "ItemCookie"),
		DEBUG("debug", "ItemDebug");
		//COOKIECHOC("cookie_chocolat", "ItemCookieChocolat");
		
		private String unlocalizedName;
		private String registryName;
		
		DecItems(String unlocalizedName, String registryName) {
			this.unlocalizedName = unlocalizedName;
			this.registryName = registryName;
		}
		
		public String getUnlocalizedName() {
			return unlocalizedName;
		}
		
		public String getRegistryName() {
			return registryName;
		}
	}
	
	public static enum DecBlocks {
		
		CHEESE("cheese", "BlockCheese"),
		JAR("jar", "BlockJar"),
		LAMP_LIBRARY("lamp_library", "BlockLampLibrary"), 
		LAMP_CHANDELIER("lamp_chandelier", "BlockLampChandelier"), 
		LAMP_CANDELABRA("lamp_candelabra", "BlockLampCandelabra");
		
		private String unlocalizedName;
		private String registryName;
		
		DecBlocks(String unlocalizedName, String registryName) {
			this.unlocalizedName = unlocalizedName;
			this.registryName = registryName;
		}
		
		public String getUnlocalizedName() {
			return unlocalizedName;
		}
		
		public String getRegistryName() {
			return registryName;
		}
	}
}
