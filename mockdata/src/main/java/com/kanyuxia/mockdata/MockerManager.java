package com.kanyuxia.mockdata;

import com.kanyuxia.mockdata.mocker.BigDecimalMocker;
import com.kanyuxia.mockdata.mocker.BigIntegerMocker;
import com.kanyuxia.mockdata.mocker.BooleanMocker;
import com.kanyuxia.mockdata.mocker.ByteMocker;
import com.kanyuxia.mockdata.mocker.CharacterMocker;
import com.kanyuxia.mockdata.mocker.DateMocker;
import com.kanyuxia.mockdata.mocker.DoubleMocker;
import com.kanyuxia.mockdata.mocker.FloatMocker;
import com.kanyuxia.mockdata.mocker.IntegerMocker;
import com.kanyuxia.mockdata.mocker.LongMocker;
import com.kanyuxia.mockdata.mocker.ShortMocker;
import com.kanyuxia.mockdata.mocker.StringMocker;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 模拟器管理
 */
public class MockerManager {

  private static Map<Class<?>, Mocker> mockCache = new HashMap<>();

  static {
    registerMocker(ByteMocker.INSTANCE, byte.class, Byte.class);
    registerMocker(BooleanMocker.INSTANCE, boolean.class, Boolean.class);
    registerMocker(CharacterMocker.INSTANCE, char.class, Character.class);
    registerMocker(ShortMocker.INSTANCE, short.class, Short.class);
    registerMocker(IntegerMocker.INSTANCE, Integer.class, int.class);
    registerMocker(FloatMocker.INSTANCE, float.class, Float.class);
    registerMocker(LongMocker.INSTANCE, long.class, Long.class);
    registerMocker(DoubleMocker.INSTANCE, double.class, Double.class);
    registerMocker(BigIntegerMocker.INSTANCE, BigInteger.class);
    registerMocker(BigDecimalMocker.INSTANCE, BigDecimal.class);
    registerMocker(StringMocker.INSTANCE, String.class);
    registerMocker(DateMocker.INSTANCE, Date.class);
  }

  private static void registerMocker(Mocker mockData, Class<?>... classes) {
    for (Class<?> clazz : classes) {
      mockCache.put(clazz, mockData);
    }
  }

  public static Mocker getMocker(Class<?> clazz) {
    return mockCache.get(clazz);
  }
}
