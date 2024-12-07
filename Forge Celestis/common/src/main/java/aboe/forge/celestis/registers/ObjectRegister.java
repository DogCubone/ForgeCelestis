package aboe.forge.celestis.registers;

import aboe.forge.celestis.ForgeCelestis;
import aboe.forge.celestis.blocks.DefaultBlock;
import aboe.forge.celestis.blocks.DefaultDoor;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DoubleHighBlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
public class ObjectRegister {

    public static void init(){
        items.register();
        blocks.register();
    }

    public static final DeferredRegister<Item> items = DeferredRegister.create(ForgeCelestis.MOD_ID, Registries.ITEM);
    public static final DeferredRegister<Block> blocks = DeferredRegister.create(ForgeCelestis.MOD_ID, Registries.BLOCK);

    private static RegistrySupplier<Item> edibleBlock(RegistrySupplier<Block> block, int nutrition, float saturation, int maxStack){
        return items.register(block.getId().getPath(), () -> new BlockItem(block.get(), new
                Item.Properties().food(new FoodProperties.Builder()
                .nutrition(nutrition).saturationMod(saturation)
                .build()).stacksTo(maxStack)));
    }

    private static RegistrySupplier<Item> blockItem(RegistrySupplier<Block> block){
    return items.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties()));
    }

    private static RegistrySupplier<Item> doubleBlockItem(RegistrySupplier<Block> block) {
        return items.register(block.getId().getPath(), () -> new DoubleHighBlockItem(block.get(), new Item.Properties()));
    }

    //Items:


    //Blocks:
    public static final RegistrySupplier<Block> BASIC_FORGE = blocks.register("basic_forge", () -> new DefaultBlock(true));
    public static final RegistrySupplier<Block> ROSE_GOLD = blocks.register("rose_gold", () -> new DefaultBlock());
    public static final RegistrySupplier<Block> VOLTITE = blocks.register("voltite", () -> new DefaultBlock());
    public static final RegistrySupplier<Block> ROSE_GOLD_DOOR = blocks.register("rose_gold_door", () -> new DefaultDoor());

    //Block Item
    public static final RegistrySupplier<Item> ROSE_GOLD_INGOT = blockItem(ROSE_GOLD);
    public static final RegistrySupplier<Item> VOLTITE_INGOT = blockItem(VOLTITE);
    public static final RegistrySupplier<Item> BASIC_FORGE_ITEM = blockItem(BASIC_FORGE);
    public static final RegistrySupplier<Item> ROSE_GOLD_DOOR_Item = doubleBlockItem(ROSE_GOLD_DOOR);

}
