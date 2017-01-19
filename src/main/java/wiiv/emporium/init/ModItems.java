package wiiv.emporium.init;

import java.util.ArrayList;
import java.util.List;

import wiiv.emporium.api.IModelHolder;
import wiiv.emporium.item.ItemCookieChocolat;
import wiiv.emporium.item.ItemCookiePlain;
import wiiv.emporium.item.ItemCookieStrawberry;
import wiiv.emporium.item.ItemDebug;
import wiiv.emporium.item.ItemPasteChocolat;

public class ModItems {

	public static List<IModelHolder> ITEM_LIST = new ArrayList<IModelHolder>();
	
	public static ItemCookiePlain cookie_plain;
	public static ItemCookieChocolat cookie_chocolat;
	public static ItemCookieStrawberry cookie_strawberry;
	
	public static ItemPasteChocolat paste_chocolat;
	
	public static ItemDebug debug;

	public static void init() {
		//cookies
		ITEM_LIST.add(cookie_plain = new ItemCookiePlain());
		ITEM_LIST.add(cookie_chocolat = new ItemCookieChocolat());
		ITEM_LIST.add(cookie_strawberry = new ItemCookieStrawberry());
		
		ITEM_LIST.add(paste_chocolat = new ItemPasteChocolat());
		
		ITEM_LIST.add(debug = new ItemDebug());
	}

	public static void initModels() {
		for (IModelHolder item : ITEM_LIST) {
			item.initModel();
		}
	}
}
