package fourthSet;

import fourthSet.utilities.ConnectionMaster;

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

            ConnectionMaster connectionMaster = new ConnectionMaster(DataSource.getConnection());
            User user17 = connectionMaster.findOne(
                    Statement.findOneStatement,
                    new Object[] {17, "Kris"},
                    (id, name) -> new User((Integer)id, (String)name)
            );
            System.out.println("user17: " + user17);

            connectionMaster.execute(
                    Statement.executeInsertRowStatement,
                    new Object[] {101, "Radek"}
            );

            User user101 = connectionMaster.findOne(
                    Statement.findOneStatement,
                    new Object[] {101, "Radek"},
                    (id, name) -> new User((Integer)id, (String)name)
            );
            System.out.println("user101: " + user101);

            List<User> resultUsers1 = connectionMaster.findMany(
                    Statement.findManyWithGivenIDsStatement,
                    new Object[] {5, 10},
                    (id, name) -> new User((Integer)id, (String)name)
            );
            System.out.println("resultUsers1: " + resultUsers1);

            List<User> resultUsers2 = connectionMaster.findMany(
                    Statement.findManyWithGivenNamesStatement,
                    new Object[] {"J%"},
                    (id, name) -> new User((Integer)id, (String)name)
            );
            System.out.println("resultUsers2: " + resultUsers2);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
