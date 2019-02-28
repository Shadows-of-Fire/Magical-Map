package shadows.map.client;

import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import shadows.map.MagicalMap;
import shadows.map.init.ModRegistry;
import shadows.placebo.util.PlaceboUtil;

@EventBusSubscriber(modid = MagicalMap.MODID, value = Side.CLIENT)
public class ClientHandler {

	@SubscribeEvent
	public static void onModelRegistry(ModelRegistryEvent e) {
		PlaceboUtil.sMRL(ModRegistry.MAP, 0, "inventory");
		PlaceboUtil.sMRL(ModRegistry.USED_MAP, 0, "inventory");
	}

}
