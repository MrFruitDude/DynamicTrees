package com.dtteam.dynamictrees.loot.entry;

import java.util.Optional;
import com.dtteam.dynamictrees.loot.DTLootContextParams;
import com.dtteam.dynamictrees.tree.species.Species;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.entries.SingleEntryContainerBase;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @author Harley O'Connor
 */
public final class ItemBySpeciesLootPoolEntry extends SingleEntryContainerBase {

    public static final MapCodec<ItemBySpeciesLootPoolEntry> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance
                    .group(Codec.unboundedMap(Identifier.CODEC, BuiltInRegistries.ITEM.holderByNameCodec()).fieldOf("name_by_species").forGetter(c->c.items))
                    .and(uniformFields(instance))
                    .apply(instance, ItemBySpeciesLootPoolEntry::new));

    /** Map of items to set, keyed by the name of the species of tree. */
    private final Map<Identifier, Holder<Item>> items;

    public ItemBySpeciesLootPoolEntry(Map<Identifier, Holder<Item>> items, int weight, int quality, Optional<Holder<LootItemCondition>> conditions,
                                      Optional<Holder<LootItemFunction>> functions) {
        super(weight, quality, conditions, functions);
        this.items = items;
    }

    @Override
    public MapCodec<? extends SingleEntryContainerBase> codec() {
        return CODEC;
    }

    //    @Override
//    public LootPoolEntryType getType() {
//        return DTRegistries.ITEM_BY_SPECIES.get();
//    }

    @Override
    protected void createItemStack(Consumer<ItemStack> stackConsumer, LootContext context) {
        final Species species = context.getOptional(DTLootContextParams.SPECIES);
        assert species != null;
        Holder<Item> itemHolder = items.get(species.getRegistryName());
        Item item = itemHolder == null ? Items.AIR : itemHolder.value();
        stackConsumer.accept(new ItemStack(item));
    }

}
