package com.kanyuxia.mockdata.mocker;

import com.kanyuxia.mockdata.MockConfig;
import com.kanyuxia.mockdata.Mocker;
import org.apache.commons.lang3.RandomUtils;

/**
 * Boolean对象模拟器
 */
public class BooleanMocker implements Mocker<Boolean> {

  public static final BooleanMocker INSTANCE = new BooleanMocker();

  public Boolean mockData(final MockConfig mockConfig) throws Exception {
    boolean[] booleanRange = mockConfig.getBooleanRange();
    return booleanRange[RandomUtils.nextInt(0, booleanRange.length - 1)];
  }
}
