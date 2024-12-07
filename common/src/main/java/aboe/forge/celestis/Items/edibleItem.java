package aboe.forge.celestis.Items;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

public class edibleItem extends Item {

    public edibleItem(int nutrition, float saturation, int maxStack) {
        super(new Item.Properties().food(new FoodProperties.Builder().nutrition(nutrition).saturationMod(saturation).build()).stacksTo(maxStack));
    }
}
