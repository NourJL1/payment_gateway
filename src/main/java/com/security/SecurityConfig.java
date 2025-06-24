package com.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
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
<<<<<<< HEAD
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/api/customers").permitAll()
                        .requestMatchers("/api/customers/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/customers/{id}").hasAnyRole("CUSTOMER", "ADMIN")
                        .requestMatchers("/api/customers/sendEmail").permitAll()
                        .requestMatchers("/api/customers/compareTOTP").permitAll()
=======

                // Gestion des autorisations
                /* .authorizeHttpRequests(auth -> auth
                        // Autoriser inscription (POST /api/customers) et login (GET/POST
                        // /api/customers/login)
                        .requestMatchers(HttpMethod.POST, "/api/customers").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/customers").permitAll()
                        .requestMatchers("/api/customers/login").permitAll()
                        .requestMatchers("/api/customers/resetPassword/**").permitAll()
                        .requestMatchers("/api/customers/email/**").permitAll()
                        .requestMatchers("/api/countries").permitAll()
                        .requestMatchers("/api/cities/getByCountry/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/wallet-types").permitAll()

                        // Autoriser GET /api/customers/** aux rôles CUSTOMER ou ADMIN
                        .requestMatchers(HttpMethod.GET, "/api/customers/{id}").hasAnyRole("CUSTOMER", "ADMIN")
                        .requestMatchers("/api/customers/sendEmail").permitAll()
                        .requestMatchers("/api/customers/compareTOTP").permitAll()

                        // Autoriser tout sur /api/wallets/** aux rôles CUSTOMER ou ADMIN
>>>>>>> 341ce17a0255abc3aa938d36ca625623e01e6045
                        .requestMatchers("/api/wallets/**").hasAnyRole("CUSTOMER", "ADMIN")
                        .requestMatchers("/api/fees/**").permitAll()
                        .requestMatchers("/api/fee-schemas/**").permitAll()
                        .requestMatchers("/api/fee-rule-types/**").permitAll()
<<<<<<< HEAD
                        .requestMatchers("/api/operation-types/**").permitAll()
                        .requestMatchers("/api/wallet-status/**").permitAll() // Simplified for all methods
                        .requestMatchers("/api/wallet-categories/**").permitAll() // Simplified for all methods
                        .anyRequest().authenticated()
                )
                .addFilterBefore(new RoleHeaderFilter(), UsernamePasswordAuthenticationFilter.class);
=======

                        // Toutes les autres requêtes nécessitent une authentification
                        .anyRequest().authenticated()) */
                .httpBasic(Customizer.withDefaults());
        ;

        // Ajout du filtre personnalisé (si tu l’as créé)
        // .addFilterBefore(new RoleHeaderFilter(),
        // UsernamePasswordAuthenticationFilter.class);
>>>>>>> 341ce17a0255abc3aa938d36ca625623e01e6045

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
<<<<<<< HEAD
        config.setExposedHeaders(List.of("X-Roles"));
        
=======

>>>>>>> 341ce17a0255abc3aa938d36ca625623e01e6045
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}