package org.emartos.recipe.manager.api.jpa.mapper;

import org.emartos.recipe.manager.api.jpa.entity.Recipe;
import org.emartos.recipe.manager.api.jpa.model.RecipeDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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

	public List<RecipeDto> recipeListToRecipeDtoList(List<Recipe> recipeList) {
		LOGGER.trace(">> recipeListToRecipeDtoList() recipeList {}", recipeList);

		if (recipeList == null || recipeList.isEmpty()) {
			LOGGER.trace("<< recipeListToRecipeDtoList() list was null or empty -> recipeList {}", recipeList);
			return new ArrayList<>();
		}
		List<RecipeDto> recipeDtoList = new ArrayList<>();
		for (Recipe recipe : recipeList) {
			recipeDtoList.add(recipeToRecipeDto(recipe));
		}

		LOGGER.trace("<< recipeListToRecipeDtoList() recipeDtoList {}", recipeDtoList);
		return recipeDtoList;
	}

}
