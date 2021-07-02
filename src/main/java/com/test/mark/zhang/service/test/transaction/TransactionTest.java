package com.test.mark.zhang.service.test.transaction;

/**
 * @Classname TransactionTest
 * @Description 事务及事务失效
 * @Date 2021/6/17 9:45 上午
 * @Created by mark
 */
public class TransactionTest {
    /**
     * 事务失效的六种形式:https://juejin.cn/post/6844904096747503629
     * 1 私有方法
     * 2 同类调用
     * 3 被catch
     * 4 受检异常
     * 5 数据库引擎不支持
     * 6 事务传播行为
     *
     *
     * 同类调用时,事务失效    https://www.jianshu.com/p/083605986c8f
     * 方式一
     * 事务不生效的原因在于，spring基于AOP机制实现事务的管理，@Authwired StudentService studentService这样的方式，调用StudentService的方法时，
     * 实际上是通过StudentService的代理类调用StudentService的方法，代理类在执行目标方法前后，加上了事务管理的代码。
     * 因此，只有通过注入的StudentService调用事务方法，才会走代理类，才会执行事务管理；如果在同类直接调用，没走代理类，事务就无效。    本类注入自己--循环依赖
     *          这不代表spring永远没有循环依赖的问题（@Async导致循环依赖了解下）  三级缓存能解决循环依赖,但不是万能药
     * 注意：除了@Transactional，@Async同样需要代理类调用，异步才会生效
     *
     *方式二:
     * 使用AopContext获取到当前代理类，需要在启动类加上@EnableAspectJAutoProxy(exposeProxy = true)
     *
     * 方式三:通过spring上下文获取到当前代理类  推荐
     * 当一个类实现ApplicationContextAware接口后，就可以方便的获得ApplicationContext中的所有bean。
     *
     * 在业务方法中一般不需要catch异常，如果非要catch一定要抛出throw new RuntimeException()，或者注解中指定抛异常类型@Transactional(rollbackFor=Exception.class)，
     * 否则会导致事务失效，数据commit造成数据不一致，所以有些时候try catch反倒会画蛇添足。
     *
     */
}
