package com.example.createaura.registry;

import com.example.createaura.CreateAura;
import com.example.createaura.item.*;
import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class ModItems {

    // --- Raw Materials ---
    public static final ItemEntry<Item> RAW_AURA_CRYSTAL = CreateAura.REGISTRATE
            .item("raw_aura_crystal", Item::new)
            .properties(p -> p.stacksTo(64))
            .lang("Raw Aura Crystal")
            .register();

    public static final ItemEntry<AuraCrystalItem> AURA_CRYSTAL = CreateAura.REGISTRATE
            .item("aura_crystal", AuraCrystalItem::new)
            .properties(p -> p.stacksTo(64).rarity(Rarity.UNCOMMON))
            .lang("Aura Crystal")
            .register();

    public static final ItemEntry<Item> POLISHED_AURA_CRYSTAL = CreateAura.REGISTRATE
            .item("polished_aura_crystal", Item::new)
            .properties(p -> p.stacksTo(64))
            .lang("Polished Aura Crystal")
            .register();

    // --- Components ---
    public static final ItemEntry<Item> AURA_CORE = CreateAura.REGISTRATE
            .item("aura_core", Item::new)
            .properties(p -> p.stacksTo(16).rarity(Rarity.RARE))
            .lang("Aura Core")
            .register();

    public static final ItemEntry<Item> AURA_INGOT = CreateAura.REGISTRATE
            .item("aura_ingot", Item::new)
            .properties(p -> p.stacksTo(64).rarity(Rarity.UNCOMMON))
            .lang("Aura Ingot")
            .register();

    public static final ItemEntry<Item> AURA_NUGGET = CreateAura.REGISTRATE
            .item("aura_nugget", Item::new)
            .properties(p -> p.stacksTo(64))
            .lang("Aura Nugget")
            .register();

    // --- Mechanical Components ---
    public static final ItemEntry<Item> AURA_GEAR = CreateAura.REGISTRATE
            .item("aura_gear", Item::new)
            .properties(p -> p.stacksTo(16).rarity(Rarity.UNCOMMON))
            .lang("Aura Gear")
            .register();

    public static final ItemEntry<Item> AURA_BELT_CONNECTOR = CreateAura.REGISTRATE
            .item("aura_belt_connector", Item::new)
            .properties(p -> p.stacksTo(16))
            .lang("Aura Belt Connector")
            .register();

    // --- Tools / Utilities ---
    public static final ItemEntry<AuraDetectorItem> AURA_DETECTOR = CreateAura.REGISTRATE
            .item("aura_detector", AuraDetectorItem::new)
            .properties(p -> p.stacksTo(1).rarity(Rarity.UNCOMMON))
            .lang("Aura Detector")
            .register();

    public static final ItemEntry<AuraWrenchItem> AURA_WRENCH = CreateAura.REGISTRATE
            .item("aura_wrench", AuraWrenchItem::new)
            .properties(p -> p.stacksTo(1).durability(256))
            .lang("Aura Wrench")
            .register();

    // --- Upgrade Items ---
    public static final ItemEntry<Item> AURA_SPEED_MODULE = CreateAura.REGISTRATE
            .item("aura_speed_module", Item::new)
            .properties(p -> p.stacksTo(16).rarity(Rarity.RARE))
            .lang("Aura Speed Module")
            .register();

    public static final ItemEntry<Item> AURA_EFFICIENCY_MODULE = CreateAura.REGISTRATE
            .item("aura_efficiency_module", Item::new)
            .properties(p -> p.stacksTo(16).rarity(Rarity.RARE))
            .lang("Aura Efficiency Module")
            .register();

    public static final ItemEntry<AuraChargedCrystalItem> CHARGED_AURA_CRYSTAL = CreateAura.REGISTRATE
            .item("charged_aura_crystal", AuraChargedCrystalItem::new)
            .properties(p -> p.stacksTo(1).rarity(Rarity.EPIC))
            .lang("Charged Aura Crystal")
            .register();

    public static void register() {}
}
