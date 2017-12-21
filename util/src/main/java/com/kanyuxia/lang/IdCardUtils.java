package com.kanyuxia.lang;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;


/**
 * 身份证相关工具类<br> see https://www.oschina.net/code/snippet_1611_2881
 */
public class IdCardUtils {

  /**
   * 中国公民身份证号码最小长度。
   */
  private static final int CHINA_ID_MIN_LENGTH = 15;
  /**
   * 中国公民身份证号码最大长度。
   */
  private static final int CHINA_ID_MAX_LENGTH = 18;
  /**
   * 每位加权因子
   */
  private static final int power[] = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
  /**
   * 身份证生日Pattern->yyMMdd
   */
  private static final String DATE_YY_MM_DD = "yyMMdd";
  /**
   * 身份证生日Pattern->yyyyMMdd
   */
  private static final String DATE_YYYY_MM_DD = "yyyyMMdd";
  /**
   * 省市代码表
   */
  private static Map<String, String> cityCodes = new HashMap<String, String>();
  /**
   * 台湾身份首字母对应数字
   */
  private static Map<String, Integer> twFirstCode = new HashMap<String, Integer>();
  /**
   * 香港身份首字母对应数字
   */
  private static Map<String, Integer> hkFirstCode = new HashMap<String, Integer>();

  static {
    cityCodes.put("11", "北京");
    cityCodes.put("12", "天津");
    cityCodes.put("13", "河北");
    cityCodes.put("14", "山西");
    cityCodes.put("15", "内蒙古");
    cityCodes.put("21", "辽宁");
    cityCodes.put("22", "吉林");
    cityCodes.put("23", "黑龙江");
    cityCodes.put("31", "上海");
    cityCodes.put("32", "江苏");
    cityCodes.put("33", "浙江");
    cityCodes.put("34", "安徽");
    cityCodes.put("35", "福建");
    cityCodes.put("36", "江西");
    cityCodes.put("37", "山东");
    cityCodes.put("41", "河南");
    cityCodes.put("42", "湖北");
    cityCodes.put("43", "湖南");
    cityCodes.put("44", "广东");
    cityCodes.put("45", "广西");
    cityCodes.put("46", "海南");
    cityCodes.put("50", "重庆");
    cityCodes.put("51", "四川");
    cityCodes.put("52", "贵州");
    cityCodes.put("53", "云南");
    cityCodes.put("54", "西藏");
    cityCodes.put("61", "陕西");
    cityCodes.put("62", "甘肃");
    cityCodes.put("63", "青海");
    cityCodes.put("64", "宁夏");
    cityCodes.put("65", "新疆");
    cityCodes.put("71", "台湾");
    cityCodes.put("81", "香港");
    cityCodes.put("82", "澳门");
    cityCodes.put("91", "国外");

    twFirstCode.put("A", 10);
    twFirstCode.put("B", 11);
    twFirstCode.put("C", 12);
    twFirstCode.put("D", 13);
    twFirstCode.put("E", 14);
    twFirstCode.put("F", 15);
    twFirstCode.put("G", 16);
    twFirstCode.put("H", 17);
    twFirstCode.put("J", 18);
    twFirstCode.put("K", 19);
    twFirstCode.put("L", 20);
    twFirstCode.put("M", 21);
    twFirstCode.put("N", 22);
    twFirstCode.put("P", 23);
    twFirstCode.put("Q", 24);
    twFirstCode.put("R", 25);
    twFirstCode.put("S", 26);
    twFirstCode.put("T", 27);
    twFirstCode.put("U", 28);
    twFirstCode.put("V", 29);
    twFirstCode.put("X", 30);
    twFirstCode.put("Y", 31);
    twFirstCode.put("W", 32);
    twFirstCode.put("Z", 33);
    twFirstCode.put("I", 34);
    twFirstCode.put("O", 35);

    hkFirstCode.put("A", 1);
    hkFirstCode.put("B", 2);
    hkFirstCode.put("C", 3);
    hkFirstCode.put("R", 18);
    hkFirstCode.put("U", 21);
    hkFirstCode.put("Z", 26);
    hkFirstCode.put("X", 24);
    hkFirstCode.put("W", 23);
    hkFirstCode.put("O", 15);
    hkFirstCode.put("N", 14);
  }

  /**
   * 将15位身份证号码转换为18位
   *
   * @param idCard 15位身份编码
   * @return 18位身份编码
   */
  public static String convert15To18(String idCard) {
    if (idCard.length() != CHINA_ID_MIN_LENGTH) {
      return null;
    }
    if (!RegexpUtils.isNumber(idCard)) {
      return null;
    }
    StringBuilder idCard18 = new StringBuilder();
    // 从身份证中获取生日
    String birthday = idCard.substring(6, 12);
    LocalDate localDate = LocalDate.parse(birthday, DateTimeFormatter.ofPattern(DATE_YY_MM_DD)).minusYears(100);
    // 获取出生年(完全表现形式,如：2010)
    int year = localDate.getYear();
    idCard18.append(idCard.substring(0, 6)).append(year).append(idCard.substring(8));
    // 获取校验位
    char sVal = getCheckCode18(idCard18.toString());
    return idCard18.append(sVal).toString();
  }

  /**
   * 是否有效身份证号
   *
   * @param idCard 身份证号，支持18位、15位和港澳台的10位
   * @return 是否有效
   */
  public static boolean isValidCard(String idCard) {
    int length = idCard.length();
    switch (idCard.length()) {
      case 18:
        // 18位身份证
        return isValidCard18(idCard);
      case 15:
        // 15位身份证
        return isValidCard15(idCard);
      case 10: {
        // 10位身份证，港澳台地区
        String[] array = isValidCard10(idCard);
        return array != null && StringUtils.equals("true", array[2]);
      }
      default:
        return false;
    }
  }

  /**
   * <p> 判断18位身份证的合法性 </p> 根据〖中华人民共和国国家标准GB11643-1999〗中有关公民身份号码的规定，公民身份号码是特征组合码，由十七位数字本体码和一位数字校验码组成。<br>
   * 排列顺序从左至右依次为：六位数字地址码，八位数字出生日期码，三位数字顺序码和一位数字校验码。 <p> 顺序码: 表示在同一地址码所标识的区域范围内，对同年、同月、同 日出生的人编定的顺序号，顺序码的奇数分配给男性，偶数分配 给女性。 </p> <ol>
   * <li>第1、2位数字表示：所在省份的代码</li> <li>第3、4位数字表示：所在城市的代码</li> <li>第5、6位数字表示：所在区县的代码</li> <li>第7~14位数字表示：出生年、月、日</li>
   * <li>第15、16位数字表示：所在地的派出所的代码</li> <li>第17位数字表示性别：奇数表示男性，偶数表示女性</li> <li>第18位数字是校检码，用来检验身份证的正确性。校检码可以是0~9的数字，有时也用x表示</li> </ol> <p>
   * 第十八位数字(校验码)的计算方法为： <ol> <li>将前面的身份证号码17位数分别乘以不同的系数。从第一位到第十七位的系数分别为：7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2</li> <li>将这17位数字和系数相乘的结果相加</li>
   * <li>用加出来和除以11，看余数是多少</li> <li>余数只可能有0 1 2 3 4 5 6 7 8 9 10这11个数字。其分别对应的最后一位身份证的号码为1 0 X 9 8 7 6 5 4 3 2</li>
   * <li>通过上面得知如果余数是2，就会在身份证的第18位数字上出现罗马数字的Ⅹ。如果余数是10，身份证的最后一位号码就是2</li> </ol>
   *
   * @param idCard 待验证的身份证
   * @return 是否有效的18位身份证
   */
  private static boolean isValidCard18(String idCard) {
    // 前17位
    String code17 = idCard.substring(0, 17);
    // 第18位
    char code18 = Character.toLowerCase(idCard.charAt(17));
    if (RegexpUtils.isNumber(code17)) {
      // 获取校验位
      char val = getCheckCode18(code17);
      if (val == code18) {
        return true;
      }
    }
    return false;
  }

  /**
   * 验证15位身份编码是否合法
   *
   * @param idCard 身份编码
   * @return 是否合法
   */
  private static boolean isValidCard15(String idCard) {
    if (!RegexpUtils.isNumber(idCard)) {
      return false;
    }
    // 省份
    String proCode = idCard.substring(0, 2);
    if (null == cityCodes.get(proCode)) {
      return false;
    }
    // 生日
    LocalDate localDate = LocalDate.parse(idCard.substring(6, 12),
        DateTimeFormatter.ofPattern(DATE_YY_MM_DD)).minusYears(100);
    return RegexpUtils.isBirthDay(localDate.toString());
  }

  /**
   * 验证10位身份编码是否合法
   *
   * @param idCard 身份编码
   * @return 身份证信息数组 <p> [0] - 台湾、澳门、香港 [1] - 性别(男M,女F,未知N) [2] - 是否合法(合法true,不合法false) 若不是身份证件号码则返回null </p>
   */
  private static String[] isValidCard10(String idCard) {
    String[] info = new String[3];
    String card = idCard.replaceAll("[\\(|\\)]", "");
    if (card.length() != 8 && card.length() != 9 && idCard.length() != 10) {
      return null;
    }
    if (idCard.matches("^[a-zA-Z][0-9]{9}$")) { // 台湾
      info[0] = "台湾";
      String char2 = idCard.substring(1, 2);
      if (char2.equals("1")) {
        info[1] = "M";
      } else if (char2.equals("2")) {
        info[1] = "F";
      } else {
        info[1] = "N";
        info[2] = "false";
        return info;
      }
      info[2] = isValidTWCard(idCard) ? "true" : "false";
    } else if (idCard.matches("^[1|5|7][0-9]{6}\\(?[0-9A-Z]\\)?$")) { // 澳门
      info[0] = "澳门";
      info[1] = "N";
      info[2] = isValidMCCard(idCard) ? "true" : "false";
    } else if (idCard.matches("^[A-Z]{1,2}[0-9]{6}\\(?[0-9A]\\)?$")) { // 香港
      info[0] = "香港";
      info[1] = "N";
      info[2] = isValidHKCard(idCard) ? "true" : "false";
    } else {
      return null;
    }
    return info;
  }

  /**
   * 验证澳门身份证号码
   *
   * @param idCard 身份证号码
   * @return 验证码是否符合
   */
  // todo 没有实现
  public static boolean isValidMCCard(String idCard) {
    return false;
  }

  /**
   * 验证台湾身份证号码
   *
   * @param idCard 身份证号码
   * @return 验证码是否符合
   */
  public static boolean isValidTWCard(String idCard) {
    String start = idCard.substring(0, 1);
    String mid = idCard.substring(1, 9);
    String end = idCard.substring(9, 10);
    Integer iStart = twFirstCode.get(start);
    Integer sum = iStart / 10 + (iStart % 10) * 9;
    char[] chars = mid.toCharArray();
    Integer iflag = 8;
    for (char c : chars) {
      sum += Integer.valueOf(String.valueOf(c)) * iflag;
      iflag--;
    }
    return (sum % 10 == 0 ? 0 : (10 - sum % 10)) == Integer.valueOf(end);
  }

  /**
   * 验证香港身份证号码(存在Bug，部份特殊身份证无法检查) <p> 身份证前2位为英文字符，如果只出现一个英文字符则表示第一位是空格，对应数字58 前2位英文字符A-Z分别对应数字10-35 最后一位校验码为0-9的数字加上字符"A"，"A"代表10 </p> <p>
   * 将身份证号码全部转换为数字，分别对应乘9-1相加的总和，整除11则证件号码有效 </p>
   *
   * @param idCard 身份证号码
   * @return 验证码是否符合
   */
  public static boolean isValidHKCard(String idCard) {
    String card = idCard.replaceAll("[\\(|\\)]", "");
    Integer sum = 0;
    if (card.length() == 9) {
      sum = ((int) card.substring(0, 1).toUpperCase().toCharArray()[0] - 55) * 9
          + ((int) card.substring(1, 2).toUpperCase().toCharArray()[0] - 55) * 8;
      card = card.substring(1, 9);
    } else {
      sum = 522 + ((int) card.substring(0, 1).toUpperCase().toCharArray()[0] - 55) * 8;
    }
    String mid = card.substring(1, 7);
    String end = card.substring(7, 8);
    char[] chars = mid.toCharArray();
    Integer iflag = 7;
    for (char c : chars) {
      sum = sum + Integer.valueOf(String.valueOf(c)) * iflag;
      iflag--;
    }
    if ("A".equals(end.toUpperCase())) {
      sum += 10;
    } else {
      sum += Integer.valueOf(end);
    }
    return sum % 11 == 0;
  }

  /**
   * 从身份证号码中获取生日日期
   *
   * @param idCard 身份证号码
   * @return 身份证合法--> LocalDate对象；身份证非法--> <code>null</code>
   */
  public static LocalDate getBirthdayDate(String idCard) {
    // 判断是否合法
    if (!isValidCard(idCard)) {
      return null;
    }
    int length = idCard.length();
    if (length == CHINA_ID_MAX_LENGTH) {
      return LocalDate.parse(idCard.substring(6, 14),
          DateTimeFormatter.ofPattern(DATE_YYYY_MM_DD));
    }
    return LocalDate.parse(idCard.substring(6, 12),
        DateTimeFormatter.ofPattern(DATE_YY_MM_DD)).minusYears(100);
  }

  /**
   * 从身份证号码中获取生日日期
   *
   * @param idCard 身份证号码
   * @return 身份证合法--> 生日；身份证非法--> <code>null</code>
   */
  public static String getBirthday(String idCard)  {
    LocalDate date = getBirthdayDate(idCard);
    return date == null ? null : date.toString();
  }

  /**
   * 根据身份编号获取生日年
   *
   * @param idCard 身份编号
   * @return 身份证合法--> 生日年份；身份证非法--> <code>null</code>
   */
  public static String getBirthdayYear(String idCard) {
    LocalDate date = getBirthdayDate(idCard);
    return date == null ? null : String.valueOf(date.getYear());
  }

  /**
   * 根据身份编号获取年龄
   *
   * @param idCard 身份编号
   * @return 身份证合法--> 年龄；身份证非法--> -1
   */
  public static int getAgeByIdCard(String idCard) {
    LocalDate date = getBirthdayDate(idCard);
    if (date == null) {
      return -1;
    }
    return LocalDate.now().getYear() - date.getYear();
  }

  /**
   * 根据身份编号获取性别
   *
   * @param idCard 身份编号
   * @return 身份证合法：男-->1，女-->0; 身份证非法：-1
   */
  public static int getSex(String idCard) {
    if (!isValidCard(idCard)) {
      return -1;
    }
    int length = idCard.length();
    if (length == CHINA_ID_MAX_LENGTH) {
      return idCard.charAt(16) % 2;
    }
    return idCard.charAt(14) % 2;
  }

  /**
   * 根据身份编号获取户籍省份
   *
   * @param idCard 身份编码
   * @return 身份证合法：省级编码; 身份证非法：null
   */
  public static String getProvince(String idCard) {
    if (!isValidCard(idCard)) {
      return null;
    }
    String provinceCode = idCard.substring(0, 2);
    return cityCodes.get(provinceCode);
  }

  /**
   * 隐藏指定位置的几个身份证号数字为“*”
   *
   * @param idCard 身份证号
   * @param startInclude 开始位置（包含）
   * @param endExclude 结束位置（不包含）
   * @return 隐藏后的身份证号码
   * @since 3.2.2
   */
  // todo 没有闪现
  public static String hide(String idCard, int startInclude, int endExclude) {
    return null;
  }

  /**
   * 获得18位身份证校验码
   *
   * @param code17 18位身份证号中的前17位
   * @return 第18位
   */
  private static char getCheckCode18(String code17) {
    int sum = getPowerSum(code17.toCharArray());
    return getCheckCode18(sum);
  }

  /**
   * 将power和值与11取模获得余数进行校验码判断
   *
   * @return 校验位
   */
  private static char getCheckCode18(int iSum) {
    switch (iSum % 11) {
      case 10:
        return '2';
      case 9:
        return '3';
      case 8:
        return '4';
      case 7:
        return '5';
      case 6:
        return '6';
      case 5:
        return '7';
      case 4:
        return '8';
      case 3:
        return '9';
      case 2:
        return 'x';
      case 1:
        return '0';
      case 0:
        return '1';
      default:
        return ' ';
    }
  }

  /**
   * 将身份证的每位和对应位的加权因子相乘之后，再得到和值
   *
   * @return 身份证编码。
   */
  private static int getPowerSum(char[] iArr) {
    int iSum = 0;
    if (power.length == iArr.length) {
      for (int i = 0; i < iArr.length; i++) {
        iSum += Integer.valueOf(String.valueOf(iArr[i])) * power[i];
      }
    }
    return iSum;
  }
  // ----------------------------------------------------------------------------------- Private method end
}
