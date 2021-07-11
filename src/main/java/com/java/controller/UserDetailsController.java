/**
 * 
 */
package com.java.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.config.JwtOperations;
import com.java.model.AppUserDetails;
import com.java.service.ApplicationUserDetailsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author Madushan Lahiru
 *
 */

@RestController
@RequestMapping("api/user")
@Api(tags = "Autherization")
public class UserDetailsController {
	
	@Autowired
	private ApplicationUserDetailsService applicationUserDetailsService;
	
	@Autowired
	private JwtOperations jwtOperations;

	@GetMapping(produces = "application/json")
	@ApiOperation(value = "Get user details.")
	public AppUserDetails getUserDetails(HttpServletRequest request) {
		
		String requestTokenHeader = request.getHeader("Authorization");
		String jwToken = requestTokenHeader.substring(7);
		String username = jwtOperations.getUsernameFromToken(jwToken);
		
		return applicationUserDetailsService.getUserDetailsByUsername(username);
	}
	
}
