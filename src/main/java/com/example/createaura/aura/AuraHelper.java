package com.example.createaura.aura;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

/**
 * 鐏垫皵浼犺緭宸ュ叿绫?- 澶勭悊鐏垫皵鍦ㄧ綉缁滀腑鐨勬祦鍔? */
public class AuraHelper {

    /**
     * 鍗曟浼犺緭鐨勭伒姘斿熀鍑嗛噺
     */
    public static final double BASE_TRANSFER_RATE = 10.0;

    /**
     * 鍦ㄤ袱涓鍣ㄤ箣闂翠紶杈撶伒姘?     */
    public static double transferAura(IAuraContainer from, IAuraContainer to, double amount) {
        if (from == null || to == null) return 0;
        if (!from.canExtract() || !to.canReceive()) return 0;

        double extractable = from.extractAura(amount, true);
        if (extractable <= 0) return 0;

        double received = to.receiveAura(extractable, true);
        if (received <= 0) return 0;

        double actualExtract = from.extractAura(received, false);
        double actualReceive = to.receiveAura(actualExtract, false);
        return actualReceive;
    }

    /**
     * 浠庢寚瀹氫綅缃殑鏂瑰潡瀹炰綋浼犺緭鐏垫皵鍒扮浉閭诲鍣?     */
    public static void distributeAuraToNeighbors(Level level, BlockPos pos, IAuraContainer source, double maxPerSide) {
        if (source == null || !source.canExtract()) return;

        double toDistribute = Math.min(maxPerSide, source.getAuraStored());
        if (toDistribute <= 0) return;

        int sides = 0;
        Direction[] validSides = new Direction[6];
        for (Direction dir : Direction.values()) {
            BlockEntity neighbor = level.getBlockEntity(pos.relative(dir));
            if (neighbor instanceof IAuraContainer container && container.canReceive()) {
                validSides[sides++] = dir;
            }
        }

        if (sides == 0) return;

        double perSide = toDistribute / sides;
        for (int i = 0; i < sides; i++) {
            BlockEntity neighbor = level.getBlockEntity(pos.relative(validSides[i]));
            if (neighbor instanceof IAuraContainer container) {
                transferAura(source, container, perSide);
            }
        }
    }

    /**
     * 浠庣浉閭诲鍣ㄥ惛鏀剁伒姘?     */
    public static void absorbAuraFromNeighbors(Level level, BlockPos pos, IAuraContainer target, double maxPerSide) {
        if (target == null || !target.canReceive()) return;

        double spaceLeft = target.getMaxAura() - target.getAuraStored();
        if (spaceLeft <= 0) return;

        for (Direction dir : Direction.values()) {
            BlockEntity neighbor = level.getBlockEntity(pos.relative(dir));
            if (neighbor instanceof IAuraContainer container && container.canExtract()) {
                double pull = Math.min(maxPerSide, spaceLeft);
                double transferred = transferAura(container, target, pull);
                spaceLeft -= transferred;
                if (spaceLeft <= 0) break;
            }
        }
    }

    /**
     * 鑾峰彇鎸囧畾浣嶇疆鏂瑰潡瀹炴満鐨勭伒姘斿鍣紙濡傛灉瀛樺湪锛?     */
    public static IAuraContainer getContainer(Level level, BlockPos pos) {
        BlockEntity be = level.getBlockEntity(pos);
        return be instanceof IAuraContainer ? (IAuraContainer) be : null;
    }
}
