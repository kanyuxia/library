package com.kanyuxia.mockdata.mocker;

import com.kanyuxia.mockdata.JMock;
import com.kanyuxia.mockdata.MockConfig;
import com.kanyuxia.mockdata.Mocker;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.RandomUtils;

/**
 * List对象模拟器
 */
@SuppressWarnings("unchecked")
public class ListMocker implements Mocker<List> {

  private final Type genericType;

  public ListMocker(Type genericType) {
    this.genericType = genericType;
  }

  public List mockData(final MockConfig mockConfig) throws Exception {
    int size = RandomUtils.nextInt(mockConfig.getSizeRange()[0], mockConfig.getSizeRange()[1]);
    List result = new ArrayList(size);
    while (size-- > 0) {
      // 判断是否还有泛型
      if (genericType instanceof ParameterizedType) {
        ParameterizedType type = (ParameterizedType) genericType;
        result.add(new BeanMocker((Class<?>) type.getRawType(), type.getActualTypeArguments()[0]).mockData(mockConfig));
        continue;
      }
      result.add(JMock.mockData((Class<?>) genericType));
    }
    return result;
  }
}
