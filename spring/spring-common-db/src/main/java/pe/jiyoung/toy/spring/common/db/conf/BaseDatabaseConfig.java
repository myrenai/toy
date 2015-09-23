package pe.jiyoung.toy.spring.common.db.conf;

import static pe.jiyoung.toy.spring.common.db.conf.DatabaseConstants.DATABASE;
import static pe.jiyoung.toy.spring.common.db.conf.DatabaseConstants.FORMAT_SQL;
import static pe.jiyoung.toy.spring.common.db.conf.DatabaseConstants.HIBERNATE_DIALECT;
import static pe.jiyoung.toy.spring.common.db.conf.DatabaseConstants.HIKARICP;
import static pe.jiyoung.toy.spring.common.db.conf.DatabaseConstants.JDBC_DRIVER_CLASSNAME;
import static pe.jiyoung.toy.spring.common.db.conf.DatabaseConstants.JDBC_PASSWORD;
import static pe.jiyoung.toy.spring.common.db.conf.DatabaseConstants.JDBC_POOL_INIT_SIZE;
import static pe.jiyoung.toy.spring.common.db.conf.DatabaseConstants.JDBC_POOL_MAX_SIZE;
import static pe.jiyoung.toy.spring.common.db.conf.DatabaseConstants.JDBC_POOL_NAME;
import static pe.jiyoung.toy.spring.common.db.conf.DatabaseConstants.JDBC_URL;
import static pe.jiyoung.toy.spring.common.db.conf.DatabaseConstants.JDBC_USERNAME;
import static pe.jiyoung.toy.spring.common.db.conf.DatabaseConstants.ODS;
import static pe.jiyoung.toy.spring.common.db.conf.DatabaseConstants.SHOW_SQL;
import static pe.jiyoung.toy.spring.common.db.conf.DatabaseConstants.TOMCAT;
import static pe.jiyoung.toy.spring.common.db.conf.DatabaseConstants.UPS;

import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariDataSource;

import pe.jiyoung.toy.spring.common.util.ToyPropertyResolver;
@Configuration
public class BaseDatabaseConfig {

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

    private org.apache.tomcat.jdbc.pool.DataSource getTomcatPoolDataSource() {
        final org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();
        dataSource.setDriverClassName(this.driverClassName);
        dataSource.setUrl(this.url);
        dataSource.setUsername(this.un);
        dataSource.setPassword(this.pw);
        dataSource.setInitialSize(this.poolInitSize);
        dataSource.setMaxActive(this.poolMaxSize);
        return dataSource;
    }

    private DataSource getHikaricpDataSource() {
        final HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(this.url);
        dataSource.setUsername(this.un);
        dataSource.setPassword(this.pw);
        return dataSource;
    }

    private DataSource getDefaultDataSource() {
        final BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(this.driverClassName);
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
    protected DataSource dataSource() {
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
