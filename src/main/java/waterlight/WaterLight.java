package waterlight;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WaterLight implements ClientModInitializer {
	public static final String MOD_ID = "waterlight";

	public static final int NO_LIGHT_WATER_COLOR = 0x76889D;
	public static final float NO_LIGHT_WATER_OPACITY = 0.6f;

	public static final boolean SODIUM_INSTALLED = FabricLoader.getInstance().isModLoaded("sodium");

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static float getSaturateProgress(int lightLevel) {
		return (lightLevel + 2) / 17f;
	}

	@Override
	public void onInitializeClient() {

	}
}