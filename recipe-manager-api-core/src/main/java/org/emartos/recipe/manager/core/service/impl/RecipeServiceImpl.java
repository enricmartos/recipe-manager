package org.emartos.recipe.manager.core.service.impl;

import org.emartos.recipe.manager.api.jpa.entity.Recipe;
import org.emartos.recipe.manager.api.jpa.mapper.RecipeMapper;
import org.emartos.recipe.manager.api.jpa.model.RecipeDto;
import org.emartos.recipe.manager.api.jpa.repository.RecipeRepository;
import org.emartos.recipe.manager.core.service.RecipeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RecipeServiceImpl implements RecipeService {

	private static final Logger LOGGER = LoggerFactory.getLogger(RecipeServiceImpl.class);

	private final RecipeRepository recipeRepository;
	private final RecipeMapper recipeMapper;

	public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeMapper recipeMapper) {
		this.recipeRepository = recipeRepository;
		this.recipeMapper = recipeMapper;
	}

	@Override
	public RecipeDto getRecipeById(Long id) {
		LOGGER.debug(">> getRecipeById() id {}", id);

		Recipe recipe =  recipeRepository.findById(id).orElse(new Recipe());
		RecipeDto recipeDto = recipeMapper.recipeToRecipeDto(recipe);

		LOGGER.debug("<< getRecipeById() recipeDto {}", recipeDto);
		return recipeDto;
	}

}
