package shadows.map.init;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import shadows.map.MagicalMap;
import shadows.map.item.ItemMap;
import shadows.map.item.ItemUsedMap;

public class ModRegistry {
	public static final ItemMap MAP = new ItemMap();
	public static final ItemUsedMap USEDMAP = new ItemUsedMap();

	@SubscribeEvent
	public void items(Register<Item> e) {
		e.getRegistry().registerAll(MAP, USEDMAP);
	}

	@SubscribeEvent
	public void recipes(Register<IRecipe> e) {
		e.getRegistry().register(MagicalMap.HELPER.genShaped(new ItemStack(ModRegistry.MAP), 3, 3, Items.PAPER, Items.EMERALD, Items.PAPER, Items.PAPER, Items.ENDER_EYE, Items.PAPER, Items.PAPER, Items.ENCHANTED_BOOK, Items.PAPER).setRegistryName(MagicalMap.MODID, "map_recipe"));
	}

	@SubscribeEvent
	public void mapRepair(AnvilUpdateEvent event) {
		if (event.getLeft().getItem() == ModRegistry.USEDMAP && event.getRight().getItem() == Items.ENDER_EYE) {
			event.setCost(1);
			event.setOutput(new ItemStack(ModRegistry.MAP));
			event.setMaterialCost(1);
		}
	}

}
