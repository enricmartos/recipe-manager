package org.emartos.recipe.manager.api.jpa.mapper;

import org.emartos.recipe.manager.api.jpa.entity.Recipe;
import org.emartos.recipe.manager.api.jpa.model.RecipeDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.emartos.recipe.manager.api.jpa.RecipeManagerApiJpaTestsUtils.getRecipe;
import static org.emartos.recipe.manager.api.jpa.RecipeManagerApiJpaTestsUtils.getRecipeDto;
import static org.emartos.recipe.manager.api.jpa.RecipeManagerApiJpaTestsUtils.getRecipeDtoList;
import static org.emartos.recipe.manager.api.jpa.RecipeManagerApiJpaTestsUtils.getRecipeList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
public class RecipeMapperTest {
	
	private RecipeMapper recipeMapper;

	@Before
	public void setup() {
		recipeMapper = new RecipeMapper();
	}

	@Test
	public void testRecipeDtoToRecipe() {
		// null
		assertNull(recipeMapper.recipeDtoToRecipe(null));

		// not null
		RecipeDto recipeDto = getRecipeDto(1L);
		Recipe recipe = recipeMapper.recipeDtoToRecipe(recipeDto);
		Recipe expectedRecipe = getRecipe(1L);
		assertEquals(expectedRecipe, recipe);
	}

	@Test
	public void testRecipeToRecipeDto() {
		// null
		assertNull(recipeMapper.recipeToRecipeDto(null));
		assertNull(recipeMapper.recipeToRecipeDto(Recipe.builder().build()));

		// not null
		Recipe recipe = getRecipe(1L);
		RecipeDto expectedRecipeDto = getRecipeDto(1L);
		assertEquals(expectedRecipeDto, recipeMapper.recipeToRecipeDto(recipe));
	}

	@Test
	public void testRecipeListToRecipeDtoList() {
		// null or empty
		assertEquals(new ArrayList<>(), recipeMapper.recipeListToRecipeDtoList(null));
		assertEquals(new ArrayList<>(), recipeMapper.recipeListToRecipeDtoList(new ArrayList<>()));

		// not empty
		List<Recipe> recipeList = getRecipeList();
		List<RecipeDto> expectedRecipeDtoList = getRecipeDtoList();
		assertEquals(expectedRecipeDtoList, recipeMapper.recipeListToRecipeDtoList(recipeList));
	}
	
}
