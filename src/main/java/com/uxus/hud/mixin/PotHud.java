package com.uxus.hud.mixin;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.StatusEffectSpriteManager;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.uxus.hud.UxusHud.pothud;
import static com.uxus.hud.UxusHud.pothudy;

@Mixin(InGameHud.class)
@Environment(EnvType.CLIENT)
public class PotHud {
    /**
     * @author Samuel-Martineau
     * @reason Prevents the default status effects overlay from being rendered
     */
    @Overwrite()
    public void renderStatusEffectOverlay(MatrixStack matrices) {
        MinecraftClient client = MinecraftClient.getInstance();
        InGameHud inGameHud = client.inGameHud;
        TextRenderer textRenderer = inGameHud.getTextRenderer();
        int spriteSize = 18;

        assert client.player != null;
        Collection<StatusEffectInstance> statusEffectsCollection = client.player.getStatusEffects();

        if (!statusEffectsCollection.isEmpty() && pothud) {
            RenderSystem.enableBlend();
            StatusEffectSpriteManager statusEffectSpriteManager = client.getStatusEffectSpriteManager();
            List<Runnable> statusEffectsRunnableList = Lists.newArrayListWithExpectedSize(statusEffectsCollection.size());
            RenderSystem.setShaderTexture(0, HandledScreen.BACKGROUND_TEXTURE);
            Iterator<StatusEffectInstance> statusEffectsIterator = Ordering.natural().reverse().sortedCopy(statusEffectsCollection).iterator();

            int i = 0;

            while (statusEffectsIterator.hasNext()) {
                int x = 3;

                int y = spriteSize * i + pothudy;
                StatusEffectInstance statusEffectInstance = statusEffectsIterator.next();
                StatusEffect statusEffect = statusEffectInstance.getEffectType();

                Sprite statusEffectSprite = statusEffectSpriteManager.getSprite(statusEffect);

                statusEffectsRunnableList.add(() -> {
                    RenderSystem.setShaderTexture(0, statusEffectSprite.getAtlas().getId());
                    DrawableHelper.drawSprite(matrices, x, y, inGameHud.getZOffset(), spriteSize, spriteSize, statusEffectSprite);


                    int duration = statusEffectInstance.getDuration() / 20;
                    long mins = TimeUnit.SECONDS.toMinutes(duration);
                    long secs = duration - TimeUnit.MINUTES.toSeconds(mins);

                    String formattedDuration;
                    if (statusEffectInstance.isPermanent()) formattedDuration = "∞";
                    else if (mins == 0) formattedDuration = secs + " sec";
                    else formattedDuration = String.format("%d min, %d sec", mins, secs);

                    float textX = x + spriteSize + 3;
                    float textY = y + (spriteSize / 2f - textRenderer.fontHeight / 2.5f);

                    int color;
                    if (duration <= 5)
                        color = 0xFF5555;
                    else if (duration <= 15)
                        color = 0xFFAA00;
                    else if (duration <= 25)
                        color = 0xFFFF55;
                    else color = 0xFFFFFF;

                    textRenderer.draw(matrices, formattedDuration, textX, textY, color);
                });
                i++;
            }

            statusEffectsRunnableList.forEach(Runnable::run);
        }
    }
}

