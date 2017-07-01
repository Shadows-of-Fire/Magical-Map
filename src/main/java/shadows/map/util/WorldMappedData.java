package shadows.map.util;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldSavedData;

public class WorldMappedData extends WorldSavedData {

	public WorldMappedData(String s) {
		super(s);
	}

	private NBTTagCompound data = new NBTTagCompound();

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		data = compound.getCompoundTag("MappedStructures");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setTag("MappedStructures", data);
		return compound;
	}

	public NBTTagCompound getData() {
		return data;
	}

	public static WorldMappedData get(World world) {
		MapStorage storage = world.getPerWorldStorage();
		WorldMappedData instance = (WorldMappedData) storage.getOrLoadData(WorldMappedData.class, "MappedStructures");

		if (instance == null) {
			instance = new WorldMappedData("MappedStructures");
			storage.setData("MappedStructures", instance);
		}
		return instance;
	}

}
