package com.kanyuxia.mockdata.mocker;

import com.kanyuxia.mockdata.MockConfig;
import com.kanyuxia.mockdata.Mocker;
import org.apache.commons.lang3.RandomUtils;

/**
 * Character对象模拟器
 */
public class CharacterMocker implements Mocker<Character> {

  public static final CharacterMocker INSTANCE = new CharacterMocker();

  public Character mockData(final MockConfig mockConfig) throws Exception {
    char[] charSeed = mockConfig.getCharSeed();
    return charSeed[RandomUtils.nextInt(0, charSeed.length)];
  }
}
