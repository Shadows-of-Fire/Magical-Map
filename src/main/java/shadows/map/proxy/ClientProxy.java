package shadows.map.proxy;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import shadows.map.core.ModRegistry;

public class ClientProxy extends CommonProxy {
	@Override
	public void preInit(FMLPreInitializationEvent e) {
		super.preInit(e);
		ModRegistry.initModels();

	}
}
