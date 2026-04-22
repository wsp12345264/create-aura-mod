package com.example.createaura.blockentity;

import com.example.createaura.CreateAura;
import com.example.createaura.aura.IAuraContainer;
import com.example.createaura.registry.ModBlocks;
import com.example.createaura.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

/**
 * 鐏垫皵鍙戠數鏈?- 娑堣€楃伒姘斾骇鐢?Create 妯＄粍搴斿姏鍗曚綅鐨勬柟鍧楀疄浣? * 鍙€氳繃鐏垫皵绠￠亾渚涜兘锛屼骇鐢熸満姊板姩鍔? */
public class AuraGeneratorBlockEntity extends BlockEntity implements IAuraContainer {

    private double auraStored = 0;
    private static final double MAX_AURA = 1000.0;
    private static final double AURA_PER_TICK = 0.5;   // 姣弔ick娑堣€楃伒姘?    private static final double SU_GENERATED = 256.0;   // 浜х敓鐨勫簲鍔涘崟浣?SU)
    private static final double RPM = 64.0;             // 杞€?
    private boolean isActive = false;
    private int tickCounter = 0;

    public AuraGeneratorBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.AURA_GENERATOR.get(), pos, state);
    }

    public void tick(net.minecraft.world.level.Level level, BlockPos pos, BlockState state) {
        if (level.isClientSide) return;
        tickCounter++;

        // 浠庣浉閭荤閬?鍌ㄧ綈鎶藉彇鐏垫皵
        double pulled = 0;
        for (Direction dir : Direction.values()) {
            BlockEntity neighbor = level.getBlockEntity(pos.relative(dir));
            if (neighbor instanceof IAuraContainer container && container.canExtract()) {
                pulled += container.extractAura(AURA_PER_TICK / 6.0, false);
            }
        }
        auraStored += pulled;
        auraStored = Math.min(auraStored, MAX_AURA);

        // 娑堣€楃伒姘斾骇鐢熷姩鍔?        if (auraStored >= AURA_PER_TICK) {
            auraStored -= AURA_PER_TICK;
            isActive = true;
        } else {
            isActive = false;
        }

        // 姣?0tick锛?绉掞級鍚屾鏁版嵁
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
    public boolean canExtract() { return false; } // 鍙戠數鏈轰笉瀵瑰杈撳嚭鐏垫皵

    // --- 鍏叡鏂规硶 ---
    public boolean isActive() { return isActive; }
    public double getGeneratedSU() { return isActive ? SU_GENERATED : 0; }
    public double getRPM() { return isActive ? RPM : 0; }

    public void dropAllAura() {
        auraStored = 0;
    }

    // --- NBT ---
    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putDouble("AuraStored", auraStored);
        tag.putBoolean("IsActive", isActive);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        auraStored = tag.getDouble("AuraStored");
        isActive = tag.getBoolean("IsActive");
    }
}
