package com.test.mark.zhang.test.other.project.study;

import org.xerial.snappy.Snappy;
import org.xerial.snappy.SnappyOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * @author by mark
 * @Classname SnappyTest
 * @Description TODO
 * @Date 2022/6/29 10:57 上午
 */
public class SnappyTest {

    public static void main(String[] args) throws IOException {
        String dataString = "The quick brown fox jumps over the lazy dog";
        byte[] compressed = Snappy.compress(dataString.getBytes(StandardCharsets.UTF_8));
        byte[] uncompressed = Snappy.uncompress(compressed);
        String result = new String(uncompressed, StandardCharsets.UTF_8);
        System.out.println(result);
    }

    /**
     * 两种压缩方式   德语schnappi 鳄鱼
     * 通过Snappy.compress()进行压缩，压缩后的数据没有magic header
     * 通过SnappyInputStream进行压缩，压缩后的数据有固定的header
     *
     * @throws IOException
     */
    private void compress() throws IOException {
        String dataString = "The quick brown fox jumps over the lazy dog";
        byte[] compressed = Snappy.compress(dataString.getBytes(StandardCharsets.UTF_8));
        byte[] uncompressed = Snappy.uncompress(compressed);
        String result = new String(uncompressed, StandardCharsets.UTF_8);
        System.out.println(result);
    }

    public static byte[] compressSnappy(byte[] data) throws IOException {
        ByteArrayInputStream is = new ByteArrayInputStream(data);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        SnappyOutputStream sos = new SnappyOutputStream(os);
        int count;
        byte temp[] = new byte[10];
        try {
            while ((count = is.read(temp)) != -1) {
                sos.write(temp, 0, count);
            }
            sos.flush();
            byte[] output = os.toByteArray();
            return output;
        } finally {
            sos.close();
            is.close();
        }
    }

    public static byte[] compressHtml(String html) {
        try {
            //api极其简单
            return Snappy.compress(html.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String decompressHtml(byte[] bytes) {
        try {
            return new String(Snappy.uncompress(bytes));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
