package aboe.forge.celestis.Items;

import net.minecraft.Util;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.EnumMap;
import java.util.function.Supplier;

public enum ArmorMaterials implements ArmorMaterial {

    Slime_Ingot(37, Util.make(new EnumMap(ArmorItem.Type.class), (enumMap) -> {
        enumMap.put(ArmorItem.Type.BOOTS, 1);
        enumMap.put(ArmorItem.Type.LEGGINGS, 2);
        enumMap.put(ArmorItem.Type.CHESTPLATE, 4);
        enumMap.put(ArmorItem.Type.HELMET, 2);
    }), 15, SoundEvents.ARMOR_EQUIP_GENERIC, 0.0F, 0.8F, () -> {
        return Ingredient.of(new ItemLike[]{Items.NETHERITE_INGOT});
    });


    private final int durabilityMultiplier;
    private final EnumMap<ArmorItem.Type, Integer> protectionFunctionForType;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyLoadedValue<Ingredient> repairIngredient;

    private ArmorMaterials(int duraMultiplier, EnumMap enumMap, int enchantment, SoundEvent soundEvent, float toughness, float knockbackRes, Supplier supplier) {
        this.durabilityMultiplier = duraMultiplier;
        this.protectionFunctionForType = enumMap;
        this.enchantmentValue = enchantment;
        this.sound = soundEvent;
        this.toughness = toughness;
        this.knockbackResistance = knockbackRes;
        this.repairIngredient = new LazyLoadedValue(supplier);
    }

    @Override
    public int getDurabilityForType(ArmorItem.Type type) {
        return 0;
    }

    @Override
    public int getDefenseForType(ArmorItem.Type type) {
        return 0;
    }

    @Override
    public int getEnchantmentValue() {
        return 0;
    }

    @Override
    public SoundEvent getEquipSound() {
        return null;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return null;
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public float getToughness() {
        return 0;
    }

    @Override
    public float getKnockbackResistance() {
        return 0;
    }
}
