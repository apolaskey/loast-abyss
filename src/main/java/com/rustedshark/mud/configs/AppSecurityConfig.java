package com.rustedshark.mud.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${mud.admin.user:admin}")
    private String _adminUser;

    @Value("${mud.admin.password:admin}")
    private String _adminPassword;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        BasicAuthenticationEntryPoint authEntry = new BasicAuthenticationEntryPoint();
        authEntry.setRealmName("LostAbyss");

        http.authorizeRequests()
                .antMatchers("/", "/game", "/health", "/info").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic().authenticationEntryPoint(authEntry);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser(_adminUser).password(_adminPassword).roles("USER, ADMIN");
    }
}
