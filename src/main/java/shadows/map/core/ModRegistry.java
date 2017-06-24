package shadows.map.core;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import shadows.map.common.ItemMap;
import shadows.map.common.ItemUsedMap;
import shadows.map.util.RecipeHelper;

public class ModRegistry {
	public static final ItemMap MAP = new ItemMap();
	public static final ItemUsedMap USEDMAP = new ItemUsedMap();

	public static void initRecipes() {
		Item P = Items.PAPER;
		Item E = Items.EMERALD;
		Item A = Items.ENDER_EYE;
		Item B = Items.ENCHANTED_BOOK;

		RecipeHelper.addShaped(ModRegistry.MAP, 3, 3, P, E, P, P, A, P, P, B, P);
	}

	@SubscribeEvent
	public void onItemRegistry(RegistryEvent.Register<Item> event) {
		IForgeRegistry<Item> reg = event.getRegistry();
		reg.registerAll(MAP, USEDMAP);
	}

	@SubscribeEvent
	public void onRecipeRegistry(RegistryEvent.Register<IRecipe> event) {
		initRecipes();
		IForgeRegistry<IRecipe> reg = event.getRegistry();
		for (IRecipe rec : RecipeHelper.recipeList) {
			reg.register(rec);
		}
	}

}
