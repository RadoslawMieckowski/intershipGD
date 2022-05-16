package fourthSet;

import java.sql.SQLException;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        try {
            Service service = new Service();
            service.createTable();
            //service.insertData();
            List<User> users = service.fetchData();
            System.out.println(users);
            service.deleteRow(DataSource.getConnection(), 3);
            System.out.println(users);
            service.deleteEverything();
            System.out.println(users);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
