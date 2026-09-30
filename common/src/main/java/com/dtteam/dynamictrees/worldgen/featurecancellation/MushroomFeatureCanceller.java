package com.dtteam.dynamictrees.worldgen.featurecancellation;

import com.dtteam.dynamictrees.api.worldgen.BiomePropertySelectors;
import com.dtteam.dynamictrees.api.worldgen.FeatureCanceller;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.core.Holder;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.RandomSelectorFeature;

import java.util.stream.Stream;


public class MushroomFeatureCanceller<T extends Feature> extends FeatureCanceller {
    private final Class<T> mushroomFeatureConfigClass;

    public MushroomFeatureCanceller(final Identifier registryName, final Class<T> mushroomFeatureConfigClass) {
        super(registryName);
        this.mushroomFeatureConfigClass = mushroomFeatureConfigClass;
    }

    @Override
    public boolean shouldCancel(final Feature configuredFeature, final BiomePropertySelectors.NormalFeatureCancellation featureCancellations) {
        final Identifier featureRegistryName = BuiltInRegistries.FEATURE_TYPE.getKey(configuredFeature.codec());

        if (featureRegistryName == null) {
            return false;
        }

        // Mushrooms come in RandomBooleanFeatureConfiguration to select between brown and red.
        if (!(configuredFeature instanceof RandomSelectorFeature randomConfig)) {
            return false;
        }

        return getConfigs(randomConfig).anyMatch(this.mushroomFeatureConfigClass::isInstance) &&
                featureCancellations.shouldCancelNamespace(featureRegistryName.getNamespace());
    }

    private Stream<Feature> getConfigs(final RandomSelectorFeature twoFeatureConfig) {
        return twoFeatureConfig.getSubFeatures().map(Holder::value);
    }
}