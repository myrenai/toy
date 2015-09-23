package pe.jiyoung.toy.spring.jdbc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pe.jiyoung.toy.spring.common.util.ToyPropertyResolver;

@ContextConfiguration(locations = { "classpath*:spring/spring-jdbc-applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringJdbcTestBase {

    @Test
    public void springJdbcTestBase() {
        System.out.println(ToyPropertyResolver.getProperty("jdbc.pool.init.size"));
        System.out.println(ToyPropertyResolver.getProperty("jdbc.pool.name"));
    }

}
