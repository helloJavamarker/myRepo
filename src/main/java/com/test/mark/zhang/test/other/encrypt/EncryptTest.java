package com.test.mark.zhang.test.other.encrypt;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.SecureRandom;

/**
 * @Classname EncryptTest
 * @Description TODO
 * @Date 2021/6/2 2:20 下午
 * @Created by mark
 */
public class EncryptTest {

    public static void main(String[] args) {
        System.out.println(encrypt("zhang"));
        System.out.println(decrypt("9HOfTbej2gFUxRqcylEIkA=="));


        StringBuilder sb = new StringBuilder("name");
        for (int i = 0; i < 10; i++) {
            sb.append("\0");
        }

        for (int i = 0; i < 10; i++) {
            sb.append('\0');
        }

        for (int i = 0; i < 10; i++) {
            sb.append(" ");
        }

        System.out.println(sb.append(".."));

        //System.out.println("test");
    }

    public static final String encrypt(String plainText) {
        Key secretKey = getKey("fendo888");
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] p = plainText.getBytes("UTF-8");
            byte[] result = cipher.doFinal(p);
            BASE64Encoder encoder = new BASE64Encoder();
            String encoded = encoder.encode(result);
            return encoded;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static final String decrypt(String cipherText) {
        Key secretKey = getKey("fendo888");
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] c = decoder.decodeBuffer(cipherText);
            byte[] result = cipher.doFinal(c);
            String plainText = new String(result, "UTF-8");
            return plainText;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Key getKey(String keySeed) {
        if (keySeed == null) {
            keySeed = System.getenv("AES_SYS_KEY");
        }
        if (keySeed == null) {
            keySeed = System.getProperty("AES_SYS_KEY");
        }
        if (keySeed == null || keySeed.trim().length() == 0) {
            keySeed = "abcd1234!@#$";// 默认种子
        }
        try {
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(keySeed.getBytes());
            KeyGenerator generator = KeyGenerator.getInstance("AES");
            generator.init(secureRandom);
            return generator.generateKey();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
