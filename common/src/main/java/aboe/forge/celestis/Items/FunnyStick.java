package aboe.forge.celestis.Items;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;


public class FunnyStick extends Item {

    public FunnyStick(Properties properties) {
        super(properties);
    }

    @Override
    public boolean hurtEnemy(ItemStack itemStack, LivingEntity enemy, LivingEntity sourceEntity) {
        if (sourceEntity instanceof Player player)
            pushAway(enemy, player);
        return true;
    }

    private void pushAway(Entity entity, Player player){
        byte multi = 3;
        double yAngle = player.getLookAngle().y;
        Vec3 sourceVec = player.getDeltaMovement();
        Vec3 entVector = entity.getDeltaMovement().multiply(3, 1.5,3);
        entVector = entVector.add(sourceVec.x, 0, sourceVec.z);


        if (yAngle >= 0.8)
           entVector = yAxisVec(entVector, multi, false);
        else if (yAngle <= -0.8)
            entVector = yAxisVec(entVector, multi, true);

       entity.setDeltaMovement(entVector);

//        player.displayClientMessage(Component.literal("VE: " + entVector + "\nYA:"  + yAngle), false);
    }

    private Vec3 yAxisVec(Vec3 vector, byte multiplier, boolean invert){
        double yAxis = vector.y;
        double newYAxis = (yAxis >= 0.0D) ? yAxis * multiplier : -yAxis;

        if (invert) newYAxis = -newYAxis * multiplier;

        return new Vec3(vector.x * 0, newYAxis, vector.z * 0);
    }

}
