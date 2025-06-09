package waterlight.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.block.FluidRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import waterlight.WaterLight;
import waterlight.WaterLightConfig;

@Mixin(FluidRenderer.class)
public class FluidRendererMixin {
    @ModifyArg(
            method = "vertex",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/VertexConsumer;color(FFFF)Lnet/minecraft/client/render/VertexConsumer;"),
            index = 3
    )
    private float changeAlpha(float alpha, @Local(argsOnly = true) int light) {
        return alpha == 1f ? WaterLightConfig.noLightWaterOpacity + (1 - WaterLightConfig.noLightWaterOpacity) * WaterLight.getOpacityProgress(LightmapTextureManager.getSkyLightCoordinates(light)) : alpha;
    }
}
