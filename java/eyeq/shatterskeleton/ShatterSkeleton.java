package eyeq.shatterskeleton;

import eyeq.util.client.renderer.ResourceLocationFactory;
import eyeq.util.client.resource.ULanguageCreator;
import eyeq.util.client.resource.lang.LanguageResourceManager;
import eyeq.util.common.registry.UEntityRegistry;
import eyeq.util.world.biome.BiomeUtils;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import eyeq.shatterskeleton.entity.monster.EntityShatterSkeleton;
import eyeq.shatterskeleton.client.renderer.entity.RenderShatterSkeleton;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.File;
import java.util.List;

import static eyeq.shatterskeleton.ShatterSkeleton.MOD_ID;

@Mod(modid = MOD_ID, version = "1.0", dependencies = "after:eyeq_util")
public class ShatterSkeleton {
    public static final String MOD_ID = "eyeq_shatterskeleton";

    @Mod.Instance(MOD_ID)
    public static ShatterSkeleton instance;

    private static final ResourceLocationFactory resource = new ResourceLocationFactory(MOD_ID);

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        registerEntities();
        if(event.getSide().isServer()) {
            return;
        }
        registerEntityRenderings();
        createFiles();
    }

    public static void registerEntities() {
        UEntityRegistry.registerModEntity(resource, EntityShatterSkeleton.class, "ShatterSkeleton", 0, instance, 0xC3C3C3, 0x929292);
        List<Biome> biomes = BiomeUtils.getSpawnBiomes(EntitySkeleton.class, EnumCreatureType.MONSTER);
        EntityRegistry.addSpawn(EntityShatterSkeleton.class, 2, 1, 5, EnumCreatureType.MONSTER, biomes.toArray(new Biome[0]));
    }

    @SideOnly(Side.CLIENT)
    public static void registerEntityRenderings() {
        RenderingRegistry.registerEntityRenderingHandler(EntityShatterSkeleton.class, RenderShatterSkeleton::new);
    }

    public static void createFiles() {
        File project = new File("../1.11.2-ShatterSkeleton");

        LanguageResourceManager language = new LanguageResourceManager();

        language.register(LanguageResourceManager.EN_US, EntityShatterSkeleton.class, "Bone Meal Skeleton");
        language.register(LanguageResourceManager.JA_JP, EntityShatterSkeleton.class, "骨粉スケルトン");

        ULanguageCreator.createLanguage(project, MOD_ID, language);
    }
}
