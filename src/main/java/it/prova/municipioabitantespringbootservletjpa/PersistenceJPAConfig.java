package it.prova.municipioabitantespringbootservletjpa;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "it.prova.municipioabitantespringbootservletjpa.dao")
public class PersistenceJPAConfig {

}
