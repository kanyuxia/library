package com.kanyuxia.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import lombok.extern.slf4j.Slf4j;

/**
 * JDBC工具类
 *
 * @author KanYuXia
 */
@Slf4j
public class DriverUtils {

  private static String driver;

  private static String url;

  private static String username;

  private static String password;

  static {
    loadProperties();
    loadDriverClass();
  }

  /**
   * 获取Connection
   * @return Connection
   */
  public static Connection getConnection() {
    Connection connection = null;
    try {
      connection = DriverManager.getConnection(url, username, password);
    } catch (SQLException e) {
      log.error("获得连接错误", e);
    }
    return connection;
  }

  private static void loadProperties() {
    try {
      InputStream inputStream = DriverUtils.class.getClassLoader().getResourceAsStream("database.properties");
      Properties properties = new Properties();
      properties.load(inputStream);
      driver = (String) properties.get("driver");
      url = (String) properties.get("url");
      username = (String) properties.get("username");
      password = (String) properties.get("password");
    } catch (IOException e) {
      log.error("读取Properties文件错误");
    }
  }

  private static void loadDriverClass() {
    try {
      Class.forName(driver);
    } catch (ClassNotFoundException e) {
      log.error("加载数据库驱动错误");
    }
  }
}
