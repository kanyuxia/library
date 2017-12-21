package com.kanyuxia.mockdata.mocker;

import com.kanyuxia.mockdata.MockConfig;
import com.kanyuxia.mockdata.Mocker;
import org.apache.commons.lang3.RandomUtils;

/**
 * 模拟String对象
 */
public class StringMocker implements Mocker<String> {

  public static final StringMocker INSTANCE = new StringMocker();

  public String mockData(final MockConfig mockConfig) throws Exception {
    String[] stringSeed = mockConfig.getStringSeed();
    return stringSeed[RandomUtils.nextInt(0, stringSeed.length)];
  }
}
