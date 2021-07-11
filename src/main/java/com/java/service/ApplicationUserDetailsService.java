/**
 * 
 */
package com.java.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.java.dao.UserDao;
import com.java.model.User;
import com.java.model.AppUserDetails;

/**
 * @author Madushan Lahiru
 *
 */

@Service
public class ApplicationUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = getUserByUsername(username);
		if (user != null) {
			return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}
	
	public void initializeUsers() {
		userDao.initializeUsers();
	}
	
	public void insertUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userDao.insertUser(user);
	}
	
	public List<User> getUsers(){
		return userDao.getAllUsers();
	}
	
	public User getUserByUsername(String username) {
		return userDao.getUserByUsername(username).get();
	}
	
	public AppUserDetails getUserDetailsByUsername(String username) {
		return userDao.getUserDetailsByUsername(username).get();
	}


}
