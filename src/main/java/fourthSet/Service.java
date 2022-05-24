package fourthSet;

import lombok.NonNull;
import secondSet.utilities.CSVReader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Service {
    public Service() {
    }

    public List<User> fetchData() throws SQLException {
        String SQL_QUERY = Statement.getEverything;
        List<User> users;
        try (Connection connection = new DataSource().getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(SQL_QUERY);
             ResultSet resultSet = prepareStatement.executeQuery()) {
             users = new ArrayList<>();
             User user;
             while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                users.add(user);
             }
        }
        return users;
    }

    public void insertData() throws SQLException {
        try (Connection connection = new DataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     Statement.insertIntoTableStatement)
             ) {
            int numberOfRows = preparedStatement.executeUpdate();
            System.out.println("Number of rows inserted: " + numberOfRows);
        }
    }

    public void createTable() throws SQLException {
        try (Connection connection = new DataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     Statement.createTableStatement)) {
        preparedStatement.executeUpdate();
        }
    }

    public int deleteRow(@NonNull Connection connection, int id) throws SQLException {
        try(PreparedStatement preparedStatement = connection.prepareStatement(
                Statement.deleteUserFromUsers)){
            preparedStatement.setInt(1, id);
            int rowAffected = preparedStatement.executeUpdate();
            return rowAffected;
        }
    }

    public int deleteEverything() throws SQLException {
        try(Connection connection = new DataSource().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                Statement.deleteEverything)) {
           int rowAffected = preparedStatement.executeUpdate();
           return rowAffected;
        }
    }

    public void insert100Users() throws SQLException {
        try (Connection connection = new DataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     Statement.insertIntoTable100Users +
                             CSVReader.prepareToInsertSQLValues(
                                     "src/main/resources/MOCK_DATA.csv") )
        ) {
            int numberOfRows = preparedStatement.executeUpdate();
            System.out.println("Number of rows inserted: " + numberOfRows);
        }
    }
}
