import java.sql.*;
import java.util.ArrayList;
import java.util.Locale;

public class Main {

    public static void main(String[] args) throws  Exception{
        OracleSavedList oracle = new OracleSavedList();

        int size = oracle.size();
        System.out.println("size of DB: " + size);
        System.out.println("content of DB:");
        for (Data e: oracle.getData()) {
            System.out.print(e.ID + "\t");
            System.out.print(e.NAME + "\t");
            System.out.print(e.PERCENT + "\n");
        }

        Data data = new Data();
        // fill your data
        //data.ID = 4; // int ID
        //data.NAME = "YOOUU"; // String NAME
        //data.PERCENT = 0.77f; // Float PERCENT

        // choose method
        //oracle.add(data);
        //oracle.set(data);
        //oracle.remove(data);

        size = oracle.size();
        System.out.println("size of DB: " + size);
        System.out.println("content of DB:");
        for (Data e: oracle.getData()) {
            System.out.print(e.ID + "\t");
            System.out.print(e.NAME + "\t");
            System.out.print(e.PERCENT + "\n");
        }
        //TestDB();
    }

    public static void TestDB() throws SQLException {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        }
        catch(ClassNotFoundException ex) {
            System.out.println("Error: unable to load driver class!");
            System.exit(1);
        }
        Locale.setDefault(Locale.ENGLISH);
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/XE", "user1", "password1");
        try {
            Statement connectionStatement = connection.createStatement();
            Statement statement = connection.createStatement();
            try {
                connectionStatement.execute("ALTER SESSION SET CURRENT_SCHEMA=SYSTEM");
                statement.execute("select * from user_tables");
            } catch (Exception e) {
                e.printStackTrace();
            }
            ResultSet resultSet = statement.getResultSet();
            int i = 0;
            while (resultSet.next() && i < 10) {
                System.out.println(resultSet.getString("TABLE_NAME"));
                ++i;
            }
        } finally {
            if (connection != null)
                connection.close();
        }
    }
}
