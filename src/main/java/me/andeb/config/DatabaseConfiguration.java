package me.andeb.config;

import javax.inject.Inject;

import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.mongodb.Mongo;

/**
 * Configuração para acesso a base de dados do MongoDB pelo Spring.
 * 
 * @author Anderson de Borba
 */
@Configuration
@EnableMongoRepositories("me.andeb.repository")
@Import(value = MongoAutoConfiguration.class)
public class DatabaseConfiguration extends AbstractMongoConfiguration {

	@Inject
	private Mongo mongo;

	@Bean
	public ValidatingMongoEventListener validatingMongoEventListener() {
		return new ValidatingMongoEventListener(validator());
	}

	@Bean
	public LocalValidatorFactoryBean validator() {
		return new LocalValidatorFactoryBean();
	}

	@Override
	protected String getDatabaseName() {
		return "test";
	}

	@Override
	public Mongo mongo() throws Exception {
		return mongo;
	}

}
