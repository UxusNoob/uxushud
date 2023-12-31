package com.uxus.sprint;

import com.uxus.sprint.util.HungerSaturationHUD;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.event.client.ClientTickCallback;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.math.MatrixStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;


public class DrakoshaTosha implements ModInitializer {

	public static final String MOD_ID = "DragonClick";
	public static MinecraftClient mc;
	static boolean a = false;
	static boolean b = false;

	public static boolean nohurtcam, tps, pothud, ping, fps, sprinthud, noloadingterrain, betterhitboxes, spectatorcameraclip = true;
	public static int pothudy = 240;
	public static int pingx = 610;
	public static int pingy = -20;
	public static int fpsx = 3;
	public static int fpsy = 3;
	public static int sprinthudx = 2;
	public static int sprinthudy = -10;

	public static int chatsprinthudx = 2;
	public static int chatsprinthudy = -25;

	public static int tpsx = 609;
	public static int tpsy = -10;

	public static boolean nobackground, noguibg, saturationhud = false;

	public static int color = 0x55FFFF;
	private static KeyBinding SPRINT_KEY_BINDING;

	//private static final Identifier CUSTOM_SCREEN_ID = new Identifier("uxus", "custom_screen");

	public static final Logger LOGGER = LoggerFactory.getLogger("DrakoshaTosha");

	@Override
	public void onInitialize() {

		if(saturationhud)HudRenderCallback.EVENT.register((matrices, tickDelta) -> renderHUD(matrices));

		LOGGER.info("Initializing MyMod");

		loadConfig();

		// Access the configuration values
		LOGGER.info("Nohurtcam: " + nohurtcam);
		LOGGER.info("PotHud: " + pothud);
		LOGGER.info("PotHudY: " + pothudy);


		LOGGER.info("BetterHitbohes: " + betterhitboxes);
		LOGGER.info("NoLoadingTerrain: " + noloadingterrain);
		LOGGER.info("Tps: " + tps);
		LOGGER.info("TpsX: " + tpsx);
		LOGGER.info("TpsY: " + tpsy);

		LOGGER.info("Ping: " + ping);
		LOGGER.info("PingX: " + pingx);
		LOGGER.info("PingY: " + pingy);


		LOGGER.info("Fps: " + fps);
		LOGGER.info("FpsX: " + fpsx);
		LOGGER.info("FpsY: " + fpsy);

		LOGGER.info("SprintToggled: " + sprinthud);
		LOGGER.info("SprintToggledX: " + sprinthudx);
		LOGGER.info("SprintToggledY: " + sprinthudy);
		LOGGER.info("ChatSprintToggledX: " + chatsprinthudx);
		LOGGER.info("ChatSprintToggledY: " + chatsprinthudx);


		LOGGER.info("SpectatorCameraClip: " + spectatorcameraclip);
		LOGGER.info("NoBackground: " + nobackground);
		LOGGER.info("NoGuiBg: " + noguibg);
		LOGGER.info("SaturationHud: " + saturationhud);

		LOGGER.info("Color: " + color);


		ClientLifecycleEvents.CLIENT_STARTED.register(client -> {
			MinecraftClient mc = client;

			// Retrieve the sprint key binding from the player's options
			SPRINT_KEY_BINDING = mc.options.sprintKey;

			// Register a client tick event
			ClientTickCallback.EVENT.register(clientTick -> {
				// Check if the sprint key binding is pressed
				if (SPRINT_KEY_BINDING.isPressed()) {

					// Sprint key is pressed, execute the desired action
					if (mc.currentScreen instanceof ChatScreen) {
						a=true;
						b=false;
					} else {
						b=true;
						a=false;

					}
				}
				if (!SPRINT_KEY_BINDING.isPressed()) {
					a=false;
					b=false;
				}
				});
		});
	}
	private void renderHUD(MatrixStack matrices) {
		MinecraftClient client = MinecraftClient.getInstance();
		int screenWidth = client.getWindow().getScaledWidth();
		int screenHeight = client.getWindow().getScaledHeight();

		// Render the custom HUD element
		HungerSaturationHUD.render(matrices, screenWidth, screenHeight);
	}

	private void loadConfig() {
		Path configPath = FabricLoader.getInstance().getConfigDir().resolve("uxushud_config.txt");

		if (Files.exists(configPath)) {
			try (BufferedReader reader = new BufferedReader(new FileReader(configPath.toFile()))) {
				String line;
				while ((line = reader.readLine()) != null) {
					String[] parts = line.split("=");
					if (parts.length == 2) {
						String key = parts[0].trim();
						String value = parts[1].trim();
						parseConfigValue(key, value);
					}
				}
			} catch (IOException e) {
				LOGGER.error("Failed to load config file", e);
			}
		} else {
			LOGGER.warn("Config file not found, using default values.");
		}

		saveConfig();
	}




	public static boolean geta(){
		return a;
	}

	public static boolean getb(){
		return b;
	}
	private void parseConfigValue(String key, String value) {
		switch (key) {
			case "Nohurtcam":
				nohurtcam = Boolean.parseBoolean(value);
				break;
			case "PotHud":
				pothud = Boolean.parseBoolean(value);
				break;
			case "BetterHitbohes":
				betterhitboxes = Boolean.parseBoolean(value);
				break;
			case "NoLoadingTerrain":
				noloadingterrain = Boolean.parseBoolean(value);
				break;
			case "Tps":
				tps = Boolean.parseBoolean(value);
				break;
			case "Ping":
				ping = Boolean.parseBoolean(value);
				break;
			case "Fps":
				fps = Boolean.parseBoolean(value);
				break;
			case "SprintToggled":
				sprinthud = Boolean.parseBoolean(value);
				break;
			case "SpectatorCameraClip":
				spectatorcameraclip = Boolean.parseBoolean(value);
				break;
			case "NoBackground":
				nobackground = Boolean.parseBoolean(value);
				break;
			case "NoGuiBg":
				noguibg = Boolean.parseBoolean(value);
				break;
			case "SaturationHud":
				saturationhud = Boolean.parseBoolean(value);
				break;
			case "SprintToggledX":
				sprinthudx = Integer.parseInt(value);
				break;
			case "SprintToggledY":
				sprinthudy = Integer.parseInt(value);
				break;
			case "ChatSprintToggledX":
				chatsprinthudx = Integer.parseInt(value);
				break;
			case "TpsX":
				tpsx = Integer.parseInt(value);
				break;
			case "TpsY":
				tpsy = Integer.parseInt(value);
				break;
			case "PingX":
				pingx = Integer.parseInt(value);
				break;
			case "PingY":
				pingy = Integer.parseInt(value);
				break;
			case "FpsX":
				fpsx = Integer.parseInt(value);
				break;
			case "FpsY":
				fpsy = Integer.parseInt(value);
				break;
			case "PotHudY":
				pothudy = Integer.parseInt(value);
				break;



			case "Color":
				color = Integer.parseInt(value);


			default:
				LOGGER.warn("Unknown config key: " + key);
				break;
		}
	}

	private void saveConfig() {
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
			writer.write("ChatSprintToggledX=" + chatsprinthudx);
			writer.newLine();
			writer.write("ChatSprintToggledY=" + chatsprinthudx);

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

		} catch (IOException e) {
			LOGGER.error("Failed to save config file", e);
		}
	}
}