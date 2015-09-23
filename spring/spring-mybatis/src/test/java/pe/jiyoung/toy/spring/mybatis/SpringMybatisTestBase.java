package pe.jiyoung.toy.spring.mybatis;

import static org.springframework.util.Assert.notNull;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pe.jiyoung.toy.spring.common.util.ToyPropertyResolver;

@ContextConfiguration(locations = { "classpath:spring/spring-mibatis-applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringMybatisTestBase {

    //    @Autowired
    //    SqlSessionFactoryBean sqlSessionFactoryBean;
    //
    //    @Test
    //    public void test() throws Exception {
    //        final SqlSession session = this.sqlSessionFactoryBean.getObject().openSession();
    //        try {
    //            final Map<String, Object> user = session.selectOne("userMapper.findUserById", "myrenai");
    //            System.out.println(user.get("userId"));
    //        } finally {
    //            session.close();
    //        }
    //    }

    @Autowired
    DataSource dataSource;

    @Test
    public void springMybatisTestBase() {
        System.out.println(ToyPropertyResolver.getProperty("mybatis.mapperLocationPattern"));
        System.out.println(ToyPropertyResolver.getProperty("mybatis.configLocation"));
        notNull(this.dataSource);
    }
}
