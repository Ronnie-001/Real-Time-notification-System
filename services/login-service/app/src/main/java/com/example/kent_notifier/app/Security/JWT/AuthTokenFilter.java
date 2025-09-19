package com.example.kent_notifier.app.Security.JWT;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.util.StringUtils;

import com.example.kent_notifier.app.User.CustomUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthTokenFilter extends OncePerRequestFilter {
    
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    
    private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);
    
    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = parseJwt(req);
            
            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
                String email = jwtUtils.getEmailFromJwt(jwt);
                
                UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);

                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));    

                SecurityContextHolder.getContext().setAuthentication(auth);
                
                // testing 
                Authentication authe = SecurityContextHolder.getContext().getAuthentication();
                System.out.println("DEBUG -----> " + authe.getAuthorities());

            }

        } catch (Exception e) {
            logger.error("Failed to set authentication: {} ", e);
        }


        filterChain.doFilter(req, res);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        RequestMatcher authMatcher = PathPatternRequestMatcher
                                     .withDefaults()
                                     .matcher(HttpMethod.POST, "/login-service/auth/v1/**");

        boolean isMatch = authMatcher.matches(request);
        return isMatch; 
    }
    
    private String parseJwt(HttpServletRequest req) {
        String headerAuth = req.getHeader("Authorization");
        
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7, headerAuth.length());
        }

       return null; 
    } 
}
