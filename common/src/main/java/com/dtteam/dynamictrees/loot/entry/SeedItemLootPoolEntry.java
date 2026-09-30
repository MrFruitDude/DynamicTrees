package com.dtteam.dynamictrees.loot.entry;

import net.minecraft.world.level.storage.loot.entries.UniformContainerBase;
import net.minecraft.core.Holder;
import java.util.Optional;
import com.dtteam.dynamictrees.loot.DTLootContextParams;
import com.dtteam.dynamictrees.tree.species.Species;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.entries.SingleEntryContainerBase;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author Harley O'Connor
 */
public final class SeedItemLootPoolEntry extends SingleEntryContainerBase {

    public static final MapCodec<SeedItemLootPoolEntry> CODEC = RecordCodecBuilder.mapCodec(
            instance -> uniformFields(instance)
                    .apply(instance, SeedItemLootPoolEntry::new));


    public SeedItemLootPoolEntry(int weight, int quality, Optional<Holder<LootItemCondition>> conditions,
                                 Optional<Holder<LootItemFunction>> functions) {
        super(weight, quality, conditions, functions);
    }

    @Override
    public MapCodec<? extends SingleEntryContainerBase> codec() {
        return CODEC;
    }

    //    @Override
//    public LootPoolEntryType getType() {
//        return DTRegistries.SEED_ITEM.get();
//    }

    @Override
    protected void createItemStack(Consumer<ItemStack> stackConsumer, LootContext context) {
        final Species species = context.getOptional(DTLootContextParams.SPECIES);
        assert species != null;
        stackConsumer.accept(species.shouldDropSeeds() ? species.getSeedStack(1) : ItemStack.EMPTY);
    }

    public static UniformContainerBase.Builder<?> lootTableSeedItem() {
        return simpleBuilder(SeedItemLootPoolEntry::new);
    }

}
