package fourthSet.utilities;

import fourthSet.DataSource;
import fourthSet.exceptions.IllegalSizeOfRezultSet;
import lombok.NonNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiFunction;

public final class ConnectionMaster {
    private final Connection connection;

    public ConnectionMaster() throws SQLException {
        connection = DataSource.getConnection();
    }

    public void execute(@NonNull String query, @NonNull Object[] args) {
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

    public <S, V, T> T findOne(@NonNull String query,
                               @NonNull Object[] args,
                               @NonNull BiFunction<S, V, T> mapper) {
        T result = null;
        try(PreparedStatement preparedStatement = connection.prepareStatement(
                query, ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE)) {
            int i = 1;
            for (Object arg : args) {
                preparedStatement.setObject(i++, arg);
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.last();
            int resultSetSize = resultSet.getRow();
            if (resultSetSize > 1) {
                throw new IllegalSizeOfRezultSet("ResultSet contains too many results!" +
                        " Expected one result");
            }
            if (resultSetSize == 0) {
                System.out.print("can't find any results in the table with the given args.\n");
            } else {
                resultSet.beforeFirst();
                result = mapper.apply((S)args[0],(V)args[1]);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public <S, V, T> List<T> findMany(@NonNull String query,
                                      @NonNull Object[] args,
                                      @NonNull BiFunction<S, V, T> mapper) {
        List<T> result = new LinkedList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            int i = 1;
            for (Object arg : args) {
                preparedStatement.setObject(i++, arg);
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.add(mapper.apply(
                        (S)resultSet.getObject(1),
                        (V)resultSet.getObject(2))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
