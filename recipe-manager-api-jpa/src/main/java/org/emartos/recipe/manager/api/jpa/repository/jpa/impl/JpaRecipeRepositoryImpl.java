package org.emartos.recipe.manager.api.jpa.repository.jpa.impl;

import org.emartos.recipe.manager.api.jpa.entity.Recipe;
import org.emartos.recipe.manager.api.jpa.mapper.RecipeMapper;
import org.emartos.recipe.manager.api.jpa.model.RecipeDto;
import org.emartos.recipe.manager.api.jpa.repository.RecipeRepository;
import org.emartos.recipe.manager.api.jpa.repository.jpa.JpaRecipeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Qualifier("jpaRecipeRepositoryImpl")
public class JpaRecipeRepositoryImpl implements RecipeRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(JpaRecipeRepositoryImpl.class);

	private final RecipeMapper recipeMapper;
	@Autowired
	private JpaRecipeRepository repository;

	public JpaRecipeRepositoryImpl(RecipeMapper recipeMapper) {
		this.recipeMapper = recipeMapper;
	}

	@Override
	public List<RecipeDto> findAll() {
		LOGGER.debug(">> findAll()");

		List<Recipe> recipeList =  repository.findAll();
		List<RecipeDto> recipeDtoList = recipeMapper.recipeListToRecipeDtoList(recipeList);

		LOGGER.debug("<< findAll() recipeDtoList {}", recipeDtoList);
		return recipeDtoList;
	}

	@Override
	public RecipeDto findById(Long id) {
		LOGGER.debug(">> findById() id {}", id);

		Recipe recipe =  repository.findById(id).orElse(new Recipe());
		RecipeDto recipeDto = recipeMapper.recipeToRecipeDto(recipe);

		LOGGER.debug("<< findById() recipeDto {}", recipeDto);
		return recipeDto;
	}

	@Override
	public RecipeDto save(RecipeDto recipeDto) {
		LOGGER.debug(">> save() recipeDto {}", recipeDto);

		Recipe recipeToPersist = recipeMapper.recipeDtoToRecipe(recipeDto);
		Recipe recipePersisted =  repository.save(recipeToPersist);
		RecipeDto recipeDtoPersisted = recipeMapper.recipeToRecipeDto(recipePersisted);

		LOGGER.debug("<< save() recipeDtoPersisted {}", recipeDtoPersisted);
		return recipeDtoPersisted;
	}

	@Override
	public boolean deleteById(Long id) {
		LOGGER.debug(">> deleteById() id {}", id);

		boolean deleted = repository.deleteRecipeById(id) > 0;

		LOGGER.debug("<< deleteById() deleted {}", deleted);
		return deleted;
	}

}
