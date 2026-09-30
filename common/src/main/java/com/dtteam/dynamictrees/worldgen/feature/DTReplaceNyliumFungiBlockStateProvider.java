package com.dtteam.dynamictrees.worldgen.feature;

import com.dtteam.dynamictrees.config.DTConfigs;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class DTReplaceNyliumFungiBlockStateProvider implements BlockStateProvider {
    public static final MapCodec<DTReplaceNyliumFungiBlockStateProvider> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            BlockStateProvider.DIRECT_CODEC.fieldOf("enabled").forGetter(provider -> provider.enabled),
            BlockStateProvider.DIRECT_CODEC.fieldOf("disabled").forGetter(provider -> provider.disabled)
    ).apply(instance, DTReplaceNyliumFungiBlockStateProvider::new));
    public final BlockStateProvider enabled;
    public final BlockStateProvider disabled;

    public DTReplaceNyliumFungiBlockStateProvider(BlockStateProvider enabled, BlockStateProvider disabled) {
        this.enabled = enabled;
        this.disabled = disabled;
    }

    @Override
    public MapCodec<? extends BlockStateProvider> codec() {
        return CODEC;
    }

    @Override
    public BlockState getState(LevelAccessor worldGenLevel, RandomSource randomSource, BlockPos blockPos) {
        return DTConfigs.COMMON.replaceNyliumFungi.get()
                ? this.enabled.getState(worldGenLevel, randomSource, blockPos)
                : this.disabled.getState(worldGenLevel, randomSource, blockPos);
    }

}
