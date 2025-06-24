package com.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RoleHeaderFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(RoleHeaderFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        String path = request.getRequestURI();
        String method = request.getMethod();
        logger.debug("Processing request: {} {}", method, path);

        // Skip authentication for public endpoints
        if (isPublicEndpoint(request)) {
            logger.debug("Skipping authentication for public endpoint: {} {}", method, path);
            filterChain.doFilter(request, response);
            return;
        }

        // Check for existing authentication
        Authentication existingAuth = SecurityContextHolder.getContext().getAuthentication();
        
        if (existingAuth != null && existingAuth.isAuthenticated()) {
            logger.debug("User already authenticated, proceeding with request");
            filterChain.doFilter(request, response);
            return;
        }

        // Process roles header if present
        String rolesHeader = request.getHeader("X-Roles");
        if (rolesHeader != null && !rolesHeader.isBlank()) {
            logger.debug("Processing roles header: {}", rolesHeader);
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            
            for (String role : rolesHeader.split(",")) {
                String trimmedRole = role.trim();
                if (trimmedRole.startsWith("ROLE_")) {
                    authorities.add(new SimpleGrantedAuthority(trimmedRole));
                    logger.debug("Added authority: {}", trimmedRole);
                }
            }
            
            if (!authorities.isEmpty()) {
                Authentication auth = new UsernamePasswordAuthenticationToken(
                    "api-user", 
                    null, 
                    authorities
                );
                SecurityContextHolder.getContext().setAuthentication(auth);
                logger.info("Authenticated request with roles: {}", authorities);
            } else {
                logger.warn("No valid roles found in X-Roles header: {}", rolesHeader);
            }
        } else {
            logger.debug("No X-Roles header provided for non-public endpoint: {} {}", method, path);
            response.setStatus(HttpServletResponse.SC_FORBIDDEN); // Explicitly set 403 for non-public endpoints
        }

        filterChain.doFilter(request, response);
    }

    private boolean isPublicEndpoint(HttpServletRequest request) {
        String path = request.getRequestURI();
        String method = request.getMethod();
        boolean isPublic = path.startsWith("/api/customers/login") ||
                          (path.startsWith("/api/customers") && method.equals("POST")) ||
                          path.startsWith("/api/customers/sendEmail") ||
                          path.startsWith("/api/customers/compareTOTP") ||
                          path.startsWith("/api/fees") ||
                          path.startsWith("/api/fee-schemas") ||
                          path.startsWith("/api/fee-rule-types") ||
                          path.startsWith("/api/operation-types") ||
                          path.startsWith("/api/wallet-status") ||
                          path.startsWith("/api/wallet-categories")||
                          path.startsWith("/api/wallet-types")||
                          path.startsWith("/api/countries")||
                          path.startsWith("/api/cities");
        logger.debug("isPublicEndpoint check: {} {} -> {}", method, path, isPublic);
        return isPublic;
    }
}