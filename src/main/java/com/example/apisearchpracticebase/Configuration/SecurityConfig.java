package com.example.apisearchpracticebase.Configuration;

import com.example.apisearchpracticebase.Security.JWTConfigurer;
import com.example.apisearchpracticebase.Security.JWTProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig{
    private final JWTProvider jwtProvider;

    public SecurityConfig(JWTProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .requestMatchers("/server/**").permitAll()
                .requestMatchers("/authentication/**").permitAll()
                .requestMatchers("/student/**").hasRole("STUDENT")
                .requestMatchers("/images/**").hasRole("STUDENT")
                .requestMatchers("/requests/**").hasRole("STUDENT")
                .requestMatchers("/visitLog/**").hasRole("STUDENT")
                .anyRequest().authenticated()
                .and()
                .apply(new JWTConfigurer(jwtProvider));
        return http.build();
    }
}
