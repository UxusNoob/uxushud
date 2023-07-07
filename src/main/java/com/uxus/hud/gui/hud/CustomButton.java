package com.uxus.hud.gui.hud;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

public class CustomButton extends ButtonWidget {
    private static final int BUTTON_WIDTH = 200;
    private static final int BUTTON_HEIGHT = 20;

    private final MinecraftClient client;
    private final TextRenderer textRenderer;

    public CustomButton(int x, int y, String text, PressAction onPress) {
        super(x, y, BUTTON_WIDTH, BUTTON_HEIGHT, Text.of(text), onPress);

        client = MinecraftClient.getInstance();
        textRenderer = client.textRenderer;
    }



    @Override
    public void renderButton(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        Text buttonText = getMessage();

        int buttonColor = 0xFF000000;
        if (active) {
            if (isHovered()) {
                buttonColor = 0xFFAAAAAA;
            } else {
                buttonColor = 0xFF888888;
            }
        }

        fill(matrices, x, y, x + width, y + height, buttonColor);

        int textColor = 0xFFFFFF;
        int textX = x + (width - textRenderer.getWidth(buttonText)) / 2;
        int textY = y + (height - textRenderer.fontHeight) / 2;
        drawTextWithShadow(matrices, textRenderer, buttonText, textX, textY, textColor);
    }

    public static void drawTextWithShadow(MatrixStack matrices, TextRenderer textRenderer, Text text, int x, int y, int color) {
        textRenderer.drawWithShadow(matrices, text, x, y, color);
    }
}

