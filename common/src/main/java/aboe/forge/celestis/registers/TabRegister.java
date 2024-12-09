package aboe.forge.celestis.registers;

import aboe.forge.celestis.ForgeCelestis;
import dev.architectury.registry.CreativeTabOutput;
import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class TabRegister {

    public static void init(){
    TABS.register();
    }
    
    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(ForgeCelestis.MOD_ID, Registries.CREATIVE_MODE_TAB);


    public static final RegistrySupplier<CreativeModeTab> BLOCKS_TAB = TABS.register(
            "bulding_blocks", // Tab ID
            () -> CreativeTabRegistry.create(
                    Component.translatable("forge_celestis.blocks_tab"), // Tab Name
                    () -> new ItemStack(ObjectRegister.ROSE_GOLD_DOOR_ITEM.get()) // Icon
            )
    );

    public static final RegistrySupplier<CreativeModeTab> MISC_ITEM_TAB = TABS.register("misc_item",
            () -> new CreativeModeTab.Builder(CreativeModeTab.Row.TOP, 1).title(Component.translatable("forge_celestis.misc_item"))
                    .icon(() -> new ItemStack(ObjectRegister.ROSE_GOLD_INGOT.get())).displayItems(((itemDisplayParameters, add) -> {
                        add.accept(new ItemStack(ObjectRegister.ROSE_GOLD_INGOT.get()));
                        add.accept(new ItemStack(ObjectRegister.VOLTITE_INGOT.get()));
                    }))
                    .build());
}
