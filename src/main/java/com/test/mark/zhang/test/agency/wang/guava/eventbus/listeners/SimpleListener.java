package com.test.mark.zhang.test.agency.wang.guava.eventbus.listeners;

import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/***************************************
 * @author:Alex Wang
 * @Date:2017/10/18
 * 532500648
 ***************************************/
public class SimpleListener {
    private final static Logger LOGGER = LoggerFactory.getLogger(SimpleListener.class);

    @Subscribe
    public void doAction(final String event) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Received event [{}] and will take a action", event);
        }
    }

    @Subscribe
    public void doAction1(final String event) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Received event [{}] and will take a action3", event);
        }
    }

    @Subscribe
    public void doAction2(final String event) {

        try {
            TimeUnit.MINUTES.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Received event [{}] and will take a action2", event);
        }
    }

    @Subscribe
    public void doAction3(final String event) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Received event [{}] and will take a action1", event);
        }
    }

    @Subscribe
    public void doAction4(final String event) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Received event [{}] and will take a action4", event);
        }
    }

    /**
     * 我们考虑下面几段代码：
     *
     * 代码清单一：
     *
     * logger.debug("hello, world")
     * 代码清单二：
     *
     * if(logger.isDebugEnabled()){
     *     logger.debug("hello, world")
     * }
     * 代码清单三：
     *
     * logger.debug("hello" + "world");
     * 代码清单四：
     *
     * logger.debug(String.format("hello, %s", "world"))
     * 代码清单五：
     *
     * logger.debug("hello {}", "world");
     * 以上5段代码，如果应用日志级别为info，那么这5段代码都不会输出日志。它们的差异其实是性能不同。下面我们来逐个分析。
     *
     * 代码清单一：执行debug方法。debug方法内部判断日志级别，然后退出。
     * 代码清单二：isDebugEnabled判断日志级别，然后退出。
     * 代码清单三：先拼接字符串“hello”和“world”。然后执行debug方法。
     * 代码清单四：先执行String.format方法，然后执行debug方法。
     * 代码清单五：执行debug方法。
     * 大家发现了吗？虽然最终都不会输出日志，但这5段代码还是有差异的。代码清单三和代码清单四分别执行了"+"字符串拼接和String.format方法，但最终却没用到。也就是说，这两段代码执行了一些无用操作，造成了额外的性能损耗。
     *
     * 所以，代码清单二中添加isDebugEnabled可以避免无用的字符串操作，提高性能。
     * logger.debug("hello, world")这种不需要判断
     *
     * isDebugEnabled最佳实践
     * 原则一：如果打印字符串常量，不需要isDebugEnabled
     * 比较下面两段代码：
     *
     * logger.debug("hello, world");
     * if(logger.isDebugEnabled()){
     *     logger.debug("hello, world");
     * }
     * 因为打印的日志是字面常量，没有计算逻辑。两段代码的性能是几乎一样的。添加isDebugEnabled反而会导致额外的代码。
     *
     * 原则二：如果有参数，且参数只是字符串常量或计算简单，使用占位符
     * 考虑如下代码，debug方法包含了参数user.getName()。虽然执行debug方法时，会计算user.getName()，但只是一个简单的get方法，没有复杂计算，这时候，也可以不添加isDebugEnabled。
     *
     * logger.debug("hello, {}", user.getName());
     * 原则三：如果有参数，且参数计算复杂，添加isDebugEnabled
     * logger.debug("order price: {}", calculatePrice());
     * 假设calculatePrice方法需要经过复杂计算。那么就应该添加isDebugEnabled判断，使用如下的代码：
     *
     * if(logger.isDebugEnabled()){
     *     logger.debug("order price: {}", calculatePrice());
     * }
     */
}
