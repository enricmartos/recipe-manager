package org.emartos.recipe.manager.api.core.service.impl;

import org.emartos.recipe.manager.api.core.service.RecipeService;
import org.emartos.recipe.manager.api.jpa.model.RecipeDto;
import org.emartos.recipe.manager.api.jpa.repository.RecipeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RecipeServiceImpl implements RecipeService {

	private static final Logger LOGGER = LoggerFactory.getLogger(RecipeServiceImpl.class);

	private final RecipeRepository recipeRepository;

	public RecipeServiceImpl(@Qualifier("inMemoryRecipeRepositoryImpl") RecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository;
	}

	@Override
	public List<RecipeDto> getRecipeList() {
		LOGGER.debug(">> getRecipeList()");

		List<RecipeDto> recipeDtoList = recipeRepository.findAll();

		LOGGER.debug("<< getRecipeList() recipeDtoList {}", recipeDtoList);
		return recipeDtoList;
	}

	@Override
	public RecipeDto getRecipeById(Long id) {
		LOGGER.debug(">> getRecipeById() id {}", id);

		RecipeDto recipeDto = recipeRepository.findById(id);

		LOGGER.debug("<< getRecipeById() recipeDto {}", recipeDto);
		return recipeDto;
	}

	@Override
	public RecipeDto createOrUpdateRecipe(RecipeDto recipeDto) {
		LOGGER.debug(">> createOrUpdate() recipeDto {}", recipeDto);

		RecipeDto recipeDtoPersisted = recipeRepository.save(recipeDto);

		LOGGER.debug("<< createOrUpdate() recipeDtoPersisted {}", recipeDtoPersisted);
		return recipeDtoPersisted;
	}

	@Override
	public boolean deleteRecipeById(Long id) {
		LOGGER.debug(">> deleteRecipeById() id {}", id);

		boolean deleted = recipeRepository.deleteById(id);

		LOGGER.debug("<< deleteRecipeById() deleted {}", deleted);
		return deleted;
	}

}
