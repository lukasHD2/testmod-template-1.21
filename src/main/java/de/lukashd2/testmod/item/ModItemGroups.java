package de.lukashd2.testmod.item;

import de.lukashd2.testmod.TestMod;
import de.lukashd2.testmod.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup PINK_GARNET_ITEMS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(TestMod.MOD_ID, "pink_garnet_items"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.PINK_GARNET))
                    .displayName(Text.translatable("itemgroup.testmod.pink_garnet_items"))
                    .entries((displayContext, entries) -> {
                            entries.add(ModItems.PINK_GARNET);
                            entries.add(ModItems.RAW_PINK_GARNET);
                            entries.add(ModItems.SMARTPHONE);
                    }).build());

    public static final ItemGroup PINK_GARNET_BLOCKS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(TestMod.MOD_ID, "pink_garnet_blocks"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModBlocks.PINK_GARNET_BLOCK))
                    .displayName(Text.translatable("itemgroup.testmod.pink_garnet_blocks"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModBlocks.PINK_GARNET_BLOCK);
                        entries.add(ModBlocks.RAW_PINK_GARNET_BLOCK);
                        entries.add(ModBlocks.DICE_BLOCK);
                        entries.add(ModBlocks.TINY_CUBE_BLOCK);
                        entries.add(ModBlocks.LITHIUM_ORE);
                    }).build());

    public static void registerItemGroups(){
        TestMod.LOGGER.info("Registering Item Groups for " + TestMod.MOD_ID);
    }
}
