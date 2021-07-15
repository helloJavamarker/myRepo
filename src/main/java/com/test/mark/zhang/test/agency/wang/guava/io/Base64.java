package com.test.mark.zhang.test.agency.wang.guava.io;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/***************************************
 * @author:Alex Wang
 * @Date:2017/10/15
 * @QQ: 532500648
 ***************************************/
public final class Base64 {

    private final static String CODE_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

    private final static char[] CODE_DICT = CODE_STRING.toCharArray();

    private Base64() {
    }

    public static String encode(String plain) {
        //Preconditions.checkNotNull(plain);
        StringBuilder result = new StringBuilder();
        String binaryString = toBinary(plain);

        int delta = 6 - binaryString.length() % 6;//should append

        //前面补0
        for (int i = 0; i < delta && delta != 6; i++) {
            binaryString += "0";
        }

        for (int index = 0; index < binaryString.length() / 6; index++) {
            int begin = index * 6;
            String encodeString = binaryString.substring(begin, begin + 6);
            char encodeChar = CODE_DICT[Integer.valueOf(encodeString, 2)];
            result.append(encodeChar);
        }

        if (delta != 6) {
            //后面补=
            for (int i = 0; i < delta / 2; i++) {
                result.append("=");
            }
        }

        return result.toString();
    }

    private static String toBinary(final String source) {
        final StringBuilder binaryResult = new StringBuilder();
        for (int index = 0; index < source.length(); index++) {
            String charBin = Integer.toBinaryString(source.charAt(index));
            int delta = 8 - charBin.length();
            for (int d = 0; d < delta; d++) {
                charBin = "0" + charBin;
            }

            binaryResult.append(charBin);
        }

        return binaryResult.toString();
    }

    public static String decode(final String encrypt) {
        StringBuilder resultBuilder = new StringBuilder();

        String temp = encrypt;
        int equalIndex = temp.indexOf("=");
        if (equalIndex > 0) {
            temp = temp.substring(0, equalIndex);
        }

        String base64Binary = toBase64Binary(temp);
        for (int i = 0; i < base64Binary.length() / 8; i++) {
            int begin = i * 8;
            String str = base64Binary.substring(begin, begin + 8);
            char c = Character.toChars(Integer.valueOf(str, 2))[0];
            resultBuilder.append(c);
        }

        return resultBuilder.toString();
    }


    private static String toBase64Binary(final String source) {
        final StringBuilder binaryResult = new StringBuilder();
        for (int index = 0; index < source.length(); index++) {
            int i = CODE_STRING.indexOf(source.charAt(index));
            String charBin = Integer.toBinaryString(i);
            int delta = 6 - charBin.length();
            for (int d = 0; d < delta; d++) {
                charBin = "0" + charBin;
            }

            binaryResult.append(charBin);
        }

        return binaryResult.toString();
    }


    public static void main(String[] args) {

        Collection<String> c = new ArrayList<>();
        c.add("大哥");
        c.add("二哥");
        c.add("三哥");
        Iterator<String> ite = c.iterator();
        while (ite.hasNext()) {   //一个循环里面尽量不要用多次next()方法
            Object obj = ite.next();

            System.out.println(obj);
            ite.next();
            ite.next();
            ite.next();
        }
        System.out.println(c);

        //List<String> list = Arrays.asList("zhang", "san", "li");
        List<String> list = new ArrayList<>();
        list.add("zhang");
        list.add("san");
        list.add("li");
        list.add("si");
//        for (String s : list) {
//            if ("zhang".equals(s)) {
//                list.remove(s);
//            }
//        }

//        for (int i = 0; i < list.size(); i++) {
//            if ("zhang".equals(list.get(i))) {
//                list.remove(i);
//            }
//        }

//        for (String s : list) {
//            list.removeIf("zhang"::equals);
//        }


//list.removeIf("zhang"::equals);
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
            if ("zhang".equals(iterator.next())) {
                iterator.remove();
            }
        }
        System.out.println(list);

//        list.forEach(item -> {
//            if ("zhang".equals(item)) {
//                list.remove(item);
//            }
//        });

        System.out.println(encode("hello"));
        System.out.println(encode("a"));
        System.out.println(encode("12a"));
        System.out.println("=======================");
        System.out.println(decode("aGVsbG8="));
        System.out.println(decode("YQ=="));
        System.out.println(decode("MTJh"));
    }
}
