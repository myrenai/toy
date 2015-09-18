package pe.jiyoung.toy.spring.jpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pe.jiyoung.toy.spring.common.util.ToyPropertyResolver;

@ContextConfiguration(locations = { "classpath:spring/test-spring-common-applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TestBase {


    @Test
    public void test() {
        System.out.println(ToyPropertyResolver.getProperty("common1"));
        System.out.println(ToyPropertyResolver.getProperty("common2"));
    }

}
