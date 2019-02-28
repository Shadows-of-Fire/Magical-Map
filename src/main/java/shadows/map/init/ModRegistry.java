package shadows.map.init;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import shadows.map.MagicalMap;
import shadows.map.item.ItemMap;
import shadows.map.item.ItemUsedMap;
import shadows.placebo.util.PlaceboUtil;
import shadows.placebo.util.RecipeHelper;

@ObjectHolder(MagicalMap.MODID)
public class ModRegistry extends RecipeHelper {

	public static final ItemMap MAP = null;
	public static final ItemUsedMap USED_MAP = null;

	public ModRegistry() {
		super(MagicalMap.MODID, MagicalMap.MODNAME);
	}

	@SubscribeEvent
	public void items(Register<Item> e) {
		e.getRegistry().registerAll(PlaceboUtil.initItem(new ItemMap(), MagicalMap.MODID, "map"), PlaceboUtil.initItem(new ItemUsedMap(), MagicalMap.MODID, "used_map"));
	}

	@Override
	public void addRecipes() {
		addShaped(new ItemStack(ModRegistry.MAP), 3, 3, Items.PAPER, Items.EMERALD, Items.PAPER, Items.PAPER, Items.ENDER_EYE, Items.PAPER, Items.PAPER, Items.ENCHANTED_BOOK, Items.PAPER);
	}

	@SubscribeEvent
	public void mapRepair(AnvilUpdateEvent event) {
		if (event.getLeft().getItem() == ModRegistry.USED_MAP && event.getRight().getItem() == Items.ENDER_EYE) {
			event.setCost(1);
			event.setOutput(new ItemStack(ModRegistry.MAP));
			event.setMaterialCost(1);
		}
	}

}
