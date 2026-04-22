package com.example.createaura;

import com.example.createaura.aura.AuraNetwork;
import com.example.createaura.registry.*;
import com.tterrag.registrate.Registrate;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(CreateAura.MODID)
public class CreateAura {
    public static final String MODID = "createaura";
    public static final Logger LOGGER = LogManager.getLogger();
    public static final Registrate REGISTRATE = Registrate.create(MODID);

    public CreateAura() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register all deferred registers
        ModBlocks.register();
        ModItems.register();
        ModBlockEntities.register();
        ModCreativeTabs.register();

        // Register Forge event listeners
        MinecraftForge.EVENT_BUS.register(this);

        LOGGER.info("Create Aura - 鐏垫皵鏈烘鎷撳睍妯＄粍宸插姞杞斤紒");
        LOGGER.info("鐏垫皵灏嗘祦娣屽湪姣忎竴涓娇杞笌杞存壙涔嬮棿鈥︹€?);
    }
}
