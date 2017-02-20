package shadows.map.common;

import java.util.List;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shadows.map.core.StructureFinder;

public class ItemUsedMap extends Item{
	
	public ItemUsedMap(String name) {
		setRegistryName(name);
		setUnlocalizedName(StructureFinder.MODID + "." + name);
		setCreativeTab(Items.MAP.getCreativeTab());
		setMaxStackSize(1);
		GameRegistry.register(this);
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean advanced) {
		if(stack.hasTagCompound() && !stack.getTagCompound().getString("structurePos").isEmpty()){
			list.add(stack.getTagCompound().getString("structurePos"));
		}
		list.add("Can be repaired in an anvil with an Eye of Ender");
	}
	
	@SideOnly(Side.CLIENT)
	public void initModel() {
			ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}
	
	@Override
	public boolean hasEffect(ItemStack stack){
		return true;
	}

}
