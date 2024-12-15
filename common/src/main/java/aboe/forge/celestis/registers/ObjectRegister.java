package aboe.forge.celestis.registers;

import aboe.forge.celestis.ForgeCelestis;
import aboe.forge.celestis.Items.FunnyStick;
import aboe.forge.celestis.blocks.DefaultBlock;
import aboe.forge.celestis.blocks.DefaultDoor;
import aboe.forge.celestis.blocks.LampBlock;
import aboe.forge.celestis.blocks.TransparentBlock;
import aboe.forge.celestis.blocks.IngotBlock;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DoubleHighBlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;


public class ObjectRegister {

    public static void init(){
        blocks.register();
        items.register();
    }

    public static final DeferredRegister<Item> items = DeferredRegister.create(ForgeCelestis.MOD_ID, Registries.ITEM);
    public static final DeferredRegister<Block> blocks = DeferredRegister.create(ForgeCelestis.MOD_ID, Registries.BLOCK);


    //region Registrador de Blocos comestiveis
    private static RegistrySupplier<Item> edibleBlock(RegistrySupplier<Block> block, int nutrition, float saturation, int maxStack){
        return items.register(block.getId().getPath(), () -> new BlockItem(block.get(), new
                Item.Properties().food(new FoodProperties.Builder()
                .nutrition(nutrition).saturationMod(saturation)
                .build()).stacksTo(maxStack)));
    }
    //endregion Registrador de Blocos comestiveis


    //region Registrador de item de bloco.

    //Registra um item para o bloco
    private static RegistrySupplier<Item> blockItem(RegistrySupplier<Block> block){
    return items.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties()));
    }

    private static RegistrySupplier<Item> blockItem(RegistrySupplier<Block> block, Rarity rarity){
        return items.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties().rarity(rarity)));
    }


    //Registra item de blocos e automaticamente coloca eles na aba "Blocos de construção"
    private static RegistrySupplier<Item> buildingBlockItem(RegistrySupplier<Block> block){
        return items.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties().arch$tab(TabRegister.BLOCKS_TAB)));
    }


    //Registra um item para um bloco duplo (como portas)
    private static RegistrySupplier<Item> doubleBlockItem(RegistrySupplier<Block> block) {
        return items.register(block.getId().getPath(), () -> new DoubleHighBlockItem(block.get(), new Item.Properties().arch$tab(TabRegister.BLOCKS_TAB)));
    }
    //endregion Registrador de item de bloco.


    //region Items:
    public static final RegistrySupplier<Item> SLIME_STICK = items.register("slime_stick", () -> new FunnyStick(new Item.Properties()));
    //endregion Items

    //region Blocks:
    public static final RegistrySupplier<Block> ROSE_GOLD_BULB = blocks.register("rose_gold_bulb", () -> new LampBlock());
    public static final RegistrySupplier<Block> BASIC_FORGE = blocks.register("basic_forge", () -> new DefaultBlock(true));
    public static final RegistrySupplier<Block> ROSE_GOLD_DOOR = blocks.register("rose_gold_door", DefaultDoor::new);
    public static final RegistrySupplier<Block> ROSE_GOLD_GRID = blocks.register("rose_gold_grid", TransparentBlock::new);
    //endregion Blocks

    //region Ingot Stuff
    public static final RegistrySupplier<Block> ROSE_GOLD = blocks.register("rose_gold", IngotBlock::new);
    public static final RegistrySupplier<Block> VOLTITE = blocks.register("voltite", IngotBlock::new);
    //ingot item
    public static final RegistrySupplier<Item> ROSE_GOLD_INGOT = blockItem(ROSE_GOLD);
    public static final RegistrySupplier<Item> VOLTITE_INGOT = blockItem(VOLTITE);
    //endregion Ingot Stuff


    //region Block Item
    public static final RegistrySupplier<Item> BASIC_FORGE_ITEM = blockItem(BASIC_FORGE);
    public static final RegistrySupplier<Item> ROSE_GOLD_BULB_ITEM = blockItem(ROSE_GOLD_BULB);
    public static final RegistrySupplier<Item> ROSE_GOLD_DOOR_ITEM = doubleBlockItem(ROSE_GOLD_DOOR);
    public static final RegistrySupplier<Item> ROSE_GOLD_GRID_ITEM = buildingBlockItem(ROSE_GOLD_GRID);
    //endregion Block Item
}
