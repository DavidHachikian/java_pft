package database;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
  private static final String URL = "jdbc:mysql://localhost:3307/mydbtest?autoReconnect=true&useSSL=false";
  private static final String USERNAME = "root5678";
  private static final String PASSWORD = "root5678";

  public static void main(String[] args) {
    Connection connection;
    try {
      Driver driver;
      driver = new com.mysql.cj.jdbc.Driver();
      DriverManager.registerDriver(driver);

      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      if (!connection.isClosed()) {
        System.out.println("Соединение с БД установлено");
      }
      connection.close();
    } catch (SQLException e) {
      System.err.println("Соединение с БД не установлено");
    }
  }
}


