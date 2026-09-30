package com.dtteam.dynamictrees.data.provider;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

/**
 * MC 26.3: loot tables are a reloadable registry; this is a {@code SingleRegistryBootstrap<LootTable>}
 * run through {@code GatherDataEvent#createReloadableRegistryObjects}.
 *
 * @author Harley O'Connor
 */
public class DTLootTableProvider extends LootTableProvider {

    /**
     * @param worldRegistries world-layer lookup (enchantments etc.). The reloadable bootstrap only runs once this
     *                        future has completed, so {@code join()} inside the sub-provider does not block.
     */
    public DTLootTableProvider(String modId, CompletableFuture<HolderLookup.Provider> worldRegistries) {
        super(Set.of(),
                List.of(new SubProviderEntry(context -> new DTBlockLootSubProvider(context, modId, worldRegistries.join()), LootContextParamSets.BLOCK)));
    }


}
