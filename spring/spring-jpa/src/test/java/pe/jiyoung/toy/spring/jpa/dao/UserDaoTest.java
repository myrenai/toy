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
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import pe.jiyoung.toy.spring.jpa.SpringJpaTestBase;

@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
public class UserDaoTest extends SpringJpaTestBase{

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
    @Transactional
    public void create() throws Exception {
        final int userIndex = 5;
        final Map<String, Object> user = this.buildUser(userIndex);
        this.userDao.create(user);
        final Map<String, Object> user2 = this.userDao.findById("userId"+userIndex);
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
