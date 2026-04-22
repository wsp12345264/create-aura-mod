package com.example.createaura.registry;

import com.example.createaura.CreateAura;
import com.example.createaura.blockentity.*;
import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class ModBlockEntities {

    public static final BlockEntityEntry<AuraGeneratorBlockEntity> AURA_GENERATOR = CreateAura.REGISTRATE
            .blockEntity("aura_generator", AuraGeneratorBlockEntity::new)
            .block(ModBlocks.AURA_GENERATOR)
            .validBlocks(ModBlocks.AURA_GENERATOR)
            .register();

    public static final BlockEntityEntry<AuraCompressorBlockEntity> AURA_COMPRESSOR = CreateAura.REGISTRATE
            .blockEntity("aura_compressor", AuraCompressorBlockEntity::new)
            .block(ModBlocks.AURA_COMPRESSOR)
            .validBlocks(ModBlocks.AURA_COMPRESSOR)
            .register();

    public static final BlockEntityEntry<AuraPipeBlockEntity> AURA_PIPE = CreateAura.REGISTRATE
            .blockEntity("aura_pipe", AuraPipeBlockEntity::new)
            .block(ModBlocks.AURA_PIPE)
            .validBlocks(ModBlocks.AURA_PIPE)
            .register();

    public static final BlockEntityEntry<AuraTankBlockEntity> AURA_TANK = CreateAura.REGISTRATE
            .blockEntity("aura_tank", AuraTankBlockEntity::new)
            .block(ModBlocks.AURA_TANK)
            .validBlocks(ModBlocks.AURA_TANK)
            .register();

    public static void register() {}
}
