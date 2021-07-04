package com.test.mark.zhang.test.agency.wang.guava.test.io;

import com.google.common.io.ByteSource;
import com.google.common.io.Closer;
import com.google.common.io.Files;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/***************************************
 * @author:Alex Wang
 * @Date:2017/10/14
 * @QQ: 532500648
 ***************************************/
public class CloserTest {

    @Test
    public void testCloser() throws IOException {
        ByteSource byteSource = Files.asByteSource(new File("C:\\Users\\wangwenjun\\IdeaProjects\\guava_programming\\src\\test\\resources\\io\\files.PNG"));
        Closer closer = Closer.create();
        try {
            InputStream inputStream = closer.register(byteSource.openStream());
        } catch (Throwable e) {
            throw closer.rethrow(e);
        } finally {
            closer.close();
        }
    }

    @Test(expected = RuntimeException.class)
    public void testTryCatchFinally() {
        try {

            System.out.println("work area.");
            throw new IllegalArgumentException();
        } catch (Exception e) {
            System.out.println("exception area");
            throw new RuntimeException();
        } finally {
            System.out.println("finally area");
        }
    }

    @Test
    public void testTryCatch() {
        Throwable t = null;
        try {
            throw new RuntimeException("1");
        } catch (Exception e) {
            t = e;
            throw e;
        } finally {
            try {
                //close
                throw new RuntimeException("2");
            } catch (Exception e) {
                t.addSuppressed(e);
            }
        }
    }
}
