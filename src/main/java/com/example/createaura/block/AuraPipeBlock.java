package com.example.createaura.block;

import com.example.createaura.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class AuraPipeBlock extends BaseEntityBlock {
    // Pipe shape - hollow center
    private static final VoxelShape PIPE_CORE = Block.box(4, 4, 0, 12, 12, 16);
    private static final VoxelShape PIPE_NORTH = Block.box(4, 4, 0, 12, 12, 6);
    private static final VoxelShape PIPE_SOUTH = Block.box(4, 4, 10, 12, 12, 16);
    private static final VoxelShape PIPE_EAST = Block.box(10, 4, 4, 16, 12, 12);
    private static final VoxelShape PIPE_WEST = Block.box(0, 4, 4, 6, 12, 12);
    private static final VoxelShape PIPE_UP = Block.box(4, 10, 4, 12, 16, 12);
    private static final VoxelShape PIPE_DOWN = Block.box(4, 0, 4, 12, 6, 12);

    private static final VoxelShape PIPE_CENTER = Block.box(5, 5, 5, 11, 11, 11);

    public AuraPipeBlock(Properties properties) {
        super(properties);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        VoxelShape shape = PIPE_CENTER;
        for (Direction dir : Direction.values()) {
            if (canConnectTo(level, pos.relative(dir))) {
                shape = Shapes.joinUnoptimized(shape, getArmShape(dir), BooleanOp.OR);
            }
        }
        return shape.optimize();
    }

    private VoxelShape getArmShape(Direction dir) {
        return switch (dir) {
            case NORTH -> PIPE_NORTH;
            case SOUTH -> PIPE_SOUTH;
            case EAST -> PIPE_EAST;
            case WEST -> PIPE_WEST;
            case UP -> PIPE_UP;
            case DOWN -> PIPE_DOWN;
        };
    }

    private boolean canConnectTo(BlockGetter level, BlockPos pos) {
        BlockState state = level.getBlockState(pos);
        return state.getBlock() instanceof AuraPipeBlock
                || state.getBlock() instanceof AuraTankBlock
                || state.getBlock() instanceof AuraGeneratorBlock
                || state.getBlock() instanceof AuraCompressorBlock;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return ModBlockEntities.AURA_PIPE.create(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (level.isClientSide) return null;
        return (lvl, pos, st, be) -> {
            if (be instanceof AuraPipeBlockEntity pipe) {
                pipe.tick(lvl, pos, st);
            }
        };
    }
}
