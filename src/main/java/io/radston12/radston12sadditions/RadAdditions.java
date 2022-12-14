package io.radston12.radston12sadditions;

import com.mojang.logging.LogUtils;
import io.radston12.radston12sadditions.block.ModBlocks;
import io.radston12.radston12sadditions.item.ModItems;
import io.radston12.radston12sadditions.recipe.ModRecipes;
import io.radston12.radston12sadditions.screen.ModMenuTypes;
import io.radston12.radston12sadditions.screen.SawMillScreen;
import io.radston12.radston12sadditions.villagers.ModVillagers;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(RadAdditions.MOD_ID)
public class RadAdditions {

    public static final String MOD_ID = "radadditions";

    private static final Logger LOGGER = LogUtils.getLogger();

    public RadAdditions() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();


        float initStart = System.currentTimeMillis();
        init();
        float initTook = (System.currentTimeMillis() - initStart);

        LOGGER.debug("[INIT] Took %f", initTook);


        float registerStart = System.currentTimeMillis();
        register(modEventBus);
        float registerTook = (System.currentTimeMillis() - registerStart);

        LOGGER.debug("[REGISTER] Took %f", registerTook);

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private static void registerCommonSetup(final FMLCommonSetupEvent event) {
        ModVillagers.registerPOIs();
    }

    private void init() {
        ModItems.initItems();
        ModBlocks.initBlocks();
        ModVillagers.initVillagers();
    }

    private void register(IEventBus modEventBus) {
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModVillagers.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        ModRecipes.register(modEventBus);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            float registerCommonStart = System.currentTimeMillis();
            registerCommonSetup(event);
            float registerCommonTook = (System.currentTimeMillis() - registerCommonStart);

            LOGGER.debug("[COMMON_SETUP] Took %f", registerCommonTook);
        });
    }

    private void clientSetup(final FMLClientSetupEvent event) {

        MenuScreens.register(ModMenuTypes.SAW_MILL_MENU.get(), SawMillScreen::new);
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
        }
    }

}
