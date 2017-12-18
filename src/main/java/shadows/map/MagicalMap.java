package shadows.map;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import shadows.map.init.ModRegistry;
import shadows.map.proxy.CommonProxy;
import shadows.placebo.registry.RegistryInformation;
import shadows.placebo.util.RecipeHelper;

@Mod(modid = MagicalMap.MODID, version = MagicalMap.VERSION, name = MagicalMap.MODNAME, dependencies = "required-after:placebo@[1.1.0,)")
public class MagicalMap {
	public static final String MODID = "magicalmap";
	public static final String MODNAME = "Magical Map";
	public static final String VERSION = "3.0.0";

	@SidedProxy(clientSide = "shadows.map.proxy.ClientProxy", serverSide = "shadows.map.proxy.CommonProxy")
	public static CommonProxy proxy;

	public static final RegistryInformation INFO = new RegistryInformation(MODID, CreativeTabs.MISC);
	public static final RecipeHelper HELPER = new RecipeHelper(MODID, MODNAME, INFO.getRecipeList());

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new ModRegistry());
		proxy.preInit(event);
	}
}
