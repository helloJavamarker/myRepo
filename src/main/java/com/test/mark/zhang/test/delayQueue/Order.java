package com.test.mark.zhang.test.delayQueue;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * JDK 中提供了一组实现延迟队列的API，位于Java.util.concurrent包下DelayQueue。
 *
 * <p>DelayQueue是一个BlockingQueue（无界阻塞）队列，它本质就是封装了一个PriorityQueue（优先队列），PriorityQueue内部使用完全二叉堆（不知道的自行了解哈）
 * 来实现队列元素排序，我们在向DelayQueue队列中添加元素时，会给元素一个Delay（延迟时间）作为排序条件，队列中最小的元素会优先放在队首。
 * 队列中的元素只有到了Delay时间才允许从队列中取出。队列中可以放基本数据类型或自定义实体类，在存放基本数据类型时，优先队列中元素默认升序排列，
 * 自定义实体类就需要我们根据类属性值比较计算了。
 *
 * <p>先简单实现一下看看效果，添加三个order入队DelayQueue，分别设置订单在当前时间的5秒、10秒、15秒后取消。
 *
 * 要实现DelayQueue延时队列，队中元素要implements Delayed 接口，这哥接口里只有一个getDelay方法，用于设置延期时间。Order类中compareTo方法负责对队列中的元素进行排序。
 */
public class Order implements Delayed {
    /**
     * 延迟时间
     */
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private long time;
    String name;

    public Order(String name, long time, TimeUnit unit) {
        this.name = name;
        this.time = System.currentTimeMillis() + (time > 0 ? unit.toMillis(time) : 0);
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return time - System.currentTimeMillis();
    }
    @Override
    public int compareTo(Delayed o) {
        Order Order = (Order) o;
        long diff = this.time - Order.time;
        if (diff <= 0) {
            return -1;
        } else {
            return 1;
        }
    }
}