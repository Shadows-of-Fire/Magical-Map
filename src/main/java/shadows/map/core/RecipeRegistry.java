package shadows.map.core;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RecipeRegistry {

	public static void init() {

		GameRegistry.addShapedRecipe(new ItemStack(ModRegistry.map), "PEP", "PAP", "PBP", 'P', Items.PAPER, 'E',
				Items.EMERALD, 'A', Items.ENDER_EYE, 'B', Items.ENCHANTED_BOOK);
	}

}

/*
 * GameRegistry.addShapelessRecipe(new ItemStack(ModRegistry.itemseed, 2),
 * ModRegistry.itemessence, Items.APPLE, Items.WHEAT_SEEDS);
 * GameRegistry.addShapedRecipe(new ItemStack(ModRegistry.soilcreator), " L ",
 * "FSF", " L ", 'F', ModRegistry.itemseed, 'L', Items.EGG, 'S', Blocks.DIRT);
 * 
 */