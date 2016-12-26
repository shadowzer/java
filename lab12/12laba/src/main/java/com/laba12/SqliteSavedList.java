package com.laba12;

import java.io.File;
import java.io.Serializable;
import java.sql.*;
import java.util.AbstractList;
import java.util.ArrayList;

public class SqliteSavedList <E extends Serializable> extends AbstractList<E>implements SavedList {
    private ArrayList list;
    String insert;
    private Connection conn;
    private String user;
    private String password;
    private String url;
    private String driver;
    File file;


    public SqliteSavedList(File f)throws SQLException{
        list=new ArrayList();
        user="";
        password="";
        insert="INSERT INTO line (id,str) VALUES ";
        url="jdbc:sqlite:C:/Users/cod_s/Downloads/lab10/"; // TODO change url to needed
        driver="org.sqlite.JDBC";
        if (f!=null) {
            this.file = f;
            try {
                openConnection(file.getName());
                createTable();
            }catch (SQLException e){}
            reload();
        }
        else{
            file=new File ("data.sqlite");
            openConnection(file.getName());
            createTable();
        }
    }
    public void createTable()throws SQLException{
        String line="CREATE TABLE line(id INT(8),str VARCHAR(255));";
        Statement st=conn.createStatement();
        st.executeUpdate(line);
    }
   public void insertRow(String line)throws SQLException{
       Statement st=conn.createStatement();
       st.executeUpdate(line);
   }
   public void openConnection(String nameFile){
       String rightURL=url+nameFile;
       try{
           Class.forName(driver);
       }catch(Exception e){}
       try{
           conn= DriverManager.getConnection(rightURL,user,password);
       }catch(Exception e){}
   }

    public ResultSet getRows(String SQLQuery)throws SQLException{
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery(SQLQuery);
        return rs;
    }

    public void closeConnection()throws SQLException{
      conn.close();
    }

    public void uploadTable()throws SQLException{
        Statement st=conn.createStatement();
        st.executeUpdate("Delete from line");
        String answer=insert;
        for (int i=0;i<list.size();i++){
            int id=i+1;
            String str=(String)list.get(i);
            answer=answer+"("+id+", '"+str+"'), ";
        }
        answer=answer.substring(0,answer.length()-2);
        insertRow(answer);
    }
    public void reload(){

        this.list.clear();
        ResultSet result;
        try {
            result = getRows("SELECT * FROM line");
            while(result.next()){
                String ans=result.getString("str");
                list.add(ans); //
            }
        }
        catch (Exception e) {}
    }

    @Override
    public E get(int index) {
        Object obj=null;
        try {
            obj=list.get(index);
        }
        catch (Exception e){}
        return (E)obj;
    }

    @Override
    public E set(int index, E element) {
        this.list.set(index,element);
        try {
            uploadTable();
        }catch (SQLException e){}
        return this.get(index);
    }

    @Override
    public int size() {
        return this.list.size();
    }

    @Override
    public void add(int index, E element) {
        this.list.add(index, element);
            try {
                uploadTable();
            } catch (SQLException e) {
            }
    }

    @Override
    public E remove(int index) {
        Object obj=this.list.get(index);
        this.list.remove(index);
        try {
            uploadTable();
        }catch (SQLException s){}

        return (E)obj;
    }
    protected void finalize() {
        try {
            closeConnection();
        }catch (SQLException e ){}
    }
}
