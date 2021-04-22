package database;

import java.sql.*;
import java.sql.ResultSet;

public class Main {
  private static final String URL = "jdbc:mysql://localhost:3307/mydbtest?autoReconnect=true&useSSL=false";
  private static final String USERNAME = "root5678";
  private static final String PASSWORD = "root5678";

  private static final String SALARY_SUM = "select DEPARTMENT, SUM(SALARY) as sum from employees group by DEPARTMENT";


  public static void main(String[] args) {
    registerDriver();

    Connection connection = null;
    Statement preparedStatement = null;
    try {
      Driver driver;
      driver = new com.mysql.cj.jdbc.Driver();
      DriverManager.registerDriver(driver);
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement(SALARY_SUM);
      ResultSet res = preparedStatement.executeQuery(SALARY_SUM);

      while (res.next())
      System.out.println(res.getString("DEPARTMENT") + ":" + " " + res.getFloat("sum"));
      preparedStatement.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  private static void registerDriver() {
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



