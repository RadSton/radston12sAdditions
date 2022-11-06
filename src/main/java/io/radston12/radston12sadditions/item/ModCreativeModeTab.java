package io.radston12.radston12sadditions.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class ModCreativeModeTab {
    public static final CreativeModeTab SUPERFLATCHANGES_TAB = new CreativeModeTab("superflatchanges") {
        @Override
        public ItemStack makeIcon() {
            return ModItems.ITEMS.containsKey("zircon") ? new ItemStack(ModItems.ITEMS.get("zircon").get()) : new ItemStack(Items.PAPER);
        }
    };
}