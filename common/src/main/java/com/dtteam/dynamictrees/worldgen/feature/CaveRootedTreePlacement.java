package com.dtteam.dynamictrees.worldgen.feature;

import com.dtteam.dynamictrees.api.worldgen.LevelContext;
import com.mojang.datafixers.util.Unit;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.levelgen.placement.PlacementContext;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.function.Consumer;

public class CaveRootedTreePlacement implements PlacementModifier {
    public static final CaveRootedTreePlacement INSTANCE = new CaveRootedTreePlacement(Unit.INSTANCE);
    public static final MapCodec<CaveRootedTreePlacement> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance
                    .group(Codec.EMPTY.forGetter(a-> Unit.INSTANCE))
                    .apply(instance, CaveRootedTreePlacement::new));

    private CaveRootedTreePlacement(Unit unit) {
    }

    @Override
    public void modify(PlacementContext context, RandomSource random, BlockPos pos, Consumer<BlockPos> output) {
        DynamicTreeFeature.DISC_PROVIDER.getPoissonDiscs(LevelContext.create(context.getLevel()), ChunkPos.containing(pos))
                .forEach(disc -> output.accept(new BlockPos(disc.x, 0, disc.z)));
    }

    @Override
    public MapCodec<? extends PlacementModifier> codec() {
        return CODEC;
    }
}
