/**
 * 
 */
package com.java.model;

import java.util.Date;

/**
 * @author Madushan Lahiru
 *
 */
public class JwtResponse {
	
	private String token;
	private String subject;
	private Date expriation;

	/**
	 * @param token
	 */
	public JwtResponse(String token) {
		super();
		this.token = token;
	}

	/**
	 * @param token
	 * @param subject
	 * @param expriation
	 */
	public JwtResponse(String token, String subject, Date expriation) {
		super();
		this.token = token;
		this.subject = subject;
		this.expriation = expriation;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the expriation
	 */
	public Date getExpriation() {
		return expriation;
	}

	/**
	 * @param expriation the expriation to set
	 */
	public void setExpriation(Date expriation) {
		this.expriation = expriation;
	}

}
