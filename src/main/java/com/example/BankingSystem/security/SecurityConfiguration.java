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
                .mvcMatchers(HttpMethod.GET, "/users").hasAnyRole("USER")
                .mvcMatchers(HttpMethod.GET,"/admin/**").hasRole("ADMIN")
                .anyRequest().permitAll();

        //.mvcMatchers(HttpMethod.GET, "/users").hasAnyRole("HOLDER", "ADMIN")
        // .mvcMatchers(HttpMethod.GET,"/admin/**").hasAnyRole("ADMIN")
        // .mvcMatchers(HttpMethod.POST,"/holder").hasAnyRole("ADMIN")
        // poner tipo de lamada a validar (post), ruta crear account holder y que esa ruta solo
        // la ejecute un admin
        // .mvcMatchers(HttpMethod.GET,"/modify-password").hasAnyRole("USER","ADMIN")
        // .mvcMatchers(HttpMethod.PATCH,"/modify-password").hasAnyRole("USER","ADMIN")

        httpSecurity.csrf().disable();

        return httpSecurity.build();

    }

}
