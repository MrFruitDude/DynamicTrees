package com.dtteam.dynamictrees.utility;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

/**
 * Helpers for the resource-key based tag appenders introduced in Minecraft 26.2.
 */
public final class DataGenKeys {
    private DataGenKeys() {
    }

    public static ResourceKey<Block> blockKey(final Block block) {
        return BuiltInRegistries.BLOCK.getResourceKey(block).orElseThrow();
    }

    public static ResourceKey<Item> itemKey(final Item item) {
        return BuiltInRegistries.ITEM.getResourceKey(item).orElseThrow();
    }
}
