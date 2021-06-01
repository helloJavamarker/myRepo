package com.example.demo.com.test.test.delayQueue.priority;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 优先队列不是根据先到先服务的模式来执行任务,而是根据优先级来执行
 * PriorityQueue和Queue的区别在于，它的出队顺序与元素的优先级有关，对PriorityQueue调用remove()或poll()方法，返回的总是优先级最高的元素。---插队
 *
 * 原先已经有A1,A2,A3三个人在排队,这时插进来V1,要先进行业务处理---优先队列
 */
public class PriorityQueueTest {
    public static void main(String[] args) {
        Queue<String> priorityQueue = new PriorityQueue<>();
        priorityQueue.offer("zhang");
        priorityQueue.offer("san");
        priorityQueue.offer("li");
        priorityQueue.offer("si");

        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue.poll());
        // li
        // san
        // si
        // zhang  前面数据是乱序的===默认排序
        //null 最后是null,没有数据了


        Queue<User> q = new PriorityQueue<>(new UserComparator());
        // 添加3个元素到队列:    穿插offer和poll更符合
        q.offer(new User("Bob", "A1"));
        q.offer(new User("Alice", "A2"));
        q.offer(new User("Boss", "V1"));
        System.out.println(q.poll()); // Boss/V1
        System.out.println(q.poll()); // Bob/A1
        System.out.println(q.poll()); // Alice/A2
        System.out.println(q.poll()); // null,因为队列为空


        q.offer(new User("Bob", "A1"));
        q.offer(new User("Alice", "A2"));
        q.offer(new User("Boss2", "V2"));
        System.out.println(q.poll());
        q.offer(new User("Alice3", "A3"));
        System.out.println(q.poll());
        q.offer(new User("Boss1", "V1"));
        q.offer(new User("Alice4", "A4"));
        q.offer(new User("Boss3", "V3"));
        System.out.println(q.poll());
        System.out.println(q.poll());

  }
}
