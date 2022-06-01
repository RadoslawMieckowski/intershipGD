package fourthSet;

public class Statement {
   public static final String createTableStatement = """
            CREATE TABLE IF NOT EXISTS usersTable (
            id INT PRIMARY KEY,
            username VARCHAR(20) NOT NULL);
            """;

   public static final String insertIntoTableStatement = """
            INSERT INTO usersTable (id, username)
            VALUES
                (1,'radek'),
                (2,'mark'),
                (3,'tom'),
                (4,'john'),
                (5,'john'),
                (6, 'bruce');
            """;

  public static final String insertIntoTable100Users = "INSERT INTO usersTable (id, username) VALUES";



  public static final String getEverything = "select * from usersTable;";

  public static final String deleteUserFromUsers = "DELETE FROM usersTable WHERE id = ?;";

  public static final String deleteEverything = "DELETE FROM usersTable;";

  public static final String executeInsertRowStatement = "INSERT INTO  usersTable (id, username) VALUES (?, ?);";

  public static final String findOneStatement = """
           Select id, username 
           FROM usersTable 
           WHERE id = ? AND username = ?;
           """;

  public static final String findManyWithGivenIDsStatement = "select * from usersTable where id between ? and ?;";

  public static  final String findManyWithGivenNamesStatement = "select * from usersTable where username like ?;";
}
