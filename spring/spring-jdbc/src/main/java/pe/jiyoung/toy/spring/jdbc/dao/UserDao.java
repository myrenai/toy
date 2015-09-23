package pe.jiyoung.toy.spring.jdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;


@Repository
public class UserDao extends JdbcDaoSupport{

    @Autowired
    DataSource dataSource;
    @PostConstruct
    public void init(){
        this.setDataSource(this.dataSource);
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDao.class);

    public Map<String, Object> findById(final String userId){
        final String sql = "select * from users where userId = ?";
        final RowMapper<Map<String, Object>> rowMapper = new RowMapper<Map<String, Object>> () {

            @Override
            public Map<String, Object> mapRow(final ResultSet rs, final int rowNum) throws SQLException {
                final Map<String, Object> mapRow = new HashMap<String, Object>();
                mapRow.put("userId", rs.getString("userId"));
                mapRow.put("password", rs.getString("password"));
                mapRow.put("name", rs.getString("name"));
                mapRow.put("email", rs.getString("email"));
                return mapRow;
            }
        };

        try {
            return this.getJdbcTemplate().queryForObject(sql, rowMapper, userId);
        } catch (final EmptyResultDataAccessException e) {
            LOGGER.warn("{} is not found", userId);
            return null;

        }
    }

    public int create(final Map<String, Object> user) {
        final String sql = "insert into users values(?,?,?,?)";
        return this.getJdbcTemplate().update(sql, user.get("userId"), user.get("password"), user.get("name"), user.get("email"));
    }

    public int update(final Map<String, Object> user) {
        final String sql = "update users set password = ?, name = ?, email = ? where userId = ?";
        return this.getJdbcTemplate().update(sql, user.get("password"), user.get("name"), user.get("email"), user.get("userId"));
    }
}
