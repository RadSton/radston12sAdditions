package io.radston12.radston12sadditions.block;

import io.radston12.radston12sadditions.RadAdditions;
import io.radston12.radston12sadditions.item.ModCreativeModeTab;
import io.radston12.radston12sadditions.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.function.Supplier;

public class ModBlocks {

    private static final CreativeModeTab DEFAULT_CREATIVE_TAB = ModCreativeModeTab.SUPERFLATCHANGES_TAB;

    public static final DeferredRegister<Block> DEFFERD_BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, RadAdditions.MOD_ID);

    public static final HashMap<String, RegistryObject<? extends Block>> BLOCKS = new HashMap<>();

    public static void initBlocks() {

        addNewBlock("zircon_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(6f).requiresCorrectToolForDrops().jumpFactor(2f)));

    }

    private static <T extends Block> void addNewBlock(String name, Supplier<T> block) {
        if (!BLOCKS.containsKey(name)) {
            BLOCKS.put(name,
                    registerBlock(name, block, DEFAULT_CREATIVE_TAB, true)
            );
        }
    }

    private static <T extends Block> void addNewBlock(String name) {
        if (!BLOCKS.containsKey(name)) {
            BLOCKS.put(name,
                    registerBlock(name,
                            () -> new Block(BlockBehaviour.Properties.of(Material.STONE))
                            , DEFAULT_CREATIVE_TAB, true)
            );
        }
    }

    private static <T extends Block> void addNewBlock(String name, float strength) {
        if (!BLOCKS.containsKey(name)) {
            BLOCKS.put(name,
                    registerBlock(name,
                            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(strength))
                            , DEFAULT_CREATIVE_TAB, true)
            );
        }
    }

    private static <T extends Block> void addNewBlock(String name, Material material, float strength) {
        if (!BLOCKS.containsKey(name)) {
            BLOCKS.put(name,
                    registerBlock(name,
                            () -> new Block(BlockBehaviour.Properties.of(material).strength(strength))
                            , DEFAULT_CREATIVE_TAB, true)
            );
        }
    }

    private static <T extends Block> void addNewBlock(String name, Supplier<T> block, boolean hasItem) {
        if (!BLOCKS.containsKey(name)) {
            BLOCKS.put(name,
                    registerBlock(name, block, DEFAULT_CREATIVE_TAB, hasItem)
            );
        }
    }

    private static <T extends Block> void addNewBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        if (!BLOCKS.containsKey(name)) {
            BLOCKS.put(name,
                    registerBlock(name, block, tab, true)
            );
        }
    }


    public static void register(IEventBus eventBus) {
        DEFFERD_BLOCKS.register(eventBus);
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab, boolean hasItem) {
        RegistryObject<T> toReturn = DEFFERD_BLOCKS.register(name, block);
        if (hasItem) registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        ModItems.addItem(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }
}
