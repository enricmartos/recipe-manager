package org.emartos.recipe.manager.api.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import org.emartos.recipe.manager.api.jpa.entity.base.BaseEntity;

import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Recipe extends BaseEntity {

	@NotNull
	private String name;

}
