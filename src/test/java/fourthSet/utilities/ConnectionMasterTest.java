package fourthSet.utilities;

import fourthSet.DataSource;
import fourthSet.Statement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.mockito.Mockito.*;

class ConnectionMasterTest {

    DataSource dataSource = mock(DataSource.class);
    Connection connection = mock(Connection.class);
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    ConnectionMaster connectionMaster = new ConnectionMaster(dataSource);


    @BeforeEach
    void stubMethods() throws SQLException {
        when(dataSource.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
    }


    @Test
    void executeTest() throws SQLException {
       String query = Statement.executeInsertRowStatement;
       Object[] args = new Object[] {101, "Radek"};

       connectionMaster.execute(query, args);

       verify(dataSource).getConnection();
       verify(connection).prepareStatement(eq(query));
       verify(preparedStatement, times(2)).setObject(anyInt(),any());
       verify(preparedStatement).executeUpdate();
    }

    @Test
    void findOne() {

    }

    @Test
    void findMany() {

    }
}