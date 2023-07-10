package com.example.walletlink.Security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors((cors)-> cors.disable()).csrf((cs)->cs.disable());
        httpSecurity.authorizeHttpRequests((authorizeRequest)-> authorizeRequest.requestMatchers("/user").authenticated()
                .requestMatchers("/wallet").authenticated());

     return  httpSecurity.build();
    }
    @Autowired
    public void configureGlobal (AuthenticationManagerBuilder managerBuilder) throws  Exception {
        managerBuilder.inMemoryAuthentication().withUser("user").password("user").roles("USER");
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
