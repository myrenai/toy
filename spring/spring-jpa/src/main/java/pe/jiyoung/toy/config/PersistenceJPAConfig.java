package pe.jiyoung.toy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import pe.jiyoung.toy.repository.ToyRepositoryFactoryBean;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages="pe.jiyoung.toy.repository", repositoryFactoryBeanClass = ToyRepositoryFactoryBean.class)
public class PersistenceJPAConfig {

}
