package de.lukashd2.testmod.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class TinyCubeLightBlock extends HorizontalFacingBlock {
    public static final IntProperty LIGHT_LEVEL = IntProperty.of("light_level", 0, 15);

    public static final MapCodec<TinyCubeLightBlock> CODEC = createCodec(TinyCubeLightBlock::new);
    private static final VoxelShape SHAPE = Block.createCuboidShape(4.0, 0.0, 4.0, 12.0, 8.0, 12.0);

    public TinyCubeLightBlock(Settings settings) {
        super(settings.luminance(state -> state.get(LIGHT_LEVEL)));
        setDefaultState(getStateManager().getDefaultState().with(LIGHT_LEVEL, 0));
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    protected MapCodec<? extends HorizontalFacingBlock> getCodec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LIGHT_LEVEL);
        builder.add(FACING);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if(!world.isClient){

            int currentLight = state.get(LIGHT_LEVEL);
            int newLight;
            if(currentLight == 0){
                newLight = 15;
            } else {
                newLight = 0;
            }

            world.setBlockState(pos, state.with(LIGHT_LEVEL, newLight), Block.NOTIFY_ALL);
        }
        return ActionResult.SUCCESS;
    }
}
