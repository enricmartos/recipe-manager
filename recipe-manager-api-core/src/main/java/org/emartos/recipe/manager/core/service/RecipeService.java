package org.emartos.recipe.manager.core.service;

import org.emartos.recipe.manager.api.jpa.model.RecipeDto;

import java.util.List;

public interface RecipeService {

	List<RecipeDto> getRecipeList();

	RecipeDto getRecipeById(Long id);

	RecipeDto createOrUpdate(RecipeDto recipeDto);

	boolean deleteRecipeById(Long id);

}
