package com.kanyuxia.mockdata.mocker;

import com.kanyuxia.mockdata.JMock;
import com.kanyuxia.mockdata.MockConfig;
import com.kanyuxia.mockdata.Mocker;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang3.RandomUtils;

public class SetMocker implements Mocker<Set> {

  private final Type genericType;

  public SetMocker(Type genericType) {
    this.genericType = genericType;
  }

  @SuppressWarnings("unchecked")
  public Set mockData(final MockConfig mockConfig) throws Exception {
    int size = RandomUtils.nextInt(mockConfig.getSizeRange()[0], mockConfig.getSizeRange()[1]);
    Set result = new HashSet(size);
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
