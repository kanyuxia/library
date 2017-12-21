package com.kanyuxia.mockdata;

/**
 * 模拟器接口
 */
public interface Mocker<T> {

  T mockData(MockConfig mockConfig) throws Exception;
}
