package eyeq.shatterskeleton.client.renderer.entity;

import eyeq.util.client.renderer.EntityRenderResourceLocation;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSkeleton;
import net.minecraft.entity.monster.AbstractSkeleton;
import net.minecraft.util.ResourceLocation;

import static eyeq.shatterskeleton.ShatterSkeleton.MOD_ID;

public class RenderShatterSkeleton extends RenderSkeleton {
    protected static final ResourceLocation textures = new EntityRenderResourceLocation(MOD_ID, "shatter_skeleton");

    public RenderShatterSkeleton(RenderManager renderManager) {
        super(renderManager);
    }

    @Override
    protected ResourceLocation getEntityTexture(AbstractSkeleton entity) {
        return textures;
    }
}
