package io.radston12.radston12sadditions;

import com.mojang.logging.LogUtils;
import io.radston12.radston12sadditions.block.ModBlocks;
import io.radston12.radston12sadditions.item.ModItems;
import io.radston12.radston12sadditions.villagers.ModVillagers;
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

    private void init() {
        ModItems.initItems();
        ModBlocks.initBlocks();
        ModVillagers.initVillagers();
    }

    private void register(IEventBus modEventBus) {
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModVillagers.register(modEventBus);
    }

    private static void registerCommonSetup(final FMLCommonSetupEvent event) {
        LOGGER.error("has been called now stfu");
        ModVillagers.registerPOIs();
    }


    public RadAdditions() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();


        float initStart = System.currentTimeMillis();
        init();
        float initTook = (System.currentTimeMillis() - initStart);

        LOGGER.debug("[INIT] Took %f",initTook);



        float registerStart = System.currentTimeMillis();
        register(modEventBus);
        float registerTook = (System.currentTimeMillis() - registerStart);

        LOGGER.debug("[REGISTER] Took %f",registerTook);

        modEventBus.addListener(this::commonSetup);
        
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            float registerCommonStart = System.currentTimeMillis();
            registerCommonSetup(event);
            float registerCommonTook = (System.currentTimeMillis() - registerCommonStart);

            LOGGER.debug("[COMMON_SETUP] Took %f",registerCommonTook);
        });
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
        }
    }

}
