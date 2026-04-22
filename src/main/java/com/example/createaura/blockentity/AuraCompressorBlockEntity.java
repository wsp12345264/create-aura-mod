package com.example.createaura.blockentity;

import com.example.createaura.CreateAura;
import com.example.createaura.aura.IAuraContainer;
import com.example.createaura.registry.ModBlockEntities;
import com.example.createaura.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

/**
 * 鐏垫皵鍘嬬缉鏈?- 娑堣€楃伒姘斿皢鍘熸枡鍘嬬缉鎴愰珮绾х墿鍝? * 閰嶆柟锛氱伒鏅?-> 鍏呰兘鐏垫櫠, 鐏垫櫠+閾侀敪 -> 鐏垫皵閿? etc.
 */
public class AuraCompressorBlockEntity extends BlockEntity implements IAuraContainer {

    private double auraStored = 0;
    private static final double MAX_AURA = 500.0;
    private static final double AURA_PER_TICK = 1.0;

    private int processingTime = 0;
    private int maxProcessingTime = 200; // 10 seconds at 20t/s
    private boolean isProcessing = false;
    private int tickCounter = 0;

    // Simple internal item buffer (0 = empty, 1 = has item)
    private ItemStack inputItem = ItemStack.EMPTY;
    private ItemStack outputItem = ItemStack.EMPTY;

    public AuraCompressorBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.AURA_COMPRESSOR.get(), pos, state);
    }

    public void tick(net.minecraft.world.level.Level level, BlockPos pos, BlockState state) {
        if (level.isClientSide) return;
        tickCounter++;

        // 浠庣浉閭诲鍣ㄦ娊鍙栫伒姘?        for (Direction dir : Direction.values()) {
            BlockEntity neighbor = level.getBlockEntity(pos.relative(dir));
            if (neighbor instanceof IAuraContainer container && container.canExtract()) {
                double pulled = container.extractAura(Math.min(AURA_PER_TICK, MAX_AURA - auraStored), false);
                auraStored += pulled;
                auraStored = Math.min(auraStored, MAX_AURA);
                if (auraStored >= MAX_AURA) break;
            }
        }

        // 澶勭悊閫昏緫
        if (isProcessing) {
            if (auraStored >= AURA_PER_TICK) {
                auraStored -= AURA_PER_TICK;
                processingTime++;
                if (processingTime >= maxProcessingTime) {
                    finishProcessing();
                }
            }
        }

        if (tickCounter % 20 == 0) {
            setChanged();
            level.sendBlockUpdated(pos, state, state, 3);
        }
    }

    private void finishProcessing() {
        isProcessing = false;
        processingTime = 0;
        // Output result
        if (!outputItem.isEmpty()) {
            // In a full implementation, this would eject items via hopper/depot
            CreateAura.LOGGER.info("鐏垫皵鍘嬬缉鏈哄畬鎴愬鐞嗭紒");
        }
    }

    public double getProgress() {
        return maxProcessingTime > 0 ? (double) processingTime / maxProcessingTime : 0;
    }

    public boolean isProcessing() { return isProcessing; }

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
    public boolean canExtract() { return false; }

    // --- NBT ---
    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putDouble("AuraStored", auraStored);
        tag.putInt("ProcessingTime", processingTime);
        tag.putInt("MaxProcessingTime", maxProcessingTime);
        tag.putBoolean("IsProcessing", isProcessing);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        auraStored = tag.getDouble("AuraStored");
        processingTime = tag.getInt("ProcessingTime");
        maxProcessingTime = tag.getInt("MaxProcessingTime");
        isProcessing = tag.getBoolean("IsProcessing");
    }
}
