package io.radston12.radston12sadditions.event;

import io.radston12.radston12sadditions.RadAdditions;
import io.radston12.radston12sadditions.villagers.ModVillagers;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
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

    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {
        if (ModVillagers.getProfession("lumberjack").get() == event.getType()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            List<MerchantOffer> lumberOffers = new ArrayList<>();

            lumberOffers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 1), new ItemStack(Items.SPRUCE_SAPLING, 5), 10, 8, 0.02f));
            lumberOffers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 1), new ItemStack(Items.OAK_SAPLING, 5), 10, 8, 0.02f));
            lumberOffers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 1), new ItemStack(Items.JUNGLE_SAPLING, 2), 10, 8, 0.02f));
            lumberOffers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 1), new ItemStack(Items.ACACIA_SAPLING, 1), 10, 8, 0.02f));
            lumberOffers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 1), new ItemStack(Items.DARK_OAK_SAPLING, 3), 10, 8, 0.02f));
            lumberOffers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 1), new ItemStack(Items.MANGROVE_PROPAGULE, 1), 10, 8, 0.02f));

            // EMERALD -> WOOD
            lumberOffers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 3), new ItemStack(Items.SPRUCE_LOG, 16), 10, 8, 0.02f));
            lumberOffers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 3), new ItemStack(Items.OAK_LOG, 16), 10, 8, 0.02f));
            lumberOffers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 5), new ItemStack(Items.JUNGLE_LOG, 16), 10, 8, 0.02f));
            lumberOffers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 5), new ItemStack(Items.ACACIA_LOG, 16), 10, 8, 0.02f));
            lumberOffers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 4), new ItemStack(Items.DARK_OAK_LOG, 16), 10, 8, 0.02f));
            lumberOffers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 6), new ItemStack(Items.MANGROVE_LOG, 16), 10, 8, 0.02f));

            // WOOD-> EMERALD
            lumberOffers.add(new MerchantOffer(new ItemStack(Items.SPRUCE_LOG, 16), new ItemStack(Items.EMERALD, 3), 10, 8, 0.02f));
            lumberOffers.add(new MerchantOffer(new ItemStack(Items.OAK_LOG, 16), new ItemStack(Items.EMERALD, 3), 10, 8, 0.02f));
            lumberOffers.add(new MerchantOffer(new ItemStack(Items.JUNGLE_LOG, 16), new ItemStack(Items.EMERALD, 3), 10, 8, 0.02f));
            lumberOffers.add(new MerchantOffer(new ItemStack(Items.ACACIA_LOG, 16), new ItemStack(Items.EMERALD, 3), 10, 8, 0.02f));
            lumberOffers.add(new MerchantOffer(new ItemStack(Items.DARK_OAK_LOG, 16), new ItemStack(Items.EMERALD, 3), 10, 8, 0.02f));
            lumberOffers.add(new MerchantOffer(new ItemStack(Items.MANGROVE_LOG, 16), new ItemStack(Items.EMERALD, 3), 10, 8, 0.02f));

            for (int i = 1; i <= 5; i++) {
                for (MerchantOffer offer : lumberOffers) {
                    trades.get(i).add((trader, rand) -> offer);
                }
            }

        } else if (ModVillagers.getProfession("pyrotechnic").get() == event.getType()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            List<MerchantOffer> offers = new ArrayList<>();

            offers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 2), new ItemStack(Items.GUNPOWDER, 10), 10, 8, 0.02f));
            offers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 14), new ItemStack(Items.TNT, 3), 10, 8, 0.02f));
            offers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 64), new ItemStack(Items.END_CRYSTAL, 2), 10, 8, 0.02f));
            offers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 8), new ItemStack(Items.TNT_MINECART, 1), 10, 8, 0.02f));
            ItemStack stack = new ItemStack(Items.FIREWORK_ROCKET, 16);
            CompoundTag tag = stack.getOrCreateTag();
            CompoundTag tag2 = new CompoundTag();
            tag2.putInt("Flight", 1);
            tag.put("Fireworks", tag2);
            offers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 1), stack, 10, 8, 0.02f));

            stack = new ItemStack(Items.FIREWORK_ROCKET, 16);
            tag = stack.getOrCreateTag();
            tag2 = new CompoundTag();
            tag2.putInt("Flight", 2);
            tag.put("Fireworks", tag2);
            offers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 3), stack, 10, 8, 0.02f));

            stack = new ItemStack(Items.FIREWORK_ROCKET, 16);
            tag = stack.getOrCreateTag();
            tag2 = new CompoundTag();
            tag2.putInt("Flight", 3);
            tag.put("Fireworks", tag2);
            offers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 5), stack, 10, 8, 0.02f));

            offers.add(new MerchantOffer(new ItemStack(Items.FIREWORK_ROCKET, 16), new ItemStack(Items.EMERALD, 4), 10, 8, 0.02f));
            offers.add(new MerchantOffer(new ItemStack(Items.TNT, 16), new ItemStack(Items.EMERALD, 4), 10, 8, 0.02f));
            offers.add(new MerchantOffer(new ItemStack(Items.TNT_MINECART, 1), new ItemStack(Items.EMERALD, 3), 10, 8, 0.02f));
            offers.add(new MerchantOffer(new ItemStack(Items.GUNPOWDER, 20), new ItemStack(Items.EMERALD, 4), 10, 8, 0.02f));
            offers.add(new MerchantOffer(new ItemStack(Items.END_CRYSTAL, 2), new ItemStack(Items.EMERALD, 54), 10, 8, 0.02f));

            // TODO: #3 add modded tnts


            for (int i = 1; i <= 5; i++) {
                for (MerchantOffer offer : offers) {
                    trades.get(i).add((trader, rand) -> offer);
                }
            }

        } else if (ModVillagers.getProfession("botanist").get() == event.getType()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            List<MerchantOffer> offers = new ArrayList<>();

            offers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 2), new ItemStack(Items.DANDELION, 16), 10, 8, 0.02f));
            offers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 2), new ItemStack(Items.POPPY, 16), 10, 8, 0.02f));
            offers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 2), new ItemStack(Items.BLUE_ORCHID, 16), 10, 8, 0.02f));
            offers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 2), new ItemStack(Items.ALLIUM, 16), 10, 8, 0.02f));
            offers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 2), new ItemStack(Items.AZURE_BLUET, 16), 10, 8, 0.02f));
            offers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 2), new ItemStack(Items.ORANGE_TULIP, 16), 10, 8, 0.02f));
            offers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 2), new ItemStack(Items.PINK_TULIP, 16), 10, 8, 0.02f));
            offers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 2), new ItemStack(Items.RED_TULIP, 16), 10, 8, 0.02f));
            offers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 2), new ItemStack(Items.WHITE_TULIP, 16), 10, 8, 0.02f));
            offers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 2), new ItemStack(Items.OXEYE_DAISY, 16), 10, 8, 0.02f));
            offers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 2), new ItemStack(Items.CORNFLOWER, 16), 10, 8, 0.02f));
            offers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 2), new ItemStack(Items.LILY_OF_THE_VALLEY, 16), 10, 8, 0.02f));
            offers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 2), new ItemStack(Items.LILY_PAD, 16), 10, 8, 0.02f));
            offers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 2), new ItemStack(Items.SUNFLOWER, 16), 10, 8, 0.02f));
            offers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 2), new ItemStack(Items.LILAC, 16), 10, 8, 0.02f));
            offers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 2), new ItemStack(Items.ROSE_BUSH, 16), 10, 8, 0.02f));
            offers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 2), new ItemStack(Items.PEONY, 16), 10, 8, 0.02f));
            offers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 5), new ItemStack(Items.BAMBOO, 16), 10, 8, 0.02f));
            offers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 1), new ItemStack(Items.DEAD_BUSH, 16), 10, 8, 0.02f));
            offers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 2), new ItemStack(Items.COCOA_BEANS, 16), 10, 8, 0.02f));
            offers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 2), new ItemStack(Items.SWEET_BERRIES, 16), 10, 8, 0.02f));
            offers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 2), new ItemStack(Items.MYCELIUM, 16), 10, 8, 0.02f));

            // TODO: #3 add botania integration


            for (int i = 1; i <= 5; i++) {
                for (MerchantOffer offer : offers) {
                    trades.get(i).add((trader, rand) -> offer);
                }
            }

        } else if (ModVillagers.getProfession("zoologist").get() == event.getType()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            List<MerchantOffer> offers = new ArrayList<>();

            String[] variants = "minecraft:black;minecraft:british_shorthair;minecraft:calico;minecraft:jellie;minecraft:persian;minecraft:ragdoll;minecraft:red;minecraft:siamese;minecraft:tabby;minecraft:white".split(";");

            for (String variant : variants) {
                ItemStack stack = new ItemStack(Items.CAT_SPAWN_EGG, 1);
                stack.setHoverName(Component.translatable("spawneggs." + RadAdditions.MOD_ID + "." + variant.split(":")[1]));
                CompoundTag stackTag = stack.getOrCreateTag();
                CompoundTag entityTag = new CompoundTag();
                entityTag.putString("variant", variant);
                stackTag.put("EntityTag", entityTag);
                stack.setTag(stackTag);
                offers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 2), stack, 1, 1, 0.2f));
            }

            String[] axolotls = "lucy;wild;gold;cyan;blue".split(";");

            for (int i = 0; i < 5; i++) {
                ItemStack stack = new ItemStack(Items.AXOLOTL_SPAWN_EGG, 1);
                stack.setHoverName(Component.translatable("spawneggs." + RadAdditions.MOD_ID + "." + axolotls[i]));
                CompoundTag stackTag = stack.getOrCreateTag();
                CompoundTag entityTag = new CompoundTag();
                entityTag.putInt("variant", i);
                stackTag.put("EntityTag", entityTag);
                stack.setTag(stackTag);
                offers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 2), stack, 1, 1, 0.2f));
            }

            offers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 2), new ItemStack(Items.WOLF_SPAWN_EGG, 1), 1, 1, 0.2f));
            offers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 2), new ItemStack(Items.SHEEP_SPAWN_EGG, 1), 1, 1, 0.2f));
            offers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 2), new ItemStack(Items.COW_SPAWN_EGG, 1), 1, 1, 0.2f));
            offers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 2), new ItemStack(Items.PIG_SPAWN_EGG, 1), 1, 1, 0.2f));
            offers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 2), new ItemStack(Items.SQUID_SPAWN_EGG, 1), 1, 1, 0.2f));


            for (int i = 1; i <= 5; i++) {
                for (MerchantOffer offer : offers) {
                    trades.get(i).add((trader, rand) -> offer);
                }
            }

        } else if (ModVillagers.getProfession("oceanographer").get() == event.getType()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            List<MerchantOffer> offers = new ArrayList<>();

            offers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 10), new ItemStack(Items.PRISMARINE_BRICKS, 16), 10, 8, 0.02f));
            offers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 10), new ItemStack(Items.SEA_LANTERN, 16), 10, 8, 0.02f));
            offers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 10), new ItemStack(Items.PRISMARINE, 16), 10, 8, 0.02f));
            offers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 10), new ItemStack(Items.DARK_PRISMARINE, 16), 10, 8, 0.02f));
            offers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 10), new ItemStack(Items.PRISMARINE_SHARD, 32), 10, 8, 0.02f));
            offers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 10), new ItemStack(Items.PRISMARINE_CRYSTALS, 16), 10, 8, 0.02f));
            offers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 64), new ItemStack(Items.TRIDENT, 1), 10, 8, 0.02f));
            offers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 10), new ItemStack(Items.SAND, 32), 10, 8, 0.02f));
            offers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 10), new ItemStack(Items.GRAVEL, 32), 10, 8, 0.02f));
            offers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 10), new ItemStack(Items.MAGMA_BLOCK, 32), 10, 8, 0.02f));
            offers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 5), new ItemStack(Items.GLOW_SQUID_SPAWN_EGG, 1), 10, 8, 0.02f));
            offers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 10), new ItemStack(Items.GLOW_INK_SAC, 5), 10, 8, 0.02f));
            offers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 10), new ItemStack(Items.DROWNED_SPAWN_EGG, 5), 10, 8, 0.02f));
            offers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 15), new ItemStack(Items.FISHING_ROD, 1), 10, 8, 0.02f));


            for (int i = 1; i <= 5; i++) {
                for (MerchantOffer offer : offers) {
                    trades.get(i).add((trader, rand) -> offer);
                }
            }

        }

    }

}
