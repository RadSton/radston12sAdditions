package io.radston12.radston12sadditions.screen;

import io.radston12.radston12sadditions.RadAdditions;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {

    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, RadAdditions.MOD_ID);

    public static final RegistryObject<MenuType<SawMillMenu>> SAW_MILL_MENU =
            MENUS.register("saw_mill_menu", () -> new MenuType(SawMillMenu::new));


    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
