package fourthSet;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {
    private static HikariConfig config = new HikariConfig();

    private static HikariDataSource dataSource;
    static {
        config.setJdbcUrl( "jdbc:postgresql://localhost/users" );
        config.setUsername( "postgres" );
        config.setPassword( "root" );
        config.addDataSourceProperty( "cachePrepStmts" , "true" );
        config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
        dataSource = new HikariDataSource( config );
    }

    public DataSource() {
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

}
