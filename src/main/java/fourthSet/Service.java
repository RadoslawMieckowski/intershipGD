package fourthSet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Service {
    public List<User> fetchData() throws SQLException {
        String SQL_QUERY = Main.getEverything;
        List<User> users = null;
        try (Connection connection = DataSource.getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement( SQL_QUERY );
             ResultSet resultSet = prepareStatement.executeQuery()) {
             users = new ArrayList<>();
             User user;
             while ( resultSet.next() ) { // nie hasnext? poczytaj
                user = new User();
                user.setId( resultSet.getInt( "id" ) );
                user.setUsername( resultSet.getString( "username" ) );//poczytaj o metodach resultseta
                users.add(user);
             }
        }
        return users;
    }
}
