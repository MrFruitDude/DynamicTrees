package com.dtteam.dynamictrees.recipe;

import com.dtteam.dynamictrees.item.DendroPotion;
import net.minecraft.world.item.ItemStack;

/**
 * Description of a brewing recipe for the {@link DendroPotion} item.
 * <p>
 * MC 26.3: NeoForge's IBrewingRecipe / RegisterBrewingRecipesEvent are gone; brewing is a vanilla
 * {@code minecraft:brewing} datapack recipe. The live recipes are the JSON files in
 * {@code data/dynamictrees/recipe/brewing/}; this record mirrors them for integrations (e.g. JEI).
 *
 * @author Harley O'Connor
 */
public record DendroBrewingRecipe(ItemStack input, ItemStack ingredient, ItemStack output) {

	public boolean isInput(final ItemStack inputStack) {
		return ItemStack.isSameItemSameComponents(input, inputStack);
	}

	public boolean isIngredient(final ItemStack ingredientStack) {
		return ItemStack.isSameItemSameComponents(ingredient, ingredientStack);
	}

	public ItemStack getOutput(final ItemStack inputStack, final ItemStack ingredientStack) {
		// We need to apply logic for the brewing or simply the ingredient defines the output and any input was allowed
		// A smarter way would be nice, but it works
		if (!inputStack.isEmpty() && !ingredientStack.isEmpty() && isIngredient(ingredientStack) && isInput(inputStack)) {
			return this.output.copy();
		}
		return ItemStack.EMPTY;
	}


}
