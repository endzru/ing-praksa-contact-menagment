package com.example.contactmenagment.security;

import com.example.contactmenagment.security.customUserService.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class CustomWebSecurityAdapter {

    @Autowired
    private final CustomUserDetailsService usersDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/users").anonymous()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/users").hasRole("ADMIN")
                .antMatchers("/contacts").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/users/**").hasRole("ADMIN")
                .and()
                .httpBasic();
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(getPasswordEncoder());
        authenticationProvider.setUserDetailsService(usersDetailsService);

        return authenticationProvider;
    }
    @Bean
    public boolean isAuthenticated(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return false;
        }
        //System.out.println(authentication.getName());
        return true;
    }


}
