package com.kanyuxia.crypto;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

public class SHA1 {

  private static final String SHA1 = "SHA-1";

  /**
   * 计算SHA-1摘要值
   *
   * @param data 被摘要数据
   * @param salt 盐
   * @param hashIterations Hash次数
   * @return SHA-1摘要
   */
  public static byte[] hash(byte[] data, byte[] salt, int hashIterations) {
    return new Digest(SHA1).hash(data, salt, hashIterations);
  }

  /**
   * 计算SHA-1摘要值
   *
   * @param data 被摘要数据
   * @param salt 盐
   * @return SHA-1摘要
   */
  public static byte[] hash(byte[] data, byte[] salt) {
    return hash(data, salt, Digest.DEFAULT_HASH_ITERATIONS);
  }

  /**
   * 计算SHA-1摘要值转为Base64编码
   *
   * @param data 被摘要数据
   * @param salt 盐
   * @param hashIterations Hash次数
   * @return Base64编码的SHA-1摘要
   */
  public static String hashToBase64(String data, String salt, int hashIterations) {
    byte[] byteData = data.getBytes(Digest.DEFAULT_CHARSET);
    byte[] byteSalt = salt.getBytes(Digest.DEFAULT_CHARSET);
    byte[] hashData = hash(byteData, byteSalt, hashIterations);
    return Base64.encodeBase64String(hashData);
  }

  /**
   * 计算SHA-1摘要值转为Base64编码
   *
   * @param data 被摘要数据
   * @param salt 盐
   * @return Base64编码的SHA-1摘要
   */
  public static String hashToBase64(String data, String salt) {
    return hashToBase64(data, salt, Digest.DEFAULT_HASH_ITERATIONS);
  }

  /**
   * 计算SHA-1摘要值转为Base64编码
   *
   * @param data 被摘要数据
   * @param salt 盐
   * @param hashIterations Hash次数
   * @return Base64编码的SHA-1摘要
   */
  public static String hashToHex(String data, String salt, int hashIterations) {
    byte[] byteData = data.getBytes(Digest.DEFAULT_CHARSET);
    byte[] byteSalt = salt.getBytes(Digest.DEFAULT_CHARSET);
    byte[] hashData = hash(byteData, byteSalt, hashIterations);
    return Hex.encodeHexString(hashData);
  }

  /**
   * 计算SHA-1摘要值转为Hex编码
   *
   * @param data 被摘要数据
   * @param salt 盐
   * @return Hex编码的SHA-1摘要
   */
  public static String hashToHex(String data, String salt) {
    return hashToHex(data, salt, Digest.DEFAULT_HASH_ITERATIONS);
  }

}
