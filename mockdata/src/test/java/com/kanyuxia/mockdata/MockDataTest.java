package com.kanyuxia.mockdata;

import com.kanyuxia.mockdata.bean.People;
import org.junit.Test;

public class MockDataTest {

  @Test
  public void test() throws Exception {
    People people1 = JMock.mockData(People.class);
    System.out.println(people1);

    People people2 = JMock.mockData(People.class, new MockConfig().setStringSeed("hello", "world"));
    System.out.println(people2);
  }

}
