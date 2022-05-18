package fourthSet;

public class Statement {
   static String createTableStatement = """
            CREATE TABLE IF NOT EXISTS usersTable (
            id INT PRIMARY KEY,
            username VARCHAR(20) NOT NULL);
            """;

    static String insertIntoTableStatement = """
            INSERT INTO usersTable (id, username)
            VALUES
                (1,'radek'),
                (2,'mark'),
                (3,'tom'),
                (4,'john'),
                (5,'john'),
                (6, 'bruce');
            """;

    static String insertIntoTable100Users = "INSERT INTO usersTable (id, username) VALUES";



    static String getEverything = "select * from usersTable;";

    static String deleteUserFromUsers = "DELETE FROM usersTable WHERE id = ?;";

    static String deleteEverything = "DELETE FROM usersTable;";

    static String executeInsertRowStatement = "INSERT INTO  usersTable (id, username) VALUES (?, ?);";

    static String findOneStatement = """
           Select id, username 
           FROM usersTable 
           WHERE id = ? AND username = ?;
           """;

    static  String findManyWithGivenIDsStatement = "select * from usersTable where id between ? and ?;";

    static  String findManyWithGivenNamesStatement = "select * from usersTable where username like ?;";
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
