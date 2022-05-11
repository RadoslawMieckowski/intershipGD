package fourthSet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Service {
    public List<User> fetchData() throws SQLException {
        String SQL_QUERY = Statement.getEverything;
        List<User> users = null;
        try (Connection connection = DataSource.getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement( SQL_QUERY );
             ResultSet resultSet = prepareStatement.executeQuery()) {
             users = new ArrayList<>();
             User user;
             while ( resultSet.next() ) {
                user = new User();
                user.setId( resultSet.getInt( "id" ) );
                user.setUsername( resultSet.getString( "username" ) );
                users.add(user);
             }
        }
        return users;
    }

    public void insertData() throws SQLException {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     Statement.insertIntoTableStatement )
             ) {
            int numberOfRows = preparedStatement.executeUpdate();
            System.out.println("Number of rows inserted: " + numberOfRows);
        }
    }

    public void createTable() throws SQLException {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     Statement.createTableStatement)) {
        preparedStatement.executeUpdate();
        }
    }
}
