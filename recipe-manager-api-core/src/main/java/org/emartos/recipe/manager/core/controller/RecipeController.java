/**
 * Copyright (c) 2021
 *
 * Author: enricmartos
 */
package org.emartos.recipe.manager.core.controller;

import org.emartos.recipe.manager.api.jpa.model.RecipeDto;
import org.emartos.recipe.manager.core.service.RecipeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(RecipeController.class);

	private final RecipeService recipeService;

	public RecipeController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}

	@GetMapping(produces = APPLICATION_JSON_VALUE)
	public RecipeDto getRecipeById(@RequestParam Long id) {
		LOGGER.info(">> getRecipeById() id {}", id);

		RecipeDto recipeDto = recipeService.getRecipeById(id);

		LOGGER.info("<< getRecipeById() recipeDto {}", recipeDto);
		return recipeDto;
	}

}
