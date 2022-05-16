package fourthSet;

import java.sql.SQLException;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        try {
            Service service = new Service();
            service.createTable();
            //service.insertData();
            service.deleteRow(DataSource.getConnection(), 3);
            List<User> users = service.fetchData();
            System.out.println(users);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
