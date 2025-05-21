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
        
        // Skip authentication for public endpoints
        if (isPublicEndpoint(request)) {
            logger.debug("Skipping authentication for public endpoint: {}", request.getRequestURI());
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
            }
        }

        filterChain.doFilter(request, response);
    }

    private boolean isPublicEndpoint(HttpServletRequest request) {
        String path = request.getRequestURI();
        return path.startsWith("/login") || 
               path.startsWith("/register") ||
               path.startsWith("/users/login") ||
               path.startsWith("/users/register") ||
               path.startsWith("/api/wallet") ||
               path.startsWith("/api/auth") ||
               path.startsWith("/users/"); // Added /users/ to match permitAll()
    }
}
