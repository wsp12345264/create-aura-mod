package com.example.createaura.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

/**
 * 鐏垫皵鎺㈡祴鍣?- 瀵瑰噯鏂瑰潡鍙抽敭鏄剧ず鐏垫皵鍚噺
 */
public class AuraDetectorItem extends Item {
    public AuraDetectorItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!level.isClientSide) {
            // Check the block the player is looking at
            var hit = player.pick(5.0, 0.0f, false);
            if (hit.getType() == net.minecraft.world.phys.HitResult.Type.BLOCK) {
                var blockPos = ((net.minecraft.world.phys.BlockHitResult) hit).getBlockPos();
                var be = level.getBlockEntity(blockPos);
                if (be instanceof com.example.createaura.aura.IAuraContainer container) {
                    double stored = container.getAuraStored();
                    double max = container.getMaxAura();
                    int percent = max > 0 ? (int) ((stored / max) * 100) : 0;
                    player.displayClientMessage(Component.literal(String.format(
                            "搂b鈹佲攣鈹?鐏垫皵鎺㈡祴缁撴灉 鈹佲攣鈹?)), false);
                    player.displayClientMessage(Component.literal(String.format(
                            "搂7鏂瑰潡: 搂f%s", level.getBlockState(blockPos).getBlock().getName().getString())), false);
                    player.displayClientMessage(Component.literal(String.format(
                            "搂7鐏垫皵: 搂b%.1f 搂7/ 搂b%.1f 搂7(搂e%d%%搂7)", stored, max, percent)), false);
                } else {
                    player.displayClientMessage(Component.literal("搂7鏈娴嬪埌鐏垫皵鈥︹€?), false);
                }
            } else {
                player.displayClientMessage(Component.literal("搂7璇峰鍑嗘柟鍧楁帰娴?), false);
            }
        }
        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.literal("搂7鎺㈡祴鏂瑰潡鐨勭伒姘斿惈閲?));
        tooltip.add(Component.literal("搂9鍙抽敭鏂瑰潡鏌ョ湅鐏垫皵淇℃伅"));
    }
}
