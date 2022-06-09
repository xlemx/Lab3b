package pl.lublin.wsei.java.cwiczenia.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.util.Properties;


public class MyDB {
    private Connection conn = null;
    private String User;
    private String Password;
    String user = "";
    String password = "";
    String dbName = "";
    Integer port = 0;
    String host = "";

    public MyDB(String localhost, int port, String mydb) {
    }

    public void setUser(String user) {
        User = user;
    }

    public void setPassword(String password) {
        Password = password;
    }

    private void connect() {

        Properties connectionProps = new Properties();
        connectionProps.put("user", user);
        connectionProps.put("password", password);
        connectionProps.put("serverTimezone", "Europe/Warsaw");

        String jdbcString = "jdbc:mysql://" + host + ":" + port + "/" + dbName;
        try {
            conn = DriverManager.getConnection(
                    jdbcString, connectionProps);
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
}