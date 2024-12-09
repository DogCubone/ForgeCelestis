package aboe.forge.celestis.datagen;

import aboe.forge.celestis.registers.ObjectRegister;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;

import java.util.Set;

public class BlockLoot extends BlockLootSubProvider {

    protected BlockLoot(FeatureFlagSet featureFlagSet) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
    dropSelf(ObjectRegister.ROSE_GOLD_DOOR.get());


   }


}
