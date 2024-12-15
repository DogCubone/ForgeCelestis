package aboe.forge.celestis.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public class LampBlock extends Block {
    public static final BooleanProperty LIGHTS_ON = BooleanProperty.create("lights_on");
    public static final BooleanProperty POWERED = BooleanProperty.create("powered");

    public LampBlock() {
        super(BlockBehaviour.Properties.of()
                .sound(SoundType.STONE).strength(1.2f)
                .lightLevel(state -> state.getValue(LIGHTS_ON) ? 15 : 0)
                .isRedstoneConductor((bs, br, bp) -> false));
        this.registerDefaultState(this.defaultBlockState().setValue(LIGHTS_ON, false).setValue(POWERED, false));
    }

    @Override
    public void onPlace(BlockState blockState, Level world, BlockPos blockPos, BlockState blockState2, boolean bl) {
        if (!world.isClientSide)
            this.alternateLight(blockState, world, blockPos);
    }

    @Override
    public void neighborChanged(BlockState state, Level world, BlockPos pos, Block block, BlockPos blockPos2, boolean bl) {
            if (!world.isClientSide)
                this.alternateLight(state, world, pos);
    }

    //It's pretty much the copper bulb code XD
    public void alternateLight(BlockState blockState, Level world, BlockPos pos){
        BlockState newState = blockState;
        boolean hasPower = world.hasNeighborSignal(pos);

        if (hasPower != blockState.getValue(POWERED)) {
            if (hasPower)
                newState = newState.cycle(LIGHTS_ON);
            world.setBlock(pos, newState.setValue(POWERED, hasPower), 3);
        }
    }


    @Override
    public boolean hasAnalogOutputSignal(BlockState blockState) {
        return true;
    }

    public int getAnalogOutputSignal(BlockState blockState, Level level, BlockPos blockPos) {
        return blockState.getValue(LIGHTS_ON) ? 15 : 0;
    }

    public boolean canConnectRedstone(BlockState state, BlockGetter world, BlockPos pos, Direction side) {
        return true;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LIGHTS_ON).add(POWERED);
    }
}
