/**
 * 
 */
package com.java.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.config.JwtOperations;
import com.java.model.JwtResponse;
import com.java.model.User;
import com.java.service.ApplicationUserDetailsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author Madushan Lahiru
 *
 */

@RestController
@RequestMapping("api/authenticate")
@Api(tags = "Authentication")
public class AuthenticateController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtOperations jwtOperations;
	
	@Autowired
	private ApplicationUserDetailsService applicationUserDetailsService;
	
	@ApiOperation(value = "Authenticate users.")
	@PostMapping(produces = "application/json")
	public ResponseEntity<JwtResponse> authenticateUser(@RequestBody User user) throws Exception {
		authenticate(user.getUsername(), user.getPassword());
		UserDetails userDetails = applicationUserDetailsService.loadUserByUsername(user.getUsername());
		String token = jwtOperations.getGeneratedToken(userDetails);
		Date expiration = jwtOperations.getExpirationDateFromToken(token);
		String subject = jwtOperations.getUsernameFromToken(token);

		return ResponseEntity.ok(new JwtResponse(token, subject, expiration));
	}
	
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
