package com.kanyuxia.mockdata.mocker;

import com.kanyuxia.mockdata.MockConfig;
import com.kanyuxia.mockdata.Mocker;
import org.apache.commons.lang3.RandomUtils;

/**
 * 模拟Short对象
 */
public class ShortMocker implements Mocker<Short> {

  public static final ShortMocker INSTANCE = new ShortMocker();

  public Short mockData(final MockConfig mockConfig) throws Exception {
    return (short) RandomUtils.nextInt(mockConfig.getShortRange()[0], mockConfig.getShortRange()[1]);
  }
}
