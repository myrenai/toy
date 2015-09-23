package pe.jiyoung.toy.spring.mybatis.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pe.jiyoung.toy.spring.common.db.conf.BaseDatabaseConfig;
import pe.jiyoung.toy.spring.common.util.ToyPropertyResolver;

@Configuration
public class MyBatisConfig extends BaseDatabaseConfig{

    private final static String MAPPER_LOCATION_PATTERN = "mybatis.mapperLocationPattern";
    private final static String TYPE_ALIASES_PACKAGE = "mybatis.typeAliasesPackage";
    private final static String CONFIG_LOCATION = " mybatis.configLocation";

    /**
     * myBatis의 {@link org.apache.ibatis.session.SqlSessionFactory}을 생성하는 팩토리빈을 등록한다.
     * @throws Exception
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(final ApplicationContext applicationContext) throws Exception {
        final SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        // 마이바티스가 사용한 DataSource를 등록
        factoryBean.setDataSource(this.dataSource());
        // 마이바티스 설정파일 위치 설정
        //factoryBean.setConfigLocation(applicationContext.getResource(ToyPropertyResolver.getProperty(CONFIG_LOCATION)));
        // com.lge.apip.mgmt.ocpo.*.model 패키지 이하의 model 클래스 이름을 짧은 별칭으로 등록
        //factoryBean.setTypeAliasesPackage(ToyPropertyResolver.getProperty(TYPE_ALIASES_PACKAGE));
        // META-INF/mybatis/mappers 패키지 이하의 모든 XML을 매퍼로 등록
        factoryBean.setMapperLocations(applicationContext.getResources(ToyPropertyResolver.getProperty(MAPPER_LOCATION_PATTERN)));
        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(final ApplicationContext applicationContext) throws Exception{
        return new SqlSessionTemplate(this.sqlSessionFactory(applicationContext));
    }


}
