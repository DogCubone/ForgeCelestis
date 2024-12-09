package aboe.forge.celestis.Items;


import aboe.forge.celestis.registers.ObjectRegister;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.function.Supplier;


public enum Materials implements Tier {
    ROSE_GOLD(0, 132, 10.0F, 1.0F, 20, () -> {
        return Ingredient.of((ItemLike) ObjectRegister.ROSE_GOLD_INGOT);
    }),
    VOLTITE(2, 560, 6.0F, 3.0F, 16, () -> {
        return Ingredient.of((ItemLike) ObjectRegister.VOLTITE_INGOT);
    });

    private final int miningLevel;
    private final int durability;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final Supplier<Ingredient> repairIngredient;

    Materials(int miningLevel, int durability, float speed, float damage, int enchantmentValue, Supplier<Ingredient> repairIngredient) {
        this.miningLevel = miningLevel;
        this.durability = durability;
        this.speed = speed;
        this.damage = damage;
        this.enchantmentValue = enchantmentValue;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getUses() {
        return this.durability;
    }

    @Override
    public float getSpeed() {
        return this.speed;
    }

    @Override
    public float getAttackDamageBonus() {
        return this.damage;
    }

    @Override
    public int getLevel() {
        return this.miningLevel;
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return (Ingredient)this.repairIngredient.get();
    }
}
