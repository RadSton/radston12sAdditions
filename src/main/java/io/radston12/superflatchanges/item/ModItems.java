package io.radston12.superflatchanges.item;

import io.radston12.superflatchanges.SuperFlatChanges;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SuperFlatChanges.MOD_ID);

    public static final RegistryObject<Item> ZIRCON = ITEMS.register("zircon", () -> new Item(new Item.Properties().tab(ModCreativeModeTab.SUPERFLATCHANGES_TAB)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
