package com.devhabit.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.r2dbc.connection.init.CompositeDatabasePopulator;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;
import org.springframework.web.reactive.config.EnableWebFlux;

import io.r2dbc.spi.ConnectionFactory;

@SpringBootApplication
public class SpringbootWebfluxApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootWebfluxApplication.class, args);
	}
	@Bean
    protected ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory){
        var initializer =  new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(connectionFactory);
        var databasePopulator = new CompositeDatabasePopulator();
        databasePopulator.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("schema.sql")));
        initializer.setDatabasePopulator(databasePopulator);
        return initializer;
    }

}
