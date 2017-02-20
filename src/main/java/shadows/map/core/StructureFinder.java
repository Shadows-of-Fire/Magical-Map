package shadows.map.core;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import shadows.map.proxy.CommonProxy;

@Mod(modid = StructureFinder.MODID, version = StructureFinder.VERSION, name = StructureFinder.MODNAME)


public class StructureFinder {
    public static final String MODID = "structurefinder";
    public static final String MODNAME = "Structure Finder";
    public static final String VERSION = "1.0.0";

    @SidedProxy(clientSide = "shadows.map.proxy.ClientProxy", serverSide = "shadows.map.proxy.CommonProxy")
    public static CommonProxy proxy;
	
    @Mod.Instance
    public static StructureFinder instance;
    
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	proxy.preInit(event);
    }
    
    @Mod.EventHandler
    public void init(FMLInitializationEvent e){
    	proxy.init(e);
    }
    
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e)
    {
    	proxy.postInit(e);
    }


}
