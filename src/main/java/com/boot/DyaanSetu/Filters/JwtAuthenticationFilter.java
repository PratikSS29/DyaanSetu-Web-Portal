package com.boot.DyaanSetu.Filters;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.boot.DyaanSetu.service.impl.CombinedUserDetailsService;
import com.boot.DyaanSetu.service.impl.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private CombinedUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getServletPath();

        // Allow open paths
        if (path.equals("/api/students/info") ||
            path.equals("/api/students/login") ||
            path.startsWith("/api/students/set-password") ||
            path.equals("/api/alumni/info") ||
            path.equals("/api/alumni/login") ||
            path.startsWith("/api/alumni/set-password")||
            path.startsWith("/api/admin/info")||
            path.startsWith("/api/admin/set-password")||
            path.startsWith("/api/admin/login")
            ) {

            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            username = jwtService.extractUserName(token);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // 🔐 Validate user and token
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (jwtService.validateToken(token, userDetails)) {
                //  Check role claim
                String role = jwtService.extractRole(token); // must implement this in JwtService

                System.out.println("Extracted role : "+role);
                System.out.println("path : "+path);
                
                // ❌ If role doesn't match URL path, block it
                if ((path.startsWith("/api/students") && !"STUDENT".equalsIgnoreCase(role)) ||
                    (path.startsWith("/api/alumni") && !"ALUMNI".equalsIgnoreCase(role))||
                    (path.startsWith("/api/admin") && !"ADMIN".equalsIgnoreCase(role))) {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied for role: " + role);
                    return;
                }

                //  Token and role are valid
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
