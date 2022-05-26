package fourthSet.utilities;


import fourthSet.DataSource;
import fourthSet.Service;
import fourthSet.Statement;
import fourthSet.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.function.BiFunction;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class ConnectionMasterTest {

    Service service = new Service();
    DataSource dataSource = new DataSource();
    ConnectionMaster connectionMaster = new ConnectionMaster(dataSource);

    @BeforeEach
    void createTable_usersTable_AndInsert100Users() throws SQLException {
        service.createTable();
        service.insert100Users();
    }

    @AfterEach
    void deleteEverythingFrom_usersTable() throws SQLException{
        service.deleteEverything();
    }

    @Test
    void findOneCorrectTest() {
        String query = Statement.findOneStatement;
        Object[] args = new Object[] {3, "Milton"};
        BiFunction<Integer, String, User> mapper =
                (id, name) -> new User(id, name);
        User expectedUser = new User(3, "Milton");

        User actualUser = connectionMaster.findOne(query, args, mapper);

        assertThat(actualUser).isEqualTo(expectedUser);
    }

    @Test
    void findOneZeroFoundWithExistingUsernameTest() {
        String query = Statement.findOneStatement;
        Object[] args = new Object[] {105, "Milton"};
        BiFunction<Integer, String, User> mapper = (id, name) -> new User(id, name);

        User actualUser = connectionMaster.findOne(query, args, mapper);

        assertThat(actualUser).isNull();
    }

    @Test
    void findOneZeroFoundWithExistingIdTest() {
        String query = Statement.findOneStatement;
        Object[] args = new Object[] {1, "Radek"};
        BiFunction<Integer, String, User> mapper = (id, name) -> new User(id, name);

        User actualUser = connectionMaster.findOne(query, args, mapper);

        assertThat(actualUser).isNull();
    }

    @Test
    void findOneZeroFoundWithNotExistingUsernameAndNotExsistingIdTest() {
        String query = Statement.findOneStatement;
        Object[] args = new Object[] {105, "Radek"};
        BiFunction<Integer, String, User> mapper = (id, name) -> new User(id, name);

        User actualUser = connectionMaster.findOne(query, args, mapper);

        assertThat(actualUser).isNull();
    }

    @Test
    void executeTest() {
        String query = Statement.executeInsertRowStatement;
        Object[] args = new Object[]{101, "Mateusz"};
        BiFunction<Integer, String, User> mapper = (id, name) -> new User(id, name);
        User expectedUser = new User(101, "Mateusz");

        connectionMaster.execute(query, args);
        User actualUser = connectionMaster.findOne(Statement.findOneStatement, args, mapper);

        assertThat(actualUser).isEqualTo(expectedUser);
    }

    @Test
    void findManyTest() {
        String query = Statement.findManyWithGivenIDsStatement;
        Object[] args = new Object[] {3, 5};
        BiFunction<Integer, String, User> mapper = (id, name) -> new User(id, name);
        User expectedUser1 = new User(3, "Milton");
        User expectedUser2 = new User(4, "Starlene");
        User expectedUser3 = new User(5, "Tomlin");
        List<User> expectedListOfUsers = List.of(expectedUser1, expectedUser2, expectedUser3);

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

    @Test
    void findManyFoundZeroResultsTest() {
        String query = Statement.findManyWithGivenIDsStatement;
        Object[] args = new Object[] {201, 203};
        BiFunction<Integer, String, User> mapper = (id, name) -> new User(id, name);
        List<User> expectedListOfUsers = List.of();

        List<User> actualListOfUsers = connectionMaster.findMany(query, args, mapper);

        assertThat(actualListOfUsers).isEqualTo(expectedListOfUsers);
        assertThat(actualListOfUsers).hasSize(0);
    }
}