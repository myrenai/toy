package pe.jiyoung.toy.spring.common.db;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import pe.jiyoung.toy.spring.common.util.ToyPropertyResolver;

@ContextConfiguration(locations = { "classpath:spring/test-spring-common-db-applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringCommonDbTestBase {

    @Autowired
    DataSource dataSource;

    @Test
    public void springCommonDbTestBaseTest() {
        System.out.println(ToyPropertyResolver.getProperty("jdbc.pool.init.size"));
        System.out.println(ToyPropertyResolver.getProperty("jdbc.pool.name"));
        Assert.notNull(this.dataSource);
    }


}
