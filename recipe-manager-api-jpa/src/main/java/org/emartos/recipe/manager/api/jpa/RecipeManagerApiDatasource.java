/**
 * Copyright (c) 2021
 *
 * Author: enricmartos
 */
package org.emartos.recipe.manager.api.jpa;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		basePackages = "org.emartos.recipe.manager.api.jpa.repository",
		entityManagerFactoryRef = "recipeManagerApiEntityManagerFactory",
		transactionManagerRef = "recipeManagerApiTransactionManager"
)
class RecipeManagerApiDatasource {

	@Value("${spring.datasource.driver-class-name}")
	private String driver;

	@Value("${recipe.manager.api.datasource.url}")
	private String url;

	@Value("${recipe.manager.api.datasource.username}")
	private String username;

	@Value("${recipe.manager.api.datasource.password}")
	private String password;

	@Value("${hibernate.dialect}")
	private String dialect;

	@Value("${hibernate.show_sql}")
	private boolean showSQL;

	@Value("${hibernate.format_sql}")
	private boolean formatSQL;

	@Value("${recipe.manager.api.entities:org.emartos.recipe.manager.api.jpa.entity}")
	private String packageScan;

	@Value("${connection.release_mode}")
	private String releaseMode;

	@Value("${hibernate.c3p0.min_size:1}")
	private String connPoolMinSize;

	@Value("${hibernate.c3p0.max_size:10}")
	private String connPoollMaxSize;

	@Value("${hibernate.c3p0.idle_test_period:10}")
	private String connPoolIdlePeriod;

	@Bean(name = "recipeManagerApiDataSource")
	public DataSource recipeManagerApiDataSource() throws PropertyVetoException {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setDriverClass(driver);
		dataSource.setJdbcUrl(url + "?useSSL=false");
		dataSource.setUser(username);
		dataSource.setPassword(password);
		dataSource.setMinPoolSize(Integer.parseInt(connPoolMinSize));
		dataSource.setMaxPoolSize(Integer.parseInt(connPoollMaxSize));
		dataSource.setMaxIdleTime(Integer.parseInt(connPoolIdlePeriod));
		dataSource.setTestConnectionOnCheckin(true);
		return dataSource;
	}

	@Bean(name = "recipeManagerApiEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean recipeManagerApiEntityManager(@Qualifier("recipeManagerApiDataSource") DataSource recipeManagerApiDataSource) {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(recipeManagerApiDataSource);
		em.setPackagesToScan(packageScan);
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(hibernateProperties());

		return em;
	}

	@Bean(name = "recipeManagerApiTransactionManager")
	public PlatformTransactionManager recipeManagerApiTransactionManager(
			@Qualifier("recipeManagerApiEntityManagerFactory") LocalContainerEntityManagerFactoryBean recipeManagerApiEntityManager) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(recipeManagerApiEntityManager.getObject());
		return transactionManager;
	}

	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", dialect);
		properties.put("hibernate.temp.use_jdbc_metadata_defaults", "false");
		properties.put("hibernate.show_sql", showSQL);
		properties.put("hibernate.format_sql", formatSQL);
		properties.put("entitymanager.packages.to.scan", packageScan);
		properties.put("connection.release_mode", releaseMode);
		properties.put("hibernate.physical_naming_strategy", SpringPhysicalNamingStrategy.class.getName());
		properties.put("hibernate.jdbc.time_zone", "UTC");
		return properties;
	}
}
