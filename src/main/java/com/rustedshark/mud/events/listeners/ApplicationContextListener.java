package com.rustedshark.mud.events.listeners;

import com.vdurmont.emoji.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextListener {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationContextListener.class);

    @Value("${project.name:From Source}")
    private String projectName;

    @EventListener({ContextRefreshedEvent.class})
    void applicationStarted() {
        logger.info("{} has bootstrapped {}", projectName, EmojiParser.parseToUnicode(":beers:"));
    }
}
