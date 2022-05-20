package fourthSet.utilities;

import fourthSet.DataSource;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ConnectionMasterTest {

    @Test
    void executeTest() throws SQLException {
        Connection connection = Mockito.mock(DataSource.getConnection().getClass());
    }
}