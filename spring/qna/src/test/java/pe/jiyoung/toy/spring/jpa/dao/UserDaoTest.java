package pe.jiyoung.toy.spring.jpa.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import pe.jiyoung.toy.spring.jpa.TestBase;

public class UserDaoTest extends TestBase{

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoTest.class);

    @Before
    public void setup(){
    }


    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDao userDao;

    @Test
    public void dbContextTest1(){
        Assert.notNull(this.dataSource);
    }


    /**
     * CREATE TABLE IF NOT EXISTS users (
  userId VARCHAR(12) NULL PRIMARY KEY,
  password VARCHAR(30) NOT NULL,
  name  VARCHAR(30) NOT NULL,
  email VARCHAR(50),
  INDEX(name)
)
     */
    @Test
    public void findById(){
        final String userId = "myrenai";
        final Map<String, Object> user = this.userDao.findById(userId);
        final Iterator<Entry<String, Object>> iter = user.entrySet().iterator();
        while(iter.hasNext()){
            final Entry<String, Object> entry = iter.next();
            entry.getKey();
            entry.getValue();
            LOGGER.debug("{} = {}", entry.getKey(), entry.getValue());
        }
    }

    @Test
    public void create() throws Exception {
        final Map<String, Object> user = this.buildUser(1);
        this.userDao.createUser(user);
        final Map<String, Object> user2 = this.userDao.findById("userId"+1);
        Assert.isTrue(user.get("userId").equals(user2.get("userId")));
    }


    private Map<String, Object> buildUser(final int index) {
        final Map<String, Object> user = new HashMap<String, Object>();
        user.put("userId", "userId" + index);
        user.put("password", "password" + index);
        user.put("name", "name" + index);
        user.put("email", "email" + index + "@gmail.com");
        return user;
    }

}
