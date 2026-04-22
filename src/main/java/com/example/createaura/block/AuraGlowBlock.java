package com.example.createaura.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

public class AuraGlowBlock extends Block {
    public AuraGlowBlock(Properties properties) {
        super(properties);
    }

    @Override
    public int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
        // Output a redstone signal based on nearby aura
        return (int) (Math.random() * 5) + 10;
    }

    @Override
    public boolean isSignalSource(BlockState state) {
        return true;
    }
}
