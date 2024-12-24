package aboe.forge.celestis.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class IngotBlock extends FallingBlock implements SimpleWaterloggedBlock {
    public static final IntegerProperty StoredIngots = IntegerProperty.create("stored_ingots", 1, 4) ;
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public IngotBlock() {
        super(BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(0.3f));
        this.registerDefaultState(this.defaultBlockState()
                .setValue(StoredIngots, 1)
                .setValue(WATERLOGGED, false)
                .setValue(FACING, Direction.NORTH));
    }

    //region Add Ingots to the block
    @Override
    public @NotNull InteractionResult use(BlockState blockState, Level world, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        ItemStack item = player.getItemInHand(interactionHand);
        int storedIngots = blockState.getValue(StoredIngots);
        boolean isClient = world.isClientSide;

        if (player.isCrouching()){
            if (!isClient) {
                //Handles removing the block/changing block state
                if (storedIngots <= 1)
                    world.removeBlock(blockPos, true);
                else
                    world.setBlockAndUpdate(blockPos, blockState.setValue(StoredIngots, storedIngots - 1));

                //Adds item to the players inventory and plays a lil sound
                player.addItem(new ItemStack(blockState.getBlock().asItem(), 1));
                world.playSound(null, blockPos,SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1.0F, 1.0F);
            }
            return InteractionResult.sidedSuccess(isClient);
        }
        else if (blockState.getBlock() == Block.byItem(item.getItem()) && storedIngots < 4){
            if (!isClient) {
                world.setBlockAndUpdate(blockPos, blockState.cycle(StoredIngots));

                //Misc Stuff, plays a lil sound and adds the item to the used stats of the player (IDK why I did that)
                player.awardStat(Stats.ITEM_USED.get(item.getItem()));
                world.playSound(null, blockPos,SoundEvents.STONE_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);

                //Removes item of the inventory if the player isn't in creative mode
                if (!player.getAbilities().instabuild) item.shrink(1);
            }
            return InteractionResult.sidedSuccess(isClient);
        }
        else return InteractionResult.PASS;
    }
    //endregion Add ingot to the block


    //Some misc code that I stole from MCreator generated code
    @Override
    public @NotNull FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
        return state.getFluidState().isEmpty();
    }

    @Override
    public @NotNull VoxelShape getVisualShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return Shapes.empty();
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            default -> box(3, 0, 3, 14, 8, 14);
            case NORTH -> box(2, 0, 2, 13, 8, 13);
            case EAST -> box(3, 0, 2, 14, 8, 13);
            case WEST -> box(2, 0, 3, 13, 8, 14);
        };
    }

    @Override
    public @NotNull BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor world, BlockPos currentPos, BlockPos facingPos) {
        if (state.getValue(WATERLOGGED)) {
            world.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
        }
        return super.updateShape(state, facing, facingState, world, currentPos, facingPos);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        boolean flag = context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER;
        return super.getStateForPlacement(context).setValue(FACING, context.getHorizontalDirection()).setValue(WATERLOGGED, flag);
    }

    public @NotNull BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    public @NotNull BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, StoredIngots, WATERLOGGED);
    }

}
