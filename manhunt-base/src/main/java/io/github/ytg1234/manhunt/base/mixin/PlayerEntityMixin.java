package io.github.ytg1234.manhunt.base.mixin;

import io.github.ytg1234.manhunt.base.ManhuntUtils;
import io.github.ytg1234.manhunt.base.config.ManhuntConfigEnums;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {
    @Shadow
    public abstract boolean isInvulnerableTo(DamageSource damageSource);

    private PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "damage(Lnet/minecraft/entity/damage/DamageSource;F)Z", at = @At(value = "TAIL"), cancellable = true)
    private void damage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (!world.isClient()) {
            if (source.getAttacker() != null) {
                if (source.getAttacker() instanceof PlayerEntity &&
                    ManhuntUtils.hunters.contains(source.getAttacker().getUuid()) &&
                    ManhuntUtils.CONFIG.damageBehaviour.equals(ManhuntConfigEnums.Damage.DAMAGE) &&
                    !isInvulnerableTo(source)) {
                    cir.setReturnValue(super.damage(source, Float.MAX_VALUE));
                }
            }
        }
    }
}
