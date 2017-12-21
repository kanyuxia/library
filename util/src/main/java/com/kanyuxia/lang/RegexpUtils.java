package com.kanyuxia.lang;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式工具类
 * <pre>
 * 包含常用的正则的校验
 * </pre>
 *
 * @author TaoYu
 */
public final class RegexpUtils {

  /**
   * 验证用户名
   */
  private static final String REGEX_USERNAME = "^[a-zA-Z]\\w{5,17}$";

  /**
   * 验证密码
   */
  private static final String REGEX_PASSWORD = "^[a-zA-Z0-9]{6,16}$";

  /**
   * 验证手机号
   */
  private static final String REGEX_MOBILE = "^((13[0-9])|(14[0-9])|(15[0-9])|(18[0-9]))\\d{8}$";

  /**
   * 验证邮箱
   */
  private static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

  /**
   * 验证汉字
   */
  private static final String REGEX_CHINESE = "^[\u4e00-\u9fa5]{0,}$";

  /**
   * 验证数字
   */
  private static final String REGEX_NUM = "[0-9]+";

  /**
   * 验证URL
   */
  private static final String REGEX_URL = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";

  /**
   * 验证IP地址
   */
  private static final String REGEX_IP_ADDR = "((?:(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d))))";

  /**
   * 验证生日
   */
  private static final String REGEX_BIRTHDAY = "(19|20)\\d{2}-(1[0-2]|0?[1-9])-(0?[1-9]|[1-2][0-9]|3[0-1])";

  private RegexpUtils() {
  }

  public static boolean matched(String source, String regex) {
    Pattern p = Pattern.compile(regex);
    Matcher m = p.matcher(source);
    return m.matches();
  }

  /**
   * 校验用户名
   *
   * @param username 带检验的用户名
   * @return 校验通过返回true，否则返回false
   */
  public static boolean isUsername(String username) {
    return Pattern.matches(REGEX_USERNAME, username);
  }

  /**
   * 校验密码
   *
   * @param password 密码
   * @return 校验通过返回true，否则返回false
   */
  public static boolean isPassword(String password) {
    return Pattern.matches(REGEX_PASSWORD, password);
  }

  /**
   * 校验手机号
   *
   * @param mobile 手机号
   * @return 校验通过返回true，否则返回false
   */
  public static boolean isMobile(String mobile) {
    return Pattern.matches(REGEX_MOBILE, mobile);
  }

  /**
   * 校验邮箱
   *
   * @param email 邮箱地址
   * @return 校验通过返回true，否则返回false
   */
  public static boolean isEmail(String email) {
    return Pattern.matches(REGEX_EMAIL, email);
  }

  /**
   * 校验汉字
   *
   * @param chinese 中文字符
   * @return 校验通过返回true，否则返回false
   */
  public static boolean isChinese(String chinese) {
    return Pattern.matches(REGEX_CHINESE, chinese);
  }

  /**
   * 校验数字
   *
   * @param number 数字
   * @return 校验通过返回true，否则返回false
   */
  public static boolean isNumber(String number) {
    return Pattern.matches(REGEX_NUM, number);
  }

  /**
   * 校验身份证
   *
   * @param idCard 身份证号码
   * @return 校验通过返回true，否则返回false
   */
  public static boolean isIDCard(String idCard) {
    return IdCardUtils.isValidCard(idCard);
  }

  /**
   * 校验URL
   *
   * @param url URL地址
   * @return 校验通过返回true，否则返回false
   */
  public static boolean isUrl(String url) {
    return Pattern.matches(REGEX_URL, url);
  }

  /**
   * 校验IP地址
   *
   * @param ipAddr IP地址
   * @return 校验通过返回true，否则返回false
   */
  public static boolean isIPAddr(String ipAddr) {
    return Pattern.matches(REGEX_IP_ADDR, ipAddr);
  }

  /**
   * 校验生日
   *
   * @param birthday 出生日期
   * @return 校验通过返回true，否则返回false
   */
  public static boolean isBirthDay(String birthday) {
    return Pattern.matches(REGEX_BIRTHDAY, birthday);
  }

}
