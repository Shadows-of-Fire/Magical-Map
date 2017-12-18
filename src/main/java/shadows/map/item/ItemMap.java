package shadows.map.item;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagLong;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shadows.map.MagicalMap;
import shadows.map.init.ModRegistry;
import shadows.map.util.WorldMappedData;
import shadows.placebo.item.base.ItemBase;

public class ItemMap extends ItemBase {

	public ItemMap() {
		super("map", MagicalMap.INFO);
		setMaxStackSize(1);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		return EnumActionResult.PASS;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		String structure = convertToStructureName(stack.getDisplayName());
		if (!world.isRemote && !stack.isEmpty()) {
			if (!structure.isEmpty() && this.hasUnmappedStructureNearby(structure, world, stack, player)) {
				player.setHeldItem(hand, mapStack(structure, world, stack, player));
				return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
			} else if (structure.isEmpty()) player.sendMessage(new TextComponentString("\"" + player.getHeldItem(hand).getDisplayName() + "\" is an invalid structure!"));
		}
		return new ActionResult<>(EnumActionResult.FAIL, player.getHeldItem(hand));
	}

	private boolean hasUnmappedStructureNearby(String structure, World world, ItemStack stack, EntityPlayer player) {
		BlockPos pos = world.findNearestStructure(structure, player.getPosition(), true);
		if (pos == null) player.sendMessage(new TextComponentString("Structure \"" + structure + "\" not found!"));
		else if (isMapped(pos, world, player)) player.sendMessage(new TextComponentString("You have already mapped the nearest " + structure + "!"));
		else if (pos != null && !isMapped(pos, world, player)) {
			addPosToMapped(pos, world, player);
			return true;
		}
		return false;
	}

	private static String convertToStructureName(String displayname) {
		String structure = "";
		String[] brokenName = displayname.toLowerCase().split(" ");

		String fixedName = "";
		int k = brokenName.length;
		for (int i = 0; i < k; i++) {
			fixedName = fixedName.concat(brokenName[i]);
		}
		switch (fixedName) {
		case ("temple"):
			structure = "Temple";
			break;
		case ("mineshaft"):
			structure = "Mineshaft";
			break;
		case ("village"):
			structure = "Village";
			break;
		case ("monument"):
			structure = "Monument";
			break;
		case ("mansion"):
			structure = "Mansion";
			break;
		case ("stronghold"):
			structure = "Stronghold";
			break;
		case ("endcity"):
			structure = "EndCity";
			break;
		case ("fortress"):
			structure = "Fortress";
			break;
		case ("netherfortress"):
			structure = "Fortress";
			break;
		}
		return structure;
	}

	private static ItemStack mapStack(String structure, World world, ItemStack stack, EntityPlayer player) {
		BlockPos coords = world.findNearestStructure(structure, player.getPosition(), false);
		String newName = TextFormatting.RESET + "Map to " + structure;
		stack.setStackDisplayName(newName);
		String newDesc = "Location: (" + coords.getX() + "," + coords.getY() + "," + coords.getZ() + ")";
		player.sendMessage(new TextComponentString("Found structure \"" + structure + "\" at " + newDesc));
		NBTTagString tag = new NBTTagString(newDesc);
		NBTTagLong pos = new NBTTagLong(coords.toLong());
		stack.setTagInfo("structurePos", tag);
		stack.setTagInfo("structurePos2", pos);
		ItemStack stack2 = new ItemStack(ModRegistry.USEDMAP);
		stack2.setTagCompound(stack.getTagCompound());
		return stack2;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World player, List<String> list, ITooltipFlag advanced) {
		list.add("Name me!");
		list.add("Valid structures are: Temple; Mineshaft; Village; Monument; Mansion; Stronghold; End City; Fortress");
	}

	private static void addPosToMapped(BlockPos pos, World world, EntityPlayer player) {
		WorldMappedData data = WorldMappedData.get(world);
		data.getData().setString(EntityPlayer.getUUID(player.getGameProfile()).toString() + pos.toString(), pos.toString());
		data.markDirty();
		world.getPerWorldStorage().setData("MappedStructures", data);
	}

	private static boolean isMapped(BlockPos pos, World world, EntityPlayer player) {
		WorldMappedData data = WorldMappedData.get(world);
		NBTTagCompound nbt = data.getData();

		if (nbt.hasNoTags()) return false;
		else if (nbt.getString(EntityPlayer.getUUID(player.getGameProfile()).toString() + pos.toString()).equals(pos.toString())) return true;
		else return false;
	}

}
