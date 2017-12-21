package com.kanyuxia.mockdata.mocker;

import com.kanyuxia.mockdata.MockConfig;
import com.kanyuxia.mockdata.Mocker;
import org.apache.commons.lang3.RandomUtils;

/**
 * Float对象模拟器
 */
public class FloatMocker implements Mocker<Float> {

  public static final FloatMocker INSTANCE = new FloatMocker();

  public Float mockData(final MockConfig mockConfig) throws Exception {
    return RandomUtils.nextFloat(mockConfig.getFloatRange()[0], mockConfig.getFloatRange()[1]);
  }
}
