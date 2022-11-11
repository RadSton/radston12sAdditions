package io.radston12.radston12sadditions.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.radston12.radston12sadditions.RadAdditions;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class SawMillingRecipe implements Recipe<Container> {

    private final ResourceLocation id;
    private final ItemStack output;
    private final NonNullList<Ingredient> recipeItems;


    public SawMillingRecipe(ResourceLocation id, ItemStack output,
                            NonNullList<Ingredient> recipeItems) {
        this.id = id;
        this.output = output;
        this.recipeItems = recipeItems;
    }

    @Override
    public boolean matches(Container simp, Level level) {
        // if (level.isClientSide()) return false; -- BAD because its not a blockentity means -> runs client side and
        return recipeItems.get(0).test(simp.getItem(0));
    }

    @Override
    public ItemStack assemble(Container p_44001_) {
        return output;
    }


    @Override
    public boolean canCraftInDimensions(int p_43999_, int p_44000_) {
        return true;
    }

    @Override
    public ItemStack getResultItem() {
        return output.copy();
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return null;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<SawMillingRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "gem_infusing";

        private Type() {
        }
    }

    public static class Serializer implements RecipeSerializer<SawMillingRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(RadAdditions.MOD_ID, "gem_infusing");

        @Override
        public SawMillingRecipe fromJson(ResourceLocation id, JsonObject obj) {
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(obj, "output"));

            JsonArray ingredients = GsonHelper.getAsJsonArray(obj, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(1, Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            return new SawMillingRecipe(id, output, inputs);

        }

        @Override
        public @Nullable SawMillingRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buf.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(buf));
            }

            ItemStack output = buf.readItem();

            return new SawMillingRecipe(id, output, inputs);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, SawMillingRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());

            for (Ingredient ing : recipe.getIngredients()) {
                ing.toNetwork(buf);
            }

            buf.writeItemStack(recipe.getResultItem(), false);
        }

    }
}
