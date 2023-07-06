package com.uxus.sprint.mixin;

import com.uxus.sprint.DrakoshaTosha;
import com.uxus.sprint.util.TPSHud;
import com.uxus.sprint.util.Utills;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

import static com.uxus.sprint.DrakoshaTosha.*;


@Mixin(InGameHud.class)
public abstract class hudMixin {

    MinecraftClient mc = MinecraftClient.getInstance();
    private ItemRenderer itemRenderer;

    int height;
    int width;
    ItemStack totemStack = new ItemStack(Items.TOTEM_OF_UNDYING);
    private List<String> playerNames = new ArrayList<>();


    @Inject(at = @At("TAIL"), method = "render")
    public void render(MatrixStack matrixStack, float tickDelta, CallbackInfo callbackInfo) {

        height = mc.getWindow().getScaledHeight();
        width = mc.getWindow().getScaledWidth();
        //matrixStack.push();
        //matrixStack.scale(0.7F, 0.7F, 0.7F);

        if(ping)mc.textRenderer.drawWithShadow(matrixStack,  "Ping: " + Utills.getPing() + "ms", pingx, height +pingy , color);

        if(tps)mc.textRenderer.drawWithShadow(matrixStack,  "Tps: " + TPSHud.tps , tpsx, height +tpsy , color);
        if(fps)mc.textRenderer.drawWithShadow(matrixStack,  AccessorMinecraftClient.getCurrentFps() + " fps" , fpsx, fpsy , color);

        if(geta() && sprinthud){
            mc.textRenderer.drawWithShadow(matrixStack, "Sprint [Toggled]", chatsprinthudx, height + chatsprinthudy, color);

        }
        if(getb() && sprinthud){
            mc.textRenderer.drawWithShadow(new MatrixStack(), "Sprint [Toggled]", sprinthudx, height + sprinthudy, color);

        }

        //matrixStack.pop();
        }
    /*private void renderSmallGuiItem(MatrixStack matrices, ItemStack itemStack) {
        MinecraftClient mc = MinecraftClient.getInstance();
        ItemRenderer itemRenderer = mc.getItemRenderer();
        MinecraftClient client = MinecraftClient.getInstance();
        int xx = 472;
        int yy = height-47;

        client.textRenderer.drawWithShadow(matrices, Utills.totemcount(), 476, height-47, 0xFFFFFF);

        // Render totem texture


        // Scale down the model by dividing the matrix scale
        float scale = 0.3f;
        matrices.push();
        matrices.scale(scale, scale, scale);

        // Render totem texture
        itemRenderer.renderGuiItemIcon(itemStack, xx, yy);


        matrices.pop();

        client.textRenderer.drawWithShadow(matrices, Utills.totemcount(), 475, height-47, 0xFFFFFF);



    }


    public void render(Entity entity) {
        if (entity instanceof PlayerEntity && entity != mc.player) {
            double distanceSq = mc.player.squaredDistanceTo(entity);
            double maxRenderDistance = MinecraftClient.getInstance().options.viewDistance * 16;
            if (distanceSq < maxRenderDistance * maxRenderDistance) {
                PlayerEntity player = (PlayerEntity) entity;
                playerNames.add(player.getEntityName());
            }
        }
    }*/



}

