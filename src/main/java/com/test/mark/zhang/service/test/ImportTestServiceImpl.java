package com.test.mark.zhang.service.test;

import com.test.mark.zhang.entity.ImportTestBean;
import com.test.mark.zhang.mapper.ImportTestMapper;
import com.test.mark.zhang.service.ImportTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by mark
 * @Classname ImportTestService
 * @Description TODO
 * @Date 2022/2/26 2:30 下午
 */
@Service
public class ImportTestServiceImpl  implements ImportTestService {

    @Autowired
    private ImportTestMapper importTestMapper;

    private List<ImportTestBean> list = new ArrayList<>();

    @Override
    public void importSimple() {
        long startTime = System.currentTimeMillis();
        addData(50_0000);
        list.forEach(importTestBean -> {
            importTestMapper.importFirst(importTestBean);
        });
        long endTime = System.currentTimeMillis();
        System.out.println("cost time:" + (endTime - startTime));
    }

    private void addData(int num) {
        for (int i = 0; i < num; i++) {
            ImportTestBean importTestBean = new ImportTestBean();
            importTestBean.setId(i);
            importTestBean.setName("zhangsan" + i);
            importTestBean.setAge(Math.min(i, 200));
            importTestBean.setHeight(Math.min(Math.max(i, 20), 200));

            importTestBean.setHobby("hobby"+ i);
            importTestBean.setMoney(i);
            importTestBean.setPlay("play" +i);
            list.add(importTestBean);
        }
    }

}
