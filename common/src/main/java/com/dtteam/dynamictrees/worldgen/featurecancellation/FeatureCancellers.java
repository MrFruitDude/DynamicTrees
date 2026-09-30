package com.dtteam.dynamictrees.worldgen.featurecancellation;

import com.dtteam.dynamictrees.DynamicTrees;
import com.dtteam.dynamictrees.api.registry.Registry;
import com.dtteam.dynamictrees.api.worldgen.FeatureCanceller;
import net.minecraft.world.level.levelgen.feature.HugeFungusFeature;
import net.minecraft.world.level.levelgen.feature.RootSystemFeature;
import net.minecraft.world.level.levelgen.feature.TreeFeature;

public class FeatureCancellers {

    public static final FeatureCanceller TREE_CANCELLER = new TreeFeatureCanceller<>(DynamicTrees.location("tree"), TreeFeature.class);

    public static final FeatureCanceller ROOTED_TREE_CANCELLER = new TreeFeatureCanceller<>(DynamicTrees.location("rooted_tree"), RootSystemFeature.class);

    public static final FeatureCanceller FUNGUS_CANCELLER = new FungusFeatureCanceller<>(DynamicTrees.location("fungus"), HugeFungusFeature.class);

    public static void register(final Registry<FeatureCanceller> registry) {
        registry.registerAll(TREE_CANCELLER, ROOTED_TREE_CANCELLER, FUNGUS_CANCELLER);
    }

}
