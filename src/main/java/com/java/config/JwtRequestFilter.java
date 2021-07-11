/**
 * 
 */
package com.java.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.java.service.ApplicationUserDetailsService;

import io.jsonwebtoken.ExpiredJwtException;

/**
 * @author Madushan Lahiru
 *
 */

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	
	@Autowired
	private ApplicationUserDetailsService applicationUserDetailsService;
	
	@Autowired
	private JwtOperations jwtOperations;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String requestTokenHeader = request.getHeader("Authorization");
		
		String username = null;
		String jwToken = null;
		
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwToken = requestTokenHeader.substring(7);
			try {
				username = jwtOperations.getUsernameFromToken(jwToken);
			} catch (IllegalArgumentException e) {
				//System.out.println("Unable to get JWT Token");
				logger.warn("Unable to get JWT Token");
			} catch (ExpiredJwtException e) {
				//System.out.println("JWT Token has expired");
				logger.warn("JWT Token has expired");
			}
		} else {
			logger.warn("JWT Token does not begin with Bearer String");
		}
		
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = this.applicationUserDetailsService.loadUserByUsername(username);

			if (jwtOperations.validateToken(jwToken, userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		
		filterChain.doFilter(request, response);
	}

}
