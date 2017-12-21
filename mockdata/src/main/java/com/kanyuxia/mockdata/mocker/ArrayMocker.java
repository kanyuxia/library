package com.kanyuxia.mockdata.mocker;

import com.kanyuxia.mockdata.JMock;
import com.kanyuxia.mockdata.MockConfig;
import com.kanyuxia.mockdata.Mocker;
import java.lang.reflect.Array;
import org.apache.commons.lang3.RandomUtils;

/**
 * 数组模拟器
 */
public class ArrayMocker implements Mocker<Object> {

  private final Class<?> componentClass;

  ArrayMocker(Class<?> clazz) {
    this.componentClass = clazz;
  }

  public Object mockData(final MockConfig mockConfig) throws Exception {
    int size = RandomUtils.nextInt(mockConfig.getSizeRange()[0], mockConfig.getSizeRange()[1]);
    Object result = Array.newInstance(componentClass, size);
    for (int index = 0; index < size; index++) {
      Object value = JMock.mockData(componentClass);
      Array.set(result, index, value);
    }
    return result;
  }

}
