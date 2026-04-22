package com.example.createaura.registry;

import com.example.createaura.CreateAura;
import com.example.createaura.block.ModBlocks;
import com.example.createaura.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CreateAura.MODID);

    public static final RegistryObject<CreativeModeTab> CREATE_AURA_TAB = CREATIVE_MODE_TABS.register("create_aura_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.AURA_CRYSTAL.get()))
                    .title(Component.translatable("itemGroup.createaura"))
                    .displayItems((parameters, output) -> {
                        // Blocks
                        output.accept(ModBlocks.AURA_CRYSTAL_ORE.get());
                        output.accept(ModBlocks.AURA_BLOCK.get());
                        output.accept(ModBlocks.AURA_GENERATOR.get());
                        output.accept(ModBlocks.AURA_COMPRESSOR.get());
                        output.accept(ModBlocks.AURA_PIPE.get());
                        output.accept(ModBlocks.AURA_TANK.get());
                        output.accept(ModBlocks.AURA_GLOW_BLOCK.get());
                        output.accept(ModBlocks.AURA_CRYSTAL_CLUSTER.get());

                        // Items
                        output.accept(ModItems.RAW_AURA_CRYSTAL.get());
                        output.accept(ModItems.AURA_CRYSTAL.get());
                        output.accept(ModItems.POLISHED_AURA_CRYSTAL.get());
                        output.accept(ModItems.AURA_CORE.get());
                        output.accept(ModItems.AURA_INGOT.get());
                        output.accept(ModItems.AURA_NUGGET.get());
                        output.accept(ModItems.AURA_GEAR.get());
                        output.accept(ModItems.AURA_BELT_CONNECTOR.get());
                        output.accept(ModItems.AURA_DETECTOR.get());
                        output.accept(ModItems.AURA_WRENCH.get());
                        output.accept(ModItems.AURA_SPEED_MODULE.get());
                        output.accept(ModItems.AURA_EFFICIENCY_MODULE.get());
                        output.accept(ModItems.CHARGED_AURA_CRYSTAL.get());
                    })
                    .build());

    public static void register() {
        CREATIVE_MODE_TABS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
