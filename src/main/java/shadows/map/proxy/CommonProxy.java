package shadows.map.proxy;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import shadows.map.core.ConfigFile;
import shadows.map.core.ModRegistry;
import shadows.map.core.RecipeRegistry;
import shadows.map.util.Events;

public class CommonProxy {

	public static Configuration config;

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		config = new Configuration(e.getSuggestedConfigurationFile());
		ConfigFile.syncConfig();
		EnumHelper.addToolMaterial("immolation", 9, 4096, 0.6f, 9.3f, 72);
		ModRegistry.init();
		RecipeRegistry.init();
	}

	public void init(FMLInitializationEvent e) {
		MinecraftForge.EVENT_BUS.register(new Events());
	}

	public void postInit(FMLPostInitializationEvent e) {

	}

}
