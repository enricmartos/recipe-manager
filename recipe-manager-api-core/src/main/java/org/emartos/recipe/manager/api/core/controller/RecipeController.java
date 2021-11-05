/**
 * Copyright (c) 2021
 *
 * Author: enricmartos
 */
package org.emartos.recipe.manager.api.core.controller;

import org.emartos.recipe.manager.api.jpa.model.RecipeDto;
import org.emartos.recipe.manager.api.core.service.RecipeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(RecipeController.class);

	private final RecipeService recipeService;

	public RecipeController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}

	@GetMapping(value = "/list", produces = APPLICATION_JSON_VALUE)
	public List<RecipeDto> getRecipeList() {
		LOGGER.info(">> getRecipeList()");

		List<RecipeDto> recipeDtoList = recipeService.getRecipeList();

		LOGGER.info("<< getRecipeList() recipeDtoList {}", recipeDtoList);
		return recipeDtoList;
	}

	@GetMapping(produces = APPLICATION_JSON_VALUE)
	public RecipeDto getRecipeById(@RequestParam Long id) {
		LOGGER.info(">> getRecipeById() id {}", id);

		RecipeDto recipeDto = recipeService.getRecipeById(id);

		LOGGER.info("<< getRecipeById() recipeDto {}", recipeDto);
		return recipeDto;
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public RecipeDto createRecipe(@RequestBody RecipeDto recipeDto) {
		LOGGER.info(">> createRecipe() recipeDto {}", recipeDto);

		RecipeDto recipeDtoPersisted = recipeService.createOrUpdate(recipeDto);

		LOGGER.info("<< createRecipe() recipeDtoPersisted {}", recipeDtoPersisted);
		return recipeDtoPersisted;
	}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public RecipeDto updateRecipe(@RequestBody RecipeDto recipeDto) {
		LOGGER.info(">> updateRecipe() recipeDto {}", recipeDto);

		RecipeDto recipeDtoPersisted = recipeService.createOrUpdate(recipeDto);

		LOGGER.info("<< updateRecipe() recipeDtoPersisted {}", recipeDtoPersisted);
		return recipeDtoPersisted;
	}

	@DeleteMapping
	public boolean deleteRecipeById(@RequestParam Long id) {
		LOGGER.info(">> deleteRecipeById() id {}", id);

		boolean deleted = recipeService.deleteRecipeById(id);

		LOGGER.info("<< updateRecipe() deleteRecipeById {}", deleted);
		return deleted;
	}

}
