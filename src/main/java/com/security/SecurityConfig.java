package com.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, UserDetailsService userDetailsService)
            throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/api/customers").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/customers").permitAll()
                        .requestMatchers("/api/customers/login").permitAll()
                        .requestMatchers("/api/customers/**").permitAll()//.hasAnyRole("ADMIN", "CUSTOMER") // Simplified for all methods
                        .requestMatchers("/api/customer-status/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/customers/{id}").hasAnyRole("CUSTOMER", "ADMIN")
                        .requestMatchers("/api/customers/sendEmail").permitAll()
                        .requestMatchers("/api/customers/compareTOTP").permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/wallets").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/customer-identity-type").permitAll() 
                        .requestMatchers("/api/wallets/**").hasAnyRole("CUSTOMER", "ADMIN")
                        .requestMatchers("/api/fees/**").permitAll()
                        .requestMatchers("/api/fee-schemas/**").permitAll()
                        .requestMatchers("/api/fee-rule-types/**").permitAll()
                        .requestMatchers("/api/operation-types/**").permitAll()
                        .requestMatchers("/api/wallet-status/**").permitAll() // Simplified for all methods
                        .requestMatchers("/api/wallet-categories/**").permitAll() // Simplified for all methods
                        .requestMatchers("/api/wallet-types/**").permitAll() // Simplified for all methods
                        .requestMatchers("/api/countries/**").permitAll() // Simplified for all methods
                        .requestMatchers("/api/cities/**").permitAll() // Simplified for all methods
                        .requestMatchers("/api/periodicities/**").permitAll() // Simplified for all methods
                        .requestMatchers("/api/fee-rule/**").permitAll() // Simplified for all methods
                        .requestMatchers("/api/vat-rates/**").permitAll() // Simplified for all methods
                        .requestMatchers("/api/doc-type").permitAll() // Simplified for all methods
                        .anyRequest().authenticated()
                )
                .addFilterBefore(new RoleHeaderFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:4200"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);
        config.setExposedHeaders(List.of("X-Roles"));
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}