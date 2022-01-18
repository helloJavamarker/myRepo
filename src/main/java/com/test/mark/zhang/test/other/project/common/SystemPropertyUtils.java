package com.test.mark.zhang.test.other.project.common;


import org.apache.commons.lang3.StringUtils;

import java.util.Iterator;
import java.util.Map;

/**
 * @author by mark
 * @Classname SystemPropertyUtils
 * @Description TODO
 * @Date 2022/1/7 9:02 上午
 */
public class SystemPropertyUtils {

    //System.getenv() 方法是获取指定的环境变量的值。
    //System.getenv(String str) 接收参数为任意字符串，当存在指定环境变量时即返回环境变量的值，否则返回null。

    //System.getProperty() 是获取系统的相关属性，包括文件编码、操作系统名称、区域、用户名等，此属性一般由jvm自动获取，不能设置。ey=value  可以指定-Dkey=value   java -jar -Dk
    //System.getProperty(String str) 接收参数为任意字符串，当存在指定属性时即返回属性的值，否则返回null。

    //System.getenv()
    //
    //USERPROFILE        ：用户目录
    //USERDNSDOMAIN      ：用户域
    //PATHEXT            ：可执行后缀
    //JAVA_HOME          ：Java安装目录
    //TEMP               ：用户临时文件目录
    //SystemDrive        ：系统盘符
    //ProgramFiles       ：默认程序目录
    //USERDOMAIN         ：帐户的域的名称
    //ALLUSERSPROFILE    ：用户公共目录
    //SESSIONNAME        ：Session名称
    //TMP                ：临时目录
    //Path               ：path环境变量
    //CLASSPATH          ：classpath环境变量
    //PROCESSOR_ARCHITECTURE ：处理器体系结构
    //OS                     ：操作系统类型
    //PROCESSOR_LEVEL    ：处理级别
    //COMPUTERNAME       ：计算机名
    //Windir             ：系统安装目录
    //SystemRoot         ：系统启动目录
    //USERNAME           ：用户名
    //ComSpec            ：命令行解释器可执行程序的准确路径
    //APPDATA            ：应用程序数据目录

    //System.getProperty()
    //
    //java.version Java ：运行时环境版本
    //java.vendor Java ：运行时环境供应商
    //java.vendor.url ：Java供应商的 URL
    //java.home &nbsp;&nbsp;：Java安装目录
    //java.vm.specification.version： Java虚拟机规范版本
    //java.vm.specification.vendor ：Java虚拟机规范供应商
    //java.vm.specification.name &nbsp; ：Java虚拟机规范名称
    //java.vm.version ：Java虚拟机实现版本
    //java.vm.vendor ：Java虚拟机实现供应商
    //java.vm.name&nbsp; ：Java虚拟机实现名称
    //java.specification.version：Java运行时环境规范版本
    //java.specification.vendor：Java运行时环境规范供应商
    //java.specification.name ：Java运行时环境规范名称
    //java.class.version ：Java类格式版本号
    //java.class.path ：Java类路径
    //java.library.path  ：加载库时搜索的路径列表
    //java.io.tmpdir  ：默认的临时文件路径
    //java.compiler  ：要使用的 JIT编译器的名称
    //java.ext.dirs ：一个或多个扩展目录的路径
    //os.name ：操作系统的名称
    //os.arch  ：操作系统的架构
    //os.version  ：操作系统的版本
    //file.separator ：文件分隔符
    //path.separator ：路径分隔符
    //line.separator ：行分隔符
    //user.name ：用户的账户名称
    //user.home ：用户的主目录
    //user.dir：用户的当前工作目录
    public static void main(String[] args) {
        System.setProperty("hello","world");
        System.setProperty("hello","world");
        Map<String, String> map = System.getenv();
        String property = System.getProperty("helloxx", "xxx");
        System.out.println(property);
        System.out.println(System.getProperty("mirror.profile.home"));
        System.out.println(System.getProperty("user.dir"));
        System.setProperty("hello2","world2");
        for(Iterator<String> it = map.keySet().iterator(); it.hasNext();){
            String key = it.next();
            //加了-Dmirror.profile.home 但是并没有打印... 启动的时候确实设置了
            //-Dmirror.profile.home=zhangsan -javaagent:/Applications/IntelliJ IDEA.app/Contents/lib/idea_rt
            System.out.println(key + "=" + map.get(key));
        }
    }
    private static final String FILE_SEPARATOR = System.getProperty("file.separator");
    private static String PROFILE_HOME = System.getProperty("mirror.profile.home", System.getenv("MIRROE_HOME"));

    static {
        if (StringUtils.isEmpty(PROFILE_HOME)) {
            PROFILE_HOME = System.getProperty("user.dir");
        }
    }

    private SystemPropertyUtils() {
    }

    public static String getProfileHome() {
        return PROFILE_HOME;
    }

    public static String getConfPath() {
        return PROFILE_HOME + FILE_SEPARATOR + "conf";
    }

    public static String getSystemPath() {
        return PROFILE_HOME + FILE_SEPARATOR + "system";
    }

    public static String getLibPath() {
        return PROFILE_HOME + FILE_SEPARATOR + "lib";
    }

    public static String getFileSeparator() {
        return FILE_SEPARATOR;
    }

    public static String getBinPath() {
        return PROFILE_HOME + FILE_SEPARATOR + "bin";
    }

    public static String getWebPath() {
        return PROFILE_HOME + FILE_SEPARATOR + "web";
    }

}
