import com.sun.deploy.util.ArrayUtil;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;

public class OracleSavedList implements SavedList {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private static class Repository {
        public static String DBHostPort = "localhost:1521/XE";
        public static String username;
        public static String password;
    }


    OracleSavedList() throws SQLException {
        this("localhost:1521/XE", "user1", "password1");
    }

    OracleSavedList(String DBHostPort, String username, String password) throws SQLException {
        Repository.DBHostPort = DBHostPort;
        Repository.username = username;
        Repository.password = password;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        }
        catch(ClassNotFoundException ex) {
            System.out.println("Error: unable to load driver class!");
            System.exit(1);
        }
        Locale.setDefault(Locale.ENGLISH);
        /*connection = DriverManager.getConnection("jdbc:oracle:thin:@//" + Repository.DBHostPort, Repository.username, Repository.password);
        statement = connection.createStatement();*/
    }

    public int size() throws SQLException {
        connection = DriverManager.getConnection("jdbc:oracle:thin:@//" + Repository.DBHostPort, Repository.username, Repository.password);
        statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        statement.execute("SELECT * FROM SHAD.SAVEDLIST");
        resultSet = statement.getResultSet();
        resultSet.last();
        int ans = resultSet.getRow();
        resultSet.close();
        if (connection != null)
            connection.close();
        return ans;
    }

    public ArrayList<Data> getData() throws SQLException {
        connection = DriverManager.getConnection("jdbc:oracle:thin:@//" + Repository.DBHostPort, Repository.username, Repository.password);
        statement = connection.createStatement();
        statement.execute("SELECT * FROM SHAD.SAVEDLIST");
        resultSet = statement.getResultSet();
        ResultSetMetaData metaData = resultSet.getMetaData();

        ArrayList<Data> ans = new ArrayList<Data>();

        while (resultSet.next()) {
            Data cur = new Data();
            cur.ID = resultSet.getInt("ID");
            cur.NAME = resultSet.getString("NAME");
            cur.PERCENT = resultSet.getFloat("PERCENT");
            ans.add(cur);
        }
        resultSet.close();
        if (connection != null)
            connection.close();
        return ans;
    }

    public boolean add(Data e) throws SQLException {
        connection = DriverManager.getConnection("jdbc:oracle:thin:@//" + Repository.DBHostPort, Repository.username, Repository.password);
        statement = connection.createStatement();
        if (statement.executeUpdate("INSERT INTO SHAD.SAVEDLIST(ID, NAME, PERCENT) VALUES (" + e.ID + ", '" + e.NAME + "', " + e.PERCENT + ")") != 0) {
            if (connection != null)
                connection.close();
            return true;
        } else {
            if (connection != null)
                connection.close();
            return false;
        }
    }

    public boolean remove(int ID) throws SQLException {
        connection = DriverManager.getConnection("jdbc:oracle:thin:@//" + Repository.DBHostPort, Repository.username, Repository.password);
        statement = connection.createStatement();
        if (statement.executeUpdate("DELETE FROM SHAD.SAVEDLIST WHERE ID = " + ID) != 0) {
            if (connection != null)
                connection.close();
            return true;
        } else {
            if (connection != null)
                connection.close();
            return false;
        }
    }

    public boolean set(Data e) throws SQLException {
        connection = DriverManager.getConnection("jdbc:oracle:thin:@//" + Repository.DBHostPort, Repository.username, Repository.password);
        statement = connection.createStatement();
        if (statement.executeUpdate("UPDATE SHAD.SAVEDLIST SET NAME = '"+ e.NAME + "', PERCENT = " + e.PERCENT + "WHERE ID = " + e.ID) != 0) {
            if (connection != null)
                connection.close();
            return true;
        } else {
            if (connection != null)
                connection.close();
            return false;
        }
    }
}
