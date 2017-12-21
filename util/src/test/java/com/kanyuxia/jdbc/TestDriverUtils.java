package com.kanyuxia.jdbc;

import java.sql.Connection;
import org.junit.Test;

public class TestDriverUtils {

  @Test
  public void getConnection() {
    Connection connection = DriverUtils.getConnection();
    System.out.println(connection);
  }
}
