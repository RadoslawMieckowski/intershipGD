package fourthSet.utilities;

import fourthSet.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.BiFunction;

public final class ConnectionMaster {
    private DataSource dataSource;

    private ConnectionMaster(DataSource dataSource) throws SQLException {
        this.dataSource = dataSource;
    }

    public void execute(String query, Object[] args) {
        try(Connection connection = DataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            int i = 1;
            for (Object arg : args) {
                preparedStatement.setObject(i++, arg);
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public <S, V, T> T findOne(String query, Object[] args, BiFunction<S, V, T> mapper) {
        try(Connection connection = DataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            int i = 1;
            for (Object arg : args) {
                preparedStatement.setObject(i++, arg);
            }
            ResultSet resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
