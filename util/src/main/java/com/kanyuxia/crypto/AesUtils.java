package com.kanyuxia.crypto;


import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES加密、解密工具类
 */
public class AesUtils {

  private static final String DEFAULT_KEY = "zhihuichengshi08";

  private static final String DEFAULT_ALGORITHM_NAME = "AES/ECB/PKCS5Padding";

  /**
   * 加密数据
   *
   * @param data 加密数据
   * @return 加密后的数据
   */
  public static byte[] encrypt(byte[] data) {
    return encrypt(DEFAULT_KEY.getBytes(), DEFAULT_ALGORITHM_NAME, data, null);
  }

  /**
   * 加密数据
   *
   * @param secretKey 秘钥
   * @param data 加密数据
   * @return 加密后的数据
   */
  public static byte[] encrypt(byte[] secretKey, byte[] data) {
    return encrypt(secretKey, DEFAULT_ALGORITHM_NAME, data, null);
  }


  /**
   * 加密数据
   *
   * @param secretKey 秘钥
   * @param algorithmName 算法名称
   * @param data 加密数据
   * @return 加密后的数据
   */
  public static byte[] encrypt(byte[] secretKey, String algorithmName, byte[] data) {
    return encrypt(secretKey, algorithmName, data, null);
  }

  /**
   * 加密数据
   *
   * @param secretKey 秘钥
   * @param algorithmName 算法名称
   * @param data 加密数据
   * @param param 加密参数
   * @return 加密后的数据
   */
  public static byte[] encrypt(byte[] secretKey, String algorithmName, byte[] data, AlgorithmParameterSpec param) {
    return new AES(secretKey, algorithmName, param).encrypt(data);
  }

  /**
   * 解密数据
   *
   * @param data 解密数据
   * @return 解密后的数据
   */
  public static byte[] decrypt(byte[] data) {
    return decrypt(DEFAULT_KEY.getBytes(), DEFAULT_ALGORITHM_NAME, data, null);
  }

  /**
   * 解密数据
   *
   * @param secretKey 秘钥
   * @param data 解密数据
   * @return 解密后的数据
   */
  public static byte[] decrypt(byte[] secretKey, byte[] data) {
    return decrypt(secretKey, DEFAULT_ALGORITHM_NAME, data, null);
  }

  /**
   * 解密数据
   *
   * @param secretKey 秘钥
   * @param algorithmName 算法名称
   * @param data 解密数据
   * @return 解密后的数据
   */
  public static byte[] decrypt(byte[] secretKey, String algorithmName, byte[] data) {
    return decrypt(secretKey, algorithmName, data, null);
  }

  /**
   * 解密数据
   *
   * @param secretKey 秘钥
   * @param algorithmName 算法名称
   * @param data 解密数据
   * @param param 解密参数
   * @return 解密后的数据
   */
  public static byte[] decrypt(byte[] secretKey, String algorithmName, byte[] data, AlgorithmParameterSpec param) {
    return new AES(secretKey, algorithmName, param).decrypt(data);
  }

  private static final class AES {

    /**
     * Cipher负责完成加密或解密工作
     */
    private final Cipher cipher;

    /**
     * 加密解密参数
     */
    private final AlgorithmParameterSpec param;

    /**
     * 加密解密秘钥
     */
    private final Key secretKey;

    private AES(byte[] secretKey, String algorithmName, AlgorithmParameterSpec param) {
      try {
        this.cipher = Cipher.getInstance(algorithmName);
        this.secretKey = new SecretKeySpec(secretKey, algorithmName);
        this.param = param;
      } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
        String msg = "No native '" + algorithmName + "' MessageDigest instance available on the current JVM.";
        throw new RuntimeException(msg, e);
      }
    }

    /**
     * 加密
     *
     * @param data 加密数据
     * @return 解密后的数据
     */
    private byte[] encrypt(byte[] data) {
      try {
        if (param == null) {
          cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        } else {
          cipher.init(Cipher.ENCRYPT_MODE, secretKey, param);
        }
        return cipher.doFinal(data);
      } catch (IllegalBlockSizeException | BadPaddingException |
          InvalidAlgorithmParameterException | InvalidKeyException e) {
        throw new RuntimeException(e);
      }
    }

    /**
     * 解密
     *
     * @param data 解密数据
     * @return 解密后的数据
     */
    private byte[] decrypt(byte[] data) {
      try {
        if (param == null) {
          cipher.init(Cipher.DECRYPT_MODE, secretKey);
        } else {
          cipher.init(Cipher.DECRYPT_MODE, secretKey, param);
        }
        return cipher.doFinal(data);
      } catch (IllegalBlockSizeException | BadPaddingException |
          InvalidAlgorithmParameterException | InvalidKeyException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
