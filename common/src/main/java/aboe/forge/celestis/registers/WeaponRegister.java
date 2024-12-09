package aboe.forge.celestis.registers;

import aboe.forge.celestis.ForgeCelestis;
import aboe.forge.celestis.Items.Materials;
import aboe.forge.celestis.Items.WeaponBase;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;

public class WeaponRegister {
    public static final DeferredRegister<Item> weapon = DeferredRegister.create(ForgeCelestis.MOD_ID, Registries.ITEM);

    public static void init() {
        weapon.register();
    }

    //region Custom var
    private static int halberdBaseDamage = 3;
    private static float halberdSpeed = -2f;
    private static int rapierBaseDamage = 2;
    private static float rapierSpeed = -1f;
    //endregion

    //region Halberd
    public static final RegistrySupplier<WeaponBase> ROSE_GOLD_Halberd = weapon.register("rose_gold_halberd", ()
            -> new WeaponBase(Materials.ROSE_GOLD, halberdBaseDamage, halberdSpeed, new Item.Properties()));


    //endregion Halberd

}