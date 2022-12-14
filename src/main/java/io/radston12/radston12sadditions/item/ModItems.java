package io.radston12.radston12sadditions.item;

import io.radston12.radston12sadditions.RadAdditions;
import io.radston12.radston12sadditions.item.custom.MannerSchnitten;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.function.Supplier;

public class ModItems {

    public static final DeferredRegister<Item> DEFFERD_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, RadAdditions.MOD_ID);
    public static final HashMap<String, RegistryObject<? extends Item>> ITEMS = new HashMap<>();
    private static final CreativeModeTab DEFCREATIVE_MODE_TAB = ModCreativeModeTab.RADADDITIONS_TAB;

    public static void initItems() {
        addItem("manner", () -> new MannerSchnitten(new Item.Properties().food(new FoodProperties.Builder().nutrition(100).saturationMod(100).build()).tab(DEFCREATIVE_MODE_TAB).stacksTo(64)));
    }

    public static RegistryObject<? extends Item> getItem(String name) {
        return ITEMS.get(name);
    }

    public static <T extends Item> void addItem(String name, Supplier<T> itemSup) {
        if (ITEMS.containsKey(name)) return;
        ITEMS.put(name, DEFFERD_ITEMS.register(name, itemSup));
    }

    private static void addItem(String name, boolean hasCreativeTab) {
        if (ITEMS.containsKey(name)) return;
        ITEMS.put(name, DEFFERD_ITEMS.register(name, () -> new Item(hasCreativeTab ? new Item.Properties().tab(DEFCREATIVE_MODE_TAB) : new Item.Properties())));
    }

    private static void addItem(String name) {
        if (ITEMS.containsKey(name)) return;
        ITEMS.put(name, DEFFERD_ITEMS.register(name, () -> new Item(new Item.Properties().tab(DEFCREATIVE_MODE_TAB))));
    }

    public static void register(IEventBus eventBus) {
        DEFFERD_ITEMS.register(eventBus);
    }
}
