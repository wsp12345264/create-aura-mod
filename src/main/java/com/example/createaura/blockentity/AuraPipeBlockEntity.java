package com.example.createaura.blockentity;

import com.example.createaura.aura.AuraHelper;
import com.example.createaura.aura.IAuraContainer;
import com.example.createaura.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

/**
 * 鐏垫皵绠￠亾 - 浼犺緭鐏垫皵鐨勭閬撴柟鍧楀疄浣? * 姣弔ick鑷姩鍦ㄧ浉閭荤殑鐏垫皵瀹瑰櫒涔嬮棿浼犺緭鐏垫皵
 */
public class AuraPipeBlockEntity extends BlockEntity implements IAuraContainer {

    private double auraStored = 0;
    private static final double MAX_AURA = 100.0;
    private static final double TRANSFER_RATE = 20.0; // 姣弔ick姣忔柟鍚戞渶澶т紶杈?    private int tickCounter = 0;

    public AuraPipeBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.AURA_PIPE.get(), pos, state);
    }

    public void tick(net.minecraft.world.level.Level level, BlockPos pos, BlockState state) {
        if (level.isClientSide) return;
        tickCounter++;

        // 鍏堜粠鑳芥彁鍙栫殑鐩搁偦瀹瑰櫒鎷夊彇鐏垫皵鍒扮閬撲腑
        for (Direction dir : Direction.values()) {
            BlockEntity neighbor = level.getBlockEntity(pos.relative(dir));
            if (neighbor instanceof IAuraContainer container && container.canExtract() && !(neighbor instanceof AuraPipeBlockEntity)) {
                double space = MAX_AURA - auraStored;
                double pulled = container.extractAura(Math.min(TRANSFER_RATE, space), false);
                auraStored += pulled;
            }
        }

        // 鍐嶅皢绠￠亾涓殑鐏垫皵鎺ㄩ€佸埌鑳芥帴鏀剁殑鐩搁偦瀹瑰櫒
        if (auraStored > 0) {
            for (Direction dir : Direction.values()) {
                BlockEntity neighbor = level.getBlockEntity(pos.relative(dir));
                if (neighbor instanceof IAuraContainer container && container.canReceive() && !(neighbor instanceof AuraPipeBlockEntity)) {
                    double pushed = container.receiveAura(Math.min(TRANSFER_RATE, auraStored), false);
                    auraStored -= pushed;
                    if (auraStored <= 0) break;
                }
            }
        }

        if (tickCounter % 20 == 0) {
            setChanged();
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
