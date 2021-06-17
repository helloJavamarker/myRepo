package com.test.mark.zhang.test.other.jmh;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @Classname StringConnectTest
 * @Description TODO
 * @Date 2021/6/10 3:33 下午
 * @Created by mark
 */
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 5, time = 5)
@Threads(4)
@Fork(1)
@State(value = Scope.Benchmark)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class StringConnectTest {

    /**
     * 文章見:https://www.cnblogs.com/wupeixuan/p/13091381.html
     * if 快还是 switch 快？HashMap 的初始化 size 要不要指定，指定之后性能可以提高多少？各种序列化方法哪个耗时更短？
     * JMH 比较典型的应用场景如下：
     *
     * 想准确地知道某个方法需要执行多长时间，以及执行时间和输入之间的相关性
     * 对比接口不同实现在给定条件下的吞吐量
     * 查看多少百分比的请求在多长时间内完成
     */
    @Param(value = {"10", "50", "100"})
    private int length;

    @Benchmark
    public void testStringAdd(Blackhole blackhole) {
        String a = "";
        for (int i = 0; i < length; i++) {
            a += i;
        }
        blackhole.consume(a);
    }

    @Benchmark
    public void testStringBuilderAdd(Blackhole blackhole) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(i);
        }
        blackhole.consume(sb.toString());
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(StringConnectTest.class.getSimpleName())
                .result("result.json")
                .resultFormat(ResultFormatType.JSON).build();
        new Runner(opt).run();
    }

    /**
     * 結果分析
     * 结果表明，在拼接字符次数越多的情况下，StringBuilder.append() 的性能就更好
     *Benchmark                               (length)  Mode  Cnt     Score     Error  Units
     * StringConnectTest.testStringAdd               10  avgt    5   181.154 ±   8.253  ns/op
     * StringConnectTest.testStringAdd               50  avgt    5  2093.726 ± 961.588  ns/op
     * StringConnectTest.testStringAdd              100  avgt    5  7055.147 ± 794.055  ns/op
     * StringConnectTest.testStringBuilderAdd        10  avgt    5   119.485 ±  91.938  ns/op
     * StringConnectTest.testStringBuilderAdd        50  avgt    5   741.962 ± 149.443  ns/op
     * StringConnectTest.testStringBuilderAdd       100  avgt    5  1327.010 ± 165.889  ns/op
     *
     * 在拼接字符次数越多的情况下，StringBuilder.append() 的性能就更好。
     */
}
