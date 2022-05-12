package com.jwt;

import com.repository.IUserRepository;
import com.service.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    UserDetailsServiceImpl userDetailsService;
    @Autowired
    IUserRepository iUserRepository;

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwtToken = getJwtFromRequest(request);
        try{
            if (jwtToken != null && jwtTokenProvider.validateJwtToken(jwtToken)){

                String username = jwtTokenProvider.getUserNameFromJWT(jwtToken);
//                String Name_User = jwtTokenProvider.getUserNameFromJWT(jwtToken);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails,null,userDetails.getAuthorities()
                );
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            }
        }catch (Exception e){
            logger.error("Cannot set user authentication: {}", e);
        }

        filterChain.doFilter(request,response);
    }

    public String getJwtFromRequest(HttpServletRequest request){

        String token = request.getHeader("Authorization");
        if (StringUtils.hasText(token) && token.startsWith("Bearer ")){
            return token.substring(7);
        }
        return null;
    }
}