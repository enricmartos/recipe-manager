package org.emartos.recipe.manager.api.jpa.repository.inmemory;

import org.emartos.recipe.manager.api.jpa.model.RecipeDto;
import org.emartos.recipe.manager.api.jpa.repository.RecipeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Component
@Qualifier("inMemoryRecipeRepositoryImpl")
public class InMemoryRecipeRepository implements RecipeRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(InMemoryRecipeRepository.class);

	private Map<Long, RecipeDto> recipeMap = new HashMap<>();
	private final AtomicLong currentId = new AtomicLong();

	@Override
	public List<RecipeDto> findAll() {
		return null;
	}

	@Override
	public RecipeDto findById(Long id) {
		LOGGER.debug(">> findById() id {}", id);

		RecipeDto recipeDto = recipeMap.computeIfAbsent(id, s -> new RecipeDto());

		LOGGER.debug("<< findById() recipeDto {}", recipeDto);
		return recipeDto;
	}

	@Override
	public RecipeDto save(RecipeDto recipeDto) {
		LOGGER.debug(">> save() recipeDto {}", recipeDto);

		long id = currentId.incrementAndGet();
		recipeDto.setId(id);
		recipeMap.putIfAbsent(id, recipeDto);

		LOGGER.debug("<< save() recipeDto {}", recipeDto);
		return recipeDto;
	}

	@Override
	public boolean deleteById(Long id) {
		return false;
	}

}
