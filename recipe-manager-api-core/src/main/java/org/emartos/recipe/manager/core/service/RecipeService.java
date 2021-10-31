package org.emartos.recipe.manager.core.service;

import org.emartos.recipe.manager.api.jpa.model.RecipeDto;

public interface RecipeService {

	RecipeDto getRecipeById(Long id);

}
