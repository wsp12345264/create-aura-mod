package com.example.createaura.blockentity;

import com.example.createaura.aura.IAuraContainer;
import com.example.createaura.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

/**
 * 鐏垫皵鍌ㄧ綈 - 澶у閲忕伒姘斿瓨鍌ㄦ柟鍧楀疄浣? * 涓诲姩浠庣閬撳惛鏀剁伒姘旓紝涔熻兘琚叾浠栨柟鍧楁娊鍙? */
public class AuraTankBlockEntity extends BlockEntity implements IAuraContainer {

    private double auraStored = 0;
    private static final double MAX_AURA = 10000.0;
    private static final double IO_RATE = 50.0; // 姣弔ick杈撳叆/杈撳嚭閫熺巼
    private int tickCounter = 0;

    public AuraTankBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.AURA_TANK.get(), pos, state);
    }

    public void tick(net.minecraft.world.level.Level level, BlockPos pos, BlockState state) {
        if (level.isClientSide) return;
        tickCounter++;

        // 琚姩浼犺緭锛氳鐩搁偦鏂瑰潡鎶藉彇鏃堕€氳繃 IAuraContainer 鎺ュ彛澶勭悊
        // 涔熷彲浠ヤ富鍔ㄤ粠绠￠亾鎷夊彇
        for (Direction dir : Direction.values()) {
            BlockEntity neighbor = level.getBlockEntity(pos.relative(dir));
            if (neighbor instanceof IAuraContainer container && container.canExtract()) {
                double space = MAX_AURA - auraStored;
                double pulled = container.extractAura(Math.min(IO_RATE, space), false);
                auraStored += pulled;
            }
        }

        if (tickCounter % 20 == 0) {
            setChanged();
            level.sendBlockUpdated(pos, state, state, 3);
        }
    }

    // --- IAuraContainer ---
    @Override
    public double getAuraStored() { return auraStored; }

    @Override
    public double getMaxAura() { return MAX_AURA; }

    @Override
    public double receiveAura(double amount, boolean simulate) {
        double canReceive = Math.min(amount, MAX_AURA - auraStored);
        if (!simulate) auraStored += canReceive;
        return canReceive;
    }

    @Override
    public double extractAura(double amount, boolean simulate) {
        double canExtract = Math.min(amount, auraStored);
        if (!simulate) auraStored -= canExtract;
        return canExtract;
    }

    @Override
    public boolean canReceive() { return true; }

    @Override
    public boolean canExtract() { return true; }

    // --- NBT ---
    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putDouble("AuraStored", auraStored);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        auraStored = tag.getDouble("AuraStored");
    }
}
