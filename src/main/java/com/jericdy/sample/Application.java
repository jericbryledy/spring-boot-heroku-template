package com.jericdy.sample;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;
import org.hibernate.cfg.Environment;
import org.hibernate.dialect.PostgreSQL9Dialect;
import org.postgresql.ds.PGPoolingDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

/**
 *
 * @author Jeric Bryle Sy Dy <jeric@jericbryledy.com>
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application {

	@Bean
	public HikariDataSource hikariDataSource() throws URISyntaxException {
		HikariConfig config = new HikariConfig();
		URI dbUri = new URI(System.getenv("DATABASE_URL"));

		config.setDataSourceClassName(PGPoolingDataSource.class.getCanonicalName());
		config.addDataSourceProperty("serverName", dbUri.getHost());
		config.addDataSourceProperty("databaseName", dbUri.getPath().substring(1));
		config.addDataSourceProperty("user", dbUri.getUserInfo().split(":")[0]);
		config.addDataSourceProperty("password", dbUri.getUserInfo().split(":")[1]);
		config.addDataSourceProperty("portNumber", dbUri.getPort());

		return new HikariDataSource(config);
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory(@Qualifier("hikariDataSource") HikariDataSource hikariDataSource) {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(hikariDataSource);
		Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty(Environment.DIALECT, PostgreSQL9Dialect.class.getCanonicalName());
		hibernateProperties.setProperty(Environment.HBM2DDL_AUTO, "create");

		sessionFactory.setHibernateProperties(hibernateProperties);

		sessionFactory.setPackagesToScan("com.jericdy.sample.orm");

		return sessionFactory;
	}

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);
	}

}
