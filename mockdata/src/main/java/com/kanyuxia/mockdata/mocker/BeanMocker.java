package com.kanyuxia.mockdata.mocker;

import com.kanyuxia.mockdata.JMock;
import com.kanyuxia.mockdata.MockConfig;
import com.kanyuxia.mockdata.Mocker;
import com.kanyuxia.mockdata.MockerManager;
import com.kanyuxia.mockdata.utils.ReflectionUtils;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Bean模拟器
 */
@SuppressWarnings("unchecked")
public class BeanMocker<T> implements Mocker<T> {

  private final Class<?> clazz;

  private final Type[] genericTypes;

  public BeanMocker(final Class<?> clazz, final Type... genericTypes) {
    this.clazz = clazz;
    this.genericTypes = genericTypes;
  }

  public T mockData(final MockConfig mockConfig) throws Exception {
    if (clazz.isArray()) {
      return (T) new ArrayMocker(clazz.getComponentType()).mockData(mockConfig);
    }
    if (Map.class.isAssignableFrom(clazz)) {
      return (T) new MapMocker(genericTypes).mockData(mockConfig);
    }
    if (List.class.isAssignableFrom(clazz)) {
      return (T) new ListMocker(genericTypes[0]).mockData(mockConfig);
    }
    if (Set.class.isAssignableFrom(clazz)) {
      return (T) new SetMocker(genericTypes[0]).mockData(mockConfig);
    }
    T result = (T) clazz.newInstance();
    // 从子对象向上依次模拟
    for (Class<?> currentClass = clazz; currentClass != Object.class; currentClass = currentClass.getSuperclass()) {
      // 模拟有setter、getter方法的字段
      for (Entry<Field, Method> entry : ReflectionUtils.fieldAndSetterMethod(currentClass).entrySet()) {
        Field field = entry.getKey();
        Method method = entry.getValue();
        Class<?> fieldClass = field.getType();
        Object value;
        // 判断是否有泛型参数
        if (ReflectionUtils.hasGeneric(fieldClass)) {
          // 模拟有泛型的数据
          Type[] types = ((ParameterizedType) field.getGenericType()).getActualTypeArguments();
          Mocker mocker = MockerManager.getMocker(fieldClass);
          if (mocker == null) {
            mocker = new BeanMocker(fieldClass, types);
          }
          value = mocker.mockData(mockConfig);
        } else {
          value = JMock.mockData(fieldClass, mockConfig);
        }
        ReflectionUtils.setRefValue(result, method, value);
      }
    }
    return result;
  }
}
