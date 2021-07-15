package com.test.mark.zhang.test.other.reactor;

import com.test.mark.zhang.test.other.project.aspect.SyncMethodEnum;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author by mark
 * @Classname ReactorTest
 * @Description TODO
 * @Date 2021/7/14 2:11 下午
 */
public class ReactorTest {
    @Test
    public void test1() {
        //** 异步和同步针对调用者，调用者发送请求，如果等着对方回应之后才去做其他事情就是同步，如果发送请求之后不等着对方回应就去做其他事情就是异步
        //** 阻塞和非阻塞针对被调用者，被调用者受到请求之后，做完请求任务之后才给出反馈就是阻塞，受到请求之后马上给出反馈然后再去做事情就是非阻塞

        //just 方法直接声明
        Flux.just(1,2,3,4);
        //调用 just 或者其他方法只是声明数据流，数据流并没有发出，只有进行订阅之后才会触发数据流，不订阅什么都不会发生的
        Flux.just("zhang",1,true,"li").subscribe(System.out::println);
        Mono.just(1);
        //其他的方法
        Integer[] array = {1,2,3,4};
        Flux.fromArray(array);
        List<Integer> list = Arrays.asList(array);
        Flux.fromIterable(list);
        Stream<Integer> stream = list.stream();
        Flux.fromStream(stream);
    }
}
