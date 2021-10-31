/**
 * Copyright (c) 2021
 *
 * Author: enricmartos
 */
package org.emartos.recipe.manager.api.jpa.repository;

import org.emartos.recipe.manager.api.jpa.entity.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {

}
