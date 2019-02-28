package shadows.map;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import shadows.map.init.ModRegistry;

@Mod(modid = MagicalMap.MODID, version = MagicalMap.VERSION, name = MagicalMap.MODNAME, dependencies = "required-after:placebo@[2.0.0,)")
public class MagicalMap {

	public static final String MODID = "magicalmap";
	public static final String MODNAME = "Magical Map";
	public static final String VERSION = "4.0.0";

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new ModRegistry());
	}
}
