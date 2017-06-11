package shadows.map.common;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shadows.map.core.MagicalMap;

public class ItemUsedMap extends Item {

	public ItemUsedMap(String name) {
		setRegistryName(name);
		setUnlocalizedName(MagicalMap.MODID + "." + name);
		setMaxStackSize(1);
		GameRegistry.register(this);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World player, List<String> list, ITooltipFlag advanced) {
		if (stack.hasTagCompound() && !stack.getTagCompound().getString("structurePos").isEmpty()) {
			list.add(stack.getTagCompound().getString("structurePos"));
		}
		list.add("Can be repaired in an anvil with an Eye of Ender");
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		if (!world.isRemote && stack.hasTagCompound() && stack.getTagCompound().hasKey("structurePos2")
				&& stack.getTagCompound().hasKey("structurePos")) {
			BlockPos pos = BlockPos.fromLong(stack.getTagCompound().getLong("structurePos2"));
			BlockPos playerpos = player.getPosition();
			double distance = get2DDistanceFromPos(playerpos, pos, world);
			int dist = (int) distance;
			String message = "The structure is " + dist + " blocks away.";
			if (dist <= 30)
				message = "You are at the structure.";
			player.sendStatusMessage(new TextComponentString(message), false);
			if (dist > 30) {
				String message2 = "Proceed " + getFacing(playerpos, pos).getName() + ".";
				player.sendStatusMessage(new TextComponentString(message2), false);
			}
		}

		return new ActionResult<ItemStack>(EnumActionResult.FAIL, player.getHeldItem(hand));
	}

	public double get2DDistanceFromPos(BlockPos origin, BlockPos destination, World world) {
		int x = origin.getX();
		int z = origin.getZ();
		int x1 = destination.getX();
		int z1 = destination.getZ();

		return Math.sqrt(Math.pow(x1 - x, 2) + Math.pow(z1 - z, 2));
	}

	public EnumFacing getFacing(BlockPos player, BlockPos dest) {
		float xDist = dest.getX() - player.getX();
		float zDist = dest.getZ() - player.getZ();
		return EnumFacing.getFacingFromVector(xDist, 0.0F, zDist);
	}

	@SideOnly(Side.CLIENT)
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

}
