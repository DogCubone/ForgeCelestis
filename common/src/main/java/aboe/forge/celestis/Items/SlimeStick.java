package aboe.forge.celestis.Items;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.phys.Vec3;

import java.util.Objects;


public class SlimeStick extends Item {

    public SlimeStick(Properties properties) {
        super(properties);
    }

    @Override
    public boolean hurtEnemy(ItemStack itemStack, LivingEntity enemy, LivingEntity sourceEntity) {
        if (sourceEntity instanceof Player player)
            pushAway(enemy, player);
        return true;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        playerAway(Objects.requireNonNull(context.getPlayer()), context.getItemInHand());
        return InteractionResult.SUCCESS;
    }

    private void playerAway(Player player, ItemStack item){
        Vec3 playerVec = player.getDeltaMovement().multiply(4.2, 1.2, 4.2);
        player.displayClientMessage(Component.literal("V: " + playerVec), false);
        if (Math.abs(playerVec.x) < 0.6 && Math.abs(playerVec.z) < 0.6 && Math.abs(playerVec.y) < 0.4) return; //Ignores if not moving

        boolean lookingUp = player.getLookAngle().y > 0.7;
        Vec3 throwVec = createVector(playerVec, lookingUp);

        if (player.isCrouching()) throwVec = throwVec.multiply(-1.4, 1, -1.4);

        player.getCooldowns().addCooldown(item.getItem(), 15);
        player.setDeltaMovement(throwVec);
    }

    private Vec3 createVector(Vec3 vector3D, boolean invertY){
        return new Vec3(vector3D.x, absYVec(vector3D.y, 2, invertY), vector3D.z);
    }

    private void pushAway(Entity entity, Player player){
        double multi = 2.6;
        double yAngle = player.getLookAngle().y;
        boolean invert = yAngle < -0.7;

        //They are separated cuz they look dead, Vec3 should have a color :(
        Vec3 sourceVec = player.getDeltaMovement();
        Vec3 entVector = entity.getDeltaMovement().multiply(3, 1.5,3).add(sourceVec.x, 0, sourceVec.z);

        // A code that makes you either go to the haven or go to hell :3
        if (yAngle >= 0.7 || yAngle <= -0.7)
           entVector = new Vec3(0, absYVec(entVector.y, multi, invert), 0);

       entity.setDeltaMovement(entVector);
    }

    private double absYVec(double yAxis, double multiplier, boolean invert){
        yAxis = (yAxis >= 0.0D) ? yAxis * multiplier : -yAxis; //Custom "Math.abs" to avoid always multiplying

        if (invert) yAxis = -yAxis * multiplier; // It's pretty much a "slam" code

        return yAxis;
    }

}
