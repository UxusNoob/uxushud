package com.uxus.hud.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.HungerManager;
import net.minecraft.util.math.MathHelper;

public class HungerSaturationHUD extends DrawableHelper {

    public static void render(MatrixStack matrices, int screenHeight) {
        MinecraftClient client = MinecraftClient.getInstance();
        HungerManager hungerManager = client.player.getHungerManager();
        int saturationLevel = MathHelper.ceil(hungerManager.getSaturationLevel());

        renderSaturationLevel(matrices, screenHeight, saturationLevel);
    }

    private static void renderSaturationLevel(MatrixStack matrices, int screenHeight, int saturationLevel) {
        MinecraftClient client = MinecraftClient.getInstance();
        TextRenderer textRenderer = client.textRenderer;
        matrices.push();
        matrices.scale(0.89f, 0.89f, 0.89f);

        int xOffset = 632;
        int yOffset = screenHeight+13;

        int barColor = getHungerBarColor(saturationLevel);

        textRenderer.drawWithShadow(matrices, String.valueOf(saturationLevel), xOffset, yOffset, barColor);
        matrices.pop();

    }

    private static int getHungerBarColor(int saturationLevel) {
        float hue = MathHelper.clamp((float) saturationLevel / 20.0f, 0.0f, 1.0f);
        int rgb = java.awt.Color.HSBtoRGB(hue / 3.0f, 0.8f, 1.0f);
        return rgb | 0xFF000000;
    }
}
