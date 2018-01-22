package com.rustedshark.mud.injection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class RuntimeInjector {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private AutowiredAnnotationBeanPostProcessor _aaProcessor;

    public <T> T bind(T instance) {
        _aaProcessor.processInjection(instance);
        return instance;
    }
}
