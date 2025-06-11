package archives.tater.waterlight.mixin.sodium;

import net.caffeinemc.mods.sodium.api.util.ColorARGB;
import net.caffeinemc.mods.sodium.client.model.color.ColorProvider;
import net.caffeinemc.mods.sodium.client.model.light.LightPipeline;
import net.caffeinemc.mods.sodium.client.model.quad.ModelQuadViewMutable;
import net.caffeinemc.mods.sodium.client.model.quad.properties.ModelQuadFacing;
import net.caffeinemc.mods.sodium.client.render.chunk.compile.pipeline.DefaultFluidRenderer;
import net.caffeinemc.mods.sodium.client.world.LevelSlice;
import net.minecraft.fluid.FluidState;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.LightType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import archives.tater.waterlight.WaterLight;
import archives.tater.waterlight.WaterLightConfig;

@Mixin(value = DefaultFluidRenderer.class, remap = false)
public class DefaultFluidRendererMixin {
    @Shadow @Final private int[] quadColors;

    @Inject(
            method = "updateQuad",
            at = @At(value = "INVOKE", target = "Lnet/caffeinemc/mods/sodium/client/model/color/ColorProvider;getColors(Lnet/caffeinemc/mods/sodium/client/world/LevelSlice;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/BlockPos$Mutable;Ljava/lang/Object;Lnet/caffeinemc/mods/sodium/client/model/quad/ModelQuadView;[I)V", shift = At.Shift.AFTER)
    )
    private void setOpacities(ModelQuadViewMutable quad, LevelSlice level, BlockPos pos, LightPipeline lighter, Direction dir, ModelQuadFacing facing, float brightness, ColorProvider<FluidState> colorProvider, FluidState fluidState, CallbackInfo ci) {
        if (!fluidState.isIn(FluidTags.WATER)) return;
        for (int i = 0; i < 4; i++) {
            quadColors[i] = ColorARGB.withAlpha(
                    quadColors[i],
                    (int) (
                            ColorARGB.unpackAlpha(quadColors[i])
                            * (WaterLightConfig.noLightWaterOpacity + (1 - WaterLightConfig.noLightWaterOpacity) * WaterLight.getOpacityProgress(level.getLightLevel(LightType.SKY, pos)))
                    )
            );
        }
    }
}
