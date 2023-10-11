package com.masai.security;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class JwtTokenGeneratorFilter extends OncePerRequestFilter {


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (null != authentication) {
            SecretKey key = Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes());
            
            String jwt = Jwts.builder()
            		.setIssuer("Akash")
            		.setSubject("JWT Token")
                    .claim("username", authentication.getName())
                    .claim("authorities", populateAuthorities(authentication.getAuthorities()))
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(new Date().getTime()+ 30000000)) // millisecond expiration time of around 8 hours
                    .signWith(key).compact();
                       
            response.setHeader(SecurityConstants.JWT_HEADER, jwt);
            
            Cookie jwtCookie = new Cookie(SecurityConstants.JWT_HEADER, jwt); // Replace jwt with your actual JWT token
            jwtCookie.setMaxAge((int) (System.currentTimeMillis() + 30000000)); // Set cookie expiration time
            jwtCookie.setPath("/");
            jwtCookie.setHttpOnly(true);
            response.addCookie(jwtCookie);
 
        }

        filterChain.doFilter(request, response);	
	}
	
    private String populateAuthorities(Collection<? extends GrantedAuthority> collection) {
        
    	Set<String> authoritiesSet = new HashSet<>();
        
        for (GrantedAuthority authority : collection) {
            authoritiesSet.add(authority.getAuthority());
        }
        return String.join(",", authoritiesSet);
   
    
    }
	
	
//	public String getValue(Collection<? extends GrantedAuthority> collection) {
//		Set<String> set = new HashSet<>() ;
//		for(GrantedAuthority autho : collection) {
//			set.add(autho.getAuthority()) ;
//		}
//		
//		return String.join(",", set) ;
//	}
	
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {
	    String servletPath = request.getServletPath();
	    return !(
	        servletPath.equals("/customers/signin") ||
	        servletPath.equals("/admin/signin") 
	    );
	}
	
	
}
