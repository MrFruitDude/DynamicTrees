package com.dtteam.dynamictrees.worldgen.featurecancellation;

import com.dtteam.dynamictrees.api.worldgen.BiomePropertySelectors;
import com.dtteam.dynamictrees.api.worldgen.FeatureCanceller;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.feature.Feature;

/**
 * This class is an alternate version of {@link TreeFeatureCanceller} specifically made for cancelling fungus features.
 * It cancels any features that have a config that extends the given class.
 *
 * @param <T> An {@link Feature} which should be cancelled.
 * @author Harley O'Connor
 */
public class FungusFeatureCanceller<T extends Feature> extends FeatureCanceller {
    private final Class<T> fungusFeatureConfigClass;

    public FungusFeatureCanceller(final Identifier registryName, final Class<T> fungusFeatureConfigClass) {
        super(registryName);
        this.fungusFeatureConfigClass = fungusFeatureConfigClass;
    }

    @Override
    public boolean shouldCancel(Feature configuredFeature, BiomePropertySelectors.NormalFeatureCancellation featureCancellations) {
        final Identifier featureRegistryName = BuiltInRegistries.FEATURE_TYPE.getKey(configuredFeature.codec());

        return featureRegistryName != null && this.fungusFeatureConfigClass.isInstance(configuredFeature) &&
                featureCancellations.shouldCancelNamespace(featureRegistryName.getNamespace());
    }
}