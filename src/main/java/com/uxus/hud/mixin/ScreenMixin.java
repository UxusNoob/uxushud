package com.uxus.hud.mixin;

import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.uxus.hud.UxusHud.noguibg;

@Mixin(value = Screen.class, priority = 500)
public abstract class ScreenMixin {
    @Inject(method = "renderBackground(Lnet/minecraft/client/util/math/MatrixStack;)V", at = @At("HEAD"), cancellable = true)
    private void onRenderBackground(CallbackInfo info) {
        if(noguibg)info.cancel();
    }


}
