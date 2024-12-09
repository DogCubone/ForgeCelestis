package aboe.forge.celestis.Items;

import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class WeaponBase extends SwordItem {
    public WeaponBase(Tier material, int baseDamage, float baseSpeed, Properties properties) {
        super(material, baseDamage, baseSpeed, properties);
    }
}
