package com.test.mark.zhang.controller.es;

import com.test.mark.zhang.entity.es.TestBean;
import com.test.mark.zhang.test.es.EsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/testes")
public class TestController {

    @Autowired
    private EsService esService;

    @RequestMapping("/findAll")
    public Iterable<TestBean> findAll() {

        return esService.findAll();
    }

    @RequestMapping("/list")
    public String save() {
        List<TestBean> list = null;
        esService.save(list);
        return "success";
    }

    @RequestMapping("save")
    public void save(TestBean bean) {
        esService.save(bean);
    }

    @RequestMapping("findByName")
    public List<TestBean> findByName(String name) {
        return esService.findByName(name);
    }

    @RequestMapping("findByNameOrDesc")
    public List<TestBean> findByNameOrDesc(@RequestParam(value = "name",required = false) String name,
                                           @RequestParam(value = "desc",required = false) String desc) {
        return esService.findByNameOrDesc(name, desc);
    }

}

