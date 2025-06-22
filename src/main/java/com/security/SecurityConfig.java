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
                // Désactivation CSRF pour API REST stateless
                .csrf(csrf -> csrf.disable())

                // Configuration CORS
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))

                // Politique de session sans état (stateless)
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Gestion des autorisations
                .authorizeHttpRequests(auth -> auth
                        //.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() 
                        .requestMatchers(HttpMethod.POST, "/api/customers").permitAll()
                        .requestMatchers("/api/customers/sendEmail").permitAll()
                        .requestMatchers("/api/customers/compareTOTP").permitAll()
                        .requestMatchers("/api/customers/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/customers/{id}").hasAnyRole("CUSTOMER", "ADMIN")
                        .requestMatchers("/api/wallets/**").hasAnyRole("CUSTOMER", "ADMIN")
                        .anyRequest().authenticated())
                .exceptionHandling(handling -> handling
                        .accessDeniedHandler((request, response, accessDeniedException) -> {
                            System.out.println("Access denied for request: " + request.getRequestURI());
                            accessDeniedException.printStackTrace();
                            response.sendError(403, "Access Denied");
                        }));

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
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
