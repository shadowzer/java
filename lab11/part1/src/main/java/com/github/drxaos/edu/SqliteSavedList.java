package com.github.drxaos.edu;

import java.io.*;
import java.sql.*;
import java.util.AbstractList;
import java.util.ArrayList;

import static java.lang.System.out;
/**
 * Created by VolgiNN on 16.11.2016.
 */
public class SqliteSavedList<E extends Serializable> extends AbstractList<E> implements SavedList<E> {

    public SqliteSavedList(File file) {
        openDB();
        if (file.exists()) {
            this.file = new File(file.toString());
            reload();
        } else {
            this.file = new File(file.toString());
        }

    }
    private ArrayList<E> savedList = new ArrayList<E>();
    private File file;
    private Connection conn = null;
    private Statement stmt = null;
    private PreparedStatement pstmt=null;
    private ResultSet rs=null;
    private String JDBC_DRIVER = "com.Sqlite.jdbc.Driver";
    private String DB_URL = "jdbc:sqlite:identifier.sqlite";
    private String sql;
    private String updateSql;
    private byte[] readFile() {
        ByteArrayOutputStream bos = null;
        try {

            FileInputStream fis = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            bos = new ByteArrayOutputStream();
            for (int len; (len = fis.read(buffer)) != -1;) {
                bos.write(buffer, 0, len);
            }
            fis.close();
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (IOException e2) {
            System.err.println(e2.getMessage());
        }

        return bos != null ? bos.toByteArray() : null;
    }
    private void uploadFile(){
        try {
            updateSql= "INSERT INTO Files (path, file) " +
                    "VALUES (?,?);";
            pstmt=conn.prepareStatement(updateSql);
            pstmt.setString(1,file.getName());
            pstmt.setBytes(2, readFile());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            out.println(e.getMessage());
        }
    }
    private void refreshFile(){
        try {
            updateSql= "DELETE FROM Files WHERE path = ? ;";
            pstmt=conn.prepareStatement(updateSql);
            pstmt.setString(1,file.getName());
            pstmt.executeUpdate();
            uploadFile();
        } catch (SQLException e) {
            out.println(e.getMessage());
        }
    }
    private void openDB(){
        try {
            Class.forName("org.sqlite.JDBC");

            out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL);

            out.println("Creating statement...");
            String sql;
            stmt = conn.createStatement();
            createTableFiles();
        }
        catch (Exception e) {
            out.println(e.getMessage());
        }
    }
    private void createTableFiles(){
        try {
            sql="DROP TABLE IF EXISTS Files";
            stmt.execute(sql);
            sql = "CREATE TABLE IF NOT EXISTS Files (path varchar PRIMARY KEY , file blob);";
            stmt.execute(sql);
            out.println("Creation in DB table 'Files'");
        } catch (SQLException e) {
            out.println(e.getMessage());
        }
    }
    private void downloadFile(){
        try {

            sql = "SELECT path, file FROM Files WHERE path=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,file.getName());
            rs = pstmt.executeQuery();
            while(rs.next()){
                out.println(rs.getString("path"));
                byte[] bytes = rs.getBytes("file");
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(bytes);
                fos.close();
            }
        }
        catch (Exception e) {
            out.println(e.getMessage());
        }
    }
    private void closeDB() {
        try {
            if (rs!=null) {
                rs.close();
            }
        } catch (SQLException e) {
            out.println(e.getMessage());
        }
        try {
            if (stmt!=null) {
                stmt.close();
            }
        } catch (SQLException e) {
            out.println(e.getMessage());
        }
        try {
            if (conn!=null) {
                conn.close();
            }
        } catch (SQLException e) {
            out.println(e.getMessage());
        }
    }
    private void save() {
        try {
            FileOutputStream fileInstos = new FileOutputStream(file);
            ObjectOutputStream objos = new ObjectOutputStream(fileInstos);
            objos.writeObject(savedList);
            fileInstos.close();
            objos.close();
            refreshFile();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
	
	
    public void reload() throws FileOperationException {
        try {
            if (!(file.exists()))
                savedList.clear();
            FileInputStream fileInstis = new FileInputStream(file);
            ObjectInputStream objis = new ObjectInputStream(fileInstis);
            savedList = (ArrayList<E>) objis.readObject();
            objis.close();
            save();
            refreshFile();
        } catch (Exception e) {
            out.println(e.getMessage());
        }
    }
	
    public E get(int index) {
        return savedList.get(index);
    }
    public E set(int index, E element) {
        savedList.set(index,element);
        save();
        return element;
    }
    public int size() {
        return savedList.size();
    }
    public void add(int index, E element) {
        savedList.add(index,element);
        save();
    }
    public E remove(int index) {
        E element = savedList.get(index);
        savedList.remove(index);
        save();
        return element;
    }
    public void remove(E element) {
        savedList.remove(element);
        save();
        refreshFile();
    }
    public boolean add(E element) {
        if (savedList.add(element)) {
            save();
            return true;
        }
        save();
        return false;
    }
    public boolean isEmpty(){
        return savedList.isEmpty();
    }
}