package org.emartos.recipe.manager.api.jpa;

import org.emartos.recipe.manager.api.jpa.entity.Recipe;
import org.emartos.recipe.manager.api.jpa.model.RecipeDto;

import java.util.ArrayList;
import java.util.List;

public class RecipeManagerApiJpaTestsUtils {

	private RecipeManagerApiJpaTestsUtils() {
		// Default constructor
	}

	// region Recipe

	public static List<RecipeDto> getRecipeDtoList() {
		List<RecipeDto> recipeDtoList = new ArrayList<>();
		recipeDtoList.add(getRecipeDto(1L));
		return recipeDtoList;
	}

	public static RecipeDto getRecipeDto(Long id) {
		return RecipeDto.builder()
						.id(id)
						.name("name")
						.build();
	}

	public static List<Recipe> getRecipeList() {
		List<Recipe> recipeList = new ArrayList<>();
		recipeList.add(getRecipe(1L));
		return recipeList;
	}

	public static Recipe getRecipe(Long id) {
		return Recipe.builder()
					 .id(id)
					 .name("name")
					 .build();
	}

	// endregion

}
