package pe.jiyoung.toy.spring.common.db.conf;

public class DatabaseConstants {
    public static String MYSQL = "MYSQL";
    public static String ORACLE = "ORACLE";

    public static String JDBC_DRIVER_CLASSNAME = "jdbc.driverClassName";

    public static String JDBC_URL = "jdbc.url";

    public static String JDBC_USERNAME = "jdbc.username";
    public static String JDBC_PASSWORD = "jdbc.password";

    // additionalProperties
    public static String HIBERNATE_DIALECT = "hibernate.dialect";
    public static String DATABASE = "database";
    public static String SHOW_SQL = "show_sql";
    public static String FORMAT_SQL = "format_sql";

    public static String PERSISTENCE = "toyPersistence";

    public static String JDBC_POOL_INIT_SIZE = "jdbc.pool.init.size";
    public static String JDBC_POOL_MAX_SIZE = "jdbc.pool.max.size";

    /**
     * # Oracle Universal Connection Pool = ups
     * # OracleDataSource = ods
     * # Tomcat connection pool = tomcat
     * # HikariCP connection pool = hikaricp
     * # default = default
     */
    public static String JDBC_POOL_NAME = "jdbc.pool.name";

    public final static String TOMCAT = "tomcat";
    public final static String UPS = "ups";
    public final static String ODS = "ods";
    public final static String HIKARICP = "hikaricp";
}
