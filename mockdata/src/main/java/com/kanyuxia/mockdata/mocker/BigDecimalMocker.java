package com.kanyuxia.mockdata.mocker;

import com.kanyuxia.mockdata.MockConfig;
import com.kanyuxia.mockdata.Mocker;
import java.math.BigDecimal;
import org.apache.commons.lang3.RandomUtils;

/**
 * BigDecimal对象模拟器
 */
public class BigDecimalMocker implements Mocker<BigDecimal> {

  public static final BigDecimalMocker INSTANCE = new BigDecimalMocker();

  public BigDecimal mockData(final MockConfig mockConfig) throws Exception {
    return BigDecimal.valueOf(RandomUtils.nextDouble(mockConfig.getDoubleRange()[0], mockConfig.getDoubleRange()[1]));
  }
}
