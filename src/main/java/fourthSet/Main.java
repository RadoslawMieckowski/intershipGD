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
