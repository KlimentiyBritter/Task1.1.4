package jm.task.core.jdbc.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/mytestbase";
    private static final String USER = "root";
    private static final String PASSWORD = "push2005%&$s18#";
    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
//            Statement statement = connection.createStatement();
//            statement.execute("insert into user(name, lastName, age) values ('Glebbas', 'Opiucha', '50');");
            System.out.println("connection is completed");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
