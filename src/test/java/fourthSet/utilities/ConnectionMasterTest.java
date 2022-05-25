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
    }


    @Test
    void executeTest() throws SQLException {
       String query = Statement.executeInsertRowStatement;
       Object[] args = new Object[] {101, "Radek"};

        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);

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
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getObject(1)).thenReturn(101);
        when(resultSet.getObject(2)).thenReturn("Radek");

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
        String query = Statement.findManyWithGivenIDsStatement;
        Object[] args = new Object[] {3, 5};
        BiFunction<Integer, String, User> mapper = (id, name) -> new User(id, name);
        User expectedUser1 = new User(3, "Milton");
        User expectedUser2 = new User(4, "Starlene");
        User expectedUser3 = new User(5, "Tomlin");
        List<User> expectedListOfUsers = List.of(expectedUser1, expectedUser2, expectedUser3);

        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true)
                .thenReturn(true)
                .thenReturn(true)
                .thenReturn(false);
        when(resultSet.getObject(1)).thenReturn(3)
                .thenReturn(4)
                .thenReturn(5);
        when(resultSet.getObject(2)).thenReturn("Milton")
                .thenReturn("Starlene")
                .thenReturn("Tomlin");

        List<User> actualListOfUsers = connectionMaster.findMany(query, args, mapper);
        User actualUser1 = actualListOfUsers.get(0);
        User actualUser2 = actualListOfUsers.get(1);
        User actualUser3 = actualListOfUsers.get(2);

        assertThat(actualListOfUsers).isEqualTo(expectedListOfUsers);
        assertThat(actualListOfUsers.size()).isEqualTo(3);
        assertThat(actualUser1).isEqualTo(expectedUser1);
        assertThat(actualUser2).isEqualTo(expectedUser2);
        assertThat(actualUser3).isEqualTo(expectedUser3);
    }
}