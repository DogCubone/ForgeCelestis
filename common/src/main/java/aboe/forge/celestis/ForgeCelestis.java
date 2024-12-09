package aboe.forge.celestis;

import aboe.forge.celestis.registers.ObjectRegister;
import aboe.forge.celestis.registers.TabRegister;
import aboe.forge.celestis.registers.WeaponRegister;

public final class ForgeCelestis {
    public static final String MOD_ID = "forge_celestis";

    public static void init() {
        ObjectRegister.init();
        WeaponRegister.init();
        TabRegister.init();
    }
}
