package pe.jiyoung.toy.spring.jpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = { "classpath:spring/spring-jpa-applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringJpaTestBase {

    @Test
    public void test() {
    }
}
