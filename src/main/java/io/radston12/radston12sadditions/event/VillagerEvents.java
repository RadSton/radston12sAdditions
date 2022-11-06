package io.radston12.radston12sadditions.event;

import io.radston12.radston12sadditions.RadAdditions;
import io.radston12.radston12sadditions.item.ModItems;
import io.radston12.radston12sadditions.villagers.ModVillagers;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Mod.EventBusSubscriber(modid = RadAdditions.MOD_ID)
public class VillagerEvents {

    public static final HashMap<String, List<MerchantOffer>> customTradesList = new HashMap<>();

    public static void initVillagerTrades() {

    }

    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {
        if(ModVillagers.getProfession("lumberjack").get() == event.getType()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            List<MerchantOffer> lumberOffers = new ArrayList<>();

            lumberOffers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 1),new ItemStack(Items.SPRUCE_SAPLING, 5),10,8,0.02f));
            lumberOffers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 1),new ItemStack(Items.OAK_SAPLING, 5),10,8,0.02f));
            lumberOffers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 1),new ItemStack(Items.JUNGLE_SAPLING, 2),10,8,0.02f));
            lumberOffers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 1),new ItemStack(Items.ACACIA_SAPLING, 1),10,8,0.02f));
            lumberOffers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 1),new ItemStack(Items.DARK_OAK_SAPLING, 3),10,8,0.02f));
            lumberOffers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 1),new ItemStack(Items.MANGROVE_PROPAGULE, 1),10,8,0.02f));

            // EMERALD -> WOOD
            lumberOffers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 3),new ItemStack(Items.SPRUCE_LOG, 16),10,8,0.02f));
            lumberOffers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 3),new ItemStack(Items.OAK_LOG, 16),10,8,0.02f));
            lumberOffers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 5),new ItemStack(Items.JUNGLE_LOG, 16),10,8,0.02f));
            lumberOffers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 5),new ItemStack(Items.ACACIA_LOG, 16),10,8,0.02f));
            lumberOffers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 4),new ItemStack(Items.DARK_OAK_LOG, 16),10,8,0.02f));
            lumberOffers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 6),new ItemStack(Items.MANGROVE_LOG, 16),10,8,0.02f));

            // WOOD-> EMERALD
            lumberOffers.add(new MerchantOffer(new ItemStack(Items.SPRUCE_LOG, 16),new ItemStack(Items.EMERALD, 3),10,8,0.02f));
            lumberOffers.add(new MerchantOffer(new ItemStack(Items.OAK_LOG, 16),new ItemStack(Items.EMERALD, 3),10,8,0.02f));
            lumberOffers.add(new MerchantOffer(new ItemStack(Items.JUNGLE_LOG, 16),new ItemStack(Items.EMERALD, 3),10,8,0.02f));
            lumberOffers.add(new MerchantOffer(new ItemStack(Items.ACACIA_LOG, 16),new ItemStack(Items.EMERALD, 3),10,8,0.02f));
            lumberOffers.add(new MerchantOffer(new ItemStack(Items.DARK_OAK_LOG, 16),new ItemStack(Items.EMERALD, 3),10,8,0.02f));
            lumberOffers.add(new MerchantOffer(new ItemStack(Items.MANGROVE_LOG, 16),new ItemStack(Items.EMERALD, 3),10,8,0.02f));

            for(int i = 1; i <= 5; i++) {
                for(MerchantOffer offer : lumberOffers) {
                    trades.get(i).add((trader,rand) -> offer);
                }
            }

        }
    }

}
