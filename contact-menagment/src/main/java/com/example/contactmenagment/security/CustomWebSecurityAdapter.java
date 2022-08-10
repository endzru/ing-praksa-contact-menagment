package com.example.contactmenagment.security;

import com.example.contactmenagment.security.customUserService.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

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
                .antMatchers("/users")
                .hasRole("ADMIN").anyRequest().authenticated().and().httpBasic();

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
//    @Bean
//    public InMemoryUserDetailsManager  userDetailsService(){
//        UserDetails userDetails = User.builder().username("user").password(getPasswordEncoder().encode("pass")).roles("ADMIN").build();
//        System.out.println(userDetails);
//        return new InMemoryUserDetailsManager(userDetails);
//    }
}
