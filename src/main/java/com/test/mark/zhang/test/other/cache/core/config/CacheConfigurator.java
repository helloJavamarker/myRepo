package com.test.mark.zhang.test.other.cache.core.config;

import com.google.common.base.Optional;
import com.test.mark.zhang.test.other.cache.CacheLoggerHolder;
import com.test.mark.zhang.test.other.cache.core.CacheManager;
import com.test.mark.zhang.test.other.cache.core.CacheManagerFactory;
import com.test.mark.zhang.test.other.cache.em.CacheClass;
import com.test.mark.zhang.test.other.cache.em.CacheType;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.util.Asserts;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Component
public class CacheConfigurator extends YamlPropertiesFactoryBean implements CacheManagerAssembler, ApplicationContextAware, EnvironmentAware, ApplicationListener<ContextClosedEvent>, CacheLoggerHolder {
    private String cachePrefix;
    Environment environment;
    ApplicationContext applicationContext;

    public CacheConfigurator() {
        cachePrefix = "components.cache";
    }

    /*private CacheDefinition parseProps(Properties properties) throws ClassNotFoundException {
        cachePrefix = Optional.fromNullable(environment.getProperty("cachePrefix")).or(cachePrefix);
        CacheDefinition cacheDefinition = new CacheDefinition();
        //总开关
        Boolean enable = Boolean.valueOf(Optional.fromNullable(properties.get(cachePrefix + ".enable")).or(CacheDefinition.DEFAULT_ENABLE).toString());
        cacheDefinition.setEnable(enable);
        Object cacheClazz = properties.get(cachePrefix + ".clazz");
        Asserts.check(cacheClazz != null, cachePrefix + ".clazz can not be null");
        Class<?> clazz = Class.forName(CacheManager.class.getPackage().getName() + "." + cacheClazz.toString());
        Asserts.check(clazz == CacheManager.class || clazz.getSuperclass() == CacheManager.class, cachePrefix + ".clazz must be CacheManager");
        cacheDefinition.setClazz(cacheClazz.toString());
        Map<String, CacheConfigBean> map = new HashMap<>();
        CacheConfigBean cacheDef;
        CacheClass cc;
        String multiplePrefix = cachePrefix + ".multiple";
        for (Object key : properties.keySet()) {
            String propName = key.toString();
            if (propName.startsWith(multiplePrefix)) {
                String propValue = properties.getProperty(propName);
                String cacheIndex = propName.substring(0, multiplePrefix.length() + 3);
                String cacheDefValue = propName.substring(multiplePrefix.length() + 4);
                cacheDef = map.get(cacheIndex);
                if (cacheDef == null) {
                    cacheDef = new CacheConfigBean();
                    map.put(cacheIndex, cacheDef);
                }
                switch (cacheDefValue) {
                    case "name": {
                        Asserts.check(!StringUtils.isBlank(propValue), propName + " can not be empty");
                        cacheDef.setName(propValue);
                    }
                    break;
                    case "enable":
                        cacheDef.setEnable(Boolean.valueOf(Optional.of(propValue).or(CacheConfigBean.DEFAULT_ENABLE.toString())));
                        break;
                    case "local.enable": {
                        cacheDef.getLocalIfNullCreate().setEnable(Boolean.valueOf(Optional.of(propValue).or(ConfigDetail.DEFAULT_LOCAL_ENABLE.toString())));
                    }
                    break;
                    case "local.clazz": {
                        Asserts.check(!StringUtils.isBlank(propValue), propName + " can not be empty");
                        Asserts.check((cc = CacheClass.valueOf(propValue)) != null && cc.type == CacheType.LOCAL, propName + " illegal");
                        cacheDef.getLocalIfNullCreate().setClazz(propValue);
                    }
                    break;
                    case "local.args": {
                        if (!StringUtils.isBlank(propValue)) {
                            cacheDef.getLocalIfNullCreate().setArgs(propValue.split(ConfigDetail.ARG_SPLIT_MARK));
                        }
                    }
                    break;
                    case "remote.enable": {
                        cacheDef.getRemoteIfNullCreate().setEnable(Boolean.valueOf(Optional.of(propValue).or(ConfigDetail.DEFAULT_REMOTE_ENABLE.toString())));
                    }
                    break;
                    case "remote.clazz": {
                        Asserts.check(!StringUtils.isBlank(propValue), propName + " can not be empty");
                        Asserts.check((cc = CacheClass.valueOf(propValue)) != null && cc.type == CacheType.REMOTE, propName + " illegal");
                        cacheDef.getRemoteIfNullCreate().setClazz(propValue);
                    }
                    break;
                    case "remote.args": {
                        if (!StringUtils.isBlank(propValue)) {
                            cacheDef.getRemoteIfNullCreate().setArgs(propValue.split(ConfigDetail.ARG_SPLIT_MARK));
                        }
                    }
                    default:
                        //
                    break;
                }
            }
        }
        cacheDefinition.setMultiple(map.values().toArray(new CacheConfigBean[map.size()]));
        return cacheDefinition;
    }*/

    @Override
    public void afterPropertiesSet() {
        Resource resource = new ClassPathResource("/cache.yml");
        if (!resource.exists()) {
            resource = new ClassPathResource("/config/cache.yml");
        }
        if (!resource.exists()) {
            resource = new ClassPathResource("/application.yml");
        }
        if (!resource.exists()) {
            resource = new ClassPathResource("/config/application.yml");
        }
        if (resource.exists()) {
            setResources(resource);
        }
        super.afterPropertiesSet();
        Properties sourceProps = getObject();
        Properties properties = new Properties();
        for (Object propName : sourceProps.keySet()) {
            properties.put(propName, environment.getProperty(propName.toString()));
//            properties.put(propName, "document");
        }
//        try {
//            assemble(parseProps(properties), applicationContext);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException("XCache init error!", e);
//        }
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        CacheManagerFactory.shutdown();
    }
}
