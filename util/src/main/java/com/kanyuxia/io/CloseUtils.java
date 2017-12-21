package com.kanyuxia.io;

import java.io.Closeable;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

/**
 * 关闭资源工具类
 *
 * @author KanYuXia
 */
@Slf4j
public class CloseUtils {

  /**
   * 关闭资源
   *
   * @param closes 可关闭的资源
   */
  public static void close(Closeable... closes) {
    try {
      for (Closeable closeable : closes) {
        if (closeable != null) {
          closeable.close();
        }
      }
    } catch (IOException e) {
      log.error("关闭资源错误", e);
    }
  }

  /**
   * 关闭资源
   *
   * @param closes 可自定关闭的资源
   */
  public static void close(AutoCloseable... closes) {
    try {
      for (AutoCloseable closeable : closes) {
        if (closeable != null) {
          closeable.close();
        }
      }
    } catch (Exception e) {
      log.error("关闭资源错误", e);
    }
  }
}

