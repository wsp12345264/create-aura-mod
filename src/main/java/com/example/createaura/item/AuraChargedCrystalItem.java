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
 * 鍏呰兘鐏垫櫠 - 婊¤浇鐏垫皵鐨勯珮绾ф按鏅? * 鍙抽敭閲婃斁鐏垫皵鑴夊啿锛屼慨澶嶅懆鍥存満姊版柟鍧? */
public class AuraChargedCrystalItem extends Item {
    private static final int MAX_CHARGES = 10;
    private static final int COOLDOWN_TICKS = 100;

    public AuraChargedCrystalItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!level.isClientSide) {
            // Aura pulse - damages nearby hostile mobs and gives buffs to player
            player.displayClientMessage(Component.literal("搂d鉁︹湨鉁?鐏垫皵鑴夊啿閲婃斁锛佲湨鉁︹湨"), false);

            // Give absorption effect to player
            player.addEffect(new net.minecraft.world.effect.MobEffectInstance(
                    net.minecraft.world.effect.MobEffects.ABSORPTION, 600, 2));

            // Damage nearby hostile mobs within 8 blocks
            level.getEntitiesOfClass(net.minecraft.world.entity.Mob.class,
                    player.getBoundingBox().inflate(8.0),
                    mob -> !mob.isAlliedTo(player) && mob.distanceTo(player) < 8.0)
                    .forEach(mob -> {
                        mob.hurt(level.damageSources().magic(), 6.0f);
                        mob.addEffect(new net.minecraft.world.effect.MobEffectInstance(
                                net.minecraft.world.effect.MobEffects.GLOWING, 100, 0));
                    });

            // Crystal particles
            for (int i = 0; i < 30; i++) {
                level.addParticle(
                        net.minecraft.core.particles.ParticleTypes.END_ROD,
                        player.getX() + (Math.random() - 0.5) * 6,
                        player.getY() + Math.random() * 3,
                        player.getZ() + (Math.random() - 0.5) * 6,
                        (Math.random() - 0.5) * 0.3,
                        Math.random() * 0.2,
                        (Math.random() - 0.5) * 0.3
                );
            }

            if (!player.isCreative()) {
                stack.shrink(1);
            }
        }

        player.getCooldowns().addCooldown(this, COOLDOWN_TICKS);
        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true; // Enchantment glint effect
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.literal("搂d婊¤浇鐏垫皵鐨勫己鍔涙按鏅?));
        tooltip.add(Component.literal("搂5鍙抽敭閲婃斁鐏垫皵鑴夊啿"));
        tooltip.add(Component.literal("搂7- 瀵?鏍煎唴鏁屽鐢熺墿閫犳垚浼ゅ"));
        tooltip.add(Component.literal("搂7- 璧嬩簣30绉掑惛鏀舵晥鏋?));
        tooltip.add(Component.literal("搂c搂o涓€娆℃€ф秷鑰楀搧"));
    }
}
