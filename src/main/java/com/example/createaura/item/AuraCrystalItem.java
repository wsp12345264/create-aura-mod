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
 * 鐏垫櫠 - 鍩虹鐏垫皵鏉愭枡锛屽彸閿娇鐢ㄥ彲鏁ｅ彂寰急鍏夎姃
 */
public class AuraCrystalItem extends Item {
    public AuraCrystalItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!level.isClientSide) {
            // Right-click creates a small glow effect
            player.displayClientMessage(Component.literal("搂b鉁?鐏垫櫠寰井鍙戝厜锛岀伒姘斿湪浣犵殑鎸囧皷娴佽浆鈥︹€?), true);
        } else {
            // Client-side particle effect would go here
            for (int i = 0; i < 8; i++) {
                level.addParticle(
                        net.minecraft.core.particles.ParticleTypes.END_ROD,
                        player.getX() + (Math.random() - 0.5) * 2,
                        player.getY() + 1 + Math.random(),
                        player.getZ() + (Math.random() - 0.5) * 2,
                        (Math.random() - 0.5) * 0.1,
                        Math.random() * 0.1,
                        (Math.random() - 0.5) * 0.1
                );
            }
        }
        player.getCooldowns().addCooldown(this, 40);
        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.literal("搂7钑村惈鍘熷鐏垫皵鐨勬櫠浣?));
        tooltip.add(Component.literal("搂9鍙抽敭閲婃斁寰急鐏垫皵鑴夊啿"));
        tooltip.add(Component.literal("搂8鐢ㄤ簬鍚堟垚鐏垫皵鏈烘閮ㄤ欢"));
    }
}
