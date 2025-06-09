package waterlight.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.world.BlockRenderView;
import net.minecraft.world.LightType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import waterlight.WaterLight;
import waterlight.WaterLightConfig;

@Mixin(BiomeColors.class)
public class BiomeColorsMixin {

	@ModifyReturnValue(
			method = "getWaterColor",
			at = @At("RETURN")
	)
	private static int interpolate(int original, @Local(argsOnly = true)BlockRenderView world, @Local(argsOnly = true)BlockPos pos) {
		var lightLevel = world.getLightLevel(LightType.SKY, pos); // I don't know why this is returning 0 when sodium is installed and liquid level is less than a certain height
		return ColorHelper.Argb.lerp(WaterLight.getSaturateProgress(lightLevel == 0 && WaterLight.SODIUM_INSTALLED ? world.getLightLevel(LightType.SKY, pos.up()) : lightLevel), WaterLightConfig.getNoLightWaterColor(), original);
	}
}