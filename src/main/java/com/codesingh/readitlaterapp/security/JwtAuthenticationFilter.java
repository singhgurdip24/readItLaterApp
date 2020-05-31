package com.codesingh.readitlaterapp.security;

import com.codesingh.readitlaterapp.service.CustomerUserDetailService;
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
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  @Autowired
  private CustomerUserDetailService customerUserDetailService;

  @Override
  protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                  HttpServletResponse httpServletResponse,
                                  FilterChain filterChain)
    throws ServletException, IOException {

    try{
      String headerString = httpServletRequest.getHeader("Authorization");

      if(StringUtils.hasText(headerString) && headerString.startsWith("Bearer ")){
        String jwt = headerString.substring(7);

        if(jwtTokenProvider.validateToken(jwt)
            && SecurityContextHolder.getContext().getAuthentication() == null){
          Long userId = jwtTokenProvider.getUserIdFromToken(jwt);

          UserDetails userDetails = customerUserDetailService.loadByUserById(userId);

          UsernamePasswordAuthenticationToken authentication =
            new UsernamePasswordAuthenticationToken( userDetails ,
              "" ,
              userDetails.getAuthorities());

          authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
          SecurityContextHolder.getContext().setAuthentication(authentication);
        }
      }
    } catch(Exception ex) {
        logger.error("Could not set user authentication in security context", ex);
    }

    filterChain.doFilter(httpServletRequest, httpServletResponse);
  }
}
