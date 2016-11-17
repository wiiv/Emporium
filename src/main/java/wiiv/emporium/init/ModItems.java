package wiiv.emporium.init;

import java.util.*;

import wiiv.emporium.api.IModelHolder;
import wiiv.emporium.item.*;

public class ModItems {

	public static List<IModelHolder> ITEM_LIST = new ArrayList<IModelHolder>();
	public static ItemCookieChocolat cookie;
	public static ItemDebug debug;

	public static void init() {
		ITEM_LIST.add(cookie = new ItemCookieChocolat());
		ITEM_LIST.add(debug = new ItemDebug());
	}

	public static void initModels() {
		for (IModelHolder item : ITEM_LIST) {
			item.initModel();
		}
	}
}
