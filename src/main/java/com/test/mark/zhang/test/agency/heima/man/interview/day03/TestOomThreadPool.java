package com.test.mark.zhang.test.agency.heima.man.interview.day03;

import com.test.mark.zhang.test.agency.heima.man.interview.day02.LoggerUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

// -Xmx64m
// 模拟短信发送超时，但这时仍有大量的任务进入队列
public class TestOomThreadPool {
    public static void main(String[] args) {

        //什么是WSL
        //WSL - Windows Subsystem for Linux
        //The Windows Subsystem for Linux lets developers run Linux environments -- including most command-line tools, utilities, and applications -- directly on Windows, unmodified, without the overhead of a virtual machine.
        //Linux 的 Windows 子系统让开发人员无需虚拟机就可以直接在 Windows 上运行 Linux 环境，包括大多数命令行工具、程序和应用。
        //
        //使用 WSL 的好处是：
        //
        //与在虚拟机下使用 Linux 相比，WSL 更加流畅；
        //WSL 可以对 Windows 文件系统下的文件直接进行读写，文件传输更方便；
        //剪贴板互通，可以直接在 Windows 下其它地方复制文本内容，粘贴到 WSL；

        //使用wsl:https://zhuanlan.zhihu.com/p/36482795
        //应用市场下载,配置注册表等

        ExecutorService executor = Executors.newFixedThreadPool(2);
        LoggerUtils.get().debug("begin...");
        while (true) {
            executor.submit(() -> {
                try {
                    LoggerUtils.get().debug("send sms...");
                    TimeUnit.SECONDS.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
