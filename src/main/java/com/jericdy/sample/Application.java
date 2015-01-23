package com.jericdy.sample;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;
import org.hibernate.dialect.PostgreSQL9Dialect;
import org.postgresql.ds.PGPoolingDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

/**
 *
 * @author Jeric Bryle Sy Dy <jeric@jericbryledy.com>
 */
@SpringBootApplication
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

		sessionFactory.setHibernateProperties(hibernateProperties);

		sessionFactory.setPackagesToScan("com.jericdy.sample.orm");

		return sessionFactory;
	}

	// Bean needs to be named "transactionManager" to be picked used for @transational annotation
	@Bean
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory);
		return transactionManager;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
