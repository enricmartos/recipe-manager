package org.emartos.recipe.manager.api.jpa.entity;

import lombok.*;
import org.emartos.recipe.manager.api.jpa.entity.base.BaseEntity;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Recipe extends BaseEntity {

	@NotNull
	private String name;

	@Builder
	public Recipe(Long id, String name) {
		super(id);
		this.name = name;
	}

}
