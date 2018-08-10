package shadows.map.item;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shadows.map.MagicalMap;
import shadows.placebo.item.ItemBase;

public class ItemUsedMap extends ItemBase {

	public ItemUsedMap() {
		super("structure_map", MagicalMap.INFO);
		setCreativeTab(null);
		setMaxStackSize(1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World world, List<String> list, ITooltipFlag advanced) {
		if (stack.hasTagCompound() && stack.getTagCompound().hasKey(ItemMap.POS_STRING)) list.add(stack.getTagCompound().getString(ItemMap.POS_STRING));
		list.add(I18n.format("desc.magicalmap.repair"));
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		if (!world.isRemote && stack.hasTagCompound() && stack.getTagCompound().hasKey(ItemMap.POS_LONG) && stack.getTagCompound().hasKey(ItemMap.POS_STRING)) {
			BlockPos pos = BlockPos.fromLong(stack.getTagCompound().getLong(ItemMap.POS_LONG));
			BlockPos playerpos = player.getPosition();
			int dist = (int) get2DDistanceFromPos(playerpos, pos, world);
			if (dist <= 30) player.sendStatusMessage(new TextComponentTranslation("msg.magicalmap.here"), true);
			else {
				player.sendStatusMessage(new TextComponentTranslation("msg.magicalmap.dist", dist, getFacingTranslation(playerpos, pos)), true);
			}
		}
		return new ActionResult<>(EnumActionResult.FAIL, player.getHeldItem(hand));
	}
	
	private static TextComponentTranslation getFacingTranslation(BlockPos pos1, BlockPos pos2) {
		EnumFacing facing = getFacing(pos1, pos2);
		return new TextComponentTranslation("dir.magicalmap." + facing.getName2());
	}

	private static double get2DDistanceFromPos(BlockPos origin, BlockPos destination, World world) {
		int x = origin.getX();
		int z = origin.getZ();
		int x1 = destination.getX();
		int z1 = destination.getZ();

		return Math.sqrt(Math.pow(x1 - x, 2) + Math.pow(z1 - z, 2));
	}

	private static EnumFacing getFacing(BlockPos player, BlockPos dest) {
		float xDist = dest.getX() - player.getX();
		float zDist = dest.getZ() - player.getZ();
		return EnumFacing.getFacingFromVector(xDist, 0.0F, zDist);
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

}
