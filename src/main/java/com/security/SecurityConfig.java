package com.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
<<<<<<< HEAD
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
=======
>>>>>>> 8d7b492c1726db82471bfb1dc6e77da1e903039d
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
<<<<<<< HEAD
=======
import org.springframework.security.crypto.password.PasswordEncoder;
>>>>>>> 8d7b492c1726db82471bfb1dc6e77da1e903039d
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
<<<<<<< HEAD
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.security.RoleHeaderFilter;

@Configuration
=======
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
>>>>>>> 8d7b492c1726db82471bfb1dc6e77da1e903039d
public class SecurityConfig {

    private final UserDetailsService customUserDetailsService;

    public SecurityConfig(UserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
<<<<<<< HEAD
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
=======
>>>>>>> 8d7b492c1726db82471bfb1dc6e77da1e903039d
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
<<<<<<< HEAD
            .csrf().disable()
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/login",
                    "/register",
                    "/users/login",
                    "/users/register",
                    "/api/wallet/**",
                    "/api/auth/**",
                    "/users",
                    "/users/"
                ).permitAll()
                .anyRequest().authenticated()
            )
            .addFilterBefore(new RoleHeaderFilter(), UsernamePasswordAuthenticationFilter.class);
=======
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                                .requestMatchers(
                                        "/api/customers/login",
                                        "/api/customers/register",
                                        "/api/wallet/**",
                                        "/api/auth/**",
                                        "/users",
                                        "/users/"
                                ).permitAll()
                                .anyRequest().authenticated()
                )
                .addFilterBefore(new RoleHeaderFilter(), UsernamePasswordAuthenticationFilter.class);
                //.httpBasic(withDefaults());
>>>>>>> 8d7b492c1726db82471bfb1dc6e77da1e903039d

        return http.build();
    }

    @Bean
<<<<<<< HEAD
=======
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
>>>>>>> 8d7b492c1726db82471bfb1dc6e77da1e903039d
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:4200")); // Adjust if frontend is on a different port
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}