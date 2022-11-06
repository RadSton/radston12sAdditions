package io.radston12.superflatchanges.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import org.checkerframework.checker.units.qual.C;

public class ModCreativeModeTab {
    public static final CreativeModeTab SUPERFLATCHANGES_TAB = new CreativeModeTab("superflatchanges") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.ZIRCON.get());
        }
    };
}
