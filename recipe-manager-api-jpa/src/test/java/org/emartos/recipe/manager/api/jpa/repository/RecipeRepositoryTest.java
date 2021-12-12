/**
 * Copyright (c) 2020 masvoz
 *
 * Author: xavier.salvador@masvoz.es
 *//*

package org.emartos.recipe.manager.api.jpa.repository;

import org.emartos.recipe.manager.api.jpa.DataSourceConfig;
import org.emartos.recipe.manager.api.jpa.entity.Recipe;
import org.emartos.recipe.manager.api.jpa.repository.jpa.JpaRecipeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.emartos.recipe.manager.api.jpa.RecipeManagerApiJpaTestsUtils.getRecipe;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DataSourceConfig.class)
@Transactional
@Sql(scripts = {"classpath:sql/basic_inserts.sql"})
public class RecipeRepositoryTest {

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private JpaRecipeRepository recipeRepository;

	@Test
	public void testSave() {
		assertEquals(1, recipeRepository.count());
		recipeRepository.save(getRecipe(2L));
		assertEquals(2, recipeRepository.count());
	}

	@Test
	public void testFindAll() {
		List<Recipe> recipeList = recipeRepository.findAll();
		assertEquals(1, recipeList.size());
		assertEquals(getRecipe(1L), recipeList.get(0));
	}

	@Test
	public void testFindById() {
		Optional<Recipe> optionalRecipe = recipeRepository.findById(1L);
		assertTrue(optionalRecipe.isPresent());
		assertEquals(getRecipe(1L), optionalRecipe.get());
	}

	@Test
	public void testDeleteById() {
		assertEquals(1, recipeRepository.count());
		recipeRepository.save(getRecipe(2L));
		entityManager.clear();
		assertEquals(2, recipeRepository.count());
		List<Recipe> recipeList = recipeRepository.findAll();
		assertFalse(recipeList.isEmpty());
		long id = recipeList.get(recipeList.size() - 1).getId();
		assertEquals(1, recipeRepository.deleteRecipeById(id));
		assertEquals(1, recipeRepository.count());
	}

}
*/
