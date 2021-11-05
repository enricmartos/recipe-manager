/**
 * Copyright (c) 2020 masvoz
 *
 * Author: xavier.salvador@masvoz.es
 */
package org.emartos.recipe.manager.api.boot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Profile("test")
@TestPropertySource(locations = "classpath:application-test.properties")
public class RecipeManagerApiBootApplicationTest {

	@Test
	public void contextLoad() {
		assertThat(SpringBootVersion.getVersion()).isNotNull();
	}

}


