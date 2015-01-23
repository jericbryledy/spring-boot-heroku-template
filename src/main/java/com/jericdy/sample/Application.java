package com.jericdy.sample;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.net.URISyntaxException;
import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;
import org.hibernate.dialect.PostgreSQL9Dialect;
import org.postgresql.ds.PGPoolingDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

/**
 *
 * @author Jeric Bryle Sy Dy <jeric@jericbryledy.com>
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
@PropertySource("application.properties")
public class Application {

	@Bean
	public HikariDataSource hikariDataSource(
			@Value("${db.host}") String dbHost, @Value("${db.port}") String dbPort, @Value("${db.name}") String dbName,
			@Value("${db.username}") String dbUsername, @Value("${db.password}") String dbPassword
	) throws URISyntaxException {
		HikariConfig config = new HikariConfig();

		config.setDataSourceClassName(PGPoolingDataSource.class.getCanonicalName());
		config.addDataSourceProperty("serverName", dbHost);
		config.addDataSourceProperty("databaseName", dbName);
		config.addDataSourceProperty("user", dbUsername);
		config.addDataSourceProperty("password", dbPassword);
		config.addDataSourceProperty("portNumber", dbPort);

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
		ApplicationContext ctx = SpringApplication.run(Application.class, args);
	}

}
