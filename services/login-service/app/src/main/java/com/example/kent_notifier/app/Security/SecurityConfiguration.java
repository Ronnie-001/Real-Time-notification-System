package com.example.kent_notifier.app.Security;

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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.kent_notifier.app.Security.JWT.AuthTokenFilter;
import com.example.kent_notifier.app.User.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    
    @Autowired
    CustomUserDetailsService customUserDetailsService;

    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean 
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(customUserDetailsService);        
        provider.setPasswordEncoder(bCryptPasswordEncoder());
        return provider;
    }

    @Bean 
    public AuthTokenFilter authTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    } 
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("api/v1/**").permitAll()
                .anyRequest().authenticated()
            );

        http.authenticationProvider(authenticationProvider());
        http.addFilterBefore(authTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
