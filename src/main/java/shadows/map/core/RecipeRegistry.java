package shadows.map.core;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;



public class RecipeRegistry {

	public static void init() {

		GameRegistry.addShapedRecipe(new ItemStack(ModRegistry.map), "PFP", "LML", "PEP", 'P', Items.PRISMARINE_SHARD, 'F', Items.RABBIT_FOOT, 'M', Items.MAP, 'L', new ItemStack(Items.DYE, 1, 4), 'E', Items.ENDER_EYE);
		
	}

	
}


/*
		GameRegistry.addShapelessRecipe(new ItemStack(ModRegistry.itemseed, 2), ModRegistry.itemessence, Items.APPLE, Items.WHEAT_SEEDS);
		GameRegistry.addShapedRecipe(new ItemStack(ModRegistry.soilcreator), " L ", "FSF", " L ", 'F', ModRegistry.itemseed, 'L', Items.EGG, 'S', Blocks.DIRT);

*/