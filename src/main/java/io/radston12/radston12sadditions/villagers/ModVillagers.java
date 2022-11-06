package io.radston12.radston12sadditions.villagers;

import com.google.common.collect.ImmutableSet;
import io.radston12.radston12sadditions.RadAdditions;
import io.radston12.radston12sadditions.block.ModBlocks;
import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class ModVillagers {

    public static final DeferredRegister<PoiType> DEFFERED_POI_TYPES = DeferredRegister.create(ForgeRegistries.POI_TYPES, RadAdditions.MOD_ID);
    public static final DeferredRegister<VillagerProfession> DEFFERED_VIlLAGER_PROFESSIONS = DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, RadAdditions.MOD_ID);

    public static final HashMap<String, RegistryObject<PoiType>> POI_TYPES = new HashMap<>();
    public static final HashMap<String, RegistryObject<VillagerProfession>> VIlLAGER_PROFESSIONS = new HashMap<>();


    public static void initVillagers() {
        addVillager("oceanographer", ModBlocks.getBlock("fishtank"), SoundEvents.VILLAGER_WORK_ARMORER);
        addVillager("zoologist", ModBlocks.getBlock("cage"), SoundEvents.VILLAGER_WORK_ARMORER);
        addVillager("pyrotechnic", ModBlocks.getBlock("pyrotechnic_table"), SoundEvents.VILLAGER_WORK_ARMORER);
        addVillager("lumberjack", ModBlocks.getBlock("sawmill"), SoundEvents.VILLAGER_WORK_ARMORER);
        addVillager("botanist", ModBlocks.getBlock("plantstation"), SoundEvents.VILLAGER_WORK_ARMORER);
    }

    public static void registerPOIs() {
        try {
            for (RegistryObject<PoiType> type : POI_TYPES.values())
                ObfuscationReflectionHelper
                        .findMethod(PoiType.class, "registerBlockStates", PoiType.class)
                        .invoke(null, type.get());
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void register(IEventBus eventBus) {
        DEFFERED_POI_TYPES.register(eventBus);
        DEFFERED_VIlLAGER_PROFESSIONS.register(eventBus);
    }

    public static RegistryObject<PoiType> getPOI(String name) {
        return POI_TYPES.get(name);
    }

    public static RegistryObject<VillagerProfession> getProfession(String name) {
        return VIlLAGER_PROFESSIONS.get(name);
    }

    private static void addVillager(String name, RegistryObject<? extends Block> workstation, SoundEvent event) {
        addPOI(name + "_poi", workstation, 1, 1);
        addProfession(name, getPOI(name + "_poi"), event);
    }

    private static void addPOI(String name, RegistryObject<? extends Block> block, int maxTickets, int validRange) {
        if (POI_TYPES.containsKey(name)) return;
        RegistryObject<PoiType> poiType = DEFFERED_POI_TYPES.register(name,
                () -> new PoiType(ImmutableSet.copyOf(block.get().getStateDefinition().getPossibleStates())
                        , maxTickets
                        , validRange));
        POI_TYPES.put(name, poiType);
    }

    private static void addProfession(String name, RegistryObject<PoiType> poi, SoundEvent events) {
        if (VIlLAGER_PROFESSIONS.containsKey(name)) return;
        RegistryObject<VillagerProfession> prof = DEFFERED_VIlLAGER_PROFESSIONS.register(
                name,
                () -> new VillagerProfession(
                        name, x -> x.get() == poi.get(),
                        x -> x.get() == poi.get(),
                        ImmutableSet.of(),
                        ImmutableSet.of(),
                        events
                ));
        VIlLAGER_PROFESSIONS.put(name, prof);
    }
}
