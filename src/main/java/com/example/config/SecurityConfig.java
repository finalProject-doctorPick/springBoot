package com.example.config;

import java.util.Arrays;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.domain.Files;
import com.example.security.jwt.exception.CustomAuthenticationEntryPoint;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationManagerConfig authenticationManagerConfig;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .formLogin().disable()
            .csrf().disable()
            .apply(authenticationManagerConfig)
            .and()
            .httpBasic().disable()
            .authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .antMatchers("/users/signup", "/users/login", "/users/refreshToken", "/users/mailConfirm", "/users/smsSend").permitAll()
                .antMatchers(HttpMethod.GET, "/**").hasAnyRole("USER", "ADMIN", "DOCTOR", "DRUGSTORE")
                .antMatchers(HttpMethod.POST, "/**").hasAnyRole("USER", "ADMIN", "DOCTOR", "DRUGSTORE")
                .anyRequest().hasAnyRole("USER", "ADMIN", "DOCTOR", "DRUGSTORE")
            .and()
            .exceptionHandling()
                .authenticationEntryPoint(customAuthenticationEntryPoint)
            .and()
            .cors()
            .and()
            .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.addAllowedMethod("*");
        config.setAllowedMethods(Arrays.asList("GET","POST","DELETE","PATCH","OPTIONS","PUT"));
        config.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));
        source.registerCorsConfiguration("/**", config);
        
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public Files files() {
        return new Files();
    }
    
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
