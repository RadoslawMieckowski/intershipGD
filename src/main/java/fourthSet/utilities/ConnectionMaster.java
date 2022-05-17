package fourthSet.utilities;

import fourthSet.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
