package com.security;

import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.servicesImp.AuthServiceImp;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http/* , UserDetailsService userDetailsService */)
            throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(false))
                .addFilterBefore((request, response, chain) -> {
                    HttpSession session = ((HttpServletRequest) request).getSession(false);
                    if (session != null) {
                        SecurityContext context = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
                        if (context != null) 
                            SecurityContextHolder.setContext(context);
                        else 
                            System.out.println("No security context in session");
                    } else 
                        System.out.println("No session exists for this request");
                    chain.doFilter(request, response);
                }, UsernamePasswordAuthenticationFilter.class)
                .formLogin(form -> form.disable())
                .logout(logout -> logout.disable())

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/transfer/**").permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/customers").permitAll()
                        // MODULE_WALLETS
                        .requestMatchers("/api/accounts/**").hasAnyAuthority("ROLE_USER", "MODULE_WALLETS")
                        .requestMatchers("/api/account-lists/**").hasAnyAuthority("ROLE_USER", "MODULE_WALLETS")
                        .requestMatchers("/api/account-types/**").hasAnyAuthority("ROLE_USER", "MODULE_WALLETS")
                        .requestMatchers("/api/banks/**").hasAnyAuthority("ROLE_USER", "MODULE_WALLETS")
                        .requestMatchers("/api/cards/**").hasAnyAuthority("ROLE_USER", "MODULE_WALLETS")
                        .requestMatchers("/api/card-lists/**").hasAnyAuthority("ROLE_USER", "MODULE_WALLETS")
                        .requestMatchers("/api/card-types/**").hasAnyAuthority("ROLE_USER", "MODULE_WALLETS")
                        .requestMatchers("/api/wallets/**").hasAnyAuthority("ROLE_USER", "MODULE_WALLETS")
                        .requestMatchers("/api/wallet-categories/**").hasAnyAuthority("ROLE_USER", "MODULE_WALLETS")
                        .requestMatchers("/api/wallet-status/**").hasAnyAuthority("ROLE_USER", "MODULE_WALLETS")
                        .requestMatchers("/api/wallet-types/**").hasAnyAuthority("ROLE_USER", "MODULE_WALLETS")
                        // MODULE_CUSTOMERS
                        .requestMatchers("/api/cities/**").hasAnyAuthority("ROLE_USER", "MODULE_CUSTOMERS")
                        .requestMatchers("/api/countries/**").hasAnyAuthority("ROLE_USER", "MODULE_CUSTOMERS")
                        .requestMatchers("/api/customer-doc/**").hasAnyAuthority("ROLE_USER", "MODULE_CUSTOMERS")
                        .requestMatchers("/api/customer-identity/**").hasAnyAuthority("ROLE_USER", "MODULE_CUSTOMERS")
                        .requestMatchers("/api/customer-identity-type/**")
                        .hasAnyAuthority("ROLE_USER", "MODULE_CUSTOMERS")
                        .requestMatchers("/api/customer-status/**").hasAnyAuthority("ROLE_USER", "MODULE_CUSTOMERS")
                        .requestMatchers("/api/customers/**").hasAnyRole("USER", "CUSTOMER")
                        .requestMatchers("/api/doc-type/**").hasAnyAuthority("ROLE_USER", "MODULE_CUSTOMERS")
                        // MODULE_ACCOUNTING
                        .requestMatchers("/api/fees/**").hasAnyAuthority("ROLE_USER", "MODULE_ACCOUNTING")
                        .requestMatchers("/api/fee-rule/**").hasAnyAuthority("ROLE_USER", "MODULE_ACCOUNTING")
                        .requestMatchers("/api/fee-rule-types/**").hasAnyAuthority("ROLE_USER", "MODULE_ACCOUNTING")
                        .requestMatchers("/api/fee-schemas/**").hasAnyAuthority("ROLE_USER", "MODULE_ACCOUNTING")
                        .requestMatchers("/api/operation-types/**").hasAnyAuthority("ROLE_USER", "MODULE_ACCOUNTING")
                        .requestMatchers("/api/periodicities/**").hasAnyAuthority("ROLE_USER", "MODULE_ACCOUNTING")
                        .requestMatchers("/api/vat-rates/**").hasAnyAuthority("ROLE_USER", "MODULE_ACCOUNTING")
                        .requestMatchers("/api/wallet-category-operation-type-map/**")
                        .hasAnyAuthority("ROLE_USER", "MODULE_ACCOUNTING")
                        .requestMatchers("/api/wallet-operation-type-map/**")
                        .hasAnyAuthority("ROLE_USER", "MODULE_ACCOUNTING")
                        // MODULE_PROFILING
                        .requestMatchers("/api/menu-options/**").hasAnyAuthority("ROLE_USER", "MODULE_PROFILING")
                        .requestMatchers("/api/modules/**").hasAnyAuthority("ROLE_USER", "MODULE_PROFILING")
                        .requestMatchers("/api/user-profile-menu-options/**")
                        .hasAnyAuthority("ROLE_USER", "MODULE_PROFILING")
                        .requestMatchers("/api/user-profiles/**").hasAnyAuthority("ROLE_USER", "MODULE_PROFILING")
                        .requestMatchers("/api/users/**").hasAnyAuthority("ROLE_USER", "MODULE_PROFILING")

                        .anyRequest().authenticated());

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }

    /*
     * @Bean
     * AuthenticationManager authenticationManager(AuthenticationConfiguration
     * authConfig) throws Exception {
     * return authConfig.getAuthenticationManager();
     * }
     */

    @Bean
    public UserDetailsService userDetailsService() {
        return new AuthServiceImp();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:4200",
                "https://localhost:4200",
                "http://127.0.0.1:4200",
                "https://127.0.0.1:4200",
                "http://gateway.uib.tn:4200",
                "https://gateway.uib.tn:4200"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);
        config.setExposedHeaders(List.of("X-Roles"));
        // config.setSameSite(CorsConfiguration.SameSite.LAX);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}