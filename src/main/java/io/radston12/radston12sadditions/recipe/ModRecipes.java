package io.radston12.radston12sadditions.recipe;

import io.radston12.radston12sadditions.RadAdditions;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {

    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, RadAdditions.MOD_ID);

    public static final RegistryObject<RecipeSerializer<SawMillingRecipe>> GEM_INFUSING = SERIALIZERS.register("gem_infusing", () -> SawMillingRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }

    /*public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, RadAdditions.MOD_ID);
    public static final DeferredRegister<RecipeType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, RadAdditions.MOD_ID);

    public static final RegistryObject<RecipeSerializer<?>> SAWMILLING_SERIALIZER = SERIALIZERS.register("sawmilling", SawMillSerializer::new);

    public static final RegistryObject<RecipeType<SawMillRecipe>> SAWMILLING_RECIPE = REGISTRY.register("sawmilling", () -> RecipeType.simple(new ResourceLocation(RadAdditions.MOD_ID, "sawmilling")));



    public static <T extends BaseRecipe> RegistryObject<RecipeSerializer<T>> register(String name, BaseRecipeSerializer<T> serializer) {
        Supplier sp = () -> serializer;

        System.err.println(sp.get().getClass().getName());

        return SERIALIZERS.register(name, sp);
    }

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
        REGISTRY.register(eventBus);
    }*/


}
