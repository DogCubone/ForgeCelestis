package aboe.forge.celestis.datagen;

import net.minecraft.WorldVersion;
import net.minecraft.data.DataGenerator;

import java.nio.file.Path;

public class BlockStateGen extends DataGenerator {
    public BlockStateGen(Path path, WorldVersion worldVersion, boolean bl) {
        super(path, worldVersion, bl);
    }
}
