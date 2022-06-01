package fourthSet.exceptions;

import java.sql.SQLException;

public class MySQLWrapperException extends RuntimeException{
    public MySQLWrapperException(String message, SQLException exception) {
        super(message, exception);
    }
}
