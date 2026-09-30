package com.dtteam.dynamictrees.loot.function;

import net.minecraft.core.Holder;
import java.util.Optional;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import java.util.List;

/**
 * @author Harley O'Connor
 */
public final class MultiplyCount extends LootItemConditionalFunction {

    public static final MapCodec<MultiplyCount> CODEC = RecordCodecBuilder.mapCodec(
            instance -> commonFields(instance)
                    .and(Codec.FLOAT.fieldOf("multiplier").forGetter(c->c.multiplier))
                    .apply(instance, MultiplyCount::new));

    private final float multiplier;

    public MultiplyCount(Optional<Holder<LootItemCondition>> conditions, float multiplier) {
        super(conditions);
        this.multiplier = multiplier;
    }

    @Override
    public MapCodec<? extends LootItemConditionalFunction> codec() {
        return CODEC;
    }

    @Override
    protected ItemStack run(ItemStack stack, LootContext context) {
        stack.setCount((int) (stack.getCount() * multiplier));
        return stack;
    }

    public static LootItemFunction.Builder multiplyCount() {
        return () -> new MultiplyCount(Optional.empty(), 1.0F);
    }

}
