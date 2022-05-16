package fourthSet;

import java.sql.SQLException;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        try {
            Service service = new Service();
            service.createTable();
            service.insertData();
            List<User> users = service.fetchData();

            System.out.println("Users after creation of the table:\n" + users);
            service.deleteRow(DataSource.getConnection(), 3);
            users = service.fetchData();

            System.out.println("Users after deleting third row:\n" + users);
            service.deleteEverything();
            users = service.fetchData();
            System.out.println("Users after deleting everything\n" + users);

            service.insert100Users();
            users = service.fetchData();
            System.out.println("Users after inserting 100 users\n" + users);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
