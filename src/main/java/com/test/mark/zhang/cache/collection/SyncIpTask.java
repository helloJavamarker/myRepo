package com.test.mark.zhang.cache.collection;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Classname SyncIpTask
 * @Description TODO
 * @Date 2021/6/29 4:46 下午
 * @Created by mark
 */
public class SyncIpTask implements Job {

    @Resource
    private SecurityZoneHelper securityZoneHelper;
    private static int retry_count = 0;
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        run();
    }

    private void run() {
        try {
            System.out.println("run");
            securityZoneHelper.syncSecurityZoneData();

        } catch (Exception e) {
            retry_count++;
            if (retry_count > 3) {
                return;
            }
            try {
                TimeUnit.SECONDS.sleep(10);
                run();
                //重试3次
                //递归调用三次
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }
    }

    public int insert() {
//        int num = mapper.insert(security);
//        if (num > 0) {
//            try {
//                addRel(security);  //这里什么时候会失败??
//            } catch (Exception e) {
//                log.error("添加失败");
//                mapper.delete(security); //万一这一步失败!!
//            }
//        }
        return 0;
    }

    public static void main(String[] args) throws InterruptedException {
        try {
            System.out.println("run");
            int a = 10 / 0;
        } catch (Exception e) {
            e.printStackTrace();
            retry_count++;
            if (retry_count > 5) {
                return;
            }
            TimeUnit.SECONDS.sleep(3);
            main(null);
        }
    }

}
