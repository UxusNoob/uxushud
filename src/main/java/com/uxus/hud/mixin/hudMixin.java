package com.uxus.hud.mixin;

import com.uxus.hud.util.TPSHud;
import com.uxus.hud.util.Utills;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.uxus.hud.UxusHud.*;


@Mixin(InGameHud.class)
public abstract class hudMixin {

    MinecraftClient mc = MinecraftClient.getInstance();


    @Inject(at = @At("TAIL"), method = "render")
    public void render(MatrixStack matrixStack, float tickDelta, CallbackInfo callbackInfo) {

        //matrixStack.push();
        //matrixStack.scale(0.7F, 0.7F, 0.7F);

        if(ping)this.mc.textRenderer.drawWithShadow(matrixStack,  "Ping: " + Utills.getPing() + "ms", pingx, pingy , color);

        if(tps)this.mc.textRenderer.drawWithShadow(matrixStack,  "Tps: " + TPSHud.tps , tpsx, tpsy , color);
        if(fps)this.mc.textRenderer.drawWithShadow(matrixStack,  AccessorMinecraftClient.getCurrentFps() + " fps" , fpsx, fpsy , color);
        //mc.textRenderer.drawWithShadow(matrixStack,  getAPMCount() + " APM" , 3, 11 , color);
        if(geta() && sprinthud){
            this.mc.textRenderer.drawWithShadow(matrixStack, "Sprint [Toggled]", sprinthudx, sprinthudy-17, color);

        }
        if(getb() && sprinthud){
            this.mc.textRenderer.drawWithShadow(new MatrixStack(), "Sprint [Toggled]", sprinthudx, sprinthudy, color);

        }
        //matrixStack.pop();
        }

}

