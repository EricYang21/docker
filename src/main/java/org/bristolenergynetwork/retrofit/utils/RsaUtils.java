package org.bristolenergynetwork.retrofit.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RsaUtils {

  private static final int DEFAULT_KEY_SIZE = 2048;
  /**
   * read publicKey from file
   *
   * @param filename PublicKey save path，相对于classpath
   * @return publicKey
   * @throws Exception
   */
  public static PublicKey getPublicKey(String filename) throws Exception {
    byte[] bytes = readFile(filename);
    return getPublicKey(bytes);
  }

  /**
   * read SecretKey from file
   *
   * @param filename SecretKey save path，相对于classpath
   * @return SecretKey
   * @throws Exception
   */
  public static PrivateKey getPrivateKey(String filename) throws Exception {
    byte[] bytes = readFile(filename);
    return getPrivateKey(bytes);
  }

  /**
   * get publicKey
   *
   * @param bytes PublicKey bytes
   * @return
   * @throws Exception
   */
  private static PublicKey getPublicKey(byte[] bytes) throws Exception {
    bytes = Base64.getDecoder().decode(bytes);
    X509EncodedKeySpec spec = new X509EncodedKeySpec(bytes);
    KeyFactory factory = KeyFactory.getInstance("RSA");
    return factory.generatePublic(spec);
  }

  /**
   * get SecretKey
   *
   * @param bytes
   * @return
   * @throws Exception
   */
  private static PrivateKey getPrivateKey(byte[] bytes)
      throws NoSuchAlgorithmException, InvalidKeySpecException {
    bytes = Base64.getDecoder().decode(bytes);
    PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(bytes);
    KeyFactory factory = KeyFactory.getInstance("RSA");
    return factory.generatePrivate(spec);
  }

  /**
   * generate rsa publicKey and SecretKey,write in path
   *
   * @param publicKeyFilename
   * @param privateKeyFilename
   * @param secret
   */
  public static void generateKey(
      String publicKeyFilename, String privateKeyFilename, String secret, int keySize)
      throws Exception {
    KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
    SecureRandom secureRandom = new SecureRandom(secret.getBytes());
    keyPairGenerator.initialize(Math.max(keySize, DEFAULT_KEY_SIZE), secureRandom);
    KeyPair keyPair = keyPairGenerator.genKeyPair();
    // 获取公钥并写出
    byte[] publicKeyBytes = keyPair.getPublic().getEncoded();
    publicKeyBytes = Base64.getEncoder().encode(publicKeyBytes);
    writeFile(publicKeyFilename, publicKeyBytes);
    // 获取私钥并写出
    byte[] privateKeyBytes = keyPair.getPrivate().getEncoded();
    privateKeyBytes = Base64.getEncoder().encode(privateKeyBytes);
    writeFile(privateKeyFilename, privateKeyBytes);
  }

  private static byte[] readFile(String fileName) throws Exception {
    return Files.readAllBytes(new File(fileName).toPath());
  }

  private static void writeFile(String destPath, byte[] bytes) throws IOException {
    File dest = new File(destPath);
    if (!dest.exists()) {
      dest.createNewFile();
    }
    Files.write(dest.toPath(), bytes);
  }
}
