package pe.jiyoung.toy.spring.jpa.config;

import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

import pe.jiyoung.toy.spring.common.util.ToyPropertyResolver;
import pe.jiyoung.toy.spring.jpa.repository.ToyRepositoryFactoryBean;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages="pe.jiyoung.toy.repository", repositoryFactoryBeanClass = ToyRepositoryFactoryBean.class)
public class PersistenceJPAConfig{

    public static String MYSQL = "MYSQL";
    public static String ORACLE = "ORACLE";

    public static String JDBC_DRIVER_CLASSNAME = "jdbc.driverClassName";

    public static String JDBC_URL = "jdbc.url";

    public static String JDBC_USERNAME = "jdbc.username";
    public static String JDBC_PASSWORD = "jdbc.password";

    // additionalProperties
    public static String HIBERNATE_DIALECT = "hibernate.dialect";
    public static String DATABASE = "database";
    public static String SHOW_SQL = "show_sql";
    public static String FORMAT_SQL = "format_sql";

    public static String PERSISTENCE = "toyPersistence";

    public static String JDBC_POOL_INIT_SIZE = "jdbc.pool.init.size";
    public static String JDBC_POOL_MAX_SIZE = "jdbc.pool.max.size";

    /**
     * # Oracle Universal Connection Pool = ups
     * # OracleDataSource = ods
     * # Tomcat connection pool = tomcat
     * # HikariCP connection pool = hikaricp
     * # default = default
     */
    public static String JDBC_POOL_NAME = "jdbc.pool.name";

    public final static String TOMCAT = "tomcat";
    public final static String UPS = "ups";
    public final static String ODS = "ods";
    public final static String HIKARICP = "hikaricp";

    private String url;
    private String un;
    private String pw;
    private String poolName;
    private String driverClassName;
    private int poolInitSize;
    private int poolMaxSize;

    private final Properties jpaProperties = new Properties();

    private String getRealValue(final String property){
        if(property != null && property.startsWith("<") && property.endsWith(">")){
            return property.substring(1, property.length()-1);
        }
        return null;
    }

    @PostConstruct
    public void init() throws Exception {

        this.url = ToyPropertyResolver.getProperty(this.getRealValue(ToyPropertyResolver.getProperty(JDBC_URL)));
        this.un = ToyPropertyResolver.getProperty(this.getRealValue(ToyPropertyResolver.getProperty(JDBC_USERNAME)));
        this.pw = ToyPropertyResolver.getProperty(this.getRealValue(ToyPropertyResolver.getProperty(JDBC_PASSWORD)));

        this.poolName = ToyPropertyResolver.getProperty(JDBC_POOL_NAME);
        this.driverClassName = ToyPropertyResolver.getProperty(JDBC_DRIVER_CLASSNAME);
        this.poolInitSize = ToyPropertyResolver.getPropertyAsInt(JDBC_POOL_INIT_SIZE);
        this.poolMaxSize = ToyPropertyResolver.getPropertyAsInt(JDBC_POOL_MAX_SIZE);

        this.jpaProperties.setProperty(DATABASE, ToyPropertyResolver.getProperty(DATABASE));
        this.jpaProperties.setProperty(SHOW_SQL, ToyPropertyResolver.getProperty(SHOW_SQL));
        this.jpaProperties.setProperty(FORMAT_SQL, ToyPropertyResolver.getProperty(FORMAT_SQL));
        this.jpaProperties.setProperty(HIBERNATE_DIALECT, ToyPropertyResolver.getProperty(HIBERNATE_DIALECT));
    }

    ////
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

    ////
    private org.apache.tomcat.jdbc.pool.DataSource getTomcatPoolDataSource() {
        final org.apache.tomcat.jdbc.pool.DataSource ds = new org.apache.tomcat.jdbc.pool.DataSource();
        ds.setDriverClassName(this.driverClassName);
        ds.setUrl(this.url);
        ds.setUsername(this.un);
        ds.setPassword(this.pw);
        ds.setInitialSize(this.poolInitSize);
        ds.setMaxActive(this.poolMaxSize);
        return ds;
    }

    private DataSource getHikaricpDataSource() {
        final HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(this.url);
        dataSource.setUsername(this.un);
        dataSource.setPassword(this.pw);
        return dataSource;
    }

    private DataSource getDefaultDataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(ToyPropertyResolver.getProperty(JDBC_DRIVER_CLASSNAME));
        dataSource.setUrl(this.url);
        dataSource.setUsername(this.un);
        dataSource.setPassword(this.pw);
        return dataSource;
    }

    private DataSource getOracleODSDataSource() {
        // TODO Auto-generated method stub
        return null;
    }

    private DataSource getOracleUPSDataSource() {
        // TODO Auto-generated method stub
        return null;
    }

    @Bean
    public DataSource dataSource() {
        switch (this.poolName.toLowerCase()) {
            case TOMCAT:
                return this.getTomcatPoolDataSource();
            case HIKARICP:
                return this.getHikaricpDataSource();
            case UPS:
                return this.getOracleUPSDataSource();
            case ODS:
                return this.getOracleODSDataSource();
            default:
                return this.getDefaultDataSource();
        }
    }

}
