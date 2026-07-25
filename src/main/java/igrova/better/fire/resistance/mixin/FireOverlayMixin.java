package igrova.better.fire.resistance.mixin;


import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameOverlayRenderer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.effect.StatusEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameOverlayRenderer.class)
public class FireOverlayMixin {

    @Inject(
            method = "renderFireOverlay",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void onRenderFireOverlay(
            MatrixStack matrices,
            VertexConsumerProvider vertexConsumers,
            Sprite sprite,
            CallbackInfo ci
    ) {
        MinecraftClient client = MinecraftClient.getInstance();

        if (client.player != null && client.player.hasStatusEffect(StatusEffects.FIRE_RESISTANCE)) {
            ci.cancel();
        }
    }
}