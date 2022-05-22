package fourthSet.utilities;

import fourthSet.DataSource;
import fourthSet.Statement;
import fourthSet.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.BiFunction;

class ConnectionMasterTest {

    @Test
    void executeTest() throws SQLException {
        ConnectionMaster connectionMaster = Mockito.mock(ConnectionMaster.class);
        //Connection connection = Mockito.mock(DataSource.getConnection().getClass());
        //PreparedStatement preparedStatement = Mockito.mock(PreparedStatement.class);
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        Object[] args = Mockito.mock(Object[].class);
        BiFunction mapper = Mockito.mock(BiFunction.class);

        Mockito.when(new ConnectionMaster()).thenReturn(connectionMaster);
        //Mockito.when(DataSource.getConnection()).thenReturn(connection);
        Mockito.when(connectionMaster.findOne(Mockito.anyString(), args, mapper)).thenReturn(resultSet);
        //Mockito.when(connection.prepareStatement(Mockito.anyString())).thenReturn(preparedStatement);
        //Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);

        ConnectionMaster actualConnectionMaster = new ConnectionMaster();
        actualConnectionMaster.findOne(
                Statement.findOneStatement,
                new Object[] {101, "Radek"},
                (id, name) -> new User((Integer)id, (String)name)
        );

        Assertions.assertThat()
    }
}