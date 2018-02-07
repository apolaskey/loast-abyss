package com.rustedshark.mud.configs;

import com.rustedshark.mud.injection.BeanNames;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

/**
 * General application bindings
 */
@Configuration
public class LocalConfig {
    @Bean(BeanNames.TEST)
    public String thing() {
        return "Cool";
    }

    public LocalConfig() {
        // Regardless of what, use UTC
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }
}