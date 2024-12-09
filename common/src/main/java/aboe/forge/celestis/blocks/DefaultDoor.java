package aboe.forge.celestis.blocks;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class DefaultDoor extends DoorBlock {

    public DefaultDoor() {
        super(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR).dynamicShape().noOcclusion(), BlockSetType.STONE);
    }


}
