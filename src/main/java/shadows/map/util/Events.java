package shadows.map.util;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import shadows.map.core.ModRegistry;

public class Events {

	@SubscribeEvent
	public void mapRepair(AnvilUpdateEvent event) {
		if (event.getLeft().getItem() == ModRegistry.usedMap && event.getRight().getItem() == Items.ENDER_EYE) {
			event.setCost(1);
			event.setOutput(new ItemStack(ModRegistry.map));
			event.setMaterialCost(1);
		}
	}

}
