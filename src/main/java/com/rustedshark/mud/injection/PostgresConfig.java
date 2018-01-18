package com.rustedshark.mud.injection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class PostgresConfig {

    @Value("${mud.postgres.host:localhost}")
    private String _host;

    @Value("${mud.postgres.port:5432}")
    private String _port;

    @Value("${mud.postgres.db_name:core}")
    private String _dbName;

    @Value("${mud.postgres.username:lostabyss}")
    private String _username;

    @Value("${mud.postgres.password:fakepassword}")
    private String _password;

    @Bean
    public DataSource postgresDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://" + _host + ":" + _port + "/" + _dbName);
        dataSource.setUsername(_username);
        dataSource.setPassword(_password);

        return dataSource;
    }
}
