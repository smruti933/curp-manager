package io.mosip.curp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/v1/curp-manager/api/curp-bio-data/**").permitAll()
                .antMatchers(HttpMethod.GET, "/v1/curp-manager/api/curp-bio-data/**").permitAll()
                .antMatchers(HttpMethod.GET, "/v1/curp-manager/actuator/health").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();
    }
}
