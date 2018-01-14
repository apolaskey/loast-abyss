package com.rustedshark.mud.processors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

@Component
public class LoggerPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        // Fetch a logger type for our application
        Logger logger = LoggerFactory.getLogger(bean.getClass());

        ReflectionUtils.doWithFields(bean.getClass(), field -> {
            // Inject the logger
            ReflectionUtils.makeAccessible(field);
            ReflectionUtils.setField(field, bean, logger);

            // Only if it matches our logger type
        }, field -> field.isAnnotationPresent(Autowired.class) && Logger.class.equals(field.getType()));

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
