package com.rustedshark.mud.injection;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean(BeanNames.TEST)
    public String thing() {
        return "Cool";
    }
}
