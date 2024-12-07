package aboe.forge.celestis.forge;

import aboe.forge.celestis.ForgeCelestis;
import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ForgeCelestis.MOD_ID)
public final class ForgeCelestisForge {
    public ForgeCelestisForge() {
        // Submit our event bus to let Architectury API register our content on the right time.
        EventBuses.registerModEventBus(ForgeCelestis.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());

        // Run our common setup.
        ForgeCelestis.init();
    }
}
