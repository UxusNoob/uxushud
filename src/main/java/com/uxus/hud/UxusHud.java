package com.uxus.hud;

import com.uxus.hud.gui.hud.HudEditor;
import com.uxus.hud.util.HungerSaturationHUD;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.event.client.ClientTickCallback;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.util.math.MatrixStack;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;


public class UxusHud implements ModInitializer {

	public static MinecraftClient mc = MinecraftClient.getInstance();
	static boolean a = false;
	static boolean b = false;


	public static boolean nohurtcam = true;
	public static boolean tps= true;
	public static boolean pothud= true;
	public static boolean ping= true;
	public static boolean fps= true;
	public static boolean sprinthud= true;
	public static boolean noloadingterrain= true;
	public static boolean betterhitboxes= true;
	public static boolean spectatorcameraclip = true;
	public static int pothudy = 240;
	public static int pingx = 2;
	public static int pingy = 40;
	public static int fpsx = 3;
	public static int fpsy = 3;
	public static int sprinthudx = 2;
	public static int sprinthudy = 60;


	public static int tpsx = 2;
	public static int tpsy = 20;

	public static boolean nobackground, noguibg, saturationhud = false;

	public static int color = 0x55FFFF;
	private static KeyBinding SPRINT_KEY_BINDING;

	public static HudEditor hudEditor = new HudEditor();
	public static final Logger LOGGER = LoggerFactory.getLogger("Uxushud");
;

	public static final KeyBinding Gui = KeyBindingHelper.registerKeyBinding(
			new KeyBinding(
					"key.uxus.sprint",
					InputUtil.Type.KEYSYM,
					GLFW.GLFW_KEY_UNKNOWN,
					"Uxushud"
			)
	);


	@Override
	public void onInitialize() {

		if (saturationhud) HudRenderCallback.EVENT.register((matrices, tickDelta) -> renderHUD(matrices));

		LOGGER.info("Initializing MyMod");

		this.loadConfig();

		/**LOGGER.info("Nohurtcam: " + nohurtcam);
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

		LOGGER.info("SpectatorCameraClip: " + spectatorcameraclip);
		LOGGER.info("NoBackground: " + nobackground);
		LOGGER.info("NoGuiBg: " + noguibg);
		LOGGER.info("SaturationHud: " + saturationhud);

		LOGGER.info("Color: " + color); **/


		ClientLifecycleEvents.CLIENT_STARTED.register(client -> {


			SPRINT_KEY_BINDING = mc.options.sprintKey;

			ClientTickCallback.EVENT.register(clientTick -> {

				if (Gui.isPressed()) {
					if (!(mc.currentScreen instanceof TitleScreen)) mc.setScreen(hudEditor);
					System.out.println("ClickGUI enabled");

				}

				if (SPRINT_KEY_BINDING.isPressed()) {

					if (mc.currentScreen instanceof ChatScreen) {
						a = true;
						b = false;
					} else {
						b = true;
						a = false;

					}
				}
				if (!SPRINT_KEY_BINDING.isPressed()) {
					a = false;
					b = false;
				}
			});
		});

	}

	private void renderHUD(MatrixStack matrices) {
		MinecraftClient client = MinecraftClient.getInstance();
		int screenWidth = client.getWindow().getScaledWidth();
		int screenHeight = client.getWindow().getScaledHeight();

		HungerSaturationHUD.render(matrices, screenHeight);
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
						this.parseConfigValue(key, value);
					}
				}
			} catch (IOException e) {
				LOGGER.error("Failed to load config file", e);
			}
		} else {
			LOGGER.warn("Config file not found, using default values.");
		}

		this.saveConfig();
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