package com.kanyuxia.web;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

/**
 * Json转换工具类
 *
 * @author TaoYu
 */
@Slf4j
public final class JsonUtils {

  private static final ObjectMapper OBJECTMAPPER = new ObjectMapper();

  private JsonUtils() {
  }

  /**
   * 将对象序列化
   */
  public static String toJsonString(Object obj) throws IOException {
    return OBJECTMAPPER.writeValueAsString(obj);
  }

  /**
   * 反序列化对象字符串
   */
  public static <T> T readFromJson(String json, Class<T> clazz) throws IOException {
    return OBJECTMAPPER.readValue(json, clazz);
  }

  /**
   * 反序列化字符串成为对象
   */
  public static <T> T readFromJson(String json, TypeReference<T> valueTypeRef) throws IOException {
    return OBJECTMAPPER.readValue(json, valueTypeRef);
  }
}
