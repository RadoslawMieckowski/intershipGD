package fourthSet.utilities;

import fourthSet.DataSource;
import fourthSet.Statement;
import fourthSet.User;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.BiFunction;

import static org.mockito.Mockito.*;

class ConnectionMasterTest {
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();
    @Mock
    private ConnectionMaster connectionMaster;
    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement preparedStatement;
    @Mock
    private ResultSet resultSet;
    private User user;

    @Test
    void executeTest() throws SQLException {

        Object[] args = mock(Object[].class);
        BiFunction mapper = mock(BiFunction.class);

       when(new ConnectionMaster()).thenReturn(connectionMaster);
       when(DataSource.getConnection()).thenReturn(connection);
       when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
       when(connectionMaster.findOne(anyString(), args, mapper)).thenReturn(resultSet);
        //Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);

        ConnectionMaster actualConnectionMaster = new ConnectionMaster();
        actualConnectionMaster.findOne(
                Statement.findOneStatement,
                new Object[] {101, "Radek"},
                (id, name) -> new User((Integer)id, (String)name)
        );

        //Assertions.assertThat()
    }
}