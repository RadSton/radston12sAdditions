package io.radston12.radston12sadditions.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class ModCreativeModeTab {
    public static final CreativeModeTab RADADDITIONS_TAB = new CreativeModeTab("radadditions") {
        @Override
        public ItemStack makeIcon() {
            return ModItems.ITEMS.containsKey("manner") ? new ItemStack(ModItems.ITEMS.get("manner").get()) : new ItemStack(Items.PAPER);
        }
    };
}
