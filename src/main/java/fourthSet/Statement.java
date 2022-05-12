package fourthSet;

public class Statement {
   static String createTableStatement = """
            CREATE TABLE IF NOT EXISTS users (
            id INT PRIMARY KEY,
            username VARCHAR(20) NOT NULL);
            """;

    static String insertIntoTableStatement = """
            INSERT INTO users (id, username)
            VALUES
                (1,'radek'),
                (2,'mark'),
                (3,'tom'),
                (4,'john'),
                (5,'john'),
                (6, 'bruce')
            RETURNING *;
            """;

    static String getEverything = "select * from users";


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
