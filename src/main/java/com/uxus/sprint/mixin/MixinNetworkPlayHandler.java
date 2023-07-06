package com.uxus.sprint.mixin;

import com.uxus.sprint.util.TPSHud;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.WorldTimeUpdateS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public class MixinNetworkPlayHandler {

    @Inject(method = "onWorldTimeUpdate", at = @At("HEAD"))
    private void onWorldUpdate(WorldTimeUpdateS2CPacket packet, CallbackInfo ci) {
        TPSHud.updateTime(packet.getTime());
    }




}
