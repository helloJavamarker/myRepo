package com.test.mark.zhang.test.other.cache.aspect;

import com.test.mark.zhang.test.other.cache.annotation.LCache;
import com.test.mark.zhang.test.other.cache.annotation.RCache;
import com.test.mark.zhang.test.other.cache.annotation.XCache;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 原类型分析以及key生成
 *
 * @author bailey
 * @version 1.0
 * @date 2017-06-22 14:27
 */
public abstract class EnhancingResolver implements CacheEnhancer/*, CacheLoggerHolder*/ {
    private static final String KEY_SIGN = "_";
    private Map<String, AnnoBean> gmMap = new HashMap<>();
    private Map<String, List<AnnoBean>> rmMap = new HashMap<>();

    public EnhancingResolver(Class<?> clazz) {
        AnnoBean xAnnoBean = null;
        XCache xCache = clazz.getDeclaredAnnotation(XCache.class);
        if (xCache != null) {
            xAnnoBean = AnnoBean.toAnnoBean(xCache, null);
        }
        for (Method m : clazz.getDeclaredMethods()) {
            // 无返回值的方法不缓存
            if (m.getReturnType() == Void.TYPE) {
                continue;
            }

            RCache rCache = m.getDeclaredAnnotation(RCache.class);
            if (rCache != null) {
                AnnoBean ab = AnnoBean.toAnnoBean(rCache, xAnnoBean);
                /** 以方法的详细描述为key，匹配唯一方法 */

                //public java.lang.String com.test.mark.zhang.entity.Person.getHobby()
                gmMap.put(m.toGenericString(), ab);
                saveRemoveMethods(ab);
                // 一个方法只解析一种缓存；RCache优先
                continue;
            }
            LCache lCache = m.getDeclaredAnnotation(LCache.class);
            if (lCache != null) {
                AnnoBean ab = AnnoBean.toAnnoBean(lCache, xAnnoBean);
                gmMap.put(m.toGenericString(), ab);
                saveRemoveMethods(ab);
            }
        }
    }

    private void saveRemoveMethods(AnnoBean ab) {
        if (ab.remove != null && ab.remove.length > 0) {
            for (String methodName : ab.remove) {
                if (!StringUtils.isBlank(methodName)) {
                    /** 一个remove方法可匹配多个缓存方法 */
                    List<AnnoBean> abColl = rmMap.get(methodName);
                    if (abColl == null) {
                        abColl = new ArrayList<>();
                    }
                    abColl.add(ab);
                    /** 以方法的名称为key，匹配所有重载方法 */
                    rmMap.put(methodName, abColl);
                }
            }
        }
    }

    protected AnnoBean annoInfo4get(String methodGenericString) {
        return gmMap.get(methodGenericString);
    }

    protected List<AnnoBean> annoInfo4remove(String methodName) {
        return rmMap.get(methodName);
    }

    protected boolean isGetCache(String methodGenericString) {
        return gmMap.containsKey(methodGenericString);
    }

    protected boolean isRemoveCache(String methodName) {
        return rmMap.containsKey(methodName);
    }

    /**
     * key生成规则；三种情况：<br/>
     * 1.未指定key，则以方法的第一个参数dump成字符串作为key <br/>
     * 2.SpEL，表达式引用参数名称正确则解析，否则将表达式以字符串形式作为key；若多个参数名称相同，则取第一个参数参与表达式运算<br/>
     * 3.字符串形式，直接作为key
     *
     * @param annoBean
     * @param params
     * @return
     */
    protected static Object renderKey(AnnoBean annoBean, String[] paramName, Object[] params) {
        Object key = null;
        if (StringUtils.isBlank(annoBean.key)) {/** 未指定key则以第一个参数作为key */
            //key = ArrayUtils.isEmpty(params) ? null : BeanUtils.dump(params[0]);
            key = "ArrayUtils.isEmpty(params) ? null : BeanUtils.dump(params[0]);";
        } else {/** 不能解析为SpEL的则以字符串形式作为key */
            if (!ArrayUtils.isEmpty(params)) {
                boolean hasMatched = false;
                Collection<String> existed = new ArrayList<>();
                ExpressionParser parser = new SpelExpressionParser();
                StandardEvaluationContext context = new StandardEvaluationContext();
                for (int i = 0; i < paramName.length; i++) {
                    // 以第一个匹配到的参数为key,若无匹配项则返回null
                    if (!existed.contains(paramName[i])) {
                        existed.add(paramName[i]);
                        if (annoBean.key.contains(paramName[i])) {
                            context.setVariable(paramName[i], params[i]);
                            hasMatched = true;
                        }
                    }
                }
                try {
                    key = hasMatched ? parser.parseExpression(annoBean.key).getValue(context, Object.class) : null;
                } catch (Exception e) {
                    //LOGGER.error("EnhancingResolver.renderKey error !", e);
                }
            }
            key = key == null ? annoBean.key : key;
        }
        return key == null ? null
                : new StringBuilder().append(annoBean.prefix).append(StringUtils.isNotBlank(annoBean.prefix) ? KEY_SIGN : StringUtils.EMPTY).append(key)
                .append(StringUtils.isNotBlank(annoBean.suffix) ? KEY_SIGN : StringUtils.EMPTY).append(annoBean.suffix).toString();
    }

}
