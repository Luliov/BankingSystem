package com.example.BankingSystem.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration {

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConf) throws Exception {
        return authConf.getAuthenticationManager();
    }


    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.httpBasic();

        httpSecurity.authorizeRequests()
                .mvcMatchers(HttpMethod.POST,"/create-third-party").hasAnyRole("ADMIN")
                .mvcMatchers(HttpMethod.POST,"/create-account-holder").hasAnyRole("ADMIN")
                .mvcMatchers(HttpMethod.POST,"/create-checking-account").hasAnyRole("ADMIN")
                .mvcMatchers(HttpMethod.POST,"/create-savings-account").hasAnyRole("ADMIN")
                .mvcMatchers(HttpMethod.POST,"/create-credit-card-account").hasAnyRole("ADMIN")
                .mvcMatchers(HttpMethod.POST,"/set-balance").hasAnyRole("ADMIN")
                .mvcMatchers(HttpMethod.POST,"/delete-account").hasAnyRole("ADMIN")
                .mvcMatchers(HttpMethod.POST,"/show-balance").hasAnyRole("ACCOUNTHOLDER")
                .mvcMatchers(HttpMethod.POST,"/wire-transfer").hasAnyRole("ACCOUNTHOLDER")
                .mvcMatchers(HttpMethod.POST,"/wire-transfer-third-party").hasAnyRole("THIRDPARTY")

                .anyRequest().permitAll();

        httpSecurity.csrf().disable();

        return httpSecurity.build();

    }

}
