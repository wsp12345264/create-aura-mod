# Create Aura

[![Minecraft](https://img.shields.io/badge/Minecraft-1.20.1-brightgreen)](https://minecraft.net)
[![Forge](https://img.shields.io/badge/Forge-47.2.0-orange)](https://files.minecraftforge.net)
[![Create](https://img.shields.io/badge/Create-0.6.0+-blue)](https://github.com/Creators-of-Create/Create)

> Infuse the world of mechanical power with mysterious aura energy.

A **Create Mod** extension that adds a completely new **Aura Energy Dimension** to the mechanical systems.

---

## Features

### Aura Ores & Materials
- **Aura Crystal Ore** - Generate in underground, smelt for Aura Crystal
- **Aura Crystal** - Basic aura material, right-click to release aura pulse
- **Aura Core** - Advanced crafting material
- **Aura Ingot/Nugget** - Metallic aura products
- **Polished Aura Crystal** - Decorative material
- **Charged Aura Crystal** - One-time powerful weapon, releases aura burst

### Aura Machine Blocks
| Block | Function |
|-------|----------|
| **Aura Generator** | Consumes aura to generate 256 SU stress units |
| **Aura Compressor** | Consumes aura to compress/synthesize advanced items |
| **Aura Pipe** | Connects aura containers, auto-transfer aura |
| **Aura Tank** | Large-capacity storage (10000 aura units) |
| **Aura Glow Block** | Emissive decorative block, outputs redstone signal |
| **Aura Crystal Cluster** | Underground decorative crystal clusters |

### Tools & Modules
- **Aura Detector** - Right-click block to view aura capacity
- **Aura Wrench** - Rotate aura machine blocks
- **Aura Gear** - Mechanical power integration component
- **Aura Belt Connector** - Enhanced belt
- **Speed Module / Efficiency Module** - Mechanical upgrade components

### Aura Network System
```
Aura Tank -> Aura Pipe -> Aura Generator -> Create Kinetic Network
                      -> Aura Compressor -> Advanced Items
```
All aura containers (pipe, tank, generator, compressor) interconnect via the
`IAuraContainer` interface. Aura flows automatically between adjacent containers,
no extra wiring required.

---

## Installation

### Prerequisites
1. [Minecraft Forge 47.2.0+](https://files.minecraftforge.net/net/minecraftforge/forge/index_1.20.1.html)
2. **Create Mod 0.6.0+**
3. Flywheel (Create dependency)

> :warning: **Requires Create Mod 0.6.0 or higher** - does NOT support 0.5.x versions.

### Installation Steps
1. Download `createaura-1.0.0.jar`
2. Place in `.minecraft/mods/` directory
3. Ensure Forge and Create 0.6.0+ are installed

---

## Building

### Requirements
- JDK 17
- Gradle 8.x (project includes Wrapper)

### Build Commands
```bash
# Clone repository
git clone https://github.com/wsp12345264/create-aura-mod.git
cd create-aura-mod

# Build
./gradlew build

# Output file: build/libs/createaura-1.0.0.jar
```

---

## Quick Start Guide

1. **Mining**: Find Aura Crystal Ore (light blue glowing ore) between Y=-20 and Y=20
2. **Smelting**: Smelt Raw Aura Crystal in furnace to get Aura Crystal
3. **Crafting**: Use Aura Crystal to craft Aura Gear and Aura Core
4. **Building**: Craft Aura Pipes and Aura Tanks to build aura network
5. **Power Generation**: Craft Aura Generator, supply power via pipes, connect to Create kinetic network

### Aura Network
- Aura Pipes auto-transfer aura between adjacent containers
- Aura Tank stores large amounts of aura (10000 units)
- Aura Generator continuously consumes aura to output stress units (SU)
- Use **Aura Detector** (right-click) to check current aura status

---

## Project Structure

```
src/main/java/com/example/createaura/
鈹溾攢鈹€ CreateAura.java              # Main class
鈹溾攢鈹€ aura/
鈹?  鈹溾攢鈹€ IAuraContainer.java      # Aura container interface
鈹?  鈹斺攢鈹€ AuraHelper.java           # Aura transfer utility
鈹溾攢鈹€ block/
鈹?  鈹溾攢鈹€ AuraGeneratorBlock.java  # Aura Generator
鈹?  鈹溾攢鈹€ AuraCompressorBlock.java # Aura Compressor
鈹?  鈹溾攢鈹€ AuraPipeBlock.java       # Aura Pipe
鈹?  鈹溾攢鈹€ AuraTankBlock.java       # Aura Tank
鈹?  鈹溾攢鈹€ AuraGlowBlock.java       # Aura Glow Block
鈹?  鈹斺攢鈹€ AuraCrystalClusterBlock.java  # Aura Crystal Cluster
鈹溾攢鈹€ blockentity/
鈹?  鈹溾攢鈹€ AuraGeneratorBlockEntity.java
鈹?  鈹溾攢鈹€ AuraCompressorBlockEntity.java
鈹?  鈹溾攢鈹€ AuraPipeBlockEntity.java
鈹?  鈹斺攢鈹€ AuraTankBlockEntity.java
鈹溾攢鈹€ item/
鈹?  鈹溾攢鈹€ AuraCrystalItem.java     # Aura Crystal
鈹?  鈹溾攢鈹€ AuraDetectorItem.java    # Aura Detector
鈹?  鈹溾攢鈹€ AuraWrenchItem.java      # Aura Wrench
鈹?  鈹斺攢鈹€ AuraChargedCrystalItem.java  # Charged Aura Crystal
鈹斺攢鈹€ registry/
    鈹溾攢鈹€ ModBlocks.java           # Block registration
    鈹溾攢鈹€ ModItems.java             # Item registration
    鈹溾攢鈹€ ModBlockEntities.java     # Block entity registration
    鈹斺攢鈹€ ModCreativeTabs.java      # Creative tab
```

---

## Roadmap

- [ ] Deep integration with Create kinetic network (KineticEntity)
- [ ] Aura world generation (ore veins, crystal caves)
- [ ] Aura crafting recipe system (custom compressor recipes)
- [ ] Aura pipe visualization (aura flow particle effects)
- [ ] Aura Portal
- [ ] More aura machine blocks

---

## License

MIT License - see [LICENSE](LICENSE)

---

## Acknowledgements

- [Create Mod](https://github.com/Creators-of-Create/Create) - Mechanical Power Framework
- [Minecraft Forge](https://files.minecraftforge.net/) - Mod Loader
- [Registrate](https://github.com/tterrag1098/Registrate) - Registration Library