package org.emartos.recipe.manager.api.jpa.mapper;

import org.emartos.recipe.manager.api.jpa.entity.Recipe;
import org.emartos.recipe.manager.api.jpa.model.RecipeDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class RecipeMapper {

	private static final Logger LOGGER = LoggerFactory.getLogger(RecipeMapper.class);

	public RecipeDto recipeToRecipeDto(Recipe recipe) {
		LOGGER.debug(">> recipeToRecipeDto() recipe {}", recipe);

		if (recipe == null) {
			LOGGER.debug("<< recipeToRecipeDto() recipe null");
			return null;
		}

		RecipeDto recipeDto = RecipeDto.builder()
									   .id(recipe.getId())
									   .name(recipe.getName())
									   .build();

		LOGGER.debug("<< recipeToRecipeDto() recipeDto {}", recipeDto);
		return recipeDto;
	}

	public Recipe recipeDtoToRecipe(RecipeDto recipeDto) {
		LOGGER.debug(">> recipeDtoToRecipe() recipeDto {}", recipeDto);

		if (recipeDto == null) {
			LOGGER.debug("<< recipeDtoToRecipe() recipeDto null");
			return null;
		}

		Recipe recipe = Recipe.builder()
							  			.id(recipeDto.getId())
									   .name(recipeDto.getName())
									   .build();

		LOGGER.debug("<< recipeDtoToRecipe() recipe {}", recipe);
		return recipe;
	}

}
