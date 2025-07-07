package org.doit.ik.di6;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lombok.Getter;

@Getter
@Repository
public class UserRepository {
	
	@Autowired
	private Map<String, User> userMap = new HashMap<>();
	
	public User findUserById(String id) {
		return userMap.get(id);
	}
	
	public void setUsers(List<User> users) {
		for (User u : users) {
			userMap.put(u.getId(), u);
		}
	}
}
