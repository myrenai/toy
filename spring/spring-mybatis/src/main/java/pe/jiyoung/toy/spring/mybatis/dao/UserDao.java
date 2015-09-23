package pe.jiyoung.toy.spring.mybatis.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends SqlSessionDaoSupport{

    @Autowired
    @Override
    public void setSqlSessionFactory(final SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    public Map<String, Object> findById(final String userId){
        return this.getSqlSession().selectOne("userMapper.findUserById", userId);
    }

    public int create(final Map<String, Object> user) {
        return this.getSqlSession().insert("userMapper.createUser", user);
    }

    public int update(final Map<String, Object> user) {
        return this.getSqlSession().update("userMapper.updateUser", user);
    }
}
