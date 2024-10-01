package com.codewithblaise.movieflix.auth.service;

import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Service

public class AuthFilterService  extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public AuthFilterService(JwtService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(@Nonnull  HttpServletRequest request, @Nonnull HttpServletResponse response, @Nonnull  FilterChain filterChain) throws ServletException, IOException {
final String authHeader=request.getHeader("Authorization");
String username,jwt;
if(authHeader==null || !authHeader.startsWith("Bearer ")){
    filterChain.doFilter(request,response
    );
    return;
}
jwt= authHeader.substring(7);
username=jwtService.extractUsername(jwt);
if(username !=null && SecurityContextHolder.getContext().getAuthentication()==null){
    UserDetails userDetails=userDetailsService.loadUserByUsername(username);
// getting the user details
    if(jwtService.isTokenValid(jwt,userDetails)){
        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
        authenticationToken.setDetails(userDetails);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
}
filterChain.doFilter(request,response);


    }
}
