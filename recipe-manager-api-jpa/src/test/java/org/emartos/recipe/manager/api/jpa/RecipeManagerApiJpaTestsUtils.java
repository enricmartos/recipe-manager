package org.emartos.recipe.manager.api.jpa;

import org.emartos.recipe.manager.api.jpa.entity.Recipe;

public class RecipeManagerApiJpaTestsUtils {

	private RecipeManagerApiJpaTestsUtils() {
		// Default constructor
	}

	public static Recipe getRecipe(Long id) {
		return Recipe.builder()
					 .id(id)
					 .name("name")
					 .build();
	}
}
