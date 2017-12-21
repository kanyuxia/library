package com.kanyuxia.mockdata;

import lombok.Getter;

/**
 * 模拟数据配置类
 */
@Getter
public class MockConfig {

  // valueRange[min,max] / [fixed value]
  private final byte[] byteRange = {0, 100};
  private final short[] shortRange = {0, 100};
  private final int[] intRange = {0, 100};
  private final float[] floatRange = {0.0f, 100.00f};
  private final double[] doubleRange = {0.0, 100.00};// Double & BigDecimal
  private final long[] longRange = {0L, 100L};// Double & BigInteger
  private boolean[] booleanRange = {true, false};

  // value[seed,seed,seed]
  private char[] charSeed =
      {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
          'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F',
          'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
  private String[] stringSeed =
      {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
          "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F",
          "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

  // dateRange[min, max)
  private final String[] dateRange = {"2000-01-01", "2017-9-26"};

  // list、set、map sizeRange[min,max) / [fixed value]
  private final int[] sizeRange = {0, 5};


  // -------------------------------- 设置模拟数据配置的值 ------------------------------------ //
  public MockConfig setByteRange(byte min, byte max) {
    byteRange[0] = min;
    byteRange[1] = max;
    return this;
  }

  public MockConfig setShortRange(short min, short max) {
    shortRange[0] = min;
    shortRange[1] = max;
    return this;
  }

  public MockConfig setIntRange(int min, int max) {
    intRange[0] = min;
    intRange[1] = max;
    return this;
  }

  public MockConfig setFloatRange(float min, float max) {
    floatRange[0] = min;
    floatRange[1] = max;
    return this;
  }

  public MockConfig setDoubleRange(double min, double max) {
    doubleRange[0] = min;
    doubleRange[1] = max;
    return this;
  }

  public MockConfig setLongRange(long min, long max) {
    longRange[0] = min;
    longRange[1] = max;
    return this;
  }

  public MockConfig setBooleanRange(boolean... booleanRange) {
    this.booleanRange = booleanRange;
    return this;
  }

  public MockConfig setCharSeed(char... charSeed) {
    this.charSeed = charSeed;
    return this;
  }

  public MockConfig setStringSeed(String... stringSeed) {
    this.stringSeed = stringSeed;
    return this;
  }

  public MockConfig setDateRange(String min, String max) {
    dateRange[0] = min;
    dateRange[1] = max;
    return this;
  }

  public MockConfig setSizeRange(int min, int max) {
    sizeRange[0] = min;
    sizeRange[1] = max;
    return this;
  }
}
