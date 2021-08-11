package com.test.mark.zhang.test.other.project.security;

import lombok.EqualsAndHashCode;
import org.springframework.context.ApplicationEvent;

/**
 * @Classname SecurityZoneEvent
 * @Description TODO
 * @Date 2021/6/25 3:11 下午
 * @Created by mark
 */
@EqualsAndHashCode(of = {"action"}, callSuper = false)
public class SecurityZoneEvent extends ApplicationEvent {

    public SecurityZoneEvent(Object source,Action action, SecurityZone securityZone) {
        super(source);
        this.action = action;
        this.securityZone = securityZone;
    }

    private Action action;
    private SecurityZone securityZone;

    /**
     * Enum的特征如下：
     * 1.它不能有public的构造函数，这样做可以保证客户代码没有办法新建一个enum的实例。
     * 2.所有枚举值都是public , static , final的。注意这一点只是针对于枚举值，我们可以和在普通类里面定义变量一样定义其它任何类型的非枚举变量，这些变量可以用任何你想用的修饰符。
     * 3．Enum默认实现了java.lang.Comparable接口。
     * 4．Enum覆载了了toString方法，因此我们如果调用Color.Blue.toString()默认返回字符串”Blue”.
     * 5．Enum提供了一个valueOf方法，这个方法和toString方法是相对应的。调用valueOf(“Blue”)将返回 Color.Blue.因此我们在自己重写toString方法的时候就要注意到这一点，一把来说应该相对应地重写valueOf方法。
     * 6．Enum还提供了values方法，这个方法使你能够方便的遍历所有的枚举值。
     * 7．Enum还有一个oridinal的方法，这个方法返回枚举值在枚举类种的顺序，这个顺序根据枚举值声明的顺序而定，这里Color.Red.ordinal()返回0。
     */
    public static enum Action {
        add,update,delete,sync
    }

}
