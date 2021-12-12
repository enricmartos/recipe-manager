/**
 * Copyright (c) 2021
 *
 * Author: enricmartos
 */
package org.emartos.recipe.manager.api.jpa.repository;

import org.emartos.recipe.manager.api.jpa.model.RecipeDto;

import java.util.List;

public interface RecipeRepository {

	List<RecipeDto> findAll();

	RecipeDto findById(Long id);

	RecipeDto save(RecipeDto recipeDto);

	boolean deleteById(Long id);
}
