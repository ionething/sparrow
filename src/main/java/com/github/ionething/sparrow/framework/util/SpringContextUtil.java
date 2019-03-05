package com.github.ionething.sparrow.framework.util;

import lombok.Getter;
import org.springframework.beans.BeansException;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class SpringContextUtil implements ApplicationContextAware {

    @Getter
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext = context;
    }

    public static <T> T getBean(Class<T> requiredType) {
        return applicationContext.getBean(requiredType);
    }

    /**
     * 获取Spring boot配置
     * @param name
     * @param target
     * @param <T>
     * @return
     */
    public static <T> T getProperty(String name, Class<T> target) {
        Environment environment = applicationContext.getEnvironment();
        Binder binder = Binder.get(environment);
        return binder.bind(name, Bindable.of(target)).get();
    }
}
