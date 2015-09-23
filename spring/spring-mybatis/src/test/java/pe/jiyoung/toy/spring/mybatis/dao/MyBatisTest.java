package pe.jiyoung.toy.spring.mybatis.dao;


import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class MyBatisTest {

    private SqlSessionFactory sqlSessionFactory ;

    @Before
    public void setup() throws IOException{
        final String resource = "mybatis-config-test.xml";
        final InputStream is = Resources.getResourceAsStream(resource);
        this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
    }

    @Test
    public void gettingStarted() throws IOException {
        final SqlSession session = this.sqlSessionFactory.openSession();
        try {
            final Map<String, Object> user = session.selectOne("userMapper.findUserById", "myrenai");
        } finally {
            session.close();
        }
    }

    @Test
    public void createUser(){
        final SqlSession session = this.sqlSessionFactory.openSession();
        try {
            final Map<String, Object> user = new HashMap<String, Object>();
            user.put("userId", "myrenai3");
            user.put("password", "pw3577");
            user.put("name", "JiyoungPark");
            user.put("email", "myrenai3@gmail.com");
            session.insert("userMapper.createUser", user);
            final Map<String, Object> user2 = session.selectOne("userMapper.findUserById", "myrenai3");

            Assert.assertEquals(user.get("userId"), user2.get("userId"));
            System.out.println(user2);
        } finally {
            session.close();
        }

    }

}
