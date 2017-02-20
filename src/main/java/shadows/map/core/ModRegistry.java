package shadows.map.core;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shadows.map.common.ItemMap;
import shadows.map.common.ItemUsedMap;


public class ModRegistry {
	public static ItemMap map;
	public static ItemUsedMap usedMap;

	
	public static void init(){ 
		map = new ItemMap("map");
		usedMap = new ItemUsedMap("structure_map");
	}
	
	@SideOnly(Side.CLIENT)
	public static void initModels(){
		map.initModel();
		usedMap.initModel();
	}
	
	
}
