package com.test.mark.zhang.test.agency.shanggg.spring.bean.ab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author by mark
 * @Classname HelloController
 * @Description 可以看到，这样的代码看起来十分不优雅，并且如果AB测试的功能点很多的话，那项目中就会充斥着大量的这种重复性分支判断，看到代码就想死有木有！！！
 * 维护代码也将会是个噩梦。比如某个功能点AB测试完毕，需要把全部功能切换到V2版本，V1版本不再需要维护，那么处理方式有两种：
 *      把A版本代码留着不管：这将会导致到处都是垃圾代码从而造成代码臃肿难以维护
 *      找到所有V1版本被调用的地方然后把相关分支删掉：这很容易在处理代码的时候删错代码从而造成生产事故
 * @Date 2021/7/13 7:49 下午
 */
@RestController
public class HelloController {

    @Autowired
    private HelloServiceV1 helloServiceV1;
    @Autowired
    private HelloServiceV2 helloServiceV2;

    @GetMapping("v1")
    public void sayHello(@RequestParam("helloVersion") String helloVersion){
        if(helloVersion.equals("A")){
            helloServiceV1.sayHello();
        }else{
            helloServiceV2.sayHello();
        }
    }

    @GetMapping("v2")
    public void sayHi(@RequestParam("hiVersion") String hiVersion){
        if(hiVersion.equals("A")){
            helloServiceV1.sayHi();
        }else{
            helloServiceV2.sayHi();
        }
    }


}
