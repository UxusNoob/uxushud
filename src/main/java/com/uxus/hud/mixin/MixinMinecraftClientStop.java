package com.uxus.hud.mixin;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

import static com.uxus.hud.UxusHud.*;

@Mixin(MinecraftClient.class)
public class MixinMinecraftClientStop {
    @Inject(at = @At("HEAD"), method = "stop()V")
    private void onStop(CallbackInfo ci) {

        Path configPath = FabricLoader.getInstance().getConfigDir().resolve("uxushud_config.txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(configPath.toFile()))) {
            writer.write("Nohurtcam=" + nohurtcam);
            writer.newLine();
            writer.write("PotHud=" + pothud);
            writer.newLine();
            writer.write("BetterHitbohes=" + betterhitboxes);
            writer.newLine();
            writer.write("NoLoadingTerrain=" + noloadingterrain);

            writer.newLine();
            writer.write("Tps=" + tps);
            writer.newLine();
            writer.write("Ping=" + ping);
            writer.newLine();
            writer.write("Fps=" + fps);

            writer.newLine();
            writer.write("SprintToggled=" + sprinthud);
            writer.newLine();
            writer.write("SpectatorCameraClip=" + spectatorcameraclip);
            writer.newLine();
            writer.write("NoBackground=" + nobackground);
            writer.newLine();
            writer.write("NoGuiBg=" + noguibg);
            writer.newLine();
            writer.write("SaturationHud=" + saturationhud);

            writer.newLine();
            writer.write("SprintToggledX=" + sprinthudx);
            writer.newLine();
            writer.write("SprintToggledY=" + sprinthudy);
            writer.newLine();
			/*writer.write("ChatSprintToggledX=" + chatsprinthudx);
			writer.newLine();
			writer.write("ChatSprintToggledY=" + chatsprinthudx);*/

            writer.newLine();
            writer.write("TpsX=" + tpsx);
            writer.newLine();
            writer.write("TpsY=" + tpsy);
            writer.newLine();
            writer.write("PingX=" + pingx);
            writer.newLine();
            writer.write("PingY=" + pingy);

            writer.newLine();
            writer.write("FpsX=" + fpsx);
            writer.newLine();
            writer.write("FpsY=" + fpsy);
            writer.newLine();
            writer.write("PotHudY=" + pothudy);


            writer.newLine();
            writer.write("Color=" + color);
            System.out.println("EZZZZZZZZZZZZZZZZZZZZZZ");
        } catch (IOException e) {
        }


    }
}
