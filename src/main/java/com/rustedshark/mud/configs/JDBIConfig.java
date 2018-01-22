package com.rustedshark.mud.configs;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.sql.DataSource;

/**
 * JBDI Binding, wraps around any generic data source bean
 */
@DependsOn("dataSource")
@Configuration
public class JDBIConfig {

    private static final Logger logger = LoggerFactory.getLogger(JDBIConfig.class);

    private final Jdbi _jdbi;

    @Autowired public JDBIConfig(DataSource dataSource) {
        logger.info("Assigned {} to JBDI client", dataSource.getClass().getSimpleName());
        _jdbi = Jdbi.create(dataSource);
        _jdbi.installPlugin(new SqlObjectPlugin());
    }

    @Bean(name = "jbdiClient")
    public Jdbi jdbi() {
        return _jdbi;
    }

}
