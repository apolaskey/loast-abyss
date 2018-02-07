package com.rustedshark.mud.injection;

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class RuntimeInjector {

    @Inject
    private AutowiredAnnotationBeanPostProcessor _aaProcessor;

    public <T> T bind(T instance) {
        _aaProcessor.processInjection(instance);
        return instance;
    }
}
