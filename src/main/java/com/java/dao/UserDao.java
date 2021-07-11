/**
 * 
 */
package com.java.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.java.model.User;
import com.java.model.AppUserDetails;


/**
 * This is the repository of users. this repository holds all the data of users.
 * 
 * @author Madushan Lahiru
 * @since 10/07/2021
 *
 */

@Repository
public class UserDao {

	private ArrayList<User> users = new ArrayList<>();
	private ArrayList<AppUserDetails> appUserDetails = new ArrayList<>();
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	/**
	 * Initialize users to login to the API.
	 */
	public void initializeUsers() {
		// Admin user create
		insertUser(new User("admin", passwordEncoder.encode("password")));
		appUserDetails.add(new AppUserDetails("admin", "Admin", "ROLE_ADMIN"));
		
		// User user create
		insertUser(new User("user", passwordEncoder.encode("user@123")));
		appUserDetails.add(new AppUserDetails("user", "User", "ROLE_USER"));
	}
	
	
	public void insertUser(User user) {
		users.add(user);
	}
	
	public void insertUserDetails(AppUserDetails appUserDetails) {
		this.appUserDetails.add(appUserDetails);
	}
	
	public List<User> getAllUsers(){
		return users;
	}
	
	/**
	 * Get relevant user by parsing username.
	 * 
	 * @param username String
	 * @return Optional<User>
	 */
	public Optional<User> getUserByUsername(String username){
		return users.stream()
				.filter(user -> user.getUsername().equals(username))
				.findFirst();
	}
	
	/**
	 * Get relevant user details by parsing username.
	 * 
	 * @param username String
	 * @return Optional<UserDetails>
	 */
	public Optional<AppUserDetails> getUserDetailsByUsername(String username){
		return appUserDetails.stream()
				.filter(user -> user.getUsername().equals(username))
				.findFirst();
	}
	
}
