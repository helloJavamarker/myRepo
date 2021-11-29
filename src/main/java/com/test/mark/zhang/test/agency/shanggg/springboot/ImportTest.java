package com.test.mark.zhang.test.agency.shanggg.springboot;

import ch.qos.logback.core.db.DBHelper;
import com.test.mark.zhang.test.agency.shanggg.springboot.bean.Person;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;

/**
 * @author by mark
 * @Classname ImportTest
 * @Description TODO
 * @Date 2021/11/27 8:33 下午
 */
@Import({Person.class, DBHelper.class})
@Controller
public class ImportTest {
}
