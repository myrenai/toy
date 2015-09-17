package pe.jiyoung.toy.spring.jpa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

import pe.jiyoung.toy.spring.common.util.ToyPropertyResolver;
import pe.jiyoung.toy.spring.common.util.ToyResourceLoader;

@Component
public class UserDao extends JdbcDaoSupport{

    private final static String INITDB = "jdbc.initDB";

    @Autowired
    DataSource dataSource;

    @PostConstruct
    public void init(){
        if(ToyPropertyResolver.getPropertyAsBoolean(INITDB)){
            final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
            populator.addScript(ToyResourceLoader.getClasspathResource("db/initDB.sql"));
            populator.addScript(ToyResourceLoader.getClasspathResource("db/populateDB.sql"));
            DatabasePopulatorUtils.execute(populator, this.dataSource);
        }

        this.setDataSource(this.dataSource);
    }

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

        return this.getJdbcTemplate().queryForObject(sql, rowMapper, userId);
    }

}
