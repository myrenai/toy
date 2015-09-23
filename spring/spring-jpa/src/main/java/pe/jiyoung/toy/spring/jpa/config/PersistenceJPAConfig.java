package pe.jiyoung.toy.spring.jpa.config;

import static pe.jiyoung.toy.spring.common.db.conf.DatabaseConstants.DATABASE;
import static pe.jiyoung.toy.spring.common.db.conf.DatabaseConstants.FORMAT_SQL;
import static pe.jiyoung.toy.spring.common.db.conf.DatabaseConstants.HIBERNATE_DIALECT;
import static pe.jiyoung.toy.spring.common.db.conf.DatabaseConstants.SHOW_SQL;

import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import pe.jiyoung.toy.spring.common.db.conf.BaseDatabaseConfig;
import pe.jiyoung.toy.spring.common.util.ToyPropertyResolver;
import pe.jiyoung.toy.spring.jpa.repository.ToyRepositoryFactoryBean;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages="pe.jiyoung.toy.repository", repositoryFactoryBeanClass = ToyRepositoryFactoryBean.class)
public class PersistenceJPAConfig extends BaseDatabaseConfig{

    private final Properties jpaProperties = new Properties();

    @Override
    @PostConstruct
    public void init() throws Exception {
        super.init();
        this.jpaProperties.setProperty(DATABASE, ToyPropertyResolver.getProperty(DATABASE));
        this.jpaProperties.setProperty(SHOW_SQL, ToyPropertyResolver.getProperty(SHOW_SQL));
        this.jpaProperties.setProperty(FORMAT_SQL, ToyPropertyResolver.getProperty(FORMAT_SQL));
        this.jpaProperties.setProperty(HIBERNATE_DIALECT, ToyPropertyResolver.getProperty(HIBERNATE_DIALECT));
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(this.dataSource());
        em.setPersistenceXmlLocation("classpath:META-INF/persistence.xml");

        final JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(this.jpaProperties);
        return em;
    }

    @Bean
    public PlatformTransactionManager transactionManager(final EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigIn() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
