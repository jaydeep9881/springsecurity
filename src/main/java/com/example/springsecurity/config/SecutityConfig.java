package com.example.springsecurity.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;




@Configuration
@EnableWebSecurity
public class SecutityConfig {

    @Autowired
    private UserDetailsService userDetailsService;
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider autho = new  DaoAuthenticationProvider();
        autho.setUserDetailsService(userDetailsService);
        autho.setPasswordEncoder(new BCryptPasswordEncoder(12));
        return autho;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(customizer ->customizer.disable())
        .authorizeHttpRequests(request ->request
                .requestMatchers("/register","login")
                .permitAll()
                .anyRequest()
                .authenticated())
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();

    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
//@Bean
//    public UserDetailsService userDetailsService(){
//        UserDetails user= User
//                .withDefaultPasswordEncoder()
//                .username("user")
//                .password("user@123")
//                .roles("USER")
//                .build();
//    System.out.println(user.getPassword());
//    UserDetails admin =User.
//            withDefaultPasswordEncoder()
//            .username("admin")
//            .password("admin@123")
//            .roles("ADMIN")
//            .build();
//        return new InMemoryUserDetailsManager(user,admin);
//    }
}
