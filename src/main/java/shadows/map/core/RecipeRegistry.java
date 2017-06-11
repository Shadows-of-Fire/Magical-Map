package shadows.map.core;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import shadows.map.util.RecipeHelper;

public class RecipeRegistry {

	public static void init() {
		Item P = Items.PAPER;
		Item E = Items.EMERALD;
		Item A = Items.ENDER_EYE;
		Item B = Items.ENCHANTED_BOOK;

		RecipeHelper.addShaped(ModRegistry.map, 3, 3, new Object[] { P, E, P, P, A, P, P, B, P });
	}

}
