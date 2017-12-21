package com.kanyuxia.mockdata.mocker;

import com.kanyuxia.mockdata.MockConfig;
import com.kanyuxia.mockdata.Mocker;
import org.apache.commons.lang3.RandomUtils;

/**
 * Double对象模拟器
 */
public class DoubleMocker implements Mocker<Double> {

  public static final DoubleMocker INSTANCE = new DoubleMocker();

  public Double mockData(final MockConfig mockConfig) throws Exception {
    return RandomUtils.nextDouble(mockConfig.getDoubleRange()[0], mockConfig.getDoubleRange()[1]);
  }
}
