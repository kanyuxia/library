package com.kanyuxia.crypto;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

final class Digest {

  static final int DEFAULT_HASH_ITERATIONS = 1;

  static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

  private final MessageDigest digest;

  Digest(String algorithmName) {
    try {
      this.digest = MessageDigest.getInstance(algorithmName);
    } catch (NoSuchAlgorithmException e) {
      String msg = "No native '" + algorithmName + "' MessageDigest instance available on the current JVM.";
      throw new RuntimeException(msg, e);
    }
  }

  byte[] hash(byte[] data, byte[] salt, int hashIterations) {
    if (salt != null) {
      digest.reset();
      digest.update(salt);
    }
    byte[] hashed = digest.digest(data);
    int iterations = hashIterations - 1; //already hashed once above
    //iterate remaining number:
    for (int i = 0; i < iterations; i++) {
      digest.reset();
      hashed = digest.digest(hashed);
    }
    return hashed;
  }
}
