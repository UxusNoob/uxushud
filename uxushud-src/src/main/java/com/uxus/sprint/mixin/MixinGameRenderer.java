package com.uxus.sprint.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.resource.SynchronousResourceReloader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.uxus.sprint.DrakoshaTosha.nohurtcam;

@Environment(EnvType.CLIENT)
@Mixin(GameRenderer.class)
public abstract class MixinGameRenderer implements SynchronousResourceReloader, AutoCloseable {

    @Inject(method = "bobViewWhenHurt", at = @At("HEAD"), cancellable = true)
    private void NoHurtCam(MatrixStack matrices, float f, CallbackInfo callbackInfo) {
        if(nohurtcam)callbackInfo.cancel();
    }

}

