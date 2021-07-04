package com.test.mark.zhang.test.agency.wang.guava.test.io;

import com.google.common.io.ByteSource;
import com.google.common.io.Files;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/***************************************
 * @author:Alex Wang
 * @Date:2017/10/14
 * @QQ: 532500648
 ***************************************/
public class ByteSourceTest {

    private final String path = "C:\\Users\\wangwenjun\\IdeaProjects\\guava_programming\\src\\test\\resources\\io\\files.PNG";

    @Test
    public void testAsByteSource() throws IOException {
        File file = new File(path);
        ByteSource byteSource = Files.asByteSource(file);
        byte[] readBytes = byteSource.read();
        assertThat(readBytes, is(Files.toByteArray(file)));
    }

    @Test
    public void testSliceByteSource() throws IOException {
        ByteSource byteSource = ByteSource.wrap(new byte[]{0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09});
        ByteSource sliceByteSource = byteSource.slice(5, 5);
        byte[] bytes = sliceByteSource.read();
        for (byte b : bytes) {
            System.out.println(b);
        }
    }

}