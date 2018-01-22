package com.rustedshark.mud.configs;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * Mud Postgres binding w/Hikari support
 */
@Configuration
public class PostgresConfig {

    private static final HikariConfig hikariConfig = new HikariConfig();

    @Value("${mud.postgres.host:localhost}")
    private String _host;

    @Value("${mud.postgres.port:5432}")
    private String _port;

    @Value("${mud.postgres.db_name:core}")
    private String _dbName;

    @Value("${mud.postgres.username:lostabyss}")
    private String _username;

    @Value("${mud.postgres.password:@Cheese88}")
    private String _password;

    private HikariDataSource _datasource;

    @Bean(name = "dataSource")
    public DataSource postgresDataSource() {
        String jdbcUrl = "jdbc:postgresql://" + _host + ":" + _port + "/" + _dbName;

        hikariConfig.setJdbcUrl(jdbcUrl);
        hikariConfig.setUsername(_username);
        hikariConfig.setPassword(_password);
        hikariConfig.setDriverClassName("org.postgresql.Driver");

        _datasource = new HikariDataSource(hikariConfig);

        return _datasource;
    }
}
