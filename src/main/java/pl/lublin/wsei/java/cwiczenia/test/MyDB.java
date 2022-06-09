package pl.lublin.wsei.java.cwiczenia.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.util.Properties;


public class MyDB {
    private Connection conn = null;
    private String user = "";
    private String password = "";
    private String dbName = "";
    private Integer port = 0;
    private String host = "";
    private Statement statement = null;

    public MyDB(String HOST, int PORT, String MYdb) {
        host= HOST;
        port=PORT;
        dbName=MYdb;

    }

    public void setUser(String User) {
        user = User;
    }

    public void setPassword(String Password) {
        password = Password;
    }

    private void connect() {

        Properties connectionProps = new Properties();
        connectionProps.put("user", user);
        connectionProps.put("password", password);
        connectionProps.put("serverTimezone", "Europe/Warsaw");

        String jdbcString = "jdbc:mysql://" + host + ":" + port + "/" + dbName;


        try {
            conn = DriverManager.getConnection(jdbcString, connectionProps);
            statement = conn.createStatement();
        } catch (SQLException e) {
            System.out.println("Błąd podłączenia do bazy:" + jdbcString);
            System.out.println("Komunikat błędu:" + e.getMessage());
            conn = null;
        }
        System.out.println("Connected to database" + dbName);
    }

    public Connection getConnection() {
        if (conn == null)
            connect();
        return conn;
    }

    public void closeConnection() {
        if (conn != null)
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("Błąd przy zamykaniu połączenia bazodanowego:" + e.getMessage());
            }
        conn = null;
    }
    public ResultSet selectData(String selectStatement){


        if((conn!=null)&&(statement!=null))
            try{
                return statement.executeQuery(selectStatement);
            }catch(SQLException e){
                System.out.println("Błąd realizacji zapytania:"+selectStatement+","+e.getMessage());
            }
        return null;
    }
}