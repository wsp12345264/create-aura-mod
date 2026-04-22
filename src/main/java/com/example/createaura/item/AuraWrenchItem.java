package com.example.createaura.item;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.List;

/**
 * 鐏垫皵鎵虫墜 - 鐢ㄤ簬鏃嬭浆鍜岄厤缃伒姘旀満姊版柟鍧? */
public class AuraWrenchItem extends Item {
    public AuraWrenchItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Player player = context.getPlayer();
        ItemStack stack = context.getItemInHand();

        if (player == null || level.isClientSide) return InteractionResult.PASS;

        BlockState state = level.getBlockState(pos);
        Block block = state.getBlock();

        // Rotate aura blocks
        if (state.hasProperty(net.minecraft.world.level.block.state.properties.BlockStateProperties.HORIZONTAL_FACING)) {
            net.minecraft.core.Direction current = state.getValue(
                    net.minecraft.world.level.block.state.properties.BlockStateProperties.HORIZONTAL_FACING);
            BlockState rotated = state.setValue(
                    net.minecraft.world.level.block.state.properties.BlockStateProperties.HORIZONTAL_FACING,
                    current.getClockWise());
            level.setBlock(pos, rotated, 3);

            player.displayClientMessage(Component.literal("搂b鉁?鏂瑰潡宸叉棆杞嚦 " + rotated.getValue(
                    net.minecraft.world.level.block.state.properties.BlockStateProperties.HORIZONTAL_FACING).getName()), true);

            if (!player.isCreative()) {
                stack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(context.getHand()));
            }
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.literal("搂7鏃嬭浆骞惰皟鏁寸伒姘旀満姊版柟鍧?));
        tooltip.add(Component.literal("搂9鍙抽敭鏃嬭浆鏂瑰潡鏈濆悜"));
        tooltip.add(Component.literal(String.format("搂8鑰愪箙: 搂7%d搂8/搂7%d",
                stack.getMaxDamage() - stack.getDamageValue(), stack.getMaxDamage())));
    }
}
