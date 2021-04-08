package com.switchfully.eurder.infrastructure.security.config;

import com.switchfully.eurder.infrastructure.security.OrderAuthenticationProvider;
import com.switchfully.eurder.infrastructure.security.jwt.JwtAuthenticationFilter;
import com.switchfully.eurder.infrastructure.security.jwt.JwtAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String CUSTOMER_CONTROLLER_PATH = "/customer";

    private final OrderAuthenticationProvider armyAuthenticationProvider;

    @Autowired
    public SecurityConfig(OrderAuthenticationProvider armyAuthenticationProvider) {
        this.armyAuthenticationProvider = armyAuthenticationProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, CUSTOMER_CONTROLLER_PATH).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtAuthorizationFilter(authenticationManager()))
                .sessionManagement().sessionCreationPolicy(STATELESS);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(armyAuthenticationProvider);
    }

}
