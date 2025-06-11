package archives.tater.waterlight;

import eu.midnightdust.lib.config.MidnightConfig;
import org.jetbrains.annotations.Nullable;

public class WaterLightConfig extends MidnightConfig {
    @Entry(isColor = true)
    public static String noLightWaterColor = "#cccccc";
    @Entry(min = 0, max = 1)
    public static float noLightWaterOpacity = 0.6f;
    @Entry(min = 0, max = 1)
    public static float minimumColorPercent = 0.2f;

    private static int cachedWaterColor;
    private static @Nullable String lastWaterColor;

    public static int getNoLightWaterColor() {
        if (!noLightWaterColor.equals(lastWaterColor)) {
            cachedWaterColor = Integer.parseInt(noLightWaterColor.substring(1), 16);
            lastWaterColor = noLightWaterColor;
            return cachedWaterColor;
        }
        return cachedWaterColor;
    }
}
