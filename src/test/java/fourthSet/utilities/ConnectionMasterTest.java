package fourthSet.utilities;

import fourthSet.DataSource;
import fourthSet.Statement;
import fourthSet.User;
import fourthSet.exceptions.IllegalSizeOfRezultSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.function.BiFunction;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Java6Assertions.assertThat;
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
    void findOneCorrect() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);
        String query = Statement.findOneStatement;
        Object[] args = new Object[] {101, "Radek"};
        BiFunction<Integer, String, User> mapper =
                (id, name) -> new User(id, name);

        User expectedUser = new User(101, "Radek");

        when(connection.prepareStatement(anyString(), anyInt(),
                anyInt())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.getRow()).thenReturn(1);

        User actualUser = connectionMaster.findOne(query, args, mapper);

        verify(dataSource).getConnection();
        verify(connection).prepareStatement(anyString(), anyInt(), anyInt());
        verify(preparedStatement, times(2)).setObject(anyInt(),any());
        verify(preparedStatement).executeQuery();
        verify(resultSet).last();
        verify(resultSet).getRow();
        verify(resultSet).beforeFirst();

        assertThat(actualUser).isEqualTo(expectedUser);
    }

    @Test
    void findOneZeroFound() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);
        String query = Statement.findOneStatement;
        Object[] args = new Object[] {101, "Radek"};
        BiFunction<Integer, String, User> mapper =
                (id, name) -> new User(id, name);

        User expectedUser = null;

        when(connection.prepareStatement(anyString(), anyInt(),
                anyInt())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.getRow()).thenReturn(0);

        User actualUser = connectionMaster.findOne(query, args, mapper);

        verify(dataSource).getConnection();
        verify(connection).prepareStatement(anyString(), anyInt(), anyInt());
        verify(preparedStatement, times(2)).setObject(anyInt(),any());
        verify(preparedStatement).executeQuery();
        verify(resultSet).last();
        verify(resultSet).getRow();

        assertThat(actualUser).isEqualTo(expectedUser);
    }

    @Test
    void findOneThrowIllegalSizeOfRezultSet() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);
        String query = Statement.findOneStatement;
        Object[] args = new Object[] {101, "Radek"};
        BiFunction<Integer, String, User> mapper =
                (id, name) -> new User(id, name);

        when(connection.prepareStatement(anyString(), anyInt(),
                anyInt())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.getRow()).thenReturn(2);

         assertThatExceptionOfType(IllegalSizeOfRezultSet.class)
                 .isThrownBy(() ->connectionMaster.findOne(query, args, mapper))
                 .withMessage("ResultSet contains too many results!" +
                         " Expected one result");

        verify(dataSource).getConnection();
        verify(connection).prepareStatement(anyString(), anyInt(), anyInt());
        verify(preparedStatement, times(2)).setObject(anyInt(),any());
        verify(preparedStatement).executeQuery();
        verify(resultSet).last();
        verify(resultSet).getRow();
    }

    @Test
    void findManyTest() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);
        BiFunction mapper = mock(BiFunction.class);
        String sampleQuery = "SAMPLE QUERY";
        var args = new Object[]{1, 2};

        User expectedUser1 = new User(1, "USERNAME1");
        User expectedUser2 = new User(2, "USERNAME2");
        List<User> expectedResultList = List.of(expectedUser1, expectedUser2);

        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, true, false);
        when(mapper.apply(eq(resultSet.getInt(1)), eq(resultSet.getString(2)))).thenReturn(new User(expectedUser1.getId(), expectedUser2.getUsername()));

        List<User> result = connectionMaster.findMany(sampleQuery, args, mapper);

        assertThat(expectedResultList).isEqualTo(result);

    }
}