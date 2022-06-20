package com.test.mark.zhang.test.agency.heima.disign.day6.disign_partten.pattern.singleton.demo9;

import cn.hutool.core.io.FileTypeUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @version v1.0
 * @ClassName: RuntimeDemo
 * @Description: TODO(一句话描述该类的功能)
 * @Author: 黑马程序员
 */
public class RuntimeDemo {
    public static void main(String[] args) throws IOException {
        //获取Runtime类的对象
        Runtime runtime = Runtime.getRuntime();

        //调用runtime的方法exec, 参数要的是一个命令
        Process process = runtime.exec("ipconfig");
        //调用process对象的获取输入流的方法
        InputStream is = process.getInputStream();

        byte[] arr = new byte[1024 * 1024 * 100];
        //读取数据
        int len = is.read(arr);//返回读到的字节的个数
        //将字节数组转换为字符串输出到控制台
        System.out.println(new String(arr, 0, len, "GBK"));
    }

    /**
     * public Process exec(String command)-----在单独的进程中执行指定的字符串命令。
     * public Process exec(String [] cmdArray)---在单独的进程中执行指定命令和变量
     * public Process exec(String command, String [] envp)----在指定环境的独立进程中执行指定命令和变量
     * public Process exec(String [] cmdArray, String [] envp)----在指定环境的独立进程中执行指定的命令和变量
     * public Process exec(String command,String[] envp,File dir)----在有指定环境和工作目录的独立进程中执行指定的字符串命令
     * public Process exec(String[] cmdarray,String[] envp,File dir)----在指定环境和工作目录的独立进程中执行指定的命令和变量
     * 举例：
     *
     * 1.RunTime.getRuntime().exec（String command）;
     * 在windows下相当于直接调用/开始/搜索程序和文件的指令，比如
     * Runtime.getRuntime().exec("notepad.exe"); -------打开windows下记事本。
     *
     * 2. public Process exec(String [] cmdArray)；
     * Linux下：
     * Runtime.getRuntime().exec(new String[]{"/bin/sh","-c", ";
     * Windows下：
     * Runtime.getRuntime().exec(new String[]{"cmd", "/c", cmds});
     *
     * 实例：
     * String command = "find " + source.getRoute() + "-name '" +source.getName();
     * Process process = Runtime.getRuntime().exec(new String[] {"/bin/sh","-c",command});
     *
     * 补充：#!/bin/bash和#!/bin/sh的区别
     * #! 是个指示路径的表示符，/bin/bash和/bin/sh指定了脚本解析器的程序路径
     * bash是sh的完整版，bash完全兼容sh命令，反之不行
     */

    public void unzipPackage(String unzipPath, String unzipPassword, String zipFilename) throws IOException, InterruptedException {
        Process exec = Runtime.getRuntime().exec(new String[]{"unzip", "-o", "-P", unzipPassword, zipFilename}, null, new File(unzipPath));
        int exitValue = exec.waitFor();
        if (exitValue != 0) {
            throw new IOException("解压zip包失败");
        }
        System.out.println("解压成功");
    }

    //unzipPath: /data/unzipapp/
    public void unzipTar(String unzipPath, String tarFilename) throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec(new String[]{"tar", "-zxf", tarFilename}, null, new File(unzipPath));
        //输出错误信息,防止信息过多导致现场阻塞
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String readLine;
        while ((readLine = bufferedReader.readLine()) != null) {
            System.out.println("warn:" + readLine);
        }
        int exitValue = process.waitFor();
        if (exitValue != 0) {
            throw new IOException("解压tar包失败");
        }
        System.out.println("解压成功");
    }


    public static String run(String cmd) throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec(new String[]{"/bin/bash", "-c", cmd});
        process.waitFor();

        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line).append("\n");
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }

    public static String runWindow(String cmd) throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec(new String[]{"cmd.exe", "/c", cmd});
        process.waitFor();

        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line).append("\n");
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }

    public static String runShell(String cmd) throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec( cmd);
        process.waitFor();

        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line).append("\n");
        }
        return sb.toString();
    }

}
