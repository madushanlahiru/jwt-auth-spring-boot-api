/**
 * 
 */
package com.java.model;

/**
 * @author Madushan Lahiru
 *
 */
public class AppUserDetails {
	
	private String username;
	private String name;
	private String userRole;
	
	/**
	 * @param username
	 * @param name
	 * @param userRole
	 */
	public AppUserDetails(String username, String name, String userRole) {
		super();
		this.username = username;
		this.name = name;
		this.userRole = userRole;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the userRole
	 */
	public String getUserRole() {
		return userRole;
	}

	/**
	 * @param userRole the userRole to set
	 */
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

}
