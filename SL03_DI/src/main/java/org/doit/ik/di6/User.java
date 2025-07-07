package org.doit.ik.di6;

import org.springframework.stereotype.Component;

import lombok.Getter;

@Getter
//@Component --> 여러명의 사용자
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
