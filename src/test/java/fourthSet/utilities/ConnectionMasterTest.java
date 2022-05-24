package fourthSet.utilities;

import fourthSet.DataSource;
import fourthSet.Statement;
import fourthSet.User;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ConnectionMasterTest {

    DataSource dataSource = mock(DataSource.class);
    Connection connection = mock(Connection.class);
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    ConnectionMaster connectionMaster = new ConnectionMaster(dataSource);

    ConnectionMasterTest() throws SQLException {
    }


    @Test
    void executeTest() throws SQLException {

//        Object[] args = mock(Object[].class);
//        BiFunction mapper = mock(BiFunction.class);

       when(new ConnectionMaster()).thenReturn(connectionMaster);
       when(DataSource.getConnection()).thenReturn(connection);
       when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
       //when(connectionMaster.findOne(anyString(), args, mapper)).thenReturn(resultSet);
       user = new User();
       user.setId(17);
       user.setUsername("Kris");
       when(resultSet.getInt(1)).thenReturn(17);
       when(resultSet.getString(2)).thenReturn("Kris");
       when(preparedStatement.executeQuery()).thenReturn(resultSet);
        //Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);

        ConnectionMaster actualConnectionMaster = new ConnectionMaster();
        actualConnectionMaster.execute(
                Statement.executeInsertRowStatement,
                new Object[] {17, "Kris"}
        );
        User actualUser = actualConnectionMaster.findOne(
                Statement.findOneStatement,
                new Object[] {user.getId(), user.getUsername()},
                (id, name) -> new User((Integer)id, (String)name)
        );

        assertThat(user).isEqualTo(actualUser);
    }
}