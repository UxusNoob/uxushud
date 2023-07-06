package com.uxus.sprint.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.DownloadingTerrainScreen;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static com.uxus.sprint.DrakoshaTosha.noloadingterrain;

@Mixin(DownloadingTerrainScreen.class)
public abstract class DownloadingTerrainScreenMixin {
    MinecraftClient client = MinecraftClient.getInstance();

    @Overwrite
    public void tick() {
        if (this.client != null && this.client.player != null) {
            this.client.setScreen(null);
        }
    }
}

