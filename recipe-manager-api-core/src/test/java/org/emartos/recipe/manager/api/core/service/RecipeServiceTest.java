package org.emartos.recipe.manager.api.core.service;

import org.emartos.recipe.manager.api.core.service.impl.RecipeServiceImpl;
import org.emartos.recipe.manager.api.jpa.entity.Recipe;
import org.emartos.recipe.manager.api.jpa.mapper.RecipeMapper;
import org.emartos.recipe.manager.api.jpa.model.RecipeDto;
import org.emartos.recipe.manager.api.jpa.repository.RecipeRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.emartos.recipe.manager.api.core.RecipeManagerApiCoreTestsUtils.getRecipe;
import static org.emartos.recipe.manager.api.core.RecipeManagerApiCoreTestsUtils.getRecipeDto;
import static org.emartos.recipe.manager.api.core.RecipeManagerApiCoreTestsUtils.getRecipeDtoList;
import static org.emartos.recipe.manager.api.core.RecipeManagerApiCoreTestsUtils.getRecipeList;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RecipeServiceTest {

	private static final long EXISTING_RECIPE_ID = 1L;
	private static final long NON_EXISTING_RECIPE_ID = 2L;

	private RecipeService recipeService;

	@Mock
	private RecipeMapper recipeMapper;

	@Mock
	private RecipeRepository recipeRepository;

	@Before
	public void setup() {
		recipeService = new RecipeServiceImpl(recipeMapper, recipeRepository);
	}

	@Test
	public void testGetRecipeList() {
		List<Recipe> recipeList = getRecipeList();
		when(recipeRepository.findAll()).thenReturn(recipeList);
		List<RecipeDto> recipeDtoList = getRecipeDtoList();
		when(recipeMapper.recipeListToRecipeDtoList(recipeList)).thenReturn(recipeDtoList);

		Assert.assertEquals(recipeDtoList, recipeService.getRecipeList());
	}

	@Test
	public void testGetRecipeById() {
		// non-existing id (non-existing recipe)
		when(recipeRepository.findById(NON_EXISTING_RECIPE_ID)).thenReturn(Optional.of(new Recipe()));
		when(recipeMapper.recipeToRecipeDto(new Recipe())).thenReturn(new RecipeDto());

		Assert.assertEquals(new RecipeDto(), recipeService.getRecipeById(2L));

		// existing id (existing recipe)
		Recipe recipe = getRecipe(EXISTING_RECIPE_ID);
		when(recipeRepository.findById(EXISTING_RECIPE_ID)).thenReturn(Optional.of(recipe));
		RecipeDto recipeDto = getRecipeDto(EXISTING_RECIPE_ID);
		when(recipeMapper.recipeToRecipeDto(recipe)).thenReturn(recipeDto);

		Assert.assertEquals(recipeDto, recipeService.getRecipeById(EXISTING_RECIPE_ID));
	}

	@Test
	public void testCreateOrUpdateRecipe() {
		// create recipe
		RecipeDto recipeDto = getRecipeDto(null);
		Recipe recipeToPersist = getRecipe(null);
		final long recipeId = 1L;
		Recipe recipePersisted = getRecipe(EXISTING_RECIPE_ID);
		when(recipeMapper.recipeDtoToRecipe(recipeDto)).thenReturn(recipeToPersist);
		when(recipeRepository.save(recipeToPersist)).thenReturn(recipePersisted);
		RecipeDto recipeDtoPersisted = getRecipeDto(EXISTING_RECIPE_ID);
		when(recipeMapper.recipeToRecipeDto(recipePersisted)).thenReturn(recipeDtoPersisted);

		Assert.assertEquals(recipeDtoPersisted, recipeService.createOrUpdateRecipe(recipeDto));

		// update recipe
		recipeDto = getRecipeDto(EXISTING_RECIPE_ID);
		recipeToPersist = getRecipe(EXISTING_RECIPE_ID);
		recipePersisted = getRecipe(EXISTING_RECIPE_ID);
		when(recipeMapper.recipeDtoToRecipe(recipeDto)).thenReturn(recipeToPersist);
		when(recipeRepository.save(recipeToPersist)).thenReturn(recipePersisted);
		recipeDtoPersisted = getRecipeDto(recipeId);
		when(recipeMapper.recipeToRecipeDto(recipePersisted)).thenReturn(recipeDtoPersisted);

		Assert.assertEquals(recipeDtoPersisted, recipeService.createOrUpdateRecipe(recipeDto));
	}

	@Test
	public void testDeleteRecipeById() {
		when(recipeRepository.deleteRecipeById(EXISTING_RECIPE_ID)).thenReturn(1);
		Assert.assertTrue(recipeService.deleteRecipeById(EXISTING_RECIPE_ID));

		when(recipeRepository.deleteRecipeById(NON_EXISTING_RECIPE_ID)).thenReturn(0);
		Assert.assertFalse(recipeService.deleteRecipeById(NON_EXISTING_RECIPE_ID));
	}
}
