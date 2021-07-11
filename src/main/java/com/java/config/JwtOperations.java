/**
 * 
 */
package com.java.config;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author Madushan Lahiru
 *
 */

@Component
public class JwtOperations {
	
	private long JWT_VALIDITY = 60 * 60 * 1000; // 60 minutes
	
	@Value("$jwt.secret")
	private String secret;
	
	
	public String getGeneratedToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return generateToken(claims, userDetails.getUsername());
	}
	
	/**
	 * This method use to generate a JSON web token for particular subject with HS256 Signature algorithm.
	 * 
	 * @param claims Map<String, Object> - claims to be set as the JWT body
	 * @param subject String - username
	 * @return String - JSON web token
	 */
	private String generateToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_VALIDITY))
				.signWith(SignatureAlgorithm.HS256, secret).compact();
	}
	
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}
	
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}
	
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
	
	private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}
	
	/**
	 * This method will extract all the claims in provided JSON web token.
	 * 
	 * @param token String - JSON web token
	 * @return Claims object
	 */
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}
	
	/**
	 * This method check the provided JSON web token expired or not.
	 * 
	 * @param token - String JSON web token
	 * @return Boolean
	 */
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

}
