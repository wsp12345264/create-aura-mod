package com.example.createaura.registry;

import com.example.createaura.CreateAura;
import com.example.createaura.block.*;
import com.example.createaura.blockentity.*;
import com.example.createaura.item.*;
import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class ModBlocks {

    // --- Ores ---
    public static final BlockEntry<Block> AURA_CRYSTAL_ORE = CreateAura.REGISTRATE
            .block("aura_crystal_ore", properties -> new Block(properties))
            .properties(p -> p.strength(3.0f, 5.0f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)
                    .mapColor(MaterialColor.COLOR_LIGHT_BLUE))
            .blockstate((ctx, prov) -> prov.simpleBlock(ctx.get(),
                    prov.models().getExistingFile(prov.modLoc("block/aura_crystal_ore"))))
            .item()
            .model((ctx, prov) -> prov.withExistingParent(ctx.getName(), prov.modLoc("block/aura_crystal_ore")))
            .build()
            .register();

    // --- Storage Blocks ---
    public static final BlockEntry<Block> AURA_BLOCK = CreateAura.REGISTRATE
            .block("aura_block", properties -> new Block(properties))
            .properties(p -> p.strength(5.0f, 8.0f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)
                    .mapColor(MaterialColor.COLOR_CYAN))
            .blockstate((ctx, prov) -> prov.simpleBlock(ctx.get(),
                    prov.models().getExistingFile(prov.modLoc("block/aura_block"))))
            .item()
            .model((ctx, prov) -> prov.withExistingParent(ctx.getName(), prov.modLoc("block/aura_block")))
            .build()
            .register();

    // --- Functional Blocks ---
    public static final BlockEntry<AuraGeneratorBlock> AURA_GENERATOR = CreateAura.REGISTRATE
            .block("aura_generator", AuraGeneratorBlock::new)
            .properties(p -> p.strength(4.0f, 6.0f)
                    .sound(SoundType.METAL)
                    .mapColor(MaterialColor.COLOR_BLUE)
                    .noOcclusion())
            .blockstate((ctx, prov) -> prov.horizontalBlock(ctx.get(), prov.modLoc("block/aura_generator_side"),
                    prov.modLoc("block/aura_generator_front")))
            .item()
            .model((ctx, prov) -> prov.withExistingParent(ctx.getName(), prov.modLoc("block/aura_generator")))
            .build()
            .register();

    public static final BlockEntry<AuraCompressorBlock> AURA_COMPRESSOR = CreateAura.REGISTRATE
            .block("aura_compressor", AuraCompressorBlock::new)
            .properties(p -> p.strength(4.0f, 6.0f)
                    .sound(SoundType.METAL)
                    .mapColor(MaterialColor.COLOR_PURPLE)
                    .noOcclusion())
            .blockstate((ctx, prov) -> prov.horizontalBlock(ctx.get(), prov.modLoc("block/aura_compressor_side"),
                    prov.modLoc("block/aura_compressor_front")))
            .item()
            .model((ctx, prov) -> prov.withExistingParent(ctx.getName(), prov.modLoc("block/aura_compressor")))
            .build()
            .register();

    public static final BlockEntry<AuraPipeBlock> AURA_PIPE = CreateAura.REGISTRATE
            .block("aura_pipe", AuraPipeBlock::new)
            .properties(p -> p.strength(2.0f, 3.0f)
                    .sound(SoundType.METAL)
                    .mapColor(MaterialColor.COLOR_LIGHT_BLUE)
                    .noOcclusion())
            .blockstate((ctx, prov) -> prov.simpleBlock(ctx.get(),
                    prov.models().cubeColumn(ctx.getName(),
                            prov.modLoc("block/aura_pipe_side"),
                            prov.modLoc("block/aura_pipe_end"))))
            .item()
            .model((ctx, prov) -> prov.withExistingParent(ctx.getName(), prov.modLoc("block/aura_pipe")))
            .build()
            .register();

    public static final BlockEntry<AuraTankBlock> AURA_TANK = CreateAura.REGISTRATE
            .block("aura_tank", AuraTankBlock::new)
            .properties(p -> p.strength(3.0f, 5.0f)
                    .sound(SoundType.METAL)
                    .mapColor(MaterialColor.COLOR_LIGHT_GREEN)
                    .noOcclusion())
            .blockstate((ctx, prov) -> prov.simpleBlock(ctx.get(),
                    prov.models().getExistingFile(prov.modLoc("block/aura_tank"))))
            .item()
            .model((ctx, prov) -> prov.withExistingParent(ctx.getName(), prov.modLoc("block/aura_tank")))
            .build()
            .register();

    // --- Decorative Blocks ---
    public static final BlockEntry<AuraGlowBlock> AURA_GLOW_BLOCK = CreateAura.REGISTRATE
            .block("aura_glow_block", AuraGlowBlock::new)
            .properties(p -> p.strength(2.0f, 3.0f)
                    .sound(SoundType.GLASS)
                    .mapColor(MaterialColor.COLOR_LIGHT_BLUE)
                    .lightLevel(state -> 12)
                    .noOcclusion())
            .blockstate((ctx, prov) -> prov.simpleBlock(ctx.get(),
                    prov.models().getExistingFile(prov.modLoc("block/aura_glow_block"))))
            .item()
            .model((ctx, prov) -> prov.withExistingParent(ctx.getName(), prov.modLoc("block/aura_glow_block")))
            .build()
            .register();

    // --- World-gen block tags ---
    public static final BlockEntry<AuraCrystalClusterBlock> AURA_CRYSTAL_CLUSTER = CreateAura.REGISTRATE
            .block("aura_crystal_cluster", AuraCrystalClusterBlock::new)
            .properties(p -> p.strength(1.0f, 2.0f)
                    .sound(SoundType.AMETHYST_CLUSTER)
                    .mapColor(MaterialColor.COLOR_CYAN)
                    .lightLevel(state -> 8)
                    .noOcclusion()
                    .noCollission())
            .blockstate((ctx, prov) -> prov.simpleBlock(ctx.get(),
                    prov.models().getExistingFile(prov.modLoc("block/aura_crystal_cluster"))))
            .item()
            .model((ctx, prov) -> prov.withExistingParent(ctx.getName(), prov.modLoc("block/aura_crystal_cluster")))
            .build()
            .register();

    public static void register() {}
}
