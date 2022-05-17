package fourthSet.utilities;

import fourthSet.DataSource;
import fourthSet.exceptions.IllegalSizeOfRezultSet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.BiFunction;

public final class ConnectionMaster {
    private Connection connection;

    public ConnectionMaster(Connection connection) {
        this.connection = connection;
    }

    public void execute(String query, Object[] args) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
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
        T result = null;
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            int i = 1;
            for (Object arg : args) {
                preparedStatement.setObject(i++, arg);
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            int resultSetSize = resultSet.getFetchSize();
            if (resultSetSize > 1) {
                throw new IllegalSizeOfRezultSet("ResultSet contains too many results!" +
                        " Expected one result");
            }
            if (resultSetSize == 0) {
                System.out.printf("can't find any results in the table with the given args.");
            } else {
                result = mapper.apply((S)args[0],(V)args[1]);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
