package waterlight;

import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WaterLight implements ClientModInitializer, ModInitializer {
	public static final String MOD_ID = "waterlight";

	public static final boolean SODIUM_INSTALLED = FabricLoader.getInstance().isModLoaded("sodium");

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static float getSaturateProgress(int lightLevel) {
		return 1 - (1 - lightLevel / 15f) * (1 - WaterLightConfig.minimumColorPercent);
	}

	public static float getOpacityProgress(int lightLevel) {
		return lightLevel / 15f;
	}

	@Override
	public void onInitializeClient() {

	}

	@Override
	public void onInitialize() {
		MidnightConfig.init(MOD_ID, WaterLightConfig.class);
	}
}