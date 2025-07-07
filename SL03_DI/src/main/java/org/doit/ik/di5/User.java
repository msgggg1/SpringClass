package org.doit.ik.di5;

import lombok.Getter;

@Getter
public class User {
	String id;
	String password;
	
	public User(String id, String password) {
		this.id = id;
		this.password = password;
	}
	
	public boolean matchPassword(String inputPasswd) {
		return password.equals(inputPasswd);
	}
	
	public void changePassword(String oldPassword, String newPassword) {
	      if (!matchPassword(oldPassword))
	          throw new IllegalArgumentException("illegal password");
	       password = newPassword;
	    }
	
}
