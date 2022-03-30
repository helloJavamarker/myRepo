package com.test.mark.zhang.test.other.encrypt;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.util.Date;

/**
 * @author by mark
 * @Classname DecryptAES
 * @Description TODO
 * @Date 2022/3/1 5:16 下午
 */
public class DecryptAES {

    private static final Charset ASCII = Charset.forName("US-ASCII");
    public static void main(String[] args) throws Exception {
        String aeskey = "aes_key_16_bytes";
//        String base64Cipher =
//                "Y7dJKaXGgrOAAJjiFxqQv8YcQ8ntcnivQwGxOCS88KXg0drfOgbZ1OKnOINDcQA7";

        String content = "";
        byte [] cipherBytes = Base64.decodeBase64(content);
        byte [] iv = aeskey.getBytes(ASCII);
        byte [] keyBytes = aeskey.getBytes(ASCII);
        SecretKey aesKey = new SecretKeySpec(keyBytes, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        cipher.init(Cipher.DECRYPT_MODE, aesKey, new IvParameterSpec(iv));
        byte[] result = cipher.doFinal(cipherBytes);
        String plainText = new String(java.util.Arrays.copyOfRange(result, 16, result.length));
        System.out.println(plainText);

        System.out.println(new Date(System.currentTimeMillis() - 15 * 60 * 1000));
        System.out.println(new Date());
    }
}
