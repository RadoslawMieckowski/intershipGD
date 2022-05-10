package fourthSet;

import org.postgresql.jdbc3.Jdbc3PoolingDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    String createTableStatement = """
            CREATE TABLE [IF NOT EXISTS] users (
               id INT PRIMARY KEY,
               username VARCHAR(20) NOT NULL,
            );
            """;

    String insertIntoTableStatement = """
            INSERT INTO users (id, username)
            VALUES
                (1,'radek'),
                (2,'mark'),
                (3,'tom'),
                (4,'john'),
                (4,'john'),
                (5, 'bruce')
            RETURNING *;
            """;

    public static List<Users> fetchData() throws SQLException {
        String SQL_QUERY = "select * from emp";
        List<Employee> employees = null;
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement( SQL_QUERY );
             ResultSet rs = pst.executeQuery();) {
            employees = new ArrayList<>();
            Employee employee;
            while ( rs.next() ) {
                employee = new Employee();
                employee.setEmpNo( rs.getInt( "empno" ) );
                employee.setEname( rs.getString( "ename" ) );
                employee.setJob( rs.getString( "job" ) );
                employee.setMgr( rs.getInt( "mgr" ) );
                employee.setHiredate( rs.getDate( "hiredate" ) );
                employee.setSal( rs.getInt( "sal" ) );
                employee.setComm( rs.getInt( "comm" ) );
                employee.setDeptno( rs.getInt( "deptno" ) );
                employees.add( employee );
            }
        }
        return employees;
    }
   /* Class driverClass;

    {
        try {
            driverClass = Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String url = "jdbc:postgresql://localhost/users";
        DataSource source = new Jdbc3PoolingDataSource();
        };

        try (Connection db = DriverManager.getConnection(url, properties)){
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
}
