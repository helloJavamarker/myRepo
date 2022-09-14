package com.test.mark.zhang.test.other.project.spring.context;

import org.apache.commons.lang3.StringUtils;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * @author by mark
 * @Classname SpringContextTest
 * @Description TODO
 * @Date 2022/9/14 2:47 下午
 */
public class SpringContextTest {
    public static void main(String[] args) {
        String[] fields = StringUtils.split("a,b,c,d");
        StandardEvaluationContext context = new StandardEvaluationContext();
        for (String field : fields) {
            context.setVariable(field,  "#" + field + "=='" + field +field + "'");
        }

        SpelExpressionParser parser = new SpelExpressionParser();
        for (String field : fields) {
            System.out.println(parser.parseExpression("#a=='AA'"));
            System.out.println(parser.parseExpression("a='AA'"));
            System.out.println(parser.parseExpression("a=='AA'"));
        }

    }
}
