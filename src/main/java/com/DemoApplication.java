package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DemoApplication   /*implements CommandLineRunner*/ {

    /*@Autowired
    private ApplicationContext appCtx;

    @Autowired
    private StringEncryptor codeSheepEncryptorBean;*/

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    /*@Override
    public void run(String... args) throws Exception {
        Environment environment = appCtx.getBean(Environment.class);

        System.out.println("password:123456,after encrity:" + encrypt("123456"));
        System.out.println("decrypt: "+ decrypt("drsrj+7uDSJkkP6IVU76pnPw80X3UJBZD/KB9Te3N4GJfm/vLSlEmTOKYWa2ekPD"));
        //这里假如加密后的字符串没有找到,项目启动不了
        //System.out.println("decrypt: "+ decrypt("I91fA6B4DdUJ3B1ofEc1w/+cEpN1XKLo/u56si76T1NfJ2B1IA7JC2/75+nrAYQi"));

        // 首先获取配置文件里的原始明文信息
        String mysqlOriginPswd = environment.getProperty("spring.datasource.password");
        String redisOriginPswd = environment.getProperty("redis.password");
        String aliSmsOriginAk = environment.getProperty("ali.sms.access_key_secret");

        // 加密
        String mysqlEncryptedPswd = encrypt( mysqlOriginPswd );
        String redisEncryptedPswd = encrypt( redisOriginPswd );
        String aliSmsEncryptedAk = encrypt( aliSmsOriginAk );

        // 打印加密前后的结果对比
        System.out.println( "MySQL原始明文密码为：" + mysqlOriginPswd );
        System.out.println( "Redis原始明文密码为：" + redisOriginPswd );
        System.out.println( "阿里云SMS原始AccessKey密码为：" + aliSmsOriginAk );
        System.out.println( "====================================" );
        System.out.println( "MySQL原始明文密码加密后的结果为：" + mysqlEncryptedPswd );
        System.out.println( "Redis原始明文密码加密后的结果为：" + redisEncryptedPswd );
        System.out.println( "阿里云SMS原始AccessKey密码加密后的结果为：" + aliSmsEncryptedAk );
    }

    private String encrypt( String originPassord ) {
        String encryptStr = codeSheepEncryptorBean.encrypt( originPassord );
        return encryptStr;
    }

    private String decrypt( String encryptedPassword ) {
        String decryptStr = codeSheepEncryptorBean.decrypt( encryptedPassword );
        return decryptStr;
    }*/
}
