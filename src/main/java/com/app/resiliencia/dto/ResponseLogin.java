package com.app.resiliencia.dto;

import com.app.resiliencia.model.User;

public class ResponseLogin {

	private Boolean allowed;
	private User user;
	
 
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Boolean getAllowed() {
		return allowed;
	}

	public void setAllowed(Boolean allowed) {
		this.allowed = allowed;
	}
	
	
}
