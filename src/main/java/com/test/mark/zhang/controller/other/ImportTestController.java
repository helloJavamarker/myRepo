package com.test.mark.zhang.controller.other;

import com.google.common.base.Joiner;
import com.test.mark.zhang.service.ImportTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author by mark
 * @Classname ImportTestController
 * @Description TODO
 * @Date 2022/2/26 2:27 下午
 */
@RestController
@RequestMapping("/import/test")
public class ImportTestController {

    @Autowired
    private ImportTestService importTestService;

    @GetMapping("/test1")
    public String test1() {
        importTestService.importSimple();
        return "success";
    }


}
