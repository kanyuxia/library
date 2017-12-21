package com.kanyuxia.mockdata.mocker;

import com.kanyuxia.mockdata.MockConfig;
import com.kanyuxia.mockdata.Mocker;
import java.math.BigInteger;
import org.apache.commons.lang3.RandomUtils;

/**
 * BigInteger对象模拟器
 */
public class BigIntegerMocker implements Mocker<BigInteger> {

  public static final BigIntegerMocker INSTANCE = new BigIntegerMocker();

  public BigInteger mockData(final MockConfig mockConfig) throws Exception {
    return BigInteger.valueOf(RandomUtils.nextLong(mockConfig.getLongRange()[0], mockConfig.getLongRange()[1]));
  }
}
