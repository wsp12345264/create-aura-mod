# Create Aura 灵气机械

[![Minecraft](https://img.shields.io/badge/Minecraft-1.20.1-brightgreen)](https://minecraft.net)
[![Forge](https://img.shields.io/badge/Forge-47.2.0-orange)](https://files.minecraftforge.net)
[![Create](https://img.shields.io/badge/Create-0.6.0+-blue)](https://github.com/Creators-of-Create/Create)

> ⚡ 将神秘的灵气之力注入机械动力的世界

一个 **机械动力 (Create Mod)** 的拓展模组，为机械系统增添了全新的**灵气能量维度**。

---

## ✨ 特性

### 灵气矿石与材料
- **灵晶矿石** - 在地下生成的灵气矿石，熔炼获得灵晶
- **灵晶** - 基础灵气材料，右键释放灵气脉冲
- **灵气核心** - 高级合成材料
- **灵气领/粒** - 灵气金属制品
- **抛光灵晶** - 装饰性材料
- **充能灵晶** - 一次性强力武器，释放灵气脉冲

### 灵气机械方块
| 方块 | 功能 |
|------|------|
| **灵气发电机** | 消耗灵气产生 256 SU 应力单位 |
| **灵气压缩机** | 消耗灵气压缩/合成高级物品 |
| **灵气管道** | 连接灵气容器，自动传输灵气 |
| **灵气储罐** | 10000 灵气容量的大容量存储 |
| **灵光块** | 发光装饰方块，可输出红石信号 |
| **灵晶簇** | 地下生成的装饰性晶簇 |

### 工具与模块
- **灵气探测器** - 右键方块查看灵气含量
- **灵气扫手** - 旋转灵气机械方块
- **灵气齿轮** - 机械动力集成组件
- **灵气皮带连接器** - 增强版传送带
- **速度模块 / 效率模块** - 机械升级组件

### 灵气网络系统
```
灵气储罐 -> 灵气管道 -> 灵气发电机 -> 机械动力网络
                     -> 灵气压缩机 -> 高级物品
```
所有灵气容器通过 `IAuraContainer` 接口互联互通，灵气在相邻容器之间自动流动。

---

## 安装

### 前置模组
1. [Minecraft Forge 47.2.0+](https://files.minecraftforge.net/net/minecraftforge/forge/index_1.20.1.html)
2. **Create Mod 0.6.0+**
3. Flywheel（Create 自带依赖）

> **注意**：本模组需要 Create Mod **0.6.0 或更高版本**，不支持 0.5.x 版本。

### 安装步骤
1. 下载 `createaura-1.0.0.jar`
2. 放入 `.minecraft/mods/` 目录
3. 确保已安装 Forge 和 Create 0.6.0+

---

## 构建

### 环境要求
- JDK 17
- Gradle 8.x（项目自带 Wrapper）

### 编译命令
```bash
git clone https://github.com/wsp12345264/create-aura-mod.git
cd create-aura-mod
./gradlew build
# 输出文件位于 build/libs/createaura-1.0.0.jar
```

---

## 使用指南

1. **挖矿**：在 Y=-20 ~ Y=20 之间寻找灵晶矿石（淡蓝色发光矿石）
2. **熔炼**：将粗灵晶在熔炉中熔炼为灵晶
3. **合成**：用灵晶合成灵气齿轮和灵气核心
4. **建造**：合成灵气管道和灵气储罐，搞建灵气网络
5. **发电**：合成灵气发电机，连接 Create 动力网络

---

## 项目结构

```
src/main/java/com/example/createaura/
├── CreateAura.java              # 主类
├── aura/
│   ├── IAuraContainer.java      # 灵气容器接口
│   └── AuraHelper.java          # 灵气传输工具
├── block/
│   ├── AuraGeneratorBlock.java  # 灵气发电机
│   ├── AuraCompressorBlock.java # 灵气压缩机
│   ├── AuraPipeBlock.java       # 灵气管道
│   ├── AuraTankBlock.java       # 灵气储罐
│   ├── AuraGlowBlock.java       # 灵光块
│   └── AuraCrystalClusterBlock.java  # 灵晶簇
├── blockentity/
│   ├── AuraGeneratorBlockEntity.java
│   ├── AuraCompressorBlockEntity.java
│   ├── AuraPipeBlockEntity.java
│   └── AuraTankBlockEntity.java
├── item/
│   ├── AuraCrystalItem.java     # 灵晶
│   ├── AuraDetectorItem.java    # 灵气探测器
│   ├── AuraWrenchItem.java      # 灵气扫手
│   └── AuraChargedCrystalItem.java  # 充能灵晶
└── registry/
    ├── ModBlocks.java           # 方块注册
    ├── ModItems.java            # 物品注册
    ├── ModBlockEntities.java    # 方块实体注册
    └── ModCreativeTabs.java     # 创造模式标签页
```

---

## 开发计划

- [ ] 与 Create 动力网络深度集成
- [ ] 灵气世界生成（矿脉、晶洞）
- [ ] 灵气合成配方系统
- [ ] 灵气管道可视化（灵气流动粒子效果）
- [ ] 灵气传送门
- [ ] 更多灵气机械方块

---

## 许可证

MIT License - 详见 [LICENSE](LICENSE)

---

## 致谢

- [Create Mod](https://github.com/Creators-of-Create/Create) - 机械动力框架
- [Minecraft Forge](https://files.minecraftforge.net/) - 模组加载器
- [Registrate](https://github.com/tterrag1098/Registrate) - 注册工具库
