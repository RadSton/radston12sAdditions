package io.radston12.radston12sadditions.screen;

import com.google.common.collect.Lists;
import io.radston12.radston12sadditions.block.ModBlocks;
import io.radston12.radston12sadditions.recipe.SawMillingRecipe;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;

public class SawMillMenu extends AbstractContainerMenu {
    private final ContainerLevelAccess access;
    private final DataSlot selectedRecipeIndex = DataSlot.standalone();
    private final Level level;
    private List<SawMillingRecipe> recipesCache = Lists.newArrayList();
    private ItemStack input = ItemStack.EMPTY;
    long lastSoundTime;
    final Slot inputSlot;
    final Slot resultSlot;
    Runnable slotUpdateListener = () -> {
    };
    public final Container container = new SimpleContainer(1) {
        public void setChanged() {
            super.setChanged();
            slotsChanged(this);
            slotUpdateListener.run();
        }
    };
    final ResultContainer resultContainer = new ResultContainer();

    public SawMillMenu(int containerId, Inventory inventory) {
        this(containerId, inventory, ContainerLevelAccess.NULL);
    }

    public SawMillMenu(int containerId, Inventory inventory, final ContainerLevelAccess accessLevel) {
        super(ModMenuTypes.SAW_MILL_MENU.get(), containerId);
        this.access = accessLevel;
        this.level = inventory.player.level;
        this.inputSlot = this.addSlot(new Slot(this.container, 0, 20, 33));
        this.resultSlot = this.addSlot(new Slot(this.resultContainer, 1, 143, 33) {
            public boolean mayPlace(ItemStack stack) {
                return false;
            }

            public void onTake(Player player, ItemStack itemStack) {
                itemStack.onCraftedBy(player.level, player, itemStack.getCount());
                resultContainer.awardUsedRecipes(player);
                ItemStack itemstack = inputSlot.remove(1);
                if (!itemstack.isEmpty()) {
                    setupResultSlot();
                }

                accessLevel.execute((level, blockPos) -> {
                    long l = level.getGameTime();
                    if (lastSoundTime != l) {
                        level.playSound(null, blockPos, SoundEvents.UI_STONECUTTER_TAKE_RESULT, SoundSource.BLOCKS, 1.0F, 1.0F);
                        lastSoundTime = l;
                    }


                });
                super.onTake(player, itemStack);
            }
        });

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(inventory, k, 8 + k * 18, 142));
        }

        this.addDataSlot(this.selectedRecipeIndex);
    }

    public int getSelectedRecipeIndex() {
        return this.selectedRecipeIndex.get();
    }

    public List<SawMillingRecipe> getRecipes() {
        return recipesCache;
    }


    public int getNumRecipes() {
        return recipesCache.size();
    }

    public boolean hasInputItem() {
        return this.inputSlot.hasItem() && getNumRecipes() != 0;
    }

    public boolean stillValid(Player player) {
        return stillValid(this.access, player, ModBlocks.getBlock("sawmill").get());
    }

    public boolean clickMenuButton(Player player, int buttonId) {
        if (this.isValidRecipeIndex(buttonId)) {
            this.selectedRecipeIndex.set(buttonId);
            this.setupResultSlot();
        }
        return true;
    }

    private boolean isValidRecipeIndex(int index) {
        return index >= 0 && index < recipesCache.size();
    }

    public void slotsChanged(Container container) {
        ItemStack itemstack = this.inputSlot.getItem();
        if (!itemstack.is(this.input.getItem())) {
            this.input = itemstack.copy();
            this.setupRecipeList(container, itemstack);
        }

    }

    private void setupRecipeList(Container container, ItemStack itemStack) {
        recipesCache.clear();
        this.selectedRecipeIndex.set(-1);
        this.resultSlot.set(ItemStack.EMPTY);
        if (!itemStack.isEmpty()) {
            recipesCache = this.level.getRecipeManager().getRecipesFor(SawMillingRecipe.Type.INSTANCE, container, this.level);
        }
    }

    void setupResultSlot() {
        if (!recipesCache.isEmpty() && this.isValidRecipeIndex(this.selectedRecipeIndex.get())) {
            SawMillingRecipe SawMillingRecipe = recipesCache.get(this.selectedRecipeIndex.get());
            resultContainer.setRecipeUsed(SawMillingRecipe);
            resultSlot.set(SawMillingRecipe.assemble(this.container));
        } else {
            resultSlot.set(ItemStack.EMPTY);
        }

        broadcastChanges();
    }

    public MenuType<?> getType() {
        return ModMenuTypes.SAW_MILL_MENU.get();
    }

    public void registerUpdateListener(Runnable listener) {
        slotUpdateListener = listener;
    }

    public boolean canTakeItemForPickAll(ItemStack itemStack, Slot itemSlot) {
        return itemSlot.container != this.resultContainer;
    }

    // NOT RESPONSIBLE
    public ItemStack quickMoveStack(Player player, int slotId) {

        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(slotId);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            Item item = itemstack1.getItem();
            itemstack = itemstack1.copy();
            if (slotId == 1) {
                item.onCraftedBy(itemstack1, player.level, player);
                if (!this.moveItemStackTo(itemstack1, 2, 38, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(itemstack1, itemstack);
            } else if (slotId == 0) {
                if (!this.moveItemStackTo(itemstack1, 2, 38, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (this.level.getRecipeManager().getRecipeFor(SawMillingRecipe.Type.INSTANCE, new SimpleContainer(itemstack1), this.level).isPresent()) {
                if (!this.moveItemStackTo(itemstack1, 0, 1, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (slotId >= 2 && slotId < 29) {
                if (!this.moveItemStackTo(itemstack1, 29, 38, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (slotId >= 29 && slotId < 38 && !this.moveItemStackTo(itemstack1, 2, 29, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            }

            slot.setChanged();
            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, itemstack1);
            this.broadcastChanges();
        }

        return itemstack;
    }

    public void removed(Player player) {
        super.removed(player);
        resultContainer.removeItemNoUpdate(1);
        access.execute((varA, varB) -> {
            this.clearContainer(player, this.container);
        });
    }

}
