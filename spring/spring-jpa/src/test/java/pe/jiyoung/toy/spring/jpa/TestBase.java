package pe.jiyoung.toy.spring.jpa;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

@ContextConfiguration(locations = { "classpath:spring/applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TestBase {

    @Autowired
    private DataSource dataSource;

    @Test
    public void dbContextTest(){
        Assert.notNull(this.dataSource);
    }

}
