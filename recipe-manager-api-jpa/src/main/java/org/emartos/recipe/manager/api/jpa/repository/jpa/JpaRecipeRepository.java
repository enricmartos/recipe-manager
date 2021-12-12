/**
 * Copyright (c) 2021
 *
 * Author: enricmartos
 */
package org.emartos.recipe.manager.api.jpa.repository.jpa;

import org.emartos.recipe.manager.api.jpa.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaRecipeRepository extends JpaRepository<Recipe, Long> {

	int deleteRecipeById(Long id);

}
