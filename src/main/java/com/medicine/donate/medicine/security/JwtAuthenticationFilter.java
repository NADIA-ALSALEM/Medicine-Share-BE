package com.medicine.donate.medicine.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {


    private final static String jwtSecret = "coRHh978jj81/GgmeQEKsCkdEjngS6X/HXz2vsXJ9vqWbchqRvrAWhU4CtzoPW5BAdl3OO/BZ1xgXbvyG/8a4g==";

   @Qualifier("customUserDetailsService")
   private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // Extract the JWT token from the Authorization header
        String authorizationHeader = request.getHeader("Authorization");
        String jwtToken = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwtToken = authorizationHeader.substring(7); // Remove "Bearer " prefix
        }

        if (jwtToken != null && validateToken(jwtToken)) {
            String username = extractUsername(jwtToken);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            // Set the authentication in the context
            var authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response); // Proceed to the next filter
    }

    private boolean validateToken(String token) {
        // Implement your token validation logic here (e.g., check expiration, signature)
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true; // Valid token
        } catch (Exception e) {
            return false; // Invalid token
        }
    }

    private String extractUsername(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}